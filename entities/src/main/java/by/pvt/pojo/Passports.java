package by.pvt.pojo;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * This class describe POJO passport
 */
@NoArgsConstructor
@ToString(exclude = {"client"})
@EqualsAndHashCode(exclude = { "client" }, callSuper = false)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "PASSPORT_NUMBER_ID"))
@Table(name = "PASSPORTS")
public class Passports extends BaseEntity {
    private  static  final long serialVersionUID= 1L;

    @Getter
    @Setter
    @Column (name = "PASSPORTS_NUMBER",nullable = false)
   // @Pattern(regexp = "^[0-9a-zA-Zа-яА-я]{7,15}$", message = "You must enter passport number correct without spaces, only letters and numbers")
    @NotNull(message = "You must enter passport number!")
    private String passport;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column (name = "DATE_OF_ISSUE",nullable = false)
    @NotNull(message = "Choose date of issue!")
    private Date passportIssueDate;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_END",nullable = false)
    @NotNull(message = "Choose date of end!")
    private Date passportEndDate;

    @Getter
    @Setter
    @OneToOne(mappedBy = "passports")
    private Client client;
}
