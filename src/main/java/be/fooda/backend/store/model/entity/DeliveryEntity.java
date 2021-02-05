package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeliveryEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Field
    @SortableField
    private String postcode;

    @Basic
    @Field
    @SortableField
    private Duration deliveryDuration;

    @Field
    @SortableField
    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal minOrderPrice;

    @Field
    @SortableField
    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal maxOrderPrice;

    @Field
    @SortableField
    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal deliveryCost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    @ContainedIn
    private StoreEntity store;
}
