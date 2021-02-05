package be.fooda.backend.store.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryCreate {

    private String postcode;

    private Duration deliveryDuration;

    private BigDecimal minOrderPrice;

    private BigDecimal maxOrderPrice;

    private BigDecimal deliveryCost;

}
