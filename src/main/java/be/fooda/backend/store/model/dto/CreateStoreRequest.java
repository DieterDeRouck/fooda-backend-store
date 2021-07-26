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

    UUID storeId;

    String eTrackingId;

    String name;

    String slogan;

    String type;

    UUID parentId;

    String about;

    CreateImageRequest bgImage;

    CreateAddressRequest address;

    CreateContactRequest contact;

    List<CreateProductRequest> products = new ArrayList<>();

    public void addProduct(CreateProductRequest product) {
        this.products.add(product);
    }

    public void removeProduct(CreateProductRequest product) {
        this.products.remove(product);
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
