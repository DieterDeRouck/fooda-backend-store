package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"deliveryId"})
public class CreateDeliveryRequest  {

    UUID deliveryId;

    String postcode;

    Duration deliveryDuration;

    BigDecimal minOrderPrice;

    BigDecimal maxOrderPrice;

    BigDecimal deliveryCost;
}
