package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
public class ClientDTO {
	@Getter
	@Setter
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter name!")
    private String name;
	
	@Getter
	@Setter
    @Size(min = 4, max=20, message = "Name must be longer then 4 letters, but shorter then 20")
    @NotNull(message = "You must enter surname!")
    private String surname;
	
	@Getter
	@Setter
    @Pattern(regexp = "^[+][0-9]{3}[0-9]{2}[0-9]{7}$",message =" you must enter number as template +375291234567" )
    @NotNull(message = "You must enter phone number!")
    private String phone;
	
	@Getter
	@Setter
    @Pattern(regexp = "^[0-9a-zA-Zа-яА-я]*[@][a-z]*[.][a-z]{1,3}$",message = "You must enter email as template names@gmail.com")
    @NotNull(message = "You must enter email!")
    private String email;
	
	@Getter
	@Setter
    @Size(min = 4, message = "Password must be longer then 4 symbols")
    private String password;
	
	@Getter
	@Setter
	@NotNull(message = "You must enter passport number!")
	private String passport;
	
	@Getter
	@Setter
    @NotNull(message = "Choose date of issue!")
    private Date passportIssueDate;
	
	@Getter
	@Setter
    @NotNull(message = "Choose date of end!")
    private Date passportEndDate;
}
