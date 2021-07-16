package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductRequest {

    private UUID productId;

    @ToString.Include
    private String name;

    @ToString.Include
    private Integer menuOrder;

    @ToString.Include
    private Set<String> categories = new HashSet<>();

    @ToString.Include
    private String dietary;

    @ToString.Include
    private String cuisine;

    @ToString.Include
    private String imageUrl;

    @ToString.Include
    private BigDecimal price;

}
