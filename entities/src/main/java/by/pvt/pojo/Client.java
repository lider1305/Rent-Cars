package by.pvt.pojo;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Describe client class with get and set methods
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "CLIENT_ID"))
@Table(name = "CLIENTS")
@DynamicUpdate
public class Client extends Entity {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME",nullable = false)
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter name!")
    private String name;

    @Column(name = "SURNAME",nullable = false)
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter surname!")
    private String surname;

    @Column(name = "PHONE")
    @Pattern(regexp = "^[+][0-9]{3}[0-9]{2}[0-9]{7}$",message =" you must enter number as template +375291234567" )
    @NotNull(message = "You must enter phone number!")
    private String phone;

    @Column (name = "MAIL",nullable = false,unique = true)
    @Pattern(regexp = "^[0-9a-zA-Zа-яА-я]*[@][a-z]*[.][a-z]{1,3}$",message = "You must enter email as template names@gmail.com")
    @NotNull(message = "You must enter email!")
    private String email;

    @Column(name = "PASSWORD",nullable = false)
    @Size(min = 4, message = "Password must be longer then 4 symbols")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinColumn (name = "PASSPORT_NUMBER_ID")
    @Fetch(FetchMode.JOIN)
    private Passports passports;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "STATUS_ID")
    @Fetch(FetchMode.JOIN)
    private StatusOfClient statusOfClient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "ROLE_ID")
    @Fetch(FetchMode.JOIN)
    private Roles role;

    @OneToMany (mappedBy = "client")
    private Set<Order> orders;

    public Client() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surName) {
        this.surname = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusOfClient getStatusOfClient() {
        return statusOfClient;
    }

    public void setStatusOfClient(StatusOfClient status) {
        this.statusOfClient = status;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Passports getPassports() {
        return passports;
    }

    public void setPassports(Passports passports) {
        this.passports = passports;
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
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (passports != null ? !passports.equals(client.passports) : client.passports != null) return false;
        if (statusOfClient != null ? !statusOfClient.equals(client.statusOfClient) : client.statusOfClient != null)
            return false;
        return role != null ? role.equals(client.role) : client.role == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passports != null ? passports.hashCode() : 0);
        result = 31 * result + (statusOfClient != null ? statusOfClient.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passports=" + passports +
                ", statusOfClient=" + statusOfClient +
                ", role=" + role +
                '}';
    }


}
