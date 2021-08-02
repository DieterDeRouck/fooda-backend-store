package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"deliveryId"})
@Entity
public class DeliveryEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long deliveryId;

    String postcode;

    Duration deliveryDuration;

    @Column(columnDefinition = "DECIMAL(8,2)")
    BigDecimal minOrderPrice;

    @Column(columnDefinition = "DECIMAL(8,2)")
    BigDecimal maxOrderPrice;

    @Column(columnDefinition = "DECIMAL(8,2)")
    BigDecimal deliveryCost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    StoreEntity store;

    @Override
    public Long getId() {
        return deliveryId;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(deliveryId);
    }
}
