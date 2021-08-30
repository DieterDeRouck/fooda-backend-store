package be.fooda.backend.store.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"title", "contactId"})

// JACKSON
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExistsByUniqueFieldsRequest {

    private String title;

    private Long contactId;

}
