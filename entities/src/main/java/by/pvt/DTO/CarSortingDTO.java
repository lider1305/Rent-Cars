package by.pvt.DTO;

public class CarSortingDTO {
    private String brand;
    private String bodyType;
    private String engineType;
    private String transmissionType;
    private String year;
    private String amount;
    private boolean ASC;

    public CarSortingDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
