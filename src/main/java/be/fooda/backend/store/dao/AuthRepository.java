package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
}