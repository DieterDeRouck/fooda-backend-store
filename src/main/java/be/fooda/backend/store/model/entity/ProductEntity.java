package be.fooda.backend.store.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"productId"})
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue
    UUID productId;

    String name;

    Integer menuOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    Set<String> categories = new HashSet<>();

    String dietary;

    String cuisine;

    @URL
    String imageUrl;

    BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    StoreEntity store;

}
