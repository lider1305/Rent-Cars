package by.pvt.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Describe the abstract entity Entity
 */
@MappedSuperclass
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    protected int id;

    /**
     * @return the id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * set entity id
     *
     * @param id of entity
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        return id == entity.id;

    }

    @Override
    public int hashCode() {
        return id;
    }


}
