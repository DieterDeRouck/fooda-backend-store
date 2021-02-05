package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.ImageCreate;
import be.fooda.backend.store.model.entity.ImageEntity;
import be.fooda.backend.store.model.update.ImageUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ImageMapper {

    ImageEntity toEntity(ImageCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ImageEntity toEntity(ImageUpdate source, @MappingTarget ImageEntity target);


    ImageCreate toCreate(ImageEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ImageUpdate toUpdate(ImageEntity source, @MappingTarget ImageUpdate target);


}
