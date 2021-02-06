package be.fooda.backend.store.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StoreCreate {

    private String name;

    private String slogan;

    private String type;

    private UUID parentId;

    private String about;

    private String eTrackingId;

    private ImageCreate bgImage;

    private AddressCreate address;

    private ContactCreate contact;

    private List<ProductCreate> products = new ArrayList<>();

    public void addProduct(ProductCreate product) {
        this.products.add(product);
    }

    public void removeProduct(ProductCreate product) {
        this.products.remove(product);
    }

    private AuthCreate auth;

    private List<PaymentCreate> payments = new ArrayList<>();

    public void addPayment(PaymentCreate payment) {
        this.payments.add(payment);
    }

    public void removePayment(PaymentCreate payment) {
        this.payments.remove(payment);
    }

    private List<DeliveryCreate> deliveries = new ArrayList<>();

    public void addDelivery(DeliveryCreate delivery) {
        this.deliveries.add(delivery);
    }

    public void removeDelivery(DeliveryCreate delivery) {
        this.deliveries.remove(delivery);
    }

    private List<ScheduleCreate> schedules;

    public void addSchedule(ScheduleCreate schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(ScheduleCreate schedule) {
        this.schedules.remove(schedule);
    }

}
