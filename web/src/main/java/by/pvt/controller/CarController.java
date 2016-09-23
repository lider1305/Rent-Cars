package by.pvt.controller;

import by.pvt.constants.Constants;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.*;
import by.pvt.service.impl.*;
import by.pvt.util.DatabaseData;
import by.pvt.util.DateFormatUtil;
import by.pvt.util.MessageManager;
import by.pvt.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;

import static by.pvt.constants.Constants.*;
import static by.pvt.constants.Pages.*;
import static by.pvt.constants.UIParams.MESSAGE_ERROR_SAVE_CAR;
import static java.lang.Integer.valueOf;

@org.springframework.stereotype.Controller
public class CarController {
    @Autowired
    OrderController orderController;
    @Autowired
    DatabaseData databaseData;
    @Autowired
    private BrandsService brandsService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private TransmissionTypeService transmissionTypeService;
    @Autowired
    private CarService carService;
    @Autowired
    private StatusOfCarService statusOfCarService;

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
            model.addAttribute(UIParams.REQUEST_WRONG_PARAM, MessageManager.getInstance().getValue(Message.PARAM_NO_CHOSEN, Locale.getDefault()));
        } else {
            car = carService.get(Car.class, Integer.valueOf(request.getParameter(CAR_ID_FOR_ORDER)));//TODO

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
                model.addAttribute("car_status", car.getBrand().getBrandName()+" "+car.getModel()+" Занят");
            } else {
                model.addAttribute("car_status", car.getBrand().getBrandName()+" "+car.getModel()+" Свободен");
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
    public String addCarGet(HttpServletRequest request, Model model) {
        databaseData.setToSessionCarParams(request, model);
        return PAGE_ADD_CAR;
    }

    @RequestMapping(value = VALUE_ADD_CAR, method = RequestMethod.POST)
    public String addCarPost(HttpServletRequest request, Model model) {
        Car newCar = new Car();
        try {
            newCar.setBrand(brandsService.get(Brands.class, valueOf(request.getParameter(Constants.AUTO_BRAND))));
            if (request.getParameter(Constants.AUTO_MODEL).length() != 0) {
                newCar.setModel(request.getParameter(Constants.AUTO_MODEL));
            } else {
                request.setAttribute(UIParams.REQUEST_EXCEPTION_NULL_MODEL,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_MODEL, Locale.getDefault()));
                return PAGE_ADD_CAR;
            }
            newCar.setBodyType(bodyTypeService.get(BodyType.class, valueOf(request.getParameter(Constants.AUTO_BODY_TYPE))));
            newCar.setEngineType(engineTypeService.get(EngineType.class, valueOf(request.getParameter(Constants.AUTO_ENGINE_TYPE))));
            newCar.setTransmissionType(transmissionTypeService.get(TransmissionType.class, valueOf(request.getParameter(Constants.AUTO_TRANSMISSION_TYPE))));
            newCar.setYearOfManufacture(valueOf(request.getParameter(Constants.AUTO_YEAR_MANUFACTURE)));
            newCar.setAmount(valueOf(request.getParameter(Constants.AUTO_AMOUNT_PER_DAY)));
            newCar.setStatus(statusOfCarService.get(StatusOfCar.class, 2));

            carService.save(newCar);
            request.getSession().setAttribute(UIParams.REQUEST_SUCCESS_ADD_NEW_CAR,
                    MessageManager.getInstance().getValue(Message.SUCCESS_ADD_NEW_CAR, Locale.getDefault()));
        } catch (ServiceException e) {
            model.addAttribute(MESSAGE_ERROR_SAVE_CAR, MessageManager.getInstance().getValue(Message.ERROR_SAVE_OBJECT, Locale.getDefault()));
        }
        return REDIRECT_PAGE_ADD_CAR;
    }

    private void setFilterParams(HttpServletRequest request, Model model, String path) {
        databaseData.setToSessionCarParams(request, model);
        Pagination.getInstance().getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, path);
        getCarsByDefaultFilter(request, model);
    }

    private void getCarsByDefaultFilter(HttpServletRequest request, Model model) {
        databaseData.getCarsListByFilter(request, model);
        request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
    }
}
