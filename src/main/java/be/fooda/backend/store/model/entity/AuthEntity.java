package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class AuthEntity extends AbstractAuditable<String, UUID> {

    @EqualsAndHashCode.Include
    @NotNull
    private String authKey;

    @EqualsAndHashCode.Include
    private String secret;

    @FutureOrPresent
    private LocalDate expiryDate;

    @URL(protocol = "https")
    private String siteUrl;

    @URL(protocol = "https")
    private String storeUrl;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private StoreEntity store;
}
