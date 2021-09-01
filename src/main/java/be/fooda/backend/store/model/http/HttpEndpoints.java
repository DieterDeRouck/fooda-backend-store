package be.fooda.backend.store.model.http;

public class HttpEndpoints {

    // PAGING DEFAULTS
    public static final String PAGE_NUMBER_TEXT = "pageNo";
    public static final String PAGE_SIZE_TEXT = "pageSize";
    public static final String PAGE_NUMBER_DEFAULT_VALUE = "1";
    public static final String PAGE_SIZE_DEFAULT_VALUE = "50";

    // STORE_ENDPOINTS
    public static final String STORE_FIND_ALL_TEXT = "find/all/";
    public static final String STORE_SEARCH_TEXT = "search/{keyword}";
    public static final String STORE_POST_SINGLE_TEXT = "create/one/";
    public static final String STORE_FIND_BY_ID_TEXT = "find/one/{storeId}";
    public static final String STORE_FIND_EXISTS_BY_ID_TEXT = "exists/one/{storeId}";
    public static final String STORE_EXISTS_BY_UNIQUE_FIELDS = "exists/one/unique/";
    public static final String STORE_PUT_SINGLE_TEXT = "update/one/{storeId}";
    public static final String STORE_DELETE_BY_ID = "delete/one/passive/{storeId}";
    public static final String STORE_DELETE_BY_ID_PERMANENTLY = "delete/one/permanent/{storeId}";
}
