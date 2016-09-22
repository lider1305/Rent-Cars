package by.pvt.util;

import by.pvt.VO.CarDTO;
import by.pvt.constants.Constants;
import by.pvt.constants.UIParams;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.BodyType;
import by.pvt.pojo.Brands;
import by.pvt.pojo.EngineType;
import by.pvt.pojo.TransmissionType;
import by.pvt.service.impl.BodyTypeService;
import by.pvt.service.impl.BrandsService;
import by.pvt.service.impl.EngineTypeService;
import by.pvt.service.impl.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.constants.Constants.*;
import static java.lang.Integer.valueOf;

/**
 * Class for getting filters params from user interface
 */
@Component("filter")
public class Filter {
    @Autowired
    private BrandsService brandsService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private TransmissionTypeService transmissionTypeService;

    //the method checks the input parameters to the filter, to send a carDTO object
    public CarDTO getCarFilter(HttpServletRequest request) {
        CarDTO carDTO = new CarDTO();
        try {
            if (request.getParameter(Constants.AUTO_BRAND).length() != 0) {
                carDTO.setBrand(brandsService.get(Brands.class, valueOf(request.getParameter(Constants.AUTO_BRAND))));
                request.setAttribute(FILTER_BRAND, carDTO.getBrand());
            } else {
                carDTO.setBrand(null);
            }
            if (request.getParameter(Constants.AUTO_BODY_TYPE).length() != 0) {
                carDTO.setBodyType(bodyTypeService.get(BodyType.class, valueOf(request.getParameter(Constants.AUTO_BODY_TYPE))));
                request.setAttribute(FILTER_BODY, carDTO.getBodyType());
            } else {
                carDTO.setBodyType(null);
            }
            if (request.getParameter(Constants.AUTO_ENGINE_TYPE).length() != 0) {
                carDTO.setEngineType(engineTypeService.get(EngineType.class, valueOf(request.getParameter(Constants.AUTO_ENGINE_TYPE))));
                request.setAttribute(FILTER_ENGINE, carDTO.getEngineType());
            } else {
                carDTO.setEngineType(null);
            }
            if (request.getParameter(Constants.AUTO_TRANSMISSION_TYPE).length() != 0) {
                carDTO.setTransmissionType(transmissionTypeService.get(TransmissionType.class, valueOf(request.getParameter(Constants.AUTO_TRANSMISSION_TYPE))));
                request.setAttribute(FILTER_TRANSMISSION, carDTO.getTransmissionType());
            } else {
                carDTO.setTransmissionType(null);
            }
            if (request.getParameter(AMOUNT_FROM).length() != 0) {
                carDTO.setAmountFrom(valueOf(request.getParameter(AMOUNT_FROM)));
                request.setAttribute(FILTER_AMOUNT_FROM, carDTO.getAmountFrom());
            } else {
                carDTO.setAmountFrom(0);
            }
            if (request.getParameter(AMOUNT_TO).length() != 0) {
                carDTO.setAmountTo(valueOf(request.getParameter(AMOUNT_TO)));
                request.setAttribute(FILTER_AMOUNT_TO, carDTO.getAmountTo());
            } else {
                carDTO.setAmountTo(0);
            }
            if (request.getParameter(YEAR_FROM).length() != 0) {
                carDTO.setYearFrom(valueOf(request.getParameter(YEAR_FROM)));
                request.setAttribute(FILTER_YEAR_FROM, carDTO.getYearFrom());
            } else {
                carDTO.setYearFrom(0);
            }
            if (request.getParameter(YEAR_TO).length() != 0) {
                carDTO.setYearTo(valueOf(request.getParameter(YEAR_TO)));
                request.setAttribute(FILTER_YEAR_TO, carDTO.getYearTo());
            } else {
                carDTO.setYearTo(0);
            }
        } catch (ServiceException e) {
            request.setAttribute(UIParams.REQUEST_EXCEPTION_DATABASE, e.getMessage());//TODO
            SystemLogger.getInstance().setLogger(getClass(), e);
        }
        return carDTO;
    }
}
