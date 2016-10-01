package by.pvt.DTO;

public class OrderSortingDTO {
    private int id;
    private String brand;
    private String model;
    private String startDate;
    private String endDate;
    private String orderStatus;
    private String amount;
    private boolean ASC;

    public OrderSortingDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return orderStatus;
    }

    public void setStatus(String status) {
        this.orderStatus = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isASC() {
        return ASC;
    }

    public void setASC(boolean ASC) {
        this.ASC = ASC;
    }
}
