package be.fooda.backend.store.model.http;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpSuccessMessages {

    STORE_CREATED("Store is created based on the given information"),
    STORE_UPDATED("Store successfully updated"),
    SOME_MESSAGE(""),
    STORE_ADDED("storeAdded"),
    STORE_EXISTS("Store Exists"),
    MENU_ITEM_ADDED_IN_STORE("menu item added in store"),
    MENU_ITEM_DELETE_SUCCESFUL("could not delete menu item"),
    STORE_DELETED("store deleted"),
    STORE_MADE_PASSIVE("store made passive");
    private final String description;
}
