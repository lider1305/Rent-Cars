package by.pvt.pojo;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Describe POJO transmission type for car
 */
@NoArgsConstructor
@ToString(exclude = {"car"})
@EqualsAndHashCode(callSuper = false, exclude = {"car"})
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "TRANSMISSION_TYPE_ID"))
@Table(name = "TRANSMISSION_TYPE")
public class TransmissionType extends Entity {
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
