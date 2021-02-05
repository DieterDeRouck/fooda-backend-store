package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {

    @Query("SELECT sauth FROM AuthEntity sauth WHERE sauth.authKey = :key AND sauth.secret = :secret AND sauth.store.id = :storeId")
    Optional<AuthEntity> findByAuth(@Param("key") final String key, @Param("secret") final String secret, @Param("storeId") final UUID storeId);

    @Query("SELECT sauth FROM AuthEntity sauth WHERE sauth.store.id = :storeId")
    List<AuthEntity> findByAuthStoreId(@Param("storeId") final UUID storeId);
}
