package be.fooda.backend.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"scheduleId"})
@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue
    UUID scheduleId;

    LocalDateTime opens;

    LocalDateTime closes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    StoreEntity store;
}

