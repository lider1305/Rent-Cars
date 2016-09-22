package by.pvt.util;

import by.pvt.VO.CarSortingDTO;
import by.pvt.VO.OrderSortingDTO;
import by.pvt.constants.ConstantsValues;

public class SortingUtil {
    private static SortingUtil instance;

    private SortingUtil() {
    }

    public static synchronized SortingUtil getInstance(){
        if (instance == null){
            instance = new SortingUtil();
        }
        return instance;
    }

    public String carSorting(CarSortingDTO sort){
        String sorting = ConstantsValues.BRAND;
        if(sort.getBrand().length() !=0){
            sorting =sort.getBrand();
        }
        if(sort.getEngineType().length() !=0){
            sorting = sort.getBodyType();
        }
        if(sort.getEngineType().length() !=0){
            sorting = sort.getEngineType();
        }
        if(sort.getTransmissionType().length() !=0){
            sorting =sort.getTransmissionType();
        }
        if(sort.getAmount().length() !=0){
            sorting =sort.getAmount();
        }
        if(sort.getYear().length() !=0){
            sorting =sort.getYear();
        }
        return sorting;
    }

    public String orderSorting(OrderSortingDTO sort){
        String sorting = ConstantsValues.ID;
        if(sort.getId() !=0){
            sorting = String.valueOf(sort.getId());
        }
        if(sort.getStartDate().length() !=0){
            sorting = sort.getStartDate();
        }
        if(sort.getEndDate().length() !=0){
            sorting =sort.getEndDate();
        }
        if(sort.getAmount().length() !=0){
            sorting =sort.getAmount();
        }
        if(sort.getStatus().length() !=0){
            sorting =sort.getStatus();
        }
        return sorting;
    }
}
