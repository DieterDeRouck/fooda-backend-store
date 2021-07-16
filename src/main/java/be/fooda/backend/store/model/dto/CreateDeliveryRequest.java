package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDeliveryRequest  {

    @ToString.Include
    private String postcode;

    @ToString.Include
    private Duration deliveryDuration;

    @ToString.Include
    private BigDecimal minOrderPrice;

    @ToString.Include
    private BigDecimal maxOrderPrice;

    @ToString.Include
    private BigDecimal deliveryCost;

}
