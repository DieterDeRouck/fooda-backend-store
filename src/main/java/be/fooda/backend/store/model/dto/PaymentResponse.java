package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {

    @ToString.Include
    String method;

    @ToString.Include
    BigDecimal minOrderAmount;

    @ToString.Include
    LocalDate expiryDate;

    @ToString.Include
    String createdBy;

    @ToString.Include
    Date createdDate;

    @ToString.Include
    String lastModifiedBy;

    @ToString.Include
    Date lastModifiedDate;
}
