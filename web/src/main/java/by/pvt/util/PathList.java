package by.pvt.util;

import java.util.ArrayList;
import java.util.List;

public class PathList {
    private  List<String> list = new ArrayList<>();

    public  List<String> setListURLGuest(){
        list.add("/go_to_registration");
        list.add("/main");
        list.add("/index");
        list.add("/");
        list.add("/forgot_password");
        list.add("/check_car");
        list.add("/rent_car");
        list.add("/login");
        list.add("/get_password");
        list.add("/new_user");
        list.add("/contact_info");
        list.add("/get_all_cars");
        list.add("/get_cars_by_filter");
        list.add("/resources/css/style.css");
        list.add("/resources/js/tcal.js");
        list.add("/resources/images/overlay.png");
        list.add("/resources/css/tcal.css");
        list.add("/resources/css/bootstrap.css");
        list.add("/resources/js/bootstrap.js");
        list.add("/resources/images/fone.jpg");
        list.add("/resources/images/arrow.png");
        return list;
    }

    public  List<String> setListURLAdmin(){
        list.add("/admin/main");
        list.add("/add_car");
        list.add("/get_all_users");
        list.add("/get_all_orders");
        list.add("/process_order");
        return list;
    }
}
