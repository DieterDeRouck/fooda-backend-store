package be.fooda.backend.store.model.entity;


import be.fooda.backend.store.service.validation.Name;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class StoreEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long id;

    String eTrackingId;

    @Name
    String name;

    @Lob
    String slogan;

    String type;

    UUID parentId;

    @Lob
    String about;

    Boolean isActive = Boolean.TRUE;

    String bgImageId;

    String addressId;

    String contactId;

    @ElementCollection
    List<String> products = new ArrayList<>();

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private AuthEntity auth;

    public void setAuth(AuthEntity auth) {
        auth.setStore(this);
        this.auth = auth;
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<PaymentEntity> acceptedPayments = new ArrayList<>();

    public void setAcceptedPayments(List<PaymentEntity> acceptedPaymentMethods) {
        this.acceptedPayments = acceptedPaymentMethods.stream()
                .map(this::setOneAcceptedPayment)
                .collect(Collectors.toList());
    }

    private PaymentEntity setOneAcceptedPayment(PaymentEntity acceptedPaymentMethod) {
        acceptedPaymentMethod.setStore(this);
        return acceptedPaymentMethod;
    }

    public void addAcceptedPayment(PaymentEntity payment) {
        payment.setStore(this);
        this.acceptedPayments.add(payment);
    }

    public void removeAcceptedPayment(PaymentEntity payment) {
        payment.setStore(null);
        this.acceptedPayments.remove(payment);
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<DeliveryEntity> deliveryLocations = new ArrayList<>();

    public void setDeliveryLocations(List<DeliveryEntity> deliveryLocations) {
        this.deliveryLocations = deliveryLocations.stream()
                .map(this::setOneDeliveryLocation).collect(Collectors.toList());
    }

    private DeliveryEntity setOneDeliveryLocation(DeliveryEntity deliveryLocation) {
        deliveryLocation.setStore(this);
        return deliveryLocation;
    }

    public void addDeliveryLocation(DeliveryEntity delivery) {
        delivery.setStore(this);
        this.deliveryLocations.add(delivery);
    }

    public void removeDeliveryLocation(DeliveryEntity delivery) {
        delivery.setStore(null);
        this.deliveryLocations.remove(delivery);
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<ScheduleEntity> schedules = new ArrayList<>();

    public void setSchedules(List<ScheduleEntity> workingHours) {
        this.schedules = workingHours.stream()
                .map(this::setOneSchedule)
                .collect(Collectors.toList());
    }

    private ScheduleEntity setOneSchedule(ScheduleEntity schedule) {
        schedule.setStore(this);
        return schedule;
    }

    public void addSchedule(ScheduleEntity schedule) {
        schedule.setStore(this);
        this.schedules.add(schedule);
    }

    public void removeSchedule(ScheduleEntity schedule) {
        schedule.setStore(this);
        this.schedules.remove(schedule);
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
