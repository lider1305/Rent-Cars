package by.pvt.handler;

import by.pvt.constants.WebErrorMessages;
import by.pvt.util.SystemLogger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static by.pvt.constants.Message.ERROR;
import static by.pvt.constants.Message.ERROR_500;
import static by.pvt.constants.Pages.PAGE_404;
import static by.pvt.constants.Pages.PAGE_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(HttpServletRequest request) {
        SystemLogger.getInstance().setLogger(getClass(), (Throwable) request.getAttribute(ERROR));
        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return PAGE_ERROR;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404Exception(HttpServletRequest request) {
        SystemLogger.getInstance().setLogger(getClass(), (Throwable) request.getAttribute(ERROR));
        return PAGE_404;
    }

}
