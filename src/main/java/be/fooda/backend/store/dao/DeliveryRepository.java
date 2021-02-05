package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, UUID> {

    @Query("SELECT sdl FROM DeliveryEntity sdl WHERE sdl.deliveryDuration = :timeAsMinutes")
    List<DeliveryEntity> findByDeliveryTime(@Param("timeAsMinutes") final Double timeAsMinutes);

    @Query("SELECT sdc FROM DeliveryEntity sdc WHERE sdc.minOrderPrice >= :minPrice AND sdc.maxOrderPrice <= :maxPrice")
    List<DeliveryEntity> findByDeliveryCost(@Param("minPrice") final BigDecimal minPrice, @Param("maxPrice") final BigDecimal maxPrice);

    @Query("SELECT sdc FROM DeliveryEntity sdc WHERE sdc.minOrderPrice >= :minPrice AND sdc.maxOrderPrice <= :maxPrice AND sdc.deliveryCost >= :amount")
    List<DeliveryEntity> findByDeliveryCost(@Param("minPrice") final BigDecimal minPrice, @Param("maxPrice") final BigDecimal maxPrice, @Param("amount") final BigDecimal amount);

    @Query("SELECT sdc FROM DeliveryEntity sdc WHERE sdc.postcode = :postcode")
    List<DeliveryEntity> findByDeliveryLocation(@Param("postcode") final String postcode);

}
