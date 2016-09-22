package by.pvt.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO CAR
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "CAR_ID"))
@Table(name = "CARS")
public class Car extends Entity {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "BRAND_ID")
    @Fetch(FetchMode.JOIN)
    private Brands brand;

    @Column(name = "MODEL",nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "ENGINE_TYPE_ID")
    @Fetch(FetchMode.JOIN)
    private EngineType engineType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "TRANSMISSION_TYPE_ID")
    @Fetch(FetchMode.JOIN)
    private TransmissionType transmissionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "BODY_TYPE_ID")
    @Fetch(FetchMode.JOIN)
    private BodyType bodyType;

    @Column(name = "YEAR_OF_MANUFACTURE", nullable = false)
    private int yearOfManufacture;

    @Column(name = "AMOUNT",nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "STATUS_ID")
    @Fetch(FetchMode.JOIN)
    private StatusOfCar status;

    @OneToMany(mappedBy = "car")
    private Set<Order> orders;

    public Car() {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public StatusOfCar getStatus() {
        return status;
    }

    public void setStatus(StatusOfCar status) {
        this.status = status;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (yearOfManufacture != car.yearOfManufacture) return false;
        if (amount != car.amount) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (engineType != null ? !engineType.equals(car.engineType) : car.engineType != null) return false;
        if (transmissionType != null ? !transmissionType.equals(car.transmissionType) : car.transmissionType != null)
            return false;
        if (bodyType != null ? !bodyType.equals(car.bodyType) : car.bodyType != null) return false;
        return status != null ? status.equals(car.status) : car.status == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (transmissionType != null ? transmissionType.hashCode() : 0);
        result = 31 * result + (bodyType != null ? bodyType.hashCode() : 0);
        result = 31 * result + yearOfManufacture;
        result = 31 * result + amount;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + brand +
                ", model='" + model + '\'' +
                ", engineType=" + engineType +
                ", transmissionType=" + transmissionType +
                ", bodyType=" + bodyType +
                ", yearOfManufacture=" + yearOfManufacture +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
