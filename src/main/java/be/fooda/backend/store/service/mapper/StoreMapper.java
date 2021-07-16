package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.dto.CreateStoreRequest;
import be.fooda.backend.store.model.dto.StoreResponse;
import be.fooda.backend.store.model.dto.UpdateStoreRequest;
import be.fooda.backend.store.model.entity.StoreEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StoreMapper {

    StoreEntity toEntity(CreateStoreRequest source);

    Set<StoreEntity> toEntities(Set<CreateStoreRequest> sourceSet);

    List<StoreEntity> toEntities(List<CreateStoreRequest> sourceList);

    StoreEntity toEntity(UpdateStoreRequest source, @MappingTarget StoreEntity target);

    List<StoreEntity> toEntities(List<UpdateStoreRequest> sources, @MappingTarget List<StoreEntity> targets);

    CreateStoreRequest toRequest(StoreEntity source);

    List<CreateStoreRequest> toRequests(List<StoreEntity> sources);

    UpdateStoreRequest toRequest(StoreEntity source, @MappingTarget UpdateStoreRequest target);

    StoreResponse toResponse(StoreEntity source);

    Set<StoreResponse> toResponses(Set<StoreEntity> sourceSet);

    List<StoreResponse> toResponses(List<StoreEntity> sourceList);

}