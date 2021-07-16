package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAuthRequest {

    @EqualsAndHashCode.Include
    private String authKey;

    @EqualsAndHashCode.Include
    private String secret;

    private LocalDate expiryDate;

    private String siteUrl;

    private String storeUrl;
}
