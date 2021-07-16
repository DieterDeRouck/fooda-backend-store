package be.fooda.backend.store.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpSuccessMassages {

    SOME_MESSAGE(""),
    STORE_ADDED("storeAdded"),
    STORE_EXISTS("Store Exists"),
   MENU_ITEM_ADDED_IN_STORE("menu item added in store"),
     MENU_ITEM_DELETE_SUCCESFUL("could not delete menu item") ,
    STORE_DELETED("store deleted");
    private final String description;
}
