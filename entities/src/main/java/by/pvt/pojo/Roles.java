package by.pvt.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO of client roles
 */
@NoArgsConstructor
@ToString(exclude={"client"})
@EqualsAndHashCode(exclude={"client"}, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID"))
@Table(name = "ROLES")
public class Roles extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column (name = "NAME",nullable = false)
    private String name;

    @Getter
    @Setter
    @OneToMany (mappedBy = "role")
    private Set<Client> client;
}
