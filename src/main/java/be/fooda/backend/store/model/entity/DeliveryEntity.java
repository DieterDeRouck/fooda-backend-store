package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"deliveryId"})
@Entity
public class DeliveryEntity {

    @Id
    @GeneratedValue
    UUID deliveryId;

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
}
