package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"contactId"})
public class ContactResponse {

    Long contactId;

    String phone;

    String email;

    String firstName;

    String lastName;

    String createdBy;

    Date createdDate;

    String lastModifiedBy;

    Date lastModifiedDate;
}
