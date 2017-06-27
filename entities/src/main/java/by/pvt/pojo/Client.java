package by.pvt.pojo;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Describe client class with get and set methods
 */
@NoArgsConstructor
@ToString(exclude = {"orders"})
@EqualsAndHashCode(callSuper = false,exclude = {"orders"})
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "CLIENT_ID"))
@Table(name = "CLIENTS")
@DynamicUpdate
public class Client extends Entity {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "NAME",nullable = false)
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter name!")
    private String name;

    @Getter
    @Setter
    @Column(name = "SURNAME",nullable = false)
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter surname!")
    private String surname;

    @Getter
    @Setter
    @Column(name = "PHONE")
    @Pattern(regexp = "^[+][0-9]{3}[0-9]{2}[0-9]{7}$",message =" you must enter number as template +375291234567" )
    @NotNull(message = "You must enter phone number!")
    private String phone;

    @Getter
    @Setter
    @Column (name = "MAIL",nullable = false,unique = true)
    @Pattern(regexp = "^[0-9a-zA-Zа-яА-я]*[@][a-z]*[.][a-z]{1,3}$",message = "You must enter email as template names@gmail.com")
    @NotNull(message = "You must enter email!")
    private String email;

    @Getter
    @Setter
    @Column(name = "PASSWORD",nullable = false)
    @Size(min = 4, message = "Password must be longer then 4 symbols")
    private String password;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinColumn (name = "PASSPORT_NUMBER_ID")
    @Fetch(FetchMode.JOIN)
    private Passports passports;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "STATUS_ID")
    @Fetch(FetchMode.JOIN)
    private ClientStatus statusOfClient;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "ROLE_ID")
    @Fetch(FetchMode.JOIN)
    private Roles role;

    @Getter
    @Setter
    @OneToMany (mappedBy = "client")
    private Set<Order> orders;
}
