package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO engine type for car
 */
@NoArgsConstructor
@ToString(exclude = {"car"})
@EqualsAndHashCode(exclude = {"car"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ENGINE_TYPE_ID"))
@Table(name = "ENGINE_TYPE")
public class EngineType extends BaseEntity {
    private  static  final long serialVersionUID= 1L;

    @Getter
    @Setter
    @Column(name = "ENGINE_NAME", updatable = false,nullable = false)
    private String engineType;

    @Getter
    @Setter
    @OneToMany(mappedBy = "engineType")
    private Set<Car> car;
}
