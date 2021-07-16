package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class DeliveryEntity extends AbstractAuditable<String, UUID> {

    @ToString.Include
    private String postcode;

    @ToString.Include
    private Duration deliveryDuration;

    @ToString.Include
    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal minOrderPrice;

    @ToString.Include
    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal maxOrderPrice;

    @ToString.Include
    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal deliveryCost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    private StoreEntity store;
}
