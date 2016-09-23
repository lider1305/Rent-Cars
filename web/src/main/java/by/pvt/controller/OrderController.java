package by.pvt.controller;

import by.pvt.VO.OrderSortingDTO;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Car;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.pojo.StatusOfOrder;
import by.pvt.service.impl.CarService;
import by.pvt.service.impl.OrderService;
import by.pvt.service.impl.StatusOfOrderService;
import by.pvt.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static by.pvt.constants.Constants.*;
import static by.pvt.constants.Pages.*;

@org.springframework.stereotype.Controller
public class OrderController {
    private static final int HOURS = 24;//TODO refactoring code
    private static final int MINUTES = 60;
    private static final int SECONDS = 60;
    private static final int MILLISECONDS = 1000;
    private static final int PAGE_FOR_PAGINATION = 1;

    @Autowired
    private OrderService orderService;
    @Autowired
    private CarService carService;
    @Autowired
    private StatusOfOrderService statusOfOrderService;
    @Autowired
    private DatabaseData databaseData;
    @Autowired
    private Pagination pagination;
    @Autowired
    private Sorting sorting;

    @RequestMapping(value = VALUE_GO_TO_ORDERS, method = RequestMethod.GET)
    public String goToOrders(HttpServletRequest request,Model model) {
        pagination.getStartRow(request);
        Client sessionClient = (Client) request.getSession().getAttribute(CLIENT);
        //get client orders for UI
        int pagesCount = getPagesCountForOrders(request, model, sessionClient);
        request.setAttribute(TOTAL_PAGE, pagesCount);
        request.getSession().setAttribute(COMMAND, VALUE_CLIENT_ORDERS);
        return PAGE_ALL_CLIENT_ORDERS;
    }

    @RequestMapping(value = VALUE_CLIENT_ORDERS, method = RequestMethod.GET)
    public String getClientOrders(HttpServletRequest request, Model model) {
        Client sessionClient = (Client) request.getSession().getAttribute(CLIENT);
        //get client orders for UI
        int pagesCount = getPagesCountForOrders(request, model, sessionClient);
        request.setAttribute(TOTAL_PAGE, pagesCount);
        request.getSession().setAttribute(COMMAND, VALUE_CLIENT_ORDERS);
        return PAGE_ALL_CLIENT_ORDERS;
    }


    @RequestMapping(value = VALUE_GET_ALL_ORDERS, method = RequestMethod.GET)
    public String getAllOrdersGet(HttpServletRequest request,Model model) {
        pagination.getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, PAGE_ALL_ORDERS);
        int pagesCount = 0;
        try {
            pagesCount = (int) (orderService.getCountOfAllOrders() / pagination.getItemPerPage(request));
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_GET_COUNT,MessageManager.getInstance().getValue(Message.ERROR_GET_COUNT, Locale.getDefault()));
        }
        pagesCount = pagination.getPagesCount(pagesCount);
        request.setAttribute(TOTAL_PAGE, pagesCount);
        request.getSession().setAttribute(COMMAND, VALUE_GET_ALL_ORDERS);
        //get client orders for UI
        try {
            List<Order> orders = orderService.getAll(pagination.getStartRow(request) - PAGE_FOR_PAGINATION, pagination.getItemPerPage(request));
            request.setAttribute(UIParams.REQUEST_ALL_ORDERS_ADMIN, orders);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_ERROR_GET_ORDERS,MessageManager.getInstance().getValue(Message.ERROR_GET_ALL_ORDERS, Locale.getDefault()));
        }
        return PAGE_ALL_ORDERS;
    }

    @RequestMapping(value = VALUE_MAKE_ORDER, method = RequestMethod.POST)
    public String makeOrder(HttpServletRequest request, Model model) {
        Order order = new Order();
        //check was the car select
        if (request.getParameter(CAR_ID_FOR_ORDER) != null) {
            try {
                //get selected car
                Car car = carService.get(Car.class, Integer.valueOf(request.getParameter(CAR_ID_FOR_ORDER)));
                // checks dates for null
                if (dateValidation(request, ISSUE_DATE)) return PAGE_RENT_CAR;
                if (dateValidation(request, END_DATE)) return PAGE_RENT_CAR;
                //get dates
                Date start = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(ISSUE_DATE));
                Date end = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(END_DATE));
                //checks dates for relevance
                if (checkDateOnActual(request, start)) return PAGE_RENT_CAR;
                if (checkEndDateOnActual(request, start, end)) return PAGE_RENT_CAR;
                //check was this car booking for chosen dates
                if (checkCarForBooking(car, start, end, model)) return PAGE_RENT_CAR;
                //set params to order
                setParamsToOrder(request, order, car, start, end, model);

                orderService.save(order);
                request.getSession().setAttribute(UIParams.REQUEST_SUCCESS_MESSAGE, MessageManager.getInstance().getValue(Message.SUCCESS_ORDER, Locale.getDefault()));
            } catch (ServiceException e) {
                model.addAttribute(UIParams.MESSAGE_ERROR_SAVE_ORDER, MessageManager.getInstance().getValue(Message.ERROR_SAVE_OBJECT, Locale.getDefault()));
                return PAGE_RENT_CAR;
            }
            return REDIRECT_PAGE_CLIENT;
        }
        request.setAttribute(UIParams.REQUEST_WRONG_PARAM, MessageManager.getInstance().getValue(Message.PARAM_NO_CHOSEN, Locale.getDefault()));
        return PAGE_RENT_CAR;
    }


    @RequestMapping(value = VALUE_DELETE_ORDER, method = RequestMethod.GET)
    public String deleteOrder(@RequestParam int orderId, Model model) {
        Order order;
        try {
            order = orderService.get(Order.class, orderId);
            orderService.delete(order);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_ERROR_DELETE, MessageManager.getInstance().getValue(Message.ERROR_DELETE_OBJECT, Locale.getDefault()));
        }

        return REDIRECT_ALL_CLIENT_ORDERS;
    }

    @RequestMapping(value = VALUE_EDIT_ORDER, method = RequestMethod.GET)
    public String editOrderGet(@RequestParam int orderId, HttpServletRequest request, Model model) {
        Order order = null;
        try {
            order = orderService.get(Order.class, orderId);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_ERROR_GET, MessageManager.getInstance().getValue(Message.ERROR_DELETE_OBJECT, Locale.getDefault()));
        }
        //get params for sorting and filter
        databaseData.setToSessionCarParams(request, model);
        //set pagination params
        pagination.getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, PAGE_EDIT_ORDER);
        request.getSession().setAttribute(ORDER_EDIT, order);
        return PAGE_EDIT_ORDER;
    }

    @RequestMapping(value = VALUE_EDIT_ORDER, method = RequestMethod.POST)
    public String editOrderPost(@RequestParam int orderId, HttpServletRequest request, Model model) {
        Order order;
        try {
            //check was the car select
            if (request.getParameter(CAR_ID_FOR_ORDER) != null) {
                //get selected car
                Car car = carService.get(Car.class, Integer.valueOf(request.getParameter(CAR_ID_FOR_ORDER)));
                // get selected order
                order = orderService.get(Order.class, orderId);
                // checks dates for null
                if (dateValidation(request, ISSUE_DATE)) return PAGE_EDIT_ORDER;
                if (dateValidation(request, END_DATE)) return PAGE_EDIT_ORDER;
                //get dates
                Date start = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(ISSUE_DATE));
                Date end = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(END_DATE));
                //check dates for invalidate
                if (checkDateOnActual(request, start)) return PAGE_EDIT_ORDER;
                if (checkEndDateOnActual(request, start, end)) return PAGE_EDIT_ORDER;
                //check car for booking
                if (checkCarForBooking(car, start, end, model)) return PAGE_EDIT_ORDER;
                setParamsToOrder(request, order, car, start, end, model);
                orderService.update(order);
                request.setAttribute(UIParams.REQUEST_SUCCESS_MESSAGE, MessageManager.getInstance().getValue(Message.SUCCESS_ORDER, Locale.getDefault()));
                return REDIRECT_ALL_CLIENT_ORDERS;
            } else {
                order = orderService.get(Order.class, orderId);
                Date start = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(ISSUE_DATE));
                Date end = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(END_DATE));
                if (checkDateOnActual(request, start)) return PAGE_EDIT_ORDER;
                if (checkEndDateOnActual(request, start, end)) return PAGE_EDIT_ORDER;
                //get car from edit order
                Car car = order.getCar();
                if(start.getTime()!=order.getStartDate().getTime() | end.getTime()!=order.getEndDate().getTime()){
                    if (checkCarForBooking(car, start, end, model)) return PAGE_EDIT_ORDER;
                }
                setParamsToOrder(request, order, car, start, end, model);
                orderService.update(order);
                request.setAttribute(UIParams.REQUEST_SUCCESS_MESSAGE, MessageManager.getInstance().getValue(Message.SUCCESS_ORDER, Locale.getDefault()));
                return REDIRECT_ALL_CLIENT_ORDERS;
            }
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_ERROR_UPDATE, MessageManager.getInstance().getValue(Message.SUCCESS_ORDER, Locale.getDefault()));
        }
        return PAGE_ALL_CLIENT_ORDERS;
    }

    //the method checks whether the machine is reserved on this date
    public boolean checkCarForBooking(Car car, Date start, Date end, Model model) {
        List<Car> rentCar = null;
        try {
            rentCar = orderService.getAllRentCarForDate(start, end);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_GET_LIST_CARS,MessageManager.getInstance().getValue(Message.ERROR_GET_ALL_ORDERS, Locale.getDefault()));
        }
        for (Car aRentCar : rentCar) {
            if (car.getId() == aRentCar.getId()) {
                model.addAttribute(UIParams.REQUEST_CAR_IS_RENT, MessageManager.getInstance().getValue(Message.PARAM_CAR_IS_RENT, Locale.getDefault()));
                return true;
            }
        }
        return false;
    }

    //the method calculates the total cost of the order
    private long countTotalCostOfOrder(Car car, Date start, Date end) {
        long times = end.getTime() - start.getTime();
        int days = (int) times / (HOURS * MINUTES * SECONDS * MILLISECONDS);
        long amount = (long) (days * car.getAmount());
        if (amount < car.getAmount()) {
            amount = (long) car.getAmount();
        }
        return amount;
    }

    //the method checks that the end date less than start date
    public boolean checkEndDateOnActual(HttpServletRequest request, Date start, Date end) {
        if (end.getTime() < start.getTime()) {
            request.setAttribute(UIParams.REQUEST_EXCEPTION_WRONG_DATE_END,
                    MessageManager.getInstance().getValue(Message.PARAM_WRONG_DATE_END, Locale.getDefault()));
            return true;
        }
        return false;
    }

    //the method checks the date on the relevance
    public boolean checkDateOnActual(HttpServletRequest request, Date start) {
        Date today = new Date();
        if (start.getTime() < today.getTime()) {
            request.setAttribute(UIParams.REQUEST_EXCEPTION_WRONG_DATE,
                    MessageManager.getInstance().getValue(Message.PARAM_WRONG_DATE, Locale.getDefault()));
            return true;
        }
        return false;
    }

    //the method checks the entered date to an empty string
    public boolean dateValidation(HttpServletRequest request, String requestParam) {
        if (request.getParameter(requestParam).length() == 0) {
            request.setAttribute(UIParams.REQUEST_EXCEPTION_NULL_DATE,
                    MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
            return true;
        }
        return false;
    }

    //the method set some params to order
    private void setParamsToOrder(HttpServletRequest request, Order order, Car car, Date start, Date end, Model model) {
        //Get client
        Client sessionClient = (Client) request.getSession().getAttribute(CLIENT);
        order.setClient(sessionClient);
        order.setCar(car);
        order.setStartDate(start);
        order.setEndDate(end);
        order.setMessage(request.getParameter(MESSAGE_FOR_ORDER));
        try {
            order.setOrderStatus(statusOfOrderService.get(StatusOfOrder.class, 1));
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_ERROR_GET_STATUS, MessageManager.getInstance().getValue(Message.ERROR_GET_STATUS, Locale.getDefault()));
        }
        //count total order price
        long amount = countTotalCostOfOrder(car, start, end);
        order.setAmount(amount);
    }

    private int getPagesCountForOrders(HttpServletRequest request, Model model, Client sessionClient) {
        //get pagination params
        int pagesCount = 0;
        try {
            pagesCount = (int) (orderService.getCountOrder(sessionClient) / pagination.getItemPerPage(request));
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_GET_COUNT,MessageManager.getInstance().getValue(Message.ERROR_GET_COUNT, Locale.getDefault()));
        }
        pagesCount = pagination.getPagesCount(pagesCount);
        //put sorting params
        OrderSortingDTO sortingDTO = sorting.getSortingParamOrder(request);
        sortingDTO.setASC(sorting.getSorting(request));
        //get the resulting list after filtering and sorting
        try {
            List<Order> allOrders = orderService.getOrdersByFilter(pagination.getStartRow(request) - PAGE_FOR_PAGINATION, pagination.getItemPerPage(request), sortingDTO);
            Date today = new Date();
            for (Order allOrder : allOrders) {
                if (allOrder.getEndDate().getTime() < today.getTime()) {
                    allOrder.setOrderStatus(statusOfOrderService.get(StatusOfOrder.class, 5));
                    orderService.update(allOrder);
                }
            }
            request.setAttribute(UIParams.REQUEST_ORDERS , allOrders);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.MESSAGE_GET_LIST_CARS,MessageManager.getInstance().getValue(Message.ERROR_GET_ALL_ORDERS, Locale.getDefault()));
        }
        return pagesCount;
    }
}
