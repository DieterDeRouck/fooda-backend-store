package be.fooda.backend.store.service.mapper;

import be.fooda.backend.store.model.dto.CreateProductRequest;
import be.fooda.backend.store.model.dto.UpdateProductRequest;
import be.fooda.backend.store.model.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        )
public interface ProductMapper {

    ProductEntity toEntity(CreateProductRequest source);

    ProductEntity toEntity(UpdateProductRequest source, @MappingTarget ProductEntity target);

    CreateProductRequest toCreate(ProductEntity source);

    UpdateProductRequest toUpdate(ProductEntity source, @MappingTarget UpdateProductRequest target);
}