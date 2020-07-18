package com.springboot.challenge.web.dto;

import com.springboot.challenge.exceptions.OverStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class DetailResponseDto {
    private Long id ;

    private Integer stockQuantity;

    private Integer count;

    public void validateStockQuantity() throws RuntimeException{
        if (count > stockQuantity) {
            throw new OverStockException();
        }
    }
}
