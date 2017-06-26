package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CarSortingDTO {
    
    @Getter
    @Setter
    private String brand;
    
    @Getter
    @Setter
    private String bodyType;
    
    @Getter
    @Setter
    private String engineType;
    
    @Getter
    @Setter
    private String transmissionType;
    
    @Getter
    @Setter
    private String year;
    
    @Getter
    @Setter
    private String amount;
    
    @Getter
    @Setter
    private boolean ASC;
}
