package by.pvt.pojo;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * Describe POJO for clients orders
 */
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ORDER_ID"))
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_ID")
    @Fetch(FetchMode.JOIN)
    private Car car;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    @Fetch(FetchMode.JOIN)
    private Client client;

    @Getter
    @Setter
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Getter
    @Setter
    @Column(name = "END_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Getter
    @Setter
    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_OF_ORDER")
    @Fetch(FetchMode.JOIN)
    private OrderStatus orderStatus;

    @Getter
    @Setter
    private long amount;
}
