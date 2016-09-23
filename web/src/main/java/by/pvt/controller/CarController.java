package by.pvt.controller;

import by.pvt.VO.CarAddDTO;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Car;
import by.pvt.service.impl.*;
import by.pvt.util.DatabaseData;
import by.pvt.util.DateFormatUtil;
import by.pvt.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

import static by.pvt.constants.Constants.*;
import static by.pvt.constants.Pages.*;
import static by.pvt.constants.UIParams.*;

@org.springframework.stereotype.Controller
public class CarController {
    @Autowired
    OrderController orderController;
    @Autowired
    DatabaseData databaseData;
    @Autowired
    private CarService carService;
    @Autowired
    Pagination pagination;

    @RequestMapping(value = VALUE_RENT_CAR, method = RequestMethod.GET)
    public String rentCar(HttpServletRequest request, Model model) {
        setFilterParams(request, model, PAGE_RENT_CAR);
        return PAGE_RENT_CAR;
    }

    @RequestMapping(value = VALUE_GET_ALL_CARS, method = RequestMethod.GET)
    public String getAllCars(HttpServletRequest request, Model model) {
        setFilterParams(request, model, PAGE_ALL_CARS);
        return PAGE_ALL_CARS;
    }

    @RequestMapping(value = VALUE_CHECK_CAR, method = RequestMethod.GET)
    public String checkCarForReserve(HttpServletRequest request, Model model) throws ServiceException {
        Car car;
        setFilterParams(request, model, PAGE_ALL_CARS);
        if (request.getParameter(CAR_ID_FOR_ORDER) == null) {
            model.addAttribute(REQUEST_WRONG_PARAM, Message.PARAM_NO_CHOSEN);
        } else {
            car = carService.get(Car.class, Integer.valueOf(request.getParameter(CAR_ID_FOR_ORDER)));
            // checks dates for null
            if (orderController.dateValidation(request, ISSUE_DATE)) return PAGE_ALL_CARS;
            if (orderController.dateValidation(request, END_DATE)) return PAGE_RENT_CAR;
            //get dates
            Date start = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(ISSUE_DATE));
            Date end = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(END_DATE));
            //checks dates for relevance
            if (orderController.checkDateOnActual(request, start)) return PAGE_ALL_CARS;
            if (orderController.checkEndDateOnActual(request, start, end)) return PAGE_ALL_CARS;
            if (orderController.checkCarForBooking(car, start, end, model)) {
                model.addAttribute(CAR_STATUS, car.getBrand().getBrandName() + " " + car.getModel());
            } else {
                model.addAttribute(CAR_STATUS_FREE, car.getBrand().getBrandName() + " " + car.getModel());
            }
        }
        return PAGE_ALL_CARS;
    }

    @RequestMapping(value = VALUE_GET_CARS_BY_FILTER, method = RequestMethod.GET)
    public String getCarsByFilterPost(HttpServletRequest request, Model model) {
        getCarsByDefaultFilter(request, model);
        return (String) request.getSession().getAttribute(REQUEST_PAGE);
    }

    @RequestMapping(value = VALUE_ADD_CAR, method = RequestMethod.GET)
    public String addCarGet(HttpServletRequest request, Model model, ModelMap modelMap) {
        databaseData.setToSessionCarParams(request, model);
        modelMap.put(ALL_CAR, new CarAddDTO());
        return PAGE_ADD_CAR;
    }

    @RequestMapping(value = VALUE_ADD_CAR, method = RequestMethod.POST)
    public String addCarPost(HttpServletRequest request, Model model, @Valid @ModelAttribute(ALL_CAR) CarAddDTO car, BindingResult result) {
        if (result.hasErrors()) {
            request.setAttribute(REQUEST_EXCEPTION_NULL_MODEL, Message.PARAM_NULL_MODEL);
            return PAGE_ADD_CAR;
        }
        try {
            carService.save(car);
            request.getSession().setAttribute(UIParams.REQUEST_SUCCESS_ADD_NEW_CAR, Message.SUCCESS_ADD_NEW_CAR);
        } catch (ServiceException e) {
            model.addAttribute(MESSAGE_ERROR_SAVE_CAR, Message.ERROR_SAVE_OBJECT);
        }
        return REDIRECT_PAGE_ADD_CAR;
    }

    private void setFilterParams(HttpServletRequest request, Model model, String path) {
        databaseData.setToSessionCarParams(request, model);
        pagination.getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, path);
        getCarsByDefaultFilter(request, model);
    }

    private void getCarsByDefaultFilter(HttpServletRequest request, Model model) {
        databaseData.getCarsListByFilter(request, model);
        request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
    }
}
