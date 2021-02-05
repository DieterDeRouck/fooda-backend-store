package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.ContactCreate;
import be.fooda.backend.store.model.entity.ContactEntity;
import be.fooda.backend.store.model.update.ContactUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ContactMapper {

    ContactEntity toEntity(ContactCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactEntity toEntity(ContactUpdate source, @MappingTarget ContactEntity target);


    ContactCreate toCreate(ContactEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactUpdate toUpdate(ContactEntity source, @MappingTarget ContactUpdate target);


}
