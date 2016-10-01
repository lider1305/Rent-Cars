package by.pvt.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Describe POJO status of order
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "STATUS_ID"))
@Table(name = "STATUS_OF_ORDER")
public class OrderStatus extends Entity {
    private  static  final long serialVersionUID= 1L;

    @Column(name = "STATUS_NAME",updatable = false,nullable = false)
    @NotNull
    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private Set<Order> orders;

    public OrderStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderStatus)) return false;
        if (!super.equals(o)) return false;

        OrderStatus that = (OrderStatus) o;

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
        return "StatusOfOrder{" +
                "status='" + status + '\'' +
                '}';
    }
}
