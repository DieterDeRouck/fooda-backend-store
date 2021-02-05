package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.DeliveryCreate;
import be.fooda.backend.store.model.entity.DeliveryEntity;
import be.fooda.backend.store.model.update.DeliveryUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface DeliveryMapper {

    DeliveryEntity toEntity(DeliveryCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeliveryEntity toEntity(DeliveryUpdate source, @MappingTarget DeliveryEntity target);


    DeliveryCreate toCreate(DeliveryEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeliveryUpdate toUpdate(DeliveryEntity source, @MappingTarget DeliveryUpdate target);


}
