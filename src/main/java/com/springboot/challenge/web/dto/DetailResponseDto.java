package com.springboot.challenge.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DetailResponseDto {
    private Long id ;

    private Integer count;

    @Builder
    public DetailResponseDto(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
