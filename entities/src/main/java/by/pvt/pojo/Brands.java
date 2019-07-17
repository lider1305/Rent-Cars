package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO brand of car
 */
@NoArgsConstructor
@ToString(exclude = {"cars"})
@EqualsAndHashCode(callSuper = false, exclude = "cars")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "BRAND_ID"))
@Table(name = "BRANDS")
public class Brands extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "BRAND_NAME", updatable = false, nullable = false)
    private String brandName;

    @Getter
    @Setter
    @OneToMany(mappedBy = "brand")
    private Set<Car> cars;
}
