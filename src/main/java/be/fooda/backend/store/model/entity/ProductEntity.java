package be.fooda.backend.store.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
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
@Entity
public class ProductEntity extends AbstractAuditable<String, UUID> {

    @EqualsAndHashCode.Include
    private UUID productId;

    @ToString.Include
    private String name;

    @ToString.Include
    private Integer menuOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    private Set<String> categories = new HashSet<>();

    @ToString.Include
    private String dietary;

    @ToString.Include
    private String cuisine;

    @URL
    private String imageUrl;

    @ToString.Include
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    private StoreEntity store;

}
