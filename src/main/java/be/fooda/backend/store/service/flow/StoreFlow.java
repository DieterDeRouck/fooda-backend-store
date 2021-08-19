package be.fooda.backend.store.service.flow;

import be.fooda.backend.store.dao.StoreRepository;
import be.fooda.backend.store.model.dto.CreateStoreRequest;
import be.fooda.backend.store.model.dto.StoreResponse;
import be.fooda.backend.store.model.dto.UpdateStoreRequest;
import be.fooda.backend.store.model.entity.StoreEntity;
import be.fooda.backend.store.model.http.HttpFailureMessages;
import be.fooda.backend.store.service.exception.ResourceNotFoundException;
import be.fooda.backend.store.service.mapper.StoreMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class StoreFlow {

    StoreRepository storeRepository;
    StoreMapper storeMapper;

    ObjectMapper jsonMapper;

    /*
        Responsibilities of this class:
        1- All scenarios (requirements) will be implemented here..
        2- Exceptions will be handled here.
        3- Mappers will be used here.
        4- Validations for class fields will be checked here.
        5- Database transactions will be processed here.
     */

    // CREATE_STORE(REQUEST)
    public void createStore(CreateStoreRequest request) throws
            NullPointerException, ResourceNotFoundException {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMessages.FAILED_TO_CREATE_STORE.getDescription());
        }

        //  IF(STORE_EXISTS)
        boolean exists = storeRepository.existsByTitleAndAddressId(
                request.getTitle(), request.getAddressId()
        );

        if (exists) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMessages.STORE_ALREADY_EXIST.getDescription());
        }

        // MAP_DTO_TO_ENTITY
        StoreEntity entity = storeMapper.toEntity(request);

        // SAVE_TO_DB(ENTITY)
        storeRepository.save(entity);

    }

    // UPDATE_STORE(UNIQUE_IDENTIFIER, REQUEST)
    public void updateStore(Long id, UpdateStoreRequest request) {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMessages.FAILED_TO_UPDATE_STORE.getDescription());
        }

        Optional<StoreEntity> oEntity = storeRepository.findById(id);

        //  IF(STORE_NOT_EXIST)
        if (oEntity.isEmpty()) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMessages.STORE_NOT_FOUND.getDescription());
        }

        // MAP_FROM_REQUEST_TO_ENTITY
        StoreEntity entity = oEntity.get();
        StoreEntity entityToUpdate = storeMapper.toEntity(request, entity);

        // UPDATE_FROM_DB
        storeRepository.save(entityToUpdate);

    }

    // FIND_ALL(PAGE_NO, PAGE_SIZE)
    public List<StoreResponse> findAll(int pageNo, int pageSize) {

        // READ_FROM_DB(PAGE_NO, PAGE_SIZE)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<StoreEntity> pages = storeRepository.findAll(pageable);

        // RETURN
        return storeMapper.toResponses(pages.toList());
    }

    // GET_BY_ID(ID)
    public StoreResponse findByID(Long id) throws ResourceNotFoundException{

        //READ_FROM_DB(ID)
        Optional<StoreEntity> storeEntity = storeRepository.findById(id);
        if (storeEntity.isEmpty()){
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMessages.STORE_NOT_FOUND.getDescription());
        }

        //RETURN
        return storeMapper.toResponse(storeEntity.get());
    }

    // EXISTS_BY_ID(ID)
    @Transactional
    public Boolean existsById(Long id) throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // NULL_VALIDATION
        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMessages.STORE_ID_IS_REQUIRED.getDescription())
        }

        final var response = storeRepository.existsById(id);

        // LOG
        final var result = new Exists(Map.of("id", id), response);
        log.info("STORE EXISTS BY UNIQUE FIELDS: " + "\n\n" + jsonMapper.writeValueAsString(result) + "\n\n");

        return response;
    }

    //GET_BY_STORE_NAME


    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    static class Exists implements Serializable {
        Map<String, Object> params;
        boolean result;
    }

}
