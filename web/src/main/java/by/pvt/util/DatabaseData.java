package by.pvt.util;

import by.pvt.VO.CarDTO;
import by.pvt.VO.CarSortingDTO;
import by.pvt.VO.OrderSortingDTO;
import by.pvt.constants.Constants;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.BodyType;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.pojo.StatusOfOrder;
import by.pvt.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Component("dataBase")
public class DatabaseData {
    private static final int PAGE_FOR_PAGINATION = 1;
    private static final int START_ROW = 0;
    private static final int ROWS_PER_PAGE = 100;
    @Autowired
    Sorting sorting;
    @Autowired
    Pagination pagination;
    @Autowired
    private Filter filter;
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
    private OrderService orderService;
    @Autowired
    private StatusOfOrderService statusOfOrderService;

    //the method get params for cars and put it to session
    public void setToSessionCarParams(HttpServletRequest request, Model model) {
        List brands;
        List<BodyType> bodyType;
        List engineType;
        List transmissionType;
        try {
            HttpSession session = request.getSession();
            brands = brandsService.getAll(START_ROW, ROWS_PER_PAGE);
            session.setAttribute(UIParams.REQUEST_ALL_BRANDS, brands);
            bodyType = bodyTypeService.getAll(START_ROW, ROWS_PER_PAGE);
            session.setAttribute(UIParams.REQUEST_ALL_BODY_TYPES, bodyType);
            engineType = engineTypeService.getAll(START_ROW, ROWS_PER_PAGE);
            session.setAttribute(UIParams.REQUEST_ALL_ENGINE_TYPES, engineType);
            transmissionType = transmissionTypeService.getAll(START_ROW, ROWS_PER_PAGE);
            session.setAttribute(UIParams.REQUEST_ALL_TRANSMISSION_TYPES, transmissionType);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.ERROR_NULL_LIST);
        }
    }

    //the method get cars by filter and sort params
    public void getCarsListByFilter(HttpServletRequest request, Model model) {
        CarDTO carDTO = filter.getCarFilter(request);
        //get pagination params
        int pagesCount = 0;
        try {
            pagesCount = (int) (carService.getCountCars(carDTO) / pagination.getItemPerPage(request));
        } catch (ServiceException e) {
            model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.ERROR_GET_COUNT);
        }
        pagesCount = pagination.getPagesCount(pagesCount);
        //put sorting params
        CarSortingDTO sortingDTO = sorting.getSortingParam(request);
        sortingDTO.setASC(sorting.getSorting(request));
        //get the resulting list after filtering and sorting
        try {
            List allCar = carService.getCarByFilter(carDTO, pagination.getStartRow(request) - PAGE_FOR_PAGINATION, pagination.getItemPerPage(request), sortingDTO);
            model.addAttribute(UIParams.REQUEST_GET_CARS, allCar);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.ERROR_GET_ALL_ORDERS);
        }
        request.setAttribute(Constants.TOTAL_PAGE, pagesCount);

    }

    public int getPagesCountForOrders(HttpServletRequest request, Model model, Client sessionClient) {
        //get pagination params
        int pagesCount = 0;
        try {
            pagesCount = (int) (orderService.getCountOrder(sessionClient) / pagination.getItemPerPage(request));
        } catch (ServiceException e) {
            model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.ERROR_GET_COUNT);
        }
        pagesCount = pagination.getPagesCount(pagesCount);
        //put sorting params
        OrderSortingDTO sortingDTO = sorting.getSortingParamOrder(request);
        sortingDTO.setASC(sorting.getSorting(request));
        //get the resulting list after filtering and sorting
        try {
            List<Order> allOrders = orderService.getOrdersByFilter(pagination.getStartRow(request) - PAGE_FOR_PAGINATION, pagination.getItemPerPage(request), sortingDTO);
            checkOrdersForActual(allOrders);
            request.setAttribute(UIParams.REQUEST_ORDERS, allOrders);
        } catch (ServiceException e) {
            model.addAttribute(UIParams.SERVICE_EXCEPTION, Message.ERROR_GET_ALL_ORDERS);
        }
        return pagesCount;
    }

    public void checkOrdersForActual(List<Order> allOrders) throws ServiceException {
        Date today = new Date();
        for (Order allOrder : allOrders) {
            if (allOrder.getEndDate().getTime() < today.getTime()) {
                allOrder.setOrderStatus(statusOfOrderService.get(StatusOfOrder.class, 5));
                orderService.update(allOrder);
            }
        }
    }

}
