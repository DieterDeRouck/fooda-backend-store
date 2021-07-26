package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"productId"})
public class UpdateProductRequest {

    UUID productId;

    String name;

    Integer menuOrder;

    Set<String> categories = new HashSet<>();

    String dietary;

    String cuisine;

    String imageUrl;

    BigDecimal price;
}
