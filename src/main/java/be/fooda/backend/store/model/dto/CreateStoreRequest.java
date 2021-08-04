package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"storeId"})
public class CreateStoreRequest {

    String eTrackingId;

    String title;

    String slogan;

    String type;

    Long parentId;

    String about;

    Long bgImageId;

    Long addressId;

    Long contactId;

    List<Long> products = new ArrayList<>();

    public void addProduct(Long productId) {
        this.products.add(productId);
    }

    public void removeProduct(Long productId) {
        this.products.remove(productId);
    }

    CreateAuthRequest auth;

    List<CreatePaymentRequest> payments = new ArrayList<>();

    public void addPayment(CreatePaymentRequest payment) {
        this.payments.add(payment);
    }

    public void removePayment(CreatePaymentRequest payment) {
        this.payments.remove(payment);
    }

    List<CreateDeliveryRequest> deliveries = new ArrayList<>();

    public void addDelivery(CreateDeliveryRequest delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(CreateDeliveryRequest delivery) {
        this.deliveries.remove(delivery);
    }

    List<CreateScheduleRequest> schedules = new ArrayList<>();

    public void addSchedule(CreateScheduleRequest schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(CreateScheduleRequest schedule) {
        this.schedules.remove(schedule);
    }

}
