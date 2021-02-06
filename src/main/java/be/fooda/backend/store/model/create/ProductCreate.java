package be.fooda.backend.store.model.create;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductCreate {

    private UUID eProductId;

    private String name;

    private Integer menuOrder;

    private Set<String> categories;

    private String dietary;

    private String cuisine;

    private String imageUrl;

    private BigDecimal price;

}
