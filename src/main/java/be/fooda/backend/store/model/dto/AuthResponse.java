package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
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

    String expiryDate;

    String siteUrl;

    String storeUrl;

    String createdBy;

    String createdDate;

    String lastModifiedBy;

    String lastModifiedDate;
}
