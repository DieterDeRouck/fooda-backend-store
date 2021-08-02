package be.fooda.backend.store.model.entity;

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
@EqualsAndHashCode(of = {"addressId"})
@Entity
public class AddressEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long addressId;

    String postcode;

    String municipality;

    String city;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    StoreEntity store;

    @Override
    public Long getId() {
        return addressId;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(addressId);
    }
}
