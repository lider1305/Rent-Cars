package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO of Statuses of client
 */
@NoArgsConstructor
@ToString(exclude={"client"})
@EqualsAndHashCode(exclude={"client"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "STATUS_ID"))
@Table(name = "STATUS_OF_CLIENT")
public class ClientStatus extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column (name = "NAME_OF_STATUS",updatable = false,nullable = false)
    private String status;

    @Getter
    @Setter
    @OneToMany (mappedBy = "statusOfClient")
    private Set<Client> client;
}
