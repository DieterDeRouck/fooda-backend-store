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

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"paymentId"})
public class ProductResponse {

    Long productId;

    String name;

    Integer menuOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    Set<String> categories = new HashSet<>();

    String dietary;

    String cuisine;

    @URL
    String imageUrl;

    BigDecimal price;

    String createdBy;

    Date createdDate;

    String lastModifiedBy;

    Date lastModifiedDate;
}
