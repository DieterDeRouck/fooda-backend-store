package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<ContactEntity, UUID> {
}