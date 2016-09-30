package by.pvt.util;

import by.pvt.pojo.Car;
import by.pvt.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DateAndAmount {
    private static final int HOURS = 24;
    private static final int MINUTES = 60;
    private static final int SECONDS = 60;
    private static final int MILLISECONDS = 1000;
    @Autowired
    private OrderService orderService;
    //btw if you like to provide comments for methods you can do it in javadoc style
    //the method calculates the total cost of the order
   public static long countTotalCostOfOrder(Car car, Date start, Date end) {
        long times = end.getTime() - start.getTime();
        int days = (int) times / (HOURS * MINUTES * SECONDS * MILLISECONDS);
        long amount = (long) (days * car.getAmount());
        if (amount < car.getAmount()) {
            amount = (long) car.getAmount();
        }
        return amount;
    }
    //the method checks that the end date less than start date
    public static boolean checkEndDateOnActual(Date start, Date end) {
        return end.getTime() < start.getTime();
    }

    //the method checks the date on the relevance
    public static boolean checkDateOnActual(Date start) {
        Date today = new Date();
        return start.getTime() < today.getTime();
    }
}
