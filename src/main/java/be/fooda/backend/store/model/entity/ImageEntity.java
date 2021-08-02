package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"mediaId"})
@Entity
public class ImageEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long mediaId;

    String type;

    @NotNull(message = "Image URL is required.")
    @URL
    String url;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    StoreEntity store;

    @Override
    public Long getId() {
        return mediaId;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(mediaId);
    }
}
