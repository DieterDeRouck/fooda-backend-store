package be.fooda.backend.store.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentUpdate {

    private String method;

    private BigDecimal minOrderAmount;

    private LocalDate expiryDate;

}
