package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    @Query("SELECT s FROM StoreEntity s WHERE s.name LIKE :name")
    List<StoreEntity> findByName(@Param("name") String name);

    List<StoreEntity> findByType(final String type);

    List<StoreEntity> findByParentId(final UUID parent);

    List<StoreEntity> findByAbout(final String about);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StoreEntity s WHERE s.name = :storeName " +
            "AND s.address.eAddressId = :eAddressId AND s.auth.authKey = :authKey")
    boolean existByUniqueFields(@Param("storeName") String storeName,
                                @Param("eAddressId") UUID externalAddressId,
                                @Param("authKey") String authKey);

    @Query("SELECT s FROM StoreEntity s WHERE s.isActive = :isActive")
    Page<StoreEntity> findAll(@Param("isActive") boolean isActive, Pageable pageable);


    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StoreEntity s WHERE s.id = :id AND s.isActive = :isActive")
    boolean existByIsActive(@Param("id") UUID id,
                            @Param("isActive") boolean isActive);
}