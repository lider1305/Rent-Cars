package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Describe POJO transmission type for car
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "TRANSMISSION_TYPE_ID"))
@Table(name = "TRANSMISSION_TYPE")
public class TransmissionType extends Entity {
    private  static  final long serialVersionUID= 1L;

    @Column(name = "TRANSMISSION_NAME",updatable = false,nullable = false)
    @NotNull
    private  String transmissionType;

    @OneToMany(mappedBy = "transmissionType")
    private Set<Car> car;

    public TransmissionType() {
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransmissionType)) return false;
        if (!super.equals(o)) return false;

        TransmissionType that = (TransmissionType) o;

        return transmissionType != null ? transmissionType.equals(that.transmissionType) : that.transmissionType == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (transmissionType != null ? transmissionType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransmissionType{" +
                "transmissionType='" + transmissionType + '\'' +
                '}';
    }
}
