package by.pvt.constants;

public class Constants {
    //model and request values
    public static final String CLIENT = "client";
    public static final String CLIENTDTO = "clientDTO";
    public static final String PASSWORD_ERROR = "password_error";
    public static final String EMAIL_ERROR = "email_error";
    public static final String DATE_ERROR = "date_error";
    public static final String PASSPORT_ERROR = "passport_error";
    public static final String ORDERS = "orders";
    public static final String REQUEST_PAGE = "requestPage";
    public static final String COMMAND = "command";
    public static final String ORDER = "order";
    public static final String TOTAL_PAGE = "totalPage";
    public static final String AMOUNT_FROM = "amountFrom";
    public static final String AMOUNT_TO = "amountTo";
    public static final String YEAR_FROM = "yearFrom";
    public static final String YEAR_TO = "yearTo";
    public static final String ORDER_EDIT = "orderEdit";
    public static final String FILTER_PARAM = "filter_param";
    public static final String LOGIN = "login";
    public static final String STATUS_OF_ORDER = "statusOfOrder";
    public static final String ORDER_DTO = "orderDTO";
    // client params
    public static final String CLIENT_EMAIL = "mail";
    public static final String CLIENT_PASSWORD = "password";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_SURNAME = "surname";
    public static final String CLIENT_PHONE = "phone";
    public static final String CLIENT_PASSPORT = "passport";
    public static final String ISSUE_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String CAR_ID_FOR_ORDER = "carId";
    public static final String MESSAGE_FOR_ORDER = "message_for_order";
    public static final String ORDER_ID = "orderId";
    public static final String BRAND_ID = "brandId";
    public static final String TRANSMISSION = "transmission";
    public static final String STATUS = "status";
    //filter params
    public static final String FILTER_BRAND = "filterBrand";
    public static final String FILTER_BODY = "filterBody";
    public static final String FILTER_ENGINE = "filterEngine";
    public static final String FILTER_TRANSMISSION = "filterTransmission";
    public static final String FILTER_AMOUNT_FROM = "filterAmountFrom";
    public static final String FILTER_AMOUNT_TO = "filterAmountTo";
    public static final String FILTER_YEAR_FROM = "filterYearFrom";
    public static final String FILTER_YEAR_TO = "filterYearTo";
    // auto param
    public static final String AUTO_MODEL = "model";
    public static final String CAR = "car";
    public static final String ID = "id";
    public static final String AUTO_BRAND = "brand";
    public static final String AUTO_BODY_TYPE = "body_type";
    public static final String AUTO_ENGINE_TYPE = "engine_type";
    public static final String AUTO_TRANSMISSION_TYPE = "transmission_type";
    public static final String AUTO_YEAR_MANUFACTURE = "year";
    public static final String AUTO_AMOUNT_PER_DAY = "amount";
    public static final String YEAR_OF_MANUFACTURE = "yearOfManufacture";
    public static final String ALL_CARS = "carAll";
    //page params
    public static final String PAGES = "pages";
    public static final String PER_PAGES = "perPages";

    //pagination
    public static final String PER_COUNT = "perCount";
    public static final String START_PAGE = "start";
    public static final String COUNTER = "counter";

    //sorting
    public static final String SORT_TYPE = "sort_type";
    public static final String SORT_NAME = "sort_name";
    public static final String MESSAGE = "message";

    //filters
    public static final String CAR_FILTER = "perPages=${perCount}&brand=${filterBrand.id}&body_type=${filterBody.id}&engine_type=${filterEngine.id}&transmission_type=${filterTransmission.id}&amountFrom=${filterAmountFrom}&amountTo=${filterAmountTo}&yearFrom=${filterYearFrom}&yearTo=${filterYearTo}&sort_type=${sort_type}&sort_name=${sort_name}";
}
