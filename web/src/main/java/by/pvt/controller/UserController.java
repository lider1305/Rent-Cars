package by.pvt.controller;

import by.pvt.DTO.ClientDTO;
import by.pvt.DTO.LoginDTO;
import by.pvt.constants.Message;
import by.pvt.constants.UIParams;
import by.pvt.constants.WebErrorMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Client;
import by.pvt.pojo.ClientStatus;
import by.pvt.pojo.Roles;
import by.pvt.service.impl.ClientService;
import by.pvt.service.impl.RoleService;
import by.pvt.service.impl.StatusOfClientService;
import by.pvt.util.MessageManager;
import by.pvt.util.Pagination;
import by.pvt.util.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static by.pvt.constants.Constants.*;
import static by.pvt.constants.Message.*;
import static by.pvt.constants.Pages.*;
import static by.pvt.constants.UIParams.*;

@Controller
public class UserController {
    private static final String REGEX_EMAIL = "^[0-9a-zA-Zа-яА-я]*[@][a-z]*[.][a-z]{1,3}$";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final int MIN_PASSWORD = 7;

    @Autowired
    ClientService clientServices;
    @Autowired
    StatusOfClientService statusOfClientService;
    @Autowired
    RoleService roleService;
    @Autowired
    Pagination pagination;

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        SystemLogger.getInstance().setLogger(getClass(), (Throwable) request.getAttribute(ERROR));
        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return PAGE_ERROR;
    }

    @RequestMapping(value = VALUE_CLIENT_PAGE, method = RequestMethod.GET)
    public String clientPage() {
        return PAGE_CLIENT;
    }

    @RequestMapping(value = VALUE_ADMIN_PAGE, method = RequestMethod.GET)
    public String adminPage() {
        return PAGE_ADMIN;
    }

    @RequestMapping(value = VALUE_GO_TO_REGISTRATION)
    public String GoToRegistration(ModelMap model) {
        model.put(CLIENT, new Client());
        return PAGE_REGISTRATION;
    }

    @RequestMapping(value = VALUE_FORGOT_PASSWORD)
    public String ForgotPassword() {
        return PAGE_FORGOT_PASSWORD;
    }

    @RequestMapping(value = VALUE_LOGIN, method = RequestMethod.POST)
    public String login(LoginDTO login, Model model, HttpServletRequest request) throws ServiceException {
        //check email and password for regexp
        if (login.getEmail().matches(REGEX_EMAIL)) {
            if (login.getPassword().length() >= 4) {
                //get client with entered params
                Client clientUI = clientServices.login(login.getEmail(), login.getPassword());
                if (clientUI == null) {
                    model.addAttribute(WRONG_USER_LOGIN, ERROR_LOGIN);
                    return PAGE_INDEX;
                }
                model.addAttribute(CLIENT, clientUI);
                HttpSession session = request.getSession();
                session.setAttribute(CLIENT, clientUI);
                return REDIRECT_PAGE_USER;
            }
            model.addAttribute(PASSWORD_ERROR, PASSWORD_ERROR_I18N);
            return PAGE_INDEX;
        }
        model.addAttribute(EMAIL_ERROR, EMAIL_ERROR_I18N);
        return PAGE_INDEX;
    }

    @RequestMapping(value = VALUE_LOGOUT)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return PAGE_INDEX;
    }

    @RequestMapping(value = VALUE_GO_TO_EDIT_CLIENT, method = RequestMethod.GET)
    public String editClientData(HttpServletRequest request, ModelMap model) {
        model.put(CLIENT, request.getSession().getAttribute(CLIENT));
        model.put(CLIENTDTO, new ClientDTO());
        return PAGE_EDIT_CLIENT;
    }

    @RequestMapping(value = VALUE_CHANGE_DATA, method = RequestMethod.POST)
    public String changeClientData(@Valid @ModelAttribute(CLIENTDTO) ClientDTO client, BindingResult result, Model model, HttpServletRequest request) throws ServiceException {
        if (result.hasErrors()) {
            return PAGE_EDIT_CLIENT;
        }
        Client clientUI = (Client) request.getSession().getAttribute(CLIENT);
        clientServices.update(client, clientUI);
        model.addAttribute(CLIENTDTO, client);
        HttpSession session = request.getSession();
        session.setAttribute(CLIENT, clientServices.get(Client.class,clientUI.getId()));
        return REDIRECT_PAGE_CLIENT;
    }

    @RequestMapping(value = VALUE_GET_PASSWORD, method = RequestMethod.POST)
    public String getPassword(HttpServletRequest request, @RequestParam String email, Model model) {
        String password = null;
        try {
            password = clientServices.forgotPassword(email);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.MESSAGE_ERROR_GET_PASSWORD, Message.ERROR_GET_PASSWORD);
        }
        request.setAttribute(REQUEST_PASSWORD, password);
        return PAGE_FORGOT_PASSWORD;
    }

    @RequestMapping(value = VALUE_NEW_USER, method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute(CLIENT) Client client, BindingResult result, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return PAGE_REGISTRATION;
        }
        if (client.getPassports().getPassport() == null || client.getPassports().getPassport().length() < MIN_PASSWORD) {
            model.addAttribute(PASSPORT_ERROR, MessageManager.getInstance().getValue(PASSPORT_ERROR_I18N, Locale.getDefault()));
            return PAGE_REGISTRATION;
        }
        if (client.getPassports().getPassportIssueDate() == null | client.getPassports().getPassportEndDate() == null) {
            model.addAttribute(DATE_ERROR, MessageManager.getInstance().getValue(DATE_ERROR_I18N, Locale.getDefault()));
            return PAGE_REGISTRATION;
        }

        try {
            client.setStatusOfClient(statusOfClientService.get(ClientStatus.class, 1));
            client.setRole(roleService.get(Roles.class, 1));
            clientServices.save(client);
            model.addAttribute(CLIENT, client);
            redirectAttributes.addFlashAttribute(UIParams.REQUEST_SUCCESS_REGISTRY, Message.SUCCESS_REGISTRY);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
        }
        return REDIRECT_INDEX;

    }

    @RequestMapping(value = VALUE_GET_ALL_USERS, method = RequestMethod.GET)
    public String getAllUsersPost(HttpServletRequest request, Model model) {
        pagination.getStartRow(request);
        request.getSession().setAttribute(REQUEST_PAGE, PAGE_ALL_USERS);
        List users = null;
        try {
            users = clientServices.getAll(pagination.getStartRow(request) - 1, pagination.getItemPerPage(request));
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
        }
        model.addAttribute(REQUEST_GET_ALL_USERS, users);
        return PAGE_ALL_USERS;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}