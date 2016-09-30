package by.pvt.util;

import by.pvt.pojo.Car;

import java.util.Date;

public class DateAndAmount {
    private static final int HOURS = 24;
    private static final int MINUTES = 60;
    private static final int SECONDS = 60;
    private static final int MILLISECONDS = 1000;

    /**
     * the method calculates the total cost of the order
     * @param car
     * @param start
     * @param end
     * @return
     */
   public static long countTotalCostOfOrder(Car car, Date start, Date end) {
        long times = end.getTime() - start.getTime();
        int days = (int) times / (HOURS * MINUTES * SECONDS * MILLISECONDS);
        long amount = (long) (days * car.getAmount());
        if (amount < car.getAmount()) {
            amount = (long) car.getAmount();
        }
        return amount;
    }

    /**
     * the method checks that the end date less than start date
     * @param start
     * @param end
     * @return
     */
    public static boolean checkEndDateOnActual(Date start, Date end) {
        return end.getTime() < start.getTime();
    }

    /**
     * the method checks the date on the relevance
     * @param start
     * @return
     */
    public static boolean checkDateOnActual(Date start) {
        Date today = new Date();
        int day = HOURS * MINUTES * SECONDS * MILLISECONDS;
        return start.getTime()+day < today.getTime();
    }
}
