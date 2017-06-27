package by.pvt.pojo;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO of client roles
 */
@NoArgsConstructor
@ToString(exclude={"client"})
@EqualsAndHashCode(callSuper = false, exclude={"client"})
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID"))
@Table(name = "ROLES")
public class Roles extends Entity {
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
