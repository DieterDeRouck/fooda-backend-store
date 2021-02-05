package be.fooda.backend.store.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.bridge.builtin.BigDecimalBridge;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Field
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "BINARY(16)")
    private UUID eProductId;

    @Field
    private String name;

    private Integer menuOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    private Set<String> categories = new HashSet<>();

    @Field
    private String dietary;

    @Field
    private String cuisine;

    @URL
    private String imageUrl;

    @Field
    @SortableField
    @NumericField
    @FieldBridge(impl = BigDecimalBridge.class)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    @ContainedIn
    private StoreEntity store;

}
