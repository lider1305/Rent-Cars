package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Describe POJO status of order
 */
@NoArgsConstructor
@ToString(exclude = {"orders"})
@EqualsAndHashCode(exclude = {"orders"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "STATUS_ID"))
@Table(name = "STATUS_OF_ORDER")
public class OrderStatus extends BaseEntity {
    private  static  final long serialVersionUID= 1L;

    @Getter
    @Setter
    @Column(name = "STATUS_NAME",updatable = false,nullable = false)
    @NotNull
    private String status;

    @Getter
    @Setter
    @OneToMany(mappedBy = "orderStatus")
    private Set<Order> orders;
}
