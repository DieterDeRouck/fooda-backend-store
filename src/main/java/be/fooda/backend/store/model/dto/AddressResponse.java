package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse extends AbstractAuditable<String, UUID> {

    UUID id;

    public boolean isNew() {
        return null == getId();
    }

    @ToString.Include
    UUID addressId;

    @ToString.Include
    String postcode;

    @ToString.Include
    String municipality;

    @ToString.Include
    String city;

    @ToString.Include
    String createdBy;

    @ToString.Include
    Date createdDate;

    @ToString.Include
    String lastModifiedBy;

    @ToString.Include
    Date lastModifiedDate;
}
