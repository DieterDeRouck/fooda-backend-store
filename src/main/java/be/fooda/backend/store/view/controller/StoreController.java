package be.fooda.backend.store.view.controller;

import be.fooda.backend.store.dao.StoreIndexer;
import be.fooda.backend.store.dao.StoreRepository;
import be.fooda.backend.store.model.dto.CreateStoreRequest;
import be.fooda.backend.store.model.dto.ExistsByUniqueFieldsRequest;
import be.fooda.backend.store.model.dto.StoreResponse;
import be.fooda.backend.store.model.dto.UpdateStoreRequest;
import be.fooda.backend.store.model.http.HttpEndpoints;
import be.fooda.backend.store.model.http.HttpFailureMessages;
import be.fooda.backend.store.model.http.HttpSuccessMessages;
import be.fooda.backend.store.service.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stores") // https://www.fooda.be/api/v1/stores
public class StoreController {

    private static final int PAGE_SIZE_PER_RESULT = 25;
    private static final int DEFAULT_PAGE_NO = 0;

    private static final String PAGE_NUMBER = "pageNo";
    private static final Integer PAGE_NUMBER_DEFAULT_VALUE = 1;
    private static final String PAGE_SIZE = "pageSize";
    private static final Integer PAGE_SIZE_DEFAULT_VALUE = 50;

    private static final String GET_ALL = "get/all";
    private static final String GET_SEARCH = "search";
    private static final String GET_FILTER = "filter";
    private static final String POST_SINGLE = "add/one";
    private static final String POST_ALL = "add/all";
    private static final String GET_BY_ID = "get/one";
    private static final String GET_EXISTS_BY_ID = "exists/one";
    private static final String PUT_SINGLE = "edit/one";
    private static final String PUT_ALL = "edit/all";
    private static final String DELETE_BY_ID = "delete/one";
    private static final String DELETE_BY_ID_PERMANENTLY = "delete/one/permanent";

    // INJECT_FLOW_BEAN
    StoreRepository storeRepository;
    StoreMapper storeMapper;
    StoreIndexer storeIndexer;

    // CREATE_NEW_STORE
    @Transactional
    @PostMapping(HttpEndpoints.STORE_POST_SINGLE_TEXT)
    public ResponseEntity<String> create(@RequestBody @Valid @NotNull CreateStoreRequest request) {

        // FLOW_AND_RETURN
        return storeRepository
                .findByTitleAndAddressId(request.getTitle(), request.getAddressId())
                .map(entity -> storeRepository.save(entity))
                .map(entity -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .header("saved_id", String.valueOf(entity.getId()))
                        .body(HttpSuccessMessages.STORE_UPDATED.getDescription()))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, HttpFailureMessages.STORE_ALREADY_EXIST.getDescription());
                });
    }

    // UPDATE_SINGLE_STORE
    @Transactional
    @PutMapping(HttpEndpoints.STORE_PUT_SINGLE_TEXT)
    public ResponseEntity<String> updateById(@PathVariable("storeId") Long storeId,   @RequestBody @Valid @NotNull UpdateStoreRequest request) {

        // FLOW_AND_RETURN
        return storeRepository
                .findById(storeId)
                .map(entity -> storeMapper.toEntity(request, entity))
                .map(entity -> storeRepository.save(entity))
                .map(entity -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .header("updated_id", String.valueOf(entity.getId()))
                        .body(HttpSuccessMessages.STORE_UPDATED.getDescription()))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMessages.STORE_NOT_FOUND.getDescription());
                });
    }

    // DELETE_BY_ID
    @Transactional
    @DeleteMapping(HttpEndpoints.STORE_DELETE_BY_ID)
    public ResponseEntity<String> deleteById(@PathVariable("storeId") @NotNull Long storeId) {

        return storeRepository
                .findById(storeId)
                .map(entity -> storeRepository.makePassive(entity.getId()))
                .map(deleteCount -> deleteCount > 0
                        ? ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(HttpSuccessMessages.STORE_MADE_PASSIVE.getDescription())
                        : ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(HttpFailureMessages.FAILED_TO_MAKE_STORE_PASSIVE.getDescription())
                )
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMessages.FAILED_TO_MAKE_STORE_PASSIVE.getDescription());
                });
    }

    // DELETE_BY_ID_PERMANENTLY
    @Transactional
    @DeleteMapping(HttpEndpoints.STORE_DELETE_BY_ID_PERMANENTLY)
    public ResponseEntity<String> deleteByIdPermanently(@PathVariable("storeId") @NotNull Long storeId) {

        return storeRepository
                .findById(storeId)
                .map(entity -> {
                    storeRepository.deleteById(storeId);
                    return ResponseEntity
                            .status(HttpStatus.ACCEPTED)
                            .body(HttpSuccessMessages.STORE_DELETED.getDescription());
                })
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMessages.STORE_NOT_FOUND.getDescription());
                });
    }

    // GET_ALL
    @Transactional
    @GetMapping(HttpEndpoints.STORE_FIND_ALL_TEXT)
    public ResponseEntity<List<StoreResponse>> findAll(
            @RequestParam(value = HttpEndpoints.PAGE_NUMBER_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_NUMBER_DEFAULT_VALUE) Integer pageNo,
            @RequestParam(value = HttpEndpoints.PAGE_SIZE_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_SIZE_DEFAULT_VALUE) Integer pageSize) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        storeRepository
                                .findAllByIsActive(true, PageRequest.of(pageNo - 1, pageSize))
                                .stream()
                                .map(entity -> storeMapper.toResponse(entity))
                                .collect(Collectors.toUnmodifiableList())
                );
    }

    // GET_BY_ID
    @Transactional
    @GetMapping(HttpEndpoints.STORE_FIND_BY_ID_TEXT)
    public ResponseEntity<StoreResponse> findById(@PathVariable("storeId") @NotNull Long storeId) {

        return storeRepository
                .findById(storeId)
                .map(entity -> storeMapper.toResponse(entity))
                .map(response -> ResponseEntity.status(HttpStatus.FOUND).body(response))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMessages.STORE_NOT_FOUND.getDescription());
                });
    }


    // SEARCH(KEYWORDS)
    @Transactional
    @GetMapping(HttpEndpoints.STORE_SEARCH_TEXT)
    public ResponseEntity<List<StoreResponse>> search(
            @PathVariable("keyword") @NotNull String keyword,
            @RequestParam(
                    value = HttpEndpoints.PAGE_NUMBER_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_NUMBER_DEFAULT_VALUE) Integer pageNo,
            @RequestParam(
                    value = HttpEndpoints.PAGE_SIZE_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_SIZE_DEFAULT_VALUE) Integer pageSize) {

        // FLOW_AND_RETURN
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        storeIndexer
                                .search(PageRequest.of(pageNo - 1, pageSize), keyword)
                                .stream()
                                .map(entity -> storeMapper.toResponse(entity))
                                .collect(Collectors.toUnmodifiableList())
                );
    }

    // EXISTS_BY_ID
    @Transactional
    @GetMapping(HttpEndpoints.STORE_FIND_EXISTS_BY_ID_TEXT)
    public ResponseEntity<String> existsById(@PathVariable("storeId") @NotNull Long storeId) {

        return storeRepository.existsById(storeId)
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.STORE_EXISTS.getDescription())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.STORE_DOES_NOT_EXIST.getDescription());
    }

    // EXISTS_BY_UNIQUE_FIELDS
    @Transactional
    @GetMapping(HttpEndpoints.STORE_EXISTS_BY_UNIQUE_FIELDS)
    public ResponseEntity<String> existsByUniqueFields(@RequestBody ExistsByUniqueFieldsRequest request) {

        return storeRepository.existsByTitleAndContactId(request.getTitle(), request.getContactId())
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.STORE_EXISTS.getDescription())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.STORE_DOES_NOT_EXIST.getDescription());
    }

}
