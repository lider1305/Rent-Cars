package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO status for cars
 */
@NoArgsConstructor
@ToString(exclude = {"car"})
@EqualsAndHashCode(exclude = {"car"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "STATUS_OF_CAR_ID"))
@Table(name = "STATUS_OF_CAR")
public class CarStatus extends BaseEntity {
    private  static  final long serialVersionUID= 1L;

    @Getter
    @Setter
    @Column(name = "STATUS_NAME", updatable = false,nullable = false)
    private String status;

    @Getter
    @Setter
    @OneToMany(mappedBy = "status")
    private Set<Car> car;
}
