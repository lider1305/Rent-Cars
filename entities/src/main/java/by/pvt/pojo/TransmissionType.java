package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Describe POJO transmission type for car
 */
@NoArgsConstructor
@ToString(exclude = {"car"})
@EqualsAndHashCode(exclude = {"car"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "TRANSMISSION_TYPE_ID"))
@Table(name = "TRANSMISSION_TYPE")
public class TransmissionType extends BaseEntity {
    private  static  final long serialVersionUID= 1L;

    @Getter
    @Setter
    @Column(name = "TRANSMISSION_NAME",updatable = false,nullable = false)
    @NotNull
    private  String transmissionType;

    @Getter
    @Setter
    @OneToMany(mappedBy = "transmissionType")
    private Set<Car> car;
}
