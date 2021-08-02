package be.fooda.backend.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"scheduleId"})
public class ScheduleResponse {

    Long scheduleId;

    LocalDateTime opens;

    LocalDateTime closes;

    String createdBy;

    Date createdDate;

    String lastModifiedBy;

    Date lastModifiedDate;
}

