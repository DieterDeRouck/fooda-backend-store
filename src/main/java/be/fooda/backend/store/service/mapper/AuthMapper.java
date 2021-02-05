package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.AuthCreate;
import be.fooda.backend.store.model.entity.AuthEntity;
import be.fooda.backend.store.model.update.AuthUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface AuthMapper {

    AuthEntity toEntity(AuthCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthEntity toEntity(AuthUpdate source, @MappingTarget AuthEntity target);


    AuthCreate toCreate(AuthEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUpdate toUpdate(AuthEntity source, @MappingTarget AuthUpdate target);


}
