package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryResponse {

    @ToString.Include
    String postcode;

    @ToString.Include
    Duration deliveryDuration;

    @ToString.Include
    BigDecimal minOrderPrice;

    @ToString.Include
    BigDecimal maxOrderPrice;

    @ToString.Include
    BigDecimal deliveryCost;

    @ToString.Include
    String createdBy;

    @ToString.Include
    Date createdDate;

    @ToString.Include
    String lastModifiedBy;

    @ToString.Include
    Date lastModifiedDate;
}
