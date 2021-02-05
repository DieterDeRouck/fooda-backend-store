package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<ContactEntity, UUID> {

    @Query("SELECT s FROM StoreEntity s WHERE s.contact.eContactId = : contactId")
    List<ContactEntity> findByExternalContactId(@Param("contactId") final UUID contactId);
}
