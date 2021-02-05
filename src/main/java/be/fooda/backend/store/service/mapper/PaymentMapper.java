package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.PaymentCreate;
import be.fooda.backend.store.model.entity.PaymentEntity;
import be.fooda.backend.store.model.update.PaymentUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface PaymentMapper {

    PaymentEntity toEntity(PaymentCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentEntity toEntity(PaymentUpdate source, @MappingTarget PaymentEntity target);


    PaymentCreate toCreate(PaymentEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentUpdate toUpdate(PaymentEntity source, @MappingTarget PaymentUpdate target);


}
