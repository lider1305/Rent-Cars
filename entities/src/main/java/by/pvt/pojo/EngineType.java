package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO engine type for car
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "ENGINE_TYPE_ID"))
@Table(name = "ENGINE_TYPE")
public class EngineType extends Entity {
    private  static  final long serialVersionUID= 1L;

    @Column(name = "ENGINE_NAME", updatable = false,nullable = false)
    private String engineType;

    @OneToMany(mappedBy = "engineType")
    private Set<Car> car;

    public EngineType() {
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
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
        if (!(o instanceof EngineType)) return false;
        if (!super.equals(o)) return false;

        EngineType that = (EngineType) o;

        return engineType != null ? engineType.equals(that.engineType) : that.engineType == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EngineType{" +
                "engineType='" + engineType + '\'' +
                '}';
    }
}
