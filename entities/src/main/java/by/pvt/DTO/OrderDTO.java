package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
public class OrderDTO {
    
    @Getter
    @Setter
    private int clientId;
    
    @Getter
    @Setter
    private int carId;
    
    @Getter
    @Setter
    private int orderId;
    
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Дата должна быть выбрана")
    private Date startDate;
    
    @Getter
    @Setter
    @NotNull(message = "Дата должна быть выбрана")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Getter
    @Setter
    private String message;
}
