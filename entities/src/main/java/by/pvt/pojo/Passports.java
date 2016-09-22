package by.pvt.pojo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * This class describe POJO passport
 */
@javax.persistence.Entity
@AttributeOverride(name = "id", column = @Column(name = "PASSPORT_NUMBER_ID"))
@Table(name = "PASSPORTS")
public class Passports extends Entity {
    private  static  final long serialVersionUID= 1L;

    @Column (name = "PASSPORTS_NUMBER",nullable = false)
   // @Pattern(regexp = "^[0-9a-zA-Zа-яА-я]{7,15}$", message = "You must enter passport number correct without spaces, only letters and numbers")
    @NotNull(message = "You must enter passport number!")
    private String passport;

    @Temporal(TemporalType.DATE)
    @Column (name = "DATE_OF_ISSUE",nullable = false)
    @NotNull(message = "Choose date of issue!")
    private Date passportIssueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_END",nullable = false)
    @NotNull(message = "Choose date of end!")
    private Date passportEndDate;

    @OneToOne(mappedBy = "passports")
    private Client client;

    public Passports() {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passports)) return false;
        if (!super.equals(o)) return false;

        Passports passports = (Passports) o;

        if (passport != null ? !passport.equals(passports.passport) : passports.passport != null) return false;
        if (passportIssueDate != null ? !passportIssueDate.equals(passports.passportIssueDate) : passports.passportIssueDate != null)
            return false;
        return passportEndDate != null ? passportEndDate.equals(passports.passportEndDate) : passports.passportEndDate == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (passportIssueDate != null ? passportIssueDate.hashCode() : 0);
        result = 31 * result + (passportEndDate != null ? passportEndDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Passports{" +
                "passport='" + passport + '\'' +
                ", passportIssueDate=" + passportIssueDate +
                ", passportEndDate=" + passportEndDate +
                '}';
    }
}
