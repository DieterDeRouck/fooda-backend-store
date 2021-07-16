package be.fooda.backend.store.model.entity;

import be.fooda.backend.store.service.validation.Name;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ContactEntity extends AbstractAuditable<String, UUID> {

    @EqualsAndHashCode.Include
    private UUID contactId;

    @ToString.Include
    private String phone;

    @ToString.Include
    private String email;

    @ToString.Include
    @Name
    private String firstName;

    @ToString.Include
    @Name
    private String lastName;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private StoreEntity store;
}
