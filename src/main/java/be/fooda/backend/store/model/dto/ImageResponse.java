package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageResponse {

    @ToString.Include
    private UUID mediaId;

    @ToString.Include
    private String type;

    @ToString.Include
    private String url;

    @ToString.Include
    String createdBy;

    @ToString.Include
    Date createdDate;

    @ToString.Include
    String lastModifiedBy;

    @ToString.Include
    Date lastModifiedDate;

}
