package com.korsuk.my_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AspectResponse {
    private String nameService;
    private long workingTime;


    public AspectResponse(String nameService) {
        this.nameService = nameService;
    }

    public void setWorkingTime(long time) {
        workingTime += time;
    }
}
