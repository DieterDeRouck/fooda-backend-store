package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}