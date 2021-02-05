package be.fooda.backend.store.model.entity;


import be.fooda.backend.store.service.validation.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Indexed
public class StoreEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Field
    @Name
    private String name;

    @Field
    @Lob
    private String slogan;

    @Field
    private String type;

    @Column(columnDefinition = "BINARY(16)")
    private UUID parentId;

    @Field
    @Lob
    private String about;

    private Boolean isActive = Boolean.TRUE;

    private String eTrackingId;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @Field
    @CreationTimestamp
    private LocalDateTime registeredAt;

    @Field
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private ImageEntity bgImage;

    public void setBgImage(ImageEntity bgImage) {
        bgImage.setStore(this);
        this.bgImage = bgImage;
    }

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private AddressEntity address;

    public void setAddress(AddressEntity address) {
        address.setStore(this);
        this.address = address;
    }

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private ContactEntity contact;

    public void setContact(ContactEntity contact) {
        contact.setStore(this);
        this.contact = contact;
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<ProductEntity> products = new ArrayList<>();

    public void setProducts(List<ProductEntity> products) {
        this.products = products.stream()
                .map(this::setOneProduct).collect(Collectors.toList());
    }

    private ProductEntity setOneProduct(ProductEntity menuItem) {
        menuItem.setStore(this);
        return menuItem;
    }

    public void addProduct(ProductEntity product) {
        product.setStore(this);
        this.products.add(product);
    }

    public void removeProduct(ProductEntity product) {
        product.setStore(null);
        this.products.remove(product);
    }

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private AuthEntity auth;

    public void setAuth(AuthEntity auth) {
        auth.setStore(this);
        this.auth = auth;
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<PaymentEntity> payments = new ArrayList<>();

    public void setPayments(List<PaymentEntity> acceptedPaymentMethods) {
        this.payments = acceptedPaymentMethods.stream()
                .map(this::setOnePayment)
                .collect(Collectors.toList());
    }

    private PaymentEntity setOnePayment(PaymentEntity acceptedPaymentMethod) {
        acceptedPaymentMethod.setStore(this);
        return acceptedPaymentMethod;
    }

    public void addPayment(PaymentEntity payment) {
        payment.setStore(this);
        this.payments.add(payment);
    }

    public void removePayment(PaymentEntity payment) {
        payment.setStore(null);
        this.payments.remove(payment);
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<DeliveryEntity> deliveries = new ArrayList<>();

    public void setDeliveries(List<DeliveryEntity> deliveryLocations) {
        this.deliveries = deliveryLocations.stream()
                .map(this::setOneDelivery).collect(Collectors.toList());
    }

    private DeliveryEntity setOneDelivery(DeliveryEntity deliveryLocation) {
        deliveryLocation.setStore(this);
        return deliveryLocation;
    }

    public void addDelivery(DeliveryEntity delivery) {
        delivery.setStore(this);
        this.deliveries.add(delivery);
    }

    public void removeDelivery(DeliveryEntity delivery) {
        delivery.setStore(null);
        this.deliveries.remove(delivery);
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<ScheduleEntity> schedules;


    public void setSchedules(List<ScheduleEntity> workingHours) {
        this.schedules = workingHours.stream()
                .map(this::setOneSchedule)
                .collect(Collectors.toList());
    }

    private ScheduleEntity setOneSchedule(ScheduleEntity foodaStoreWorkingHours) {
        foodaStoreWorkingHours.setStore(this);
        return foodaStoreWorkingHours;
    }

    public void addSchedule(ScheduleEntity schedule) {
        schedule.setStore(this);
        this.schedules.add(schedule);
    }

    public void removeSchedule(ScheduleEntity schedule) {
        schedule.setStore(this);
        this.schedules.remove(schedule);
    }

}
