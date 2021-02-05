package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query("SELECT s FROM ProductEntity s WHERE s.name IN :productName")
    List<ProductEntity> findByProductName(@Param("productName") final String productName);

    @Query("SELECT s FROM ProductEntity s WHERE s.cuisine IN :cuisine")
    List<ProductEntity> findByCuisine(@Param("cuisine") final String cuisine);

    @Query("SELECT s FROM ProductEntity s WHERE s.dietary IN :dietary")
    List<ProductEntity> findByCategory(@Param("dietary") final String dietary);

    @Query("SELECT s FROM ProductEntity s WHERE s.price IN :price")
    List<ProductEntity> findByProductPrice(@Param("price") final BigDecimal price);

}
