package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"storeId"})
public class StoreResponse {

    Long storeId;

    String eTrackingId;

    String storeName;

    String slogan;

    String type;

    UUID parentId;

    String about;

    Boolean isActive;

    ImageResponse bgImage;

    AddressResponse address;

    ContactResponse contact;

    List<ProductResponse> products = new ArrayList<>();

    public void addProduct(ProductResponse product) {
        this.products.add(product);
    }

    public void removeProduct(ProductResponse product) {
        this.products.remove(product);
    }

    AuthResponse auth;

    List<PaymentResponse> payments = new ArrayList<>();

    public void addPayment(PaymentResponse payment) {
        this.payments.add(payment);
    }

    public void removePayment(PaymentResponse payment) {
        this.payments.remove(payment);
    }

    List<DeliveryResponse> deliveries = new ArrayList<>();

    public void addDelivery(DeliveryResponse delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(DeliveryResponse delivery) {
        this.deliveries.remove(delivery);
    }

    List<ScheduleResponse> schedules = new ArrayList<>();

    public void addSchedule(ScheduleResponse schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(ScheduleResponse schedule) {
        this.schedules.remove(schedule);
    }

    String createdBy;

    Date createdDate;

    String lastModifiedBy;

    Date lastModifiedDate;
}
