package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO brand of car
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "BRAND_ID"))
@Table(name = "BRANDS")
public class Brands extends Entity {
    private static final long serialVersionUID = 1L;

    @Column(name = "BRAND_NAME", updatable = false, nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "brand")
    private Set<Car> cars;

    public Brands() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brand) {
        this.brandName = brand;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brands)) return false;
        if (!super.equals(o)) return false;

        Brands brands = (Brands) o;

        return brandName != null ? brandName.equals(brands.brandName) : brands.brandName == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (brandName != null ? brandName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brandName='" + brandName + '\'' +
                '}';
    }
}
