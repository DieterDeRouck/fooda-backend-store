package be.fooda.backend.store.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ContactUpdate {

    private UUID eContactId;

    private String phone;

    private String email;

    private String firstName;

    private String lastName;

}
