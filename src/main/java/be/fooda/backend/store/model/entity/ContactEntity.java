package be.fooda.backend.store.model.entity;

import be.fooda.backend.store.service.validation.Name;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"contactId"})
@Entity
public class ContactEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long contactId;

    String phone;

    String email;

    @Name
    String firstName;

    @Name
    String lastName;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    StoreEntity store;

    @Override
    public Long getId() {
        return contactId;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(contactId);
    }
}
