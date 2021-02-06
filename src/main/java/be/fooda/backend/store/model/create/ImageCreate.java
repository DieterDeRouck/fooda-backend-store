package be.fooda.backend.store.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ImageCreate {

    private UUID eMediaId;

    private String type;

    private String url;

}
