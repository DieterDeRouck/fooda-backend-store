package be.fooda.backend.store.view.controller;


import be.fooda.backend.store.model.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate rest;

    //@Test
    void shouldCreate() throws URISyntaxException {

        //GIVEN
        final var baseUrl = "http://localhost:" + port;
        final var createUrl = baseUrl + "/api/v2/stores/create/one/";
        final var uri = new URI(createUrl);
        final var store = getCreateRequest();

        //WHEN
        final var headers = new HttpHeaders();
        final var request = new HttpEntity<>(store, headers);
        final var response = rest.exchange(uri, HttpMethod.POST, request, String.class);

        //THEN
        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals("Store is created based on the given information", response.getBody());

    }

    //@Test
    void shouldUpdateOnlyTitleByStoreId() throws URISyntaxException{

        //GIVEN
        final var baseUrl = "http://localhost:" + port;
        final var storeId = 1;
        final var createUrl = baseUrl + "/api/v2/stores/create/one/";
        final var createUri = new URI(createUrl);
        final var updateUrl = baseUrl + "/api/v2/stores/update/one/" + storeId;
        final var updateUri = new URI(updateUrl);
        final var storeToCreate = getCreateRequest();

        //WHEN
        final var createHeaders = new HttpHeaders();
        final var createRequest = new HttpEntity<>(storeToCreate, createHeaders);
        final var createResponse = rest.postForEntity(createUri, createRequest, String.class);

        //GIVEN
        final var storeIdToUpdate = Long.valueOf(createResponse.getHeaders().get("saved_id").get(0));
        final var storeToUpdate = getUpdateRequestForTitle("A Far Away Restaurant");

        //WHEN
        final var updateHeaders = new HttpHeaders();
        final var updateRequest = new HttpEntity<>(storeToUpdate, updateHeaders);
        final var updateResponse = rest.exchange(updateUri, HttpMethod.PUT, updateRequest, String.class);

        //THEN
        Assertions.assertEquals(202, updateResponse.getStatusCode());
        Assertions.assertEquals("Store successfully updated", updateResponse.getBody());

    }

    private CreateStoreRequest getCreateRequest() {

        final var store = new CreateStoreRequest();
        store.setETrackingId("125");
        store.setTitle("A Local Restaurant");
        store.setSlogan("A Normal Restaurant, but Local!");
        store.setType("Restaurant");
        store.setParentId(121L);
        store.setAbout("At this local restaurant, enjoy food made with local products.");
        store.setBgImageId(122L);
        store.setAddressId(123L);
        store.setContactId(124L);

        final var products = new ArrayList<Long>();
        products.add(201L);
        products.add(202L);
        products.add(203L);
        store.setProducts(products);

        final var auth = new CreateAuthRequest();
        auth.setAuthKey("Auth01");
        store.setAuth(auth);

        final var payment1 = new CreatePaymentRequest();
        payment1.setMethod("Paypal");
        store.addPayment(payment1);

        final var payment2 = new CreatePaymentRequest();
        payment2.setMethod("BanContact");
        store.addPayment(payment2);

        final var delivery = new CreateDeliveryRequest();
        delivery.setDeliveryDuration(0.6);
        store.addDelivery(delivery);

        final var schedule = new CreateScheduleRequest();
        schedule.setOpens("noon");
        schedule.setCloses("evening");
        store.addSchedule(schedule);

        return store;
    }

    private UpdateStoreRequest getUpdateRequestForTitle(String title) {

        final var store = new UpdateStoreRequest();
        store.setTitle(title);

        return store;
    }

}