package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.StoreCreate;
import be.fooda.backend.store.model.entity.StoreEntity;
import be.fooda.backend.store.model.update.StoreUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface StoreMapper {

    StoreEntity toEntity(StoreCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StoreEntity toEntity(StoreUpdate source, @MappingTarget StoreEntity target);


    StoreCreate toCreate(StoreEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StoreUpdate toUpdate(StoreEntity source, @MappingTarget StoreUpdate target);


}
