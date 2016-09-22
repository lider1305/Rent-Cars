package by.pvt.util;

import by.pvt.VO.CarSortingDTO;
import by.pvt.VO.OrderSortingDTO;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.constants.Constants.*;

/**
 * Class for getting sorting params from the user interface
 */
public class Sorting {
    private static Sorting instance;

    public Sorting() {
    }

    public static synchronized Sorting getInstance() {
        if (instance == null) {
            instance = new Sorting();
        }
        return instance;
    }

    //the method gets the sort order
    public boolean getSorting(HttpServletRequest request) {
        boolean flag = true;
        if (request.getParameter(SORT_TYPE) != null) {
            flag = Boolean.parseBoolean(request.getParameter(SORT_TYPE));
        }
        request.setAttribute(SORT_TYPE, flag);
        return flag;
    }

    //the method gets the parameters for sorting
    public CarSortingDTO getSortingParam(HttpServletRequest request) {
        CarSortingDTO sortingDTO = new CarSortingDTO();
        if (request.getParameter(SORT_NAME) != null) {
            String param = request.getParameter(SORT_NAME);
            if (param.equals(AUTO_BRAND)) {
                sortingDTO.setBrand(param);
            } else {
                sortingDTO.setBrand("");
            }
            if (param.equals(AUTO_BODY_TYPE)) {
                sortingDTO.setBodyType(param);
            } else {
                sortingDTO.setBodyType("");
            }
            if (param.equals(AUTO_ENGINE_TYPE)) {
                sortingDTO.setEngineType(param);
            } else {
                sortingDTO.setEngineType("");
            }
            if (param.equals(AUTO_TRANSMISSION_TYPE)) {
                sortingDTO.setTransmissionType(param);
            } else {
                sortingDTO.setTransmissionType("");
            }
            if (param.equals(AUTO_AMOUNT_PER_DAY)) {
                sortingDTO.setAmount(param);
            } else {
                sortingDTO.setAmount("");
            }
            if (param.equals(YEAR_OF_MANUFACTURE)) {
                sortingDTO.setYear(param);
            } else {
                sortingDTO.setYear("");
            }
            request.setAttribute(SORT_NAME, param);
        } else {
            sortingDTO.setBrand(AUTO_BRAND);
            request.setAttribute(SORT_NAME, AUTO_BRAND);
        }
        return sortingDTO;
    }

    //the method gets the parameters for sorting
    public OrderSortingDTO getSortingParamOrder(HttpServletRequest request) {
        OrderSortingDTO sortingDTO = new OrderSortingDTO();
        if (request.getParameter(SORT_NAME) != null) {
            String param = request.getParameter(SORT_NAME);
            if (param.equals(ISSUE_DATE)) {
                sortingDTO.setStartDate(param);
            } else {
                sortingDTO.setStartDate("");
            }
            if (param.equals(END_DATE)) {
                sortingDTO.setEndDate(param);
            } else {
                sortingDTO.setEndDate("");
            }
            if (param.equals(AUTO_AMOUNT_PER_DAY)) {
                sortingDTO.setAmount(param);
            } else {
                sortingDTO.setAmount("");
            }
            if (param.equals(STATUS)) {
                sortingDTO.setStatus(param);
            } else {
                sortingDTO.setStatus("");
            }
            request.setAttribute(SORT_NAME, param);
        } else {
            sortingDTO.setBrand(ID);
            request.setAttribute(SORT_NAME, ID);
        }
        return sortingDTO;
    }
}
