package com.ffm.backend.data.model.output;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class CurrentFire {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private String confidence;
    private LocalDate date;
}
