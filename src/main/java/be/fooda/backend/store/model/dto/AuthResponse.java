package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"authId"})
public class AuthResponse {

    Long authId;

    String authKey;

    String secret;

    LocalDate expiryDate;

    String siteUrl;

    String storeUrl;

    String createdBy;

    Date createdDate;

    String lastModifiedBy;

    Date lastModifiedDate;
}
