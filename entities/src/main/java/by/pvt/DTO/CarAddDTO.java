package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@NoArgsConstructor
public class CarAddDTO{
    @Getter
    @Setter
    private int brand;
    
    @Getter
    @Setter
    @Size(min=2,message = "Model of cars shouldn't has less then 2 characters")
    private String model;
    
    @Getter
    @Setter
    private int engineType;
    
    @Getter
    @Setter
    private int transmissionType;
    
    @Getter
    @Setter
    private int bodyType;
    
    @Getter
    @Setter
    private int yearOfManufacture;
    
    @Getter
    @Setter
    private int amount;
}
