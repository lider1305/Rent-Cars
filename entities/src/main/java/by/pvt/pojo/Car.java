package by.pvt.pojo;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * Describe POJO CAR
 */
@NoArgsConstructor
@ToString(exclude = {"orders"})
@EqualsAndHashCode(callSuper = false,exclude = {"orders"})
@Entity
@AttributeOverride(name = "id", column = @Column(name = "CAR_ID"))
@Table(name = "CARS")
public class Car extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "BRAND_ID")
    @Fetch(FetchMode.JOIN)
    private Brands brand;

    @Getter
    @Setter
    @Column(name = "MODEL",nullable = false)
    private String model;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "ENGINE_TYPE_ID")
    @Fetch(FetchMode.JOIN)
    private EngineType engineType;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "TRANSMISSION_TYPE_ID")
    @Fetch(FetchMode.JOIN)
    private TransmissionType transmissionType;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "BODY_TYPE_ID")
    @Fetch(FetchMode.JOIN)
    private BodyType bodyType;

    @Getter
    @Setter
    @Column(name = "YEAR_OF_MANUFACTURE", nullable = false)
    private int yearOfManufacture;

    @Getter
    @Setter
    @Column(name = "AMOUNT",nullable = false)
    private int amount;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "STATUS_ID")
    @Fetch(FetchMode.JOIN)
    private CarStatus status;

    @Getter
    @Setter
    @OneToMany(mappedBy = "car")
    private Set<Order> orders;
}
