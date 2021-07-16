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
public class UpdateStoreRequest {

    private String eTrackingId;

    private String name;

    private String slogan;

    private String type;

    private UUID parentId;

    private String about;

    private UpdateImageRequest bgImage;

    private UpdateAddressRequest address;

    private UpdateContactRequest contact;

    private List<UpdateProductRequest> products = new ArrayList<>();

    public void addProduct(UpdateProductRequest product) {
        this.products.add(product);
    }

    public void removeProduct(UpdateProductRequest product) {
        this.products.remove(product);
    }

    private UpdateAuthRequest auth;

    private List<UpdatePaymentRequest> payments = new ArrayList<>();

    public void addPayment(UpdatePaymentRequest payment) {
        this.payments.add(payment);
    }

    public void removePayment(UpdatePaymentRequest payment) {
        this.payments.remove(payment);
    }

    private List<UpdateDeliveryRequest> deliveries = new ArrayList<>();

    public void addDelivery(UpdateDeliveryRequest delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(UpdateDeliveryRequest delivery) {
        this.deliveries.remove(delivery);
    }

    private List<UpdateScheduleRequest> schedules = new ArrayList<>();

    public void addSchedule(UpdateScheduleRequest schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(UpdateScheduleRequest schedule) {
        this.schedules.remove(schedule);
    }

}
