package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class OrderSortingDTO {
    
    @Getter
    @Setter
    private int id;
    
    @Getter
    @Setter
    private String brand;
    
    @Getter
    @Setter
    private String model;
    
    @Getter
    @Setter
    private String startDate;
    
    @Getter
    @Setter
    private String endDate;
    
    @Getter
    @Setter
    private String status;
    
    @Getter
    @Setter
    private String amount;
    
    @Getter
    @Setter
    private boolean ASC;
}
