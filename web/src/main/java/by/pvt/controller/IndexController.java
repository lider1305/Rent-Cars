package by.pvt.controller;

import by.pvt.DTO.LoginDTO;
import by.pvt.constants.Constants;
import by.pvt.pojo.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static by.pvt.constants.Pages.*;

@Controller
public class IndexController {

    @RequestMapping(value = {VALUE_START, VALUE_INDEX}, method = RequestMethod.GET)
    public String mainPage(Model model) {
        Client client = new Client();
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
