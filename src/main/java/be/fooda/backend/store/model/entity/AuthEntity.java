package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"authId"})
@Entity
public class AuthEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long authId;

    @NotNull
    String authKey;

    String secret;

    //@FutureOrPresent
    String expiryDate;

    @URL(protocol = "https")
    String siteUrl;

    @URL(protocol = "https")
    String storeUrl;

    @ToString.Exclude //cannot coexist with 'of' operand in annotations.
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    StoreEntity store;

    @Override
    public Long getId() {
        return authId;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(authId);
    }
}
