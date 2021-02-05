package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {
    @Query("SELECT swh FROM ScheduleEntity swh WHERE swh.openTime = :opens AND swh.closeTime =:closes")
    List<ScheduleEntity> findByWorkingHours(@Param("opens") final LocalDateTime opens, @Param("closes") final LocalDateTime closes);

}
