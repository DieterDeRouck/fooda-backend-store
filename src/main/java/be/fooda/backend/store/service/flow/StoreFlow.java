package be.fooda.backend.store.service.flow;

import be.fooda.backend.store.dao.StoreRepository;
import be.fooda.backend.store.model.dto.CreateStoreRequest;
import be.fooda.backend.store.model.dto.StoreResponse;
import be.fooda.backend.store.model.entity.StoreEntity;
import be.fooda.backend.store.service.exception.ResourceNotFoundException;
import be.fooda.backend.store.service.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StoreFlow {

    private final StoreRepository productRepository;
    private final StoreMapper productMapper;

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
            throw new NullPointerException(HttpFailureMassages.FAILED_TO_CREATE_STORE.getDescription());
        }

        //  IF(STORE_EXISTS)
        boolean exists = productRepository.existsByNameAndStore_StoreId(
                request.getName(), request.getStore().getStoreId()
        );

        if (exists) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMassages.STORE_ALREADY_EXIST.getDescription());
        }

        // MAP_DTO_TO_ENTITY
        StoreEntity entity = productMapper.toEntity(request);

        // SAVE_TO_DB(ENTITY)
        productRepository.save(entity);

    }

    // UPDATE_STORE(UNIQUE_IDENTIFIER, REQUEST)
    public void updateStore(UUID id, UpdateStoreRequest request) {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMassages.FAILED_TO_UPDATE_STORE.getDescription());
        }

        Optional<StoreEntity> oEntity = productRepository.findById(id);

        //  IF(STORE_NOT_EXIST)
        if (oEntity.isEmpty()) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMassages.STORE_NOT_FOUND.getDescription());
        }

        // MAP_FROM_REQUEST_TO_ENTITY
        StoreEntity entity = oEntity.get();
        StoreEntity entityToUpdate = productMapper.toEntity(request, entity);

        // UPDATE_FROM_DB
        productRepository.save(entityToUpdate);

    }

    // FIND_ALL(PAGE_NO, PAGE_SIZE)
    public List<StoreResponse> findAll(int pageNo, int pageSize) {

        // READ_FROM_DB(PAGE_NO, PAGE_SIZE)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<StoreEntity> pages = productRepository.findAll(pageable);

        // RETURN
        return productMapper.toResponses(pages.toList());
    }
}
