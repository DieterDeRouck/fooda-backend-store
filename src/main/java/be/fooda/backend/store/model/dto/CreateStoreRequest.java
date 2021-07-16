package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStoreRequest {

    private String eTrackingId;

    private String name;

    private String slogan;

    private String type;

    private UUID parentId;

    private String about;

    private CreateImageRequest bgImage;

    private CreateAddressRequest address;

    private CreateContactRequest contact;

    private List<CreateProductRequest> products = new ArrayList<>();

    public void addProduct(CreateProductRequest product) {
        this.products.add(product);
    }

    public void removeProduct(CreateProductRequest product) {
        this.products.remove(product);
    }

    private CreateAuthRequest auth;

    private List<CreatePaymentRequest> payments = new ArrayList<>();

    public void addPayment(CreatePaymentRequest payment) {
        this.payments.add(payment);
    }

    public void removePayment(CreatePaymentRequest payment) {
        this.payments.remove(payment);
    }

    private List<CreateDeliveryRequest> deliveries = new ArrayList<>();

    public void addDelivery(CreateDeliveryRequest delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(CreateDeliveryRequest delivery) {
        this.deliveries.remove(delivery);
    }

    private List<CreateScheduleRequest> schedules = new ArrayList<>();

    public void addSchedule(CreateScheduleRequest schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(CreateScheduleRequest schedule) {
        this.schedules.remove(schedule);
    }

}
