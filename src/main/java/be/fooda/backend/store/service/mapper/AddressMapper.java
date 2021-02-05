package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.AddressCreate;
import be.fooda.backend.store.model.entity.AddressEntity;
import be.fooda.backend.store.model.update.AddressUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface AddressMapper {

    AddressEntity toEntity(AddressCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressEntity toEntity(AddressUpdate source, @MappingTarget AddressEntity target);


    AddressCreate toCreate(AddressEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressUpdate toUpdate(AddressEntity source, @MappingTarget AddressUpdate target);


}
