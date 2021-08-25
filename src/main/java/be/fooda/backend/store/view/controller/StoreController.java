package be.fooda.backend.store.view.controller;

import be.fooda.backend.store.model.dto.CreateStoreRequest;
import be.fooda.backend.store.model.dto.StoreResponse;
import be.fooda.backend.store.model.dto.UpdateStoreRequest;
import be.fooda.backend.store.model.http.HttpFailureMessages;
import be.fooda.backend.store.model.http.HttpSuccessMessages;
import be.fooda.backend.store.service.exception.ResourceNotFoundException;
import be.fooda.backend.store.service.flow.StoreFlow;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private final StoreFlow storeFlow;

    // RESPONSE_ENTITY = STATUS, HEADERS, BODY

    @PostMapping(POST_SINGLE) // CREATING NEW STORE
    public ResponseEntity<String> createStore(@RequestBody @Valid CreateStoreRequest request) {

        // CREATE_FLOW
        storeFlow.createStore(request);

        // RETURN_SUCCESS
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(HttpSuccessMessages.STORE_CREATED.getDescription());
    }

    // @PutMapping // UPDATE STORE(S)
    @PutMapping(PUT_SINGLE)
    public ResponseEntity<String> updateStore(@RequestParam("storeId") Long id, @RequestBody @Valid UpdateStoreRequest request) {

        // UPDATE_FLOW
        storeFlow.updateStore(id, request);

        // RETURN_SUCCESS
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(HttpSuccessMessages.STORE_UPDATED.getDescription());
    }

    // DELETE_BY_ID
    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<String> deleteById(@RequestParam("storeId") Long id) {



        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMessages.STORE_DELETED.getDescription());
    }

    // DELETE_BY_ID_PERMANENTLY
    @DeleteMapping(DELETE_BY_ID_PERMANENTLY)
    public ResponseEntity<String> deleteByIdPermanently(@RequestParam("storeId") Long id) {


        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMessages.STORE_DELETED.getDescription());
    }

    // @PatchMapping // UPDATE STORE(S) BUT NOT ALL THE FIELDS


    // GET_ALL
    @GetMapping(GET_ALL)
    public ResponseEntity<List<StoreResponse>> findAllStores(
            @RequestParam(value = PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = PAGE_SIZE, required = false) Integer pageSize) {

        // SET DEFAULT VALUES ..
        pageNo = PAGE_NUMBER_DEFAULT_VALUE;
        pageSize = PAGE_SIZE_DEFAULT_VALUE;

        // START_SELECT_FLOW
        final List<StoreResponse> responses = storeFlow.findAll(pageNo, pageSize);

        // RETURN_ALL_STORES_IN_PAGES
        return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }

    // GET_BY_ID
    @GetMapping(GET_BY_ID)
    public ResponseEntity<StoreResponse> findStoreById(@RequestParam("storeId") Long id) {

        // START_SELECT_FLOW
        final StoreResponse response = storeFlow.findByID(id);

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


    // SEARCH(KEYWORDS)
    @GetMapping(GET_SEARCH)
    public ResponseEntity<List<StoreResponse>> search(@RequestParam Map<String, String> keywords) {

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.FOUND).body(Collections.EMPTY_LIST);
    }

    @GetMapping(GET_EXISTS_BY_ID)
    public ResponseEntity existsById(@RequestParam("storeId") Long storeId) {

        final var response = new Object() {
            public boolean exists = false;
        };

        // START_SELECT_FLOW
        try {
            response.exists = storeFlow.existsById(storeId);
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        final var body = (response.exists == Boolean.TRUE) ? HttpSuccessMessages.STORE_EXISTS.getDescription()
                : HttpFailureMessages.STORE_DOES_NOT_EXIST.getDescription();

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
    }

    /*
    // @GetMapping // EXISTS_BY_UNIQUE_FIELDS
    public ResponseEntity<String> existsByUniqueFields(@RequestParam("name") String name, @RequestParam("storeId") UUID storeId) {
        // RETURN_SUCCESS
    }
    */
}
