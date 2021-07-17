package be.fooda.backend.store.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreResponse {

    @ToString.Include
    String eTrackingId;

    @ToString.Include
    String name;

    @ToString.Include
    String slogan;

    @ToString.Include
    String type;

    UUID parentId;

    @ToString.Include
    String about;

    @ToString.Include
    Boolean isActive;

    @ToString.Include
    ImageResponse bgImage;

    @ToString.Include
    AddressResponse address;

    @ToString.Include
    ContactResponse contact;

    private List<ProductResponse> products = new ArrayList<>();

    public void addProduct(ProductResponse product) {
        this.products.add(product);
    }

    public void removeProduct(ProductResponse product) {
        this.products.remove(product);
    }

    @ToString.Include
    private AuthResponse auth;

    private List<PaymentResponse> payments = new ArrayList<>();

    public void addPayment(PaymentResponse payment) {
        this.payments.add(payment);
    }

    public void removePayment(PaymentResponse payment) {
        this.payments.remove(payment);
    }

    private List<DeliveryResponse> deliveries = new ArrayList<>();

    public void addDelivery(DeliveryResponse delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(DeliveryResponse delivery) {
        this.deliveries.remove(delivery);
    }

    private List<ScheduleResponse> schedules = new ArrayList<>();

    public void addSchedule(ScheduleResponse schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(ScheduleResponse schedule) {
        this.schedules.remove(schedule);
    }


    @ToString.Include
    String createdBy;

    @ToString.Include
    Date createdDate;

    @ToString.Include
    String lastModifiedBy;

    @ToString.Include
    Date lastModifiedDate;

}