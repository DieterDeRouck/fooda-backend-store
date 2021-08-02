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
public class UpdateStoreRequest {

    Long storeId;

    String eTrackingId;

    String name;

    String slogan;

    String type;

    UUID parentId;

    String about;

    UpdateImageRequest bgImage;

    UpdateAddressRequest address;

    UpdateContactRequest contact;

    List<UpdateProductRequest> products = new ArrayList<>();

    public void addProduct(UpdateProductRequest product) {
        this.products.add(product);
    }

    public void removeProduct(UpdateProductRequest product) {
        this.products.remove(product);
    }

    UpdateAuthRequest auth;

    List<UpdatePaymentRequest> payments = new ArrayList<>();

    public void addPayment(UpdatePaymentRequest payment) {
        this.payments.add(payment);
    }

    public void removePayment(UpdatePaymentRequest payment) {
        this.payments.remove(payment);
    }

    List<UpdateDeliveryRequest> deliveries = new ArrayList<>();

    public void addDelivery(UpdateDeliveryRequest delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(UpdateDeliveryRequest delivery) {
        this.deliveries.remove(delivery);
    }

    List<UpdateScheduleRequest> schedules = new ArrayList<>();

    public void addSchedule(UpdateScheduleRequest schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(UpdateScheduleRequest schedule) {
        this.schedules.remove(schedule);
    }

}
