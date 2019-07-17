package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO body type for car
 */
@NoArgsConstructor
@ToString(exclude = {"car"})
@EqualsAndHashCode(exclude = {"car"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "BODY_TYPE_ID"))
@Table(name = "BODY_TYPE")
public class BodyType extends BaseEntity {
    private  static  final long serialVersionUID= 1L;

    @Getter
    @Setter
    @Column(name = "BODY_NAME",updatable = false,nullable = false)
    private String bodyType;

    @Getter
    @Setter
    @OneToMany(mappedBy = "bodyType")
    private Set<Car> car;
}
