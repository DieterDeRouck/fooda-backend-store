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
public class UpdateImageRequest {

    private UUID mediaId;

    @ToString.Include
    private String type;

    @ToString.Include
    private String url;


}
