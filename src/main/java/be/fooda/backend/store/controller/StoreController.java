package be.fooda.backend.store.controller;

import be.fooda.backend.store.client.*;
import be.fooda.backend.store.dao.ProductRepository;
import be.fooda.backend.store.dao.StoreRepository;
import be.fooda.backend.store.dao.StoreSearch;
import be.fooda.backend.store.model.create.ProductCreate;
import be.fooda.backend.store.model.create.StoreCreate;
import be.fooda.backend.store.model.entity.ProductEntity;
import be.fooda.backend.store.model.entity.StoreEntity;
import be.fooda.backend.store.model.http.HttpFailureMessages;
import be.fooda.backend.store.model.http.HttpSuccessMessages;
import be.fooda.backend.store.service.mapper.ProductMapper;
import be.fooda.backend.store.service.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class StoreController {

    private final StoreRepository storeRepository;
    private final ProductRepository menuItemRepository;
    private final StoreSearch storeSearch;

    private final StoreMapper storeMapper;
    private final ProductMapper productMapper;

    private final UserClient userClient;
    private final ProductClient productClient;
    private final MediaClient mediaClient;
    private final AddressClient addressClient;
    private final ContactClient contactClient;

    // Search -> indexRepository ... non-unique data ..
    // Filters -> storeRepository if input is -> range, or set, or list ... non-unique data .. postcode ..
    // Get -> storeRepository if input is unique or set, list of unique ids.. id, externalId, username, phone

    private static final String GET_ALL = "get_all_stores";
    private static final String SEARCH_BY_STORE_NAME = "search_by_store_name";
    private static final String SEARCH = "search";
    private static final String STORE_EXISTS_BY_ID = "store_exists_by_id";
    private static final String STORE_EXISTS = "store_exists";
    private static final String SEARCH_BY_ADDRESS = "search_by_address";
    private static final String FILTER_BY_DELIVERY_COSTS = "filter_by_delivery_costs";
    private static final String FILTER_BY_DELIVERY_LOCATIONS = "filter_by_delivery_locations";
    private static final String FILTER_BY_DELIVERY_DURATION = "filter_by_delivery_duration";
    private static final String SEARCH_BY_PRODUCT_NAME = "search_by_product_name";
    private static final String SEARCH_BY_CUISINE = "search_by_cuisine";
    private static final String GET_BY_MENU_ITEMS_PRICE = "get_by_menu_item_price";
    private static final String SEARCH_BY_DIETARY = "search_by_dietary";
    private static final String ADD_STORE = "add_store";
    private static final String ADD_PRODUCT = "add_product";
    private static final String REMOVE_PRODUCT = "remove_product";
    private static final String DELETE_STORE = "delete_store";
    private static final String SEARCH_BY_RANGE = "search_by_range";
    private static final String GET_PRODUCTS_BY_STORE_ID = "get_all_products_by_store_id";

    @GetMapping(GET_ALL)
    public ResponseEntity getAllStores(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize) {

        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        Page<StoreEntity> pageProducts = storeRepository.findAll(true, paging);

        if (!pageProducts.hasContent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);
        }

        List<StoreEntity> stores = pageProducts.getContent();
        return ResponseEntity.status(HttpStatus.FOUND).body(stores);

    }

    @GetMapping(SEARCH_BY_STORE_NAME)
    public ResponseEntity searchByStoreName(@RequestParam String name, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "25") int pageSize) {

        final List<StoreEntity> foundStores = storeSearch.searchByStoreName(name, pageNo, pageSize);
        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(GET_PRODUCTS_BY_STORE_ID)
    public ResponseEntity getAllMenuItemsByStore(@RequestParam UUID id) {
        final boolean storeExists = storeRepository.existsById(id);

        StoreEntity foundStore = storeRepository.findById(id).get();
        if (!storeExists)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);
        List<ProductEntity> menuItems = foundStore.getProducts();
        return ResponseEntity.status(HttpStatus.FOUND).body(menuItems);
    }


    @GetMapping(SEARCH)
    public ResponseEntity search(@RequestParam Set<String> keywords) {

        final List<StoreEntity> foundStores = storeSearch.searchCombined(keywords);

        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(SEARCH_BY_RANGE)
    public ResponseEntity searchByRange(@RequestParam Set<String> keywords) {

        final List<StoreEntity> foundStores = storeSearch.searchCombinedRange(keywords);

        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(STORE_EXISTS_BY_ID)
    public ResponseEntity storeExistsById(@RequestParam UUID storeId) {
        return storeRepository.existsById(storeId)
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.STORE_EXISTS)
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpFailureMessages.STORE_DOES_NOT_EXIST);

    }

    @PostMapping(STORE_EXISTS)
    public ResponseEntity storeExists(@RequestBody StoreCreate store) {
        Example<StoreEntity> example = Example.of(storeMapper.toEntity(store));
        return storeRepository.exists(example)
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.STORE_EXISTS)
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpFailureMessages.STORE_DOES_NOT_EXIST);
    }


    @GetMapping(SEARCH_BY_ADDRESS)
    public ResponseEntity searchByAddress(@RequestParam String postcode, @RequestParam(required = false) String municipality, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "25") int pageSize) {

        List<StoreEntity> stores = storeSearch.searchByAddress(postcode, municipality, pageNo, pageSize);

        if (stores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(stores);
    }


    @GetMapping(FILTER_BY_DELIVERY_COSTS)
    public ResponseEntity filterByDeliveryCosts(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize) {

        final List<StoreEntity> foundStores = storeSearch.filterByDeliveryCosts(minPrice, maxPrice, pageNo, pageSize);
        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(FILTER_BY_DELIVERY_DURATION)
    public ResponseEntity filterByDeliveryDuration(
            @RequestParam Double minDuration, @RequestParam Double maxDuration,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false) Integer pageSize) {
        final List<StoreEntity> foundStores = storeSearch.filterByDeliveryDuration(minDuration, maxDuration, pageNo, pageSize);
        if (!foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);
    }

    @GetMapping(FILTER_BY_DELIVERY_LOCATIONS)
    public ResponseEntity filterByDeliveryLocations(@RequestParam Set<String> postcode, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "25") int pageSize) {
        List<StoreEntity> foundStores = storeSearch.filterByDeliveryLocations(postcode, pageNo, pageSize);
        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(SEARCH_BY_PRODUCT_NAME)
    public ResponseEntity searchByMenuItemsName(@RequestParam String menuItem, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "25") Integer pageSize) {
        List<StoreEntity> foundStores = storeSearch.searchByMenuItemsName(menuItem, pageNo, pageSize);
        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(SEARCH_BY_CUISINE)
    public ResponseEntity searchByCuisine(@RequestParam String cuisine, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "25") Integer pageSize) {
        List<StoreEntity> foundStores = storeSearch.searchByCuisine(cuisine, pageNo, pageSize);
        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @GetMapping(GET_BY_MENU_ITEMS_PRICE)
    public ResponseEntity getByMenuItemsPrice(@RequestParam BigDecimal maxPrice) {
        List<ProductEntity> foundProducts = menuItemRepository.findByProductPrice(maxPrice);
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_MENU_ITEM_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping(SEARCH_BY_DIETARY)
    public ResponseEntity searchByDietary(@RequestParam String dietary, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "25") Integer pageSize) {
        List<StoreEntity> foundStores = storeSearch.searchByDietary(dietary, pageNo, pageSize);
        if (foundStores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.NO_STORES_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStores);
    }

    @PostMapping(ADD_STORE)
    public ResponseEntity addStore(@RequestBody @Valid StoreCreate storeCreate) {

        if (storeRepository.existByUniqueFields(storeCreate.getName(), storeCreate.getAddress().getEAddressId(), storeCreate.getAuth().getAuthKey())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.STORE_ALREADY_EXIST);
        }

        if (!addressClient.existsById(storeCreate.getAddress().getEAddressId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.ADDRESS_DOES_NOT_EXIST);
        }

        if (!contactClient.existsById(storeCreate.getContact().getEContactId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.CONTACT_DOES_NOT_EXIST);
        }

        if (!productClient.existsByIdSet(storeCreate.getProducts().stream().map(ProductCreate::getEProductId).collect(Collectors.toSet()))) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.MISSING_PRODUCT_SET);
        }

        if (!mediaClient.existsById(storeCreate.getBgImage().getEMediaId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.MISSING_MEDIA_SET);


        final StoreEntity storeEntity = storeMapper.toEntity(storeCreate);
        storeRepository.save(storeEntity);
        return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.STORE_ADDED);
    }


    @PatchMapping(ADD_PRODUCT)
    public ResponseEntity addMenuItem(@RequestParam UUID storeId, @RequestBody ProductCreate menuItem) {
        final Optional<StoreEntity> store = storeRepository.findById(storeId);

        if (!store.isPresent()) {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpFailureMessages.CAN_NOT_ADD_MENU_ITEM_AS_STORE_DOES_NOT_EXIST);
        }

        if (!productClient.existsByIdSet(store.get().getProducts().stream().map(ProductEntity::getEProductId).collect(Collectors.toSet()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.MISSING_PRODUCT_SET);
        }
        ProductEntity menuItemEntityToBeSaved = productMapper.toEntity(menuItem);
        store.get().setProducts(Arrays.asList(menuItemEntityToBeSaved));
        menuItemEntityToBeSaved.setStore(store.get());
        //.setExternalProductId(menuItemEntityToBeSaved.getExternalProductId());

        menuItemRepository.save(menuItemEntityToBeSaved);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpSuccessMessages.MENU_ITEM_ADDED_IN_STORE);
    }

    @DeleteMapping(REMOVE_PRODUCT)
    public ResponseEntity removeMenuItem(@RequestParam UUID menuItemId) {
        menuItemRepository.deleteById(menuItemId);

        boolean isDeleted = !menuItemRepository.existsById(menuItemId);
        return isDeleted
                ? ResponseEntity.status(HttpStatus.GONE).body(HttpSuccessMessages.MENU_ITEM_DELETE_SUCCESFUL)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.MENU_ITEM_CAN_NOT_BE_DELETED);
    }

    @DeleteMapping(DELETE_STORE)
    public ResponseEntity deleteStoreById(@RequestParam UUID id) {
        Optional<StoreEntity> foundStore = storeRepository.findById(id);

        StoreEntity storeBeingDeleted = foundStore.get();

        storeBeingDeleted.setIsActive(Boolean.FALSE);
        storeRepository.save(storeBeingDeleted);

        return storeRepository.existByIsActive(id, false)
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.STORE_DELETED)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(HttpFailureMessages.STORE_COULD_NOT_BE_DELETED);
    }

}
