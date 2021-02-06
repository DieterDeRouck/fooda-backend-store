package be.fooda.backend.store.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AuthUpdate {

    private String authKey;

    private String secret;

    private LocalDate expiryDate;

    private String siteUrl;

    private String storeUrl;

}
