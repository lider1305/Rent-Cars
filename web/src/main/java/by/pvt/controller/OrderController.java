package by.pvt.controller;

import by.pvt.DTO.OrderDTO;
import by.pvt.constants.ConstantsValues;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.constants.WebErrorMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.service.impl.OrderService;
import by.pvt.util.DatabaseData;
import by.pvt.util.DateAndAmount;
import by.pvt.util.Pagination;
import by.pvt.util.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static by.pvt.constants.Constants.*;
import static by.pvt.constants.Message.ERROR;
import static by.pvt.constants.Message.ERROR_500;
import static by.pvt.constants.Pages.*;

@Controller
public class OrderController {
    private static final int PAGE_FOR_PAGINATION = 1;

    @Autowired
    private OrderService orderService;
    @Autowired
    private DatabaseData databaseData;
    @Autowired
    private Pagination pagination;

    @RequestMapping(value = VALUE_GO_TO_ORDERS, method = RequestMethod.GET)
    public String goToOrders(HttpServletRequest request, Model model) {
        pagination.getStartRow(request);
        Client sessionClient = (Client) request.getSession().getAttribute(CLIENT);
        //get client orders for UI
        int pagesCount = databaseData.getPagesCountForOrders(request, model, sessionClient);
        request.setAttribute(TOTAL_PAGE, pagesCount);
        request.getSession().setAttribute(COMMAND, VALUE_CLIENT_ORDERS);
        return PAGE_ALL_CLIENT_ORDERS;
    }

    @RequestMapping(value = VALUE_CLIENT_ORDERS, method = RequestMethod.GET)
    public String getClientOrders(HttpServletRequest request, Model model) {
        Client sessionClient = (Client) request.getSession().getAttribute(CLIENT);
        //get client orders for UI
        int pagesCount = databaseData.getPagesCountForOrders(request, model, sessionClient);
        model.addAttribute(ORDER_DTO,new OrderDTO());
        request.setAttribute(TOTAL_PAGE, pagesCount);
        request.getSession().setAttribute(COMMAND, VALUE_CLIENT_ORDERS);
        return PAGE_ALL_CLIENT_ORDERS;
    }


    @RequestMapping(value = VALUE_GET_ALL_ORDERS, method = RequestMethod.GET)
    public String getAllOrdersGet(HttpServletRequest request, Model model) {
        int pagesCount;
        pagination.getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, PAGE_ALL_ORDERS);
        try {
            pagesCount = (int) (orderService.getCountOfAllOrders() / pagination.getItemPerPage(request));
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(),e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
            return PAGE_ALL_ORDERS;
        }
        pagesCount = pagination.getPagesCount(pagesCount);
        request.setAttribute(TOTAL_PAGE, pagesCount);
        request.getSession().setAttribute(COMMAND, VALUE_GET_ALL_ORDERS);
        //get client orders for UI
        try {
            List<Order> orders = orderService.getAll(pagination.getStartRow(request) - PAGE_FOR_PAGINATION, pagination.getItemPerPage(request));
            databaseData.checkOrdersForActual(orders);
            request.setAttribute(UIParams.REQUEST_ALL_ORDERS_ADMIN, orders);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(),e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
            return PAGE_ALL_ORDERS;
        }
        return PAGE_ALL_ORDERS;
    }

    @RequestMapping(value = VALUE_MAKE_ORDER, method = RequestMethod.POST)
    public String makeOrder(@Valid @ModelAttribute(ORDER_DTO) OrderDTO order, BindingResult result, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            databaseData.getCarsListByFilter(request, model);
            request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
            return PAGE_RENT_CAR;
        }
        try {
            databaseData.getCarsListByFilter(request, model);
            request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
            if(DateAndAmount.checkDateOnActual(order.getStartDate())){
                model.addAttribute(UIParams.SERVICE_EXCEPTION,Message.PARAM_WRONG_DATE);
                return PAGE_RENT_CAR;
            }
            if(DateAndAmount.checkEndDateOnActual(order.getStartDate(),order.getEndDate())){
                model.addAttribute(UIParams.SERVICE_EXCEPTION,Message.PARAM_WRONG_DATE_END);
                return PAGE_RENT_CAR;
            }
            orderService.save(order);
            redirectAttributes.addFlashAttribute(UIParams.REQUEST_SUCCESS_MESSAGE, Message.SUCCESS_ORDER);
        } catch (ServiceException e) {
            databaseData.getCarsListByFilter(request, model);
            request.getSession().setAttribute(COMMAND, VALUE_GET_CARS_BY_FILTER);
            SystemLogger.getInstance().setLogger(getClass(),e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
            return PAGE_RENT_CAR;
        }
        return REDIRECT_PAGE_CLIENT;
    }


    @RequestMapping(value = VALUE_DELETE_ORDER, method = RequestMethod.GET)
    public String deleteOrder(@RequestParam int orderId, Model model) {
        Order order;
        try {
            order = orderService.get(Order.class, orderId);
            orderService.delete(order);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(),e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
        }
        return REDIRECT_ALL_CLIENT_ORDERS;
    }

    @RequestMapping(value = VALUE_EDIT_ORDER, method = RequestMethod.GET)
    public String editOrderGet(@RequestParam int orderId, HttpServletRequest request, Model model) {
        Order order;
        model.addAttribute(ORDER_DTO,new OrderDTO());
        try {
            databaseData.setToSessionCarParams(request,model);
            order = orderService.get(Order.class, orderId);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(),e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
            return PAGE_EDIT_ORDER;
        }
        request.getSession().setAttribute(REQUEST_PAGE, PAGE_EDIT_ORDER);
        request.getSession().setAttribute(ORDER_EDIT, order);
        return PAGE_EDIT_ORDER;
    }

    @RequestMapping(value = VALUE_EDIT_ORDER, method = RequestMethod.POST)
    public String editOrder(@Valid @ModelAttribute(ORDER_DTO) OrderDTO order, BindingResult result, HttpServletRequest request, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return PAGE_EDIT_ORDER;
        }
        //get params for sorting and filter
        databaseData.setToSessionCarParams(request, model);
        //set pagination params
        pagination.getStartRow(request);
        model.addAttribute(ORDER_DTO, new OrderDTO());
        try {
            orderService.update(order);
            redirectAttributes.addFlashAttribute(UIParams.REQUEST_SUCCESS_MESSAGE, Message.SUCCESS_ORDER_UPDATE);
            return REDIRECT_ALL_CLIENT_ORDERS;
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(),e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
            return PAGE_EDIT_ORDER;
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsValues.DATE_PATTERN);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        SystemLogger.getInstance().setLogger(getClass(), (Throwable) request.getAttribute(ERROR));
        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return PAGE_ERROR;
    }
}
