package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePaymentRequest {

    @ToString.Include
    private String method;

    @ToString.Include
    private BigDecimal minOrderAmount;

    @ToString.Include
    private LocalDate expiryDate;

}
