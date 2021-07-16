package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    @EqualsAndHashCode.Include
    UUID productId;

    @ToString.Include
    String name;

    @ToString.Include
    Integer menuOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    Set<String> categories = new HashSet<>();

    @ToString.Include
    String dietary;

    @ToString.Include
    String cuisine;

    @URL
    String imageUrl;

    @ToString.Include
    BigDecimal price;

    @ToString.Include
    String createdBy;

    @ToString.Include
    Date createdDate;

    @ToString.Include
    String lastModifiedBy;

    @ToString.Include
    Date lastModifiedDate;

}
