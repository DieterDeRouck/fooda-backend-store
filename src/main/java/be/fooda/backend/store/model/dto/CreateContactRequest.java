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
public class CreateContactRequest {

    @EqualsAndHashCode.Include
    private UUID contactId;

    @ToString.Include
    private String phone;

    @ToString.Include
    private String email;

    @ToString.Include
    private String firstName;

    @ToString.Include
    private String lastName;

}
