package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO body type for car
 *
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "BODY_TYPE_ID"))
@Table(name = "BODY_TYPE")
public class BodyType extends Entity {
    private  static  final long serialVersionUID= 1L;

    @Column(name = "BODY_NAME",updatable = false,nullable = false)
    private String bodyType;

    @OneToMany(mappedBy = "bodyType")
    private Set<Car> car;

    public BodyType() {
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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
        if (!(o instanceof BodyType)) return false;
        if (!super.equals(o)) return false;

        BodyType bodyType1 = (BodyType) o;

        return bodyType != null ? bodyType.equals(bodyType1.bodyType) : bodyType1.bodyType == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bodyType != null ? bodyType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "bodyType='" + bodyType + '\'' +
                '}';
    }
}
