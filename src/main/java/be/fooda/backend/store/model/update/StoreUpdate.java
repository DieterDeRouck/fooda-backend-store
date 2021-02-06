package be.fooda.backend.store.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StoreUpdate {

    private String name;

    private String slogan;

    private String type;

    private UUID parentId;

    private String about;

    private String eTrackingId;

    private ImageUpdate bgImage;

    private AddressUpdate address;

    private ContactUpdate contact;

    private List<ProductUpdate> products = new ArrayList<>();

    public void addProduct(ProductUpdate product) {
        this.products.add(product);
    }

    public void removeProduct(ProductUpdate product) {
        this.products.remove(product);
    }

    private AuthUpdate auth;

    private List<PaymentUpdate> payments = new ArrayList<>();

    public void addPayment(PaymentUpdate payment) {
        this.payments.add(payment);
    }

    public void removePayment(PaymentUpdate payment) {
        this.payments.remove(payment);
    }

    private List<DeliveryUpdate> deliveries = new ArrayList<>();

    public void addDelivery(DeliveryUpdate delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(DeliveryUpdate delivery) {
        this.deliveries.remove(delivery);
    }

    private List<ScheduleUpdate> schedules;

    public void addSchedule(ScheduleUpdate schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(ScheduleUpdate schedule) {
        this.schedules.remove(schedule);
    }

}
