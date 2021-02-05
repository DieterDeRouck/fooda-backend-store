package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
    
}
