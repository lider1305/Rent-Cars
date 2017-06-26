package by.pvt.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for getting pagination params from user pages
 */
@NoArgsConstructor
public class PaginationDTO {
    
    @Getter
    @Setter
    private int id;
    
    @Getter
    @Setter
    private int pages;
    
    @Getter
    @Setter
    private int perPages;
}
