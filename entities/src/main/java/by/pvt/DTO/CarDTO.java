package by.pvt.DTO;

import by.pvt.pojo.BodyType;
import by.pvt.pojo.Brands;
import by.pvt.pojo.EngineType;
import by.pvt.pojo.TransmissionType;
import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CarDTO {
    @Getter
    @Setter
    private int id;
    
    @Getter
    @Setter
    private Brands brand;
    
    @Getter
    @Setter
    private BodyType bodyType;
    
    @Getter
    @Setter
    private EngineType engineType;
    
    @Getter
    @Setter
    private TransmissionType transmissionType;
    
    @Getter
    @Setter
    private int amountFrom;
    
    @Getter
    @Setter
    private int amountTo;
    
    @Getter
    @Setter
    private int yearFrom;
    
    @Getter
    @Setter
    private int yearTo;
}
