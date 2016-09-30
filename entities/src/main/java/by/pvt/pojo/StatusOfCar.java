package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO status for cars
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "STATUS_OF_CAR_ID"))
@Table(name = "STATUS_OF_CAR")
//Consider renaming as CarStatus
public class StatusOfCar extends Entity {
    private  static  final long serialVersionUID= 1L;

    @Column(name = "STATUS_NAME", updatable = false,nullable = false)
    private String status;

    @OneToMany(mappedBy = "status")
    private Set<Car> car;

    public StatusOfCar() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(o instanceof StatusOfCar)) return false;
        if (!super.equals(o)) return false;

        StatusOfCar that = (StatusOfCar) o;

        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatusOfCar{" +
                "status='" + status + '\'' +
                '}';
    }
}
