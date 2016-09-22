package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Describe POJO of Statuses of client
 */

@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "STATUS_ID"))
@Table(name = "STATUS_OF_CLIENT")
public class StatusOfClient extends Entity {
    private static final long serialVersionUID = 1L;

    @Column (name = "NAME_OF_STATUS",updatable = false,nullable = false)
    private String status;

    @OneToMany (mappedBy = "statusOfClient")
    private Set<Client> client;

    public StatusOfClient() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(o instanceof StatusOfClient)) return false;

        StatusOfClient that = (StatusOfClient) o;

        if (id != that.id) return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatusOfClient{" +
                "status='" + status + '\'' +
                '}';
    }
}
