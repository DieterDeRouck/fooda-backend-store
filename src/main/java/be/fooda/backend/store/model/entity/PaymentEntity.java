package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"paymentId"})
@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue
    UUID paymentId;

    String method;

    BigDecimal minOrderAmount;

    @FutureOrPresent
    LocalDate expiryDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    StoreEntity store;
}
