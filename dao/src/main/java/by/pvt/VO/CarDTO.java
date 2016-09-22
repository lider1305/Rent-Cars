package by.pvt.VO;

import by.pvt.pojo.BodyType;
import by.pvt.pojo.Brands;
import by.pvt.pojo.EngineType;
import by.pvt.pojo.TransmissionType;

public class CarDTO {
    private int id;
    private Brands brand;
    private BodyType bodyType;
    private EngineType engineType;
    private TransmissionType transmissionType;
    private int amountFrom;
    private int amountTo;
    private int yearFrom;
    private int yearTo;

    public CarDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(int amountFrom) {
        this.amountFrom = amountFrom;
    }

    public int getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(int amountTo) {
        this.amountTo = amountTo;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDTO)) return false;

        CarDTO carDTO = (CarDTO) o;

        if (id != carDTO.id) return false;
        if (amountFrom != carDTO.amountFrom) return false;
        if (amountTo != carDTO.amountTo) return false;
        if (yearFrom != carDTO.yearFrom) return false;
        if (yearTo != carDTO.yearTo) return false;
        if (brand != null ? !brand.equals(carDTO.brand) : carDTO.brand != null) return false;
        if (bodyType != null ? !bodyType.equals(carDTO.bodyType) : carDTO.bodyType != null) return false;
        if (engineType != null ? !engineType.equals(carDTO.engineType) : carDTO.engineType != null) return false;
        return transmissionType != null ? transmissionType.equals(carDTO.transmissionType) : carDTO.transmissionType == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (bodyType != null ? bodyType.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (transmissionType != null ? transmissionType.hashCode() : 0);
        result = 31 * result + amountFrom;
        result = 31 * result + amountTo;
        result = 31 * result + yearFrom;
        result = 31 * result + yearTo;
        return result;
    }
}
