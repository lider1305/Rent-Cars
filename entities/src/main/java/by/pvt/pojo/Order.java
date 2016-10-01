package by.pvt.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * Describe POJO for clients orders
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "ORDER_ID"))
@Table(name = "ORDERS")
public class Order extends Entity {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_ID")
    @Fetch(FetchMode.JOIN)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    @Fetch(FetchMode.JOIN)
    private Client client;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_OF_ORDER")
    @Fetch(FetchMode.JOIN)
    private OrderStatus orderStatus;

    private long amount;

    public Order() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (car != null ? !car.equals(order.car) : order.car != null) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (startDate != null ? !startDate.equals(order.startDate) : order.startDate != null) return false;
        if (endDate != null ? !endDate.equals(order.endDate) : order.endDate != null) return false;
        if (message != null ? !message.equals(order.message) : order.message != null) return false;
        return orderStatus != null ? orderStatus.equals(order.orderStatus) : order.orderStatus == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "car=" + car +
                ", client=" + client +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", message='" + message + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
