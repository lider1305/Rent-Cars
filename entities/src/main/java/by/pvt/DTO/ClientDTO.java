package by.pvt.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class ClientDTO {
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter name!")
    private String name;

    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter surname!")
    private String surname;

    @Pattern(regexp = "^[+][0-9]{3}[0-9]{2}[0-9]{7}$",message =" you must enter number as template +375291234567" )
    @NotNull(message = "You must enter phone number!")
    private String phone;

    @Pattern(regexp = "^[0-9a-zA-Zа-яА-я]*[@][a-z]*[.][a-z]{1,3}$",message = "You must enter email as template names@gmail.com")
    @NotNull(message = "You must enter email!")
    private String email;

    @Size(min = 4, message = "Password must be longer then 4 symbols")
    private String password;

    @NotNull(message = "You must enter passport number!")
    private String passport;

    @NotNull(message = "Choose date of issue!")
    private Date passportIssueDate;

    @NotNull(message = "Choose date of end!")
    private Date passportEndDate;

    public ClientDTO() {
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

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public Date getPassportEndDate() {
        return passportEndDate;
    }

    public void setPassportEndDate(Date passportEndDate) {
        this.passportEndDate = passportEndDate;
    }
}
