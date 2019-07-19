package by.pvt.controller;

import by.pvt.DTO.CarAddDTO;
import by.pvt.DTO.OrderDTO;
import by.pvt.constants.ConstantsValues;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Car;
import by.pvt.service.impl.CarService;
import by.pvt.service.impl.OrderService;
import by.pvt.util.DatabaseData;
import by.pvt.util.DateAndAmount;
import by.pvt.util.Pagination;
import by.pvt.util.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.pvt.constants.Constants.*;
import static by.pvt.constants.Pages.*;
import static by.pvt.constants.UIParams.*;

@Controller
public class CarController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DatabaseData databaseData;
    @Autowired
    private CarService carService;
    @Autowired
    private Pagination pagination;

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
    public String checkCarForReserve(@Valid @ModelAttribute(ORDER_DTO) OrderDTO orderDTO, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            databaseData.getCarsListByFilter(request, model);
            request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
            return PAGE_ALL_CARS;
        }
        try {
            databaseData.getCarsListByFilter(request, model);
            request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
            if (orderDTO.getCarId() == 0) {
                model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.PARAM_NO_CHOSEN);
                return PAGE_ALL_CARS;
            }
            Car car = carService.get(Car.class, orderDTO.getCarId());
            //checks dates for relevance
            if (DateAndAmount.checkDateOnActual(orderDTO.getStartDate())) {
                databaseData.getCarsListByFilter(request, model);
                request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
                model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.PARAM_WRONG_DATE);
                return PAGE_ALL_CARS;
            }
            if (DateAndAmount.checkEndDateOnActual(orderDTO.getStartDate(), orderDTO.getEndDate())) {
                databaseData.getCarsListByFilter(request, model);
                request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
                model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.PARAM_WRONG_DATE_END);
                return PAGE_ALL_CARS;
            }
            if (orderService.isCarReserved(car, orderDTO.getStartDate(), orderDTO.getEndDate())) {
                model.addAttribute(CAR_STATUS, Message.RESERVED);
            } else {
                model.addAttribute(CAR_STATUS, Message.FREE);
                return PAGE_ALL_CARS;
            }
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
            return PAGE_ALL_CARS;
        }
        return PAGE_ALL_CARS;
    }

    @RequestMapping(value = VALUE_GET_CARS_BY_FILTER, method = RequestMethod.GET)
    public String getCarsByFilter(HttpServletRequest request, Model model) {
        getCarsByDefaultFilter(request, model);
        model.addAttribute(ORDER_DTO, new OrderDTO());
        return (String) request.getSession().getAttribute(REQUEST_PAGE);
    }

    @RequestMapping(value = VALUE_ADD_CAR, method = RequestMethod.GET)
    public String addCarGet(HttpServletRequest request, Model model, ModelMap modelMap) {
        databaseData.setToSessionCarParams(request, model);
        modelMap.put(ALL_CARS, new CarAddDTO());
        return PAGE_ADD_CAR;
    }

    @RequestMapping(value = VALUE_ADD_CAR, method = RequestMethod.POST)
    public String addCarPost(HttpServletRequest request, Model model, @Valid @ModelAttribute(ALL_CARS) CarAddDTO car, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            request.setAttribute(REQUEST_EXCEPTION_NULL_MODEL, Message.PARAM_NULL_MODEL);
            return PAGE_ADD_CAR;
        }
        try {
            carService.save(car);
            redirectAttributes.addFlashAttribute(UIParams.REQUEST_SUCCESS_ADD_NEW_CAR, Message.SUCCESS_ADD_NEW_CAR);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(SERVICE_EXCEPTION, Message.ERROR_SAVE_OBJECT);
            return PAGE_ADD_CAR;
        }
        return REDIRECT_PAGE_ADD_CAR;
    }

    public void setFilterParams(HttpServletRequest request, Model model, String path) {
        databaseData.setToSessionCarParams(request, model);
        pagination.getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, path);
        model.addAttribute(ORDER_DTO, new OrderDTO());
        getCarsByDefaultFilter(request, model);
    }

    private void getCarsByDefaultFilter(HttpServletRequest request, Model model) {
        databaseData.getCarsListByFilter(request, model);
        request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsValues.DATE_PATTERN);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
