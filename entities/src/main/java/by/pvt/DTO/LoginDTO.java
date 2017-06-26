package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class LoginDTO {
    
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String password;
}
