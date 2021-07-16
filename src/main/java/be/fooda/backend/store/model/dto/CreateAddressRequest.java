package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAddressRequest {

    @EqualsAndHashCode.Include
    private UUID addressId;

    @ToString.Include
    private String postcode;

    @ToString.Include
    private String municipality;

    @ToString.Include
    private String city;

}
