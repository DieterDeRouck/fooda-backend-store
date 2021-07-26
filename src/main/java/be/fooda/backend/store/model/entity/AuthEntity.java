package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"authId"})
@Entity
public class AuthEntity {

    @Id
    @GeneratedValue
    UUID authId;

    @EqualsAndHashCode.Include
    @NotNull
    String authKey;

    @EqualsAndHashCode.Include
    String secret;

    @FutureOrPresent
    LocalDate expiryDate;

    @URL(protocol = "https")
    String siteUrl;

    @URL(protocol = "https")
    String storeUrl;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    StoreEntity store;
}
