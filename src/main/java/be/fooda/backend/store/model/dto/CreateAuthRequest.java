package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"authId"})
public class CreateAuthRequest {

    UUID authId;

    String authKey;

    String secret;

    LocalDate expiryDate;

    String siteUrl;

     String storeUrl;
}
