package by.pvt.controller;

import by.pvt.DTO.LoginDTO;
import by.pvt.constants.Constants;
import by.pvt.pojo.Client;
import by.pvt.util.DatabaseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.constants.Pages.*;

@Controller
public class IndexController {
    @Autowired
    DatabaseData databaseData;

    @RequestMapping(value = {VALUE_START,VALUE_INDEX}, method = RequestMethod.GET)
    public String mainPage(HttpServletRequest request,Model model) {
        Client client = new Client();
        databaseData.setToSessionCarParams(request, model);
        model.addAttribute(Constants.CLIENT, client);
        model.addAttribute(Constants.LOGIN, new LoginDTO());
        return PAGE_INDEX;
    }

    @RequestMapping(value = VALUE_CONTACT_INFO, method = RequestMethod.GET)
    public String contactPage() {
        return PAGE_CONTACT;
    }

    @RequestMapping(value = VALUE_404, method = RequestMethod.GET)
    public String page404() {
        return PAGE_404;
    }
}
