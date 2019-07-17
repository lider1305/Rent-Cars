package by.pvt.controller;

import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.constants.WebErrorMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Order;
import by.pvt.pojo.OrderStatus;
import by.pvt.service.impl.OrderService;
import by.pvt.service.impl.StatusOfOrderService;
import by.pvt.util.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.constants.Constants.ORDER;
import static by.pvt.constants.Constants.STATUS_OF_ORDER;
import static by.pvt.constants.Message.ERROR;
import static by.pvt.constants.Message.ERROR_500;
import static by.pvt.constants.Pages.*;

@Controller
public class AdminController {
    @Autowired
    OrderService orderService;
    @Autowired
    StatusOfOrderService statusOfOrderService;

    @RequestMapping(value = PROCESS_ORDER, method = RequestMethod.GET)
    public String processOrderGet(Model model, @RequestParam int orderId) {
        try {
            model.addAttribute(STATUS_OF_ORDER, statusOfOrderService.getAll());
            model.addAttribute(ORDER, orderService.get(Order.class, orderId));
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.MESSAGE_ERROR_GET_ORDERS, Message.ERROR_GET_ALL_ORDERS);
            return PAGE_PROCESS_ORDER;
        }
        return PAGE_PROCESS_ORDER;
    }

    @RequestMapping(value = PROCESS_ORDER, method = RequestMethod.POST)
    public String processOrderPost(Model model, @RequestParam int orderId, @RequestParam int statusId) {
        try {
            Order order = orderService.get(Order.class, orderId);
            order.setOrderStatus(statusOfOrderService.get(OrderStatus.class, statusId));
            orderService.update(order);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.MESSAGE_ERROR_GET_ORDERS, Message.ERROR_GET_ALL_ORDERS);
            return PAGE_PROCESS_ORDER;
        }
        return REDIRECT_ALL_ORDERS;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        SystemLogger.getInstance().setLogger(getClass(), (Throwable) request.getAttribute(ERROR));
        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return PAGE_ERROR;
    }
}
