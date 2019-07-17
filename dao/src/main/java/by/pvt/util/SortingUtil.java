package by.pvt.util;

import by.pvt.DTO.CarSortingDTO;
import by.pvt.DTO.OrderSortingDTO;
import by.pvt.constants.ConstantsValues;
import org.apache.commons.lang3.StringUtils;

public class SortingUtil {
    private static SortingUtil instance;

    private SortingUtil() {
    }

    public static synchronized SortingUtil getInstance() {
        if (instance == null) {
            instance = new SortingUtil();
        }
        return instance;
    }

    public String carSorting(CarSortingDTO sort) {
        String sorting = ConstantsValues.BRAND;
        if (StringUtils.isNotBlank(sort.getBrand())) {
            sorting = sort.getBrand();
        }
        if (StringUtils.isNotBlank(sort.getEngineType())) {
            sorting = sort.getBodyType();
        }
        if (StringUtils.isNotBlank(sort.getEngineType())) {
            sorting = sort.getEngineType();
        }
        if (StringUtils.isNotBlank(sort.getTransmissionType())) {
            sorting = sort.getTransmissionType();
        }
        if (StringUtils.isNotBlank(sort.getAmount())) {
            sorting = sort.getAmount();
        }
        if (StringUtils.isNotBlank(sort.getYear())) {
            sorting = sort.getYear();
        }
        return sorting;
    }

    public String orderSorting(OrderSortingDTO sort) {
        String sorting = ConstantsValues.ID;
        if (sort.getId() != 0) {
            sorting = String.valueOf(sort.getId());
        }
        if (StringUtils.isNotBlank(sort.getStartDate())) {
            sorting = sort.getStartDate();
        }
        if (StringUtils.isNotBlank(sort.getEndDate())) {
            sorting = sort.getEndDate();
        }
        if (StringUtils.isNotBlank(sort.getAmount())) {
            sorting = sort.getAmount();
        }
        if (StringUtils.isNotBlank(sort.getStatus())) {
            sorting = sort.getStatus();
        }
        return sorting;
    }
}
