package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO of client roles
 */

@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID"))
@Table(name = "ROLES")
public class Roles extends Entity {
    private static final long serialVersionUID = 1L;

    @Column (name = "NAME",nullable = false)
    private String name;

    @OneToMany (mappedBy = "role")
    private Set<Client> client;

    public Roles() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;
        if (!super.equals(o)) return false;

        Roles roles = (Roles) o;

        return name != null ? name.equals(roles.name) : roles.name == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "name='" + name + '\'' +
                '}';
    }
}
