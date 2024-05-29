package com.amenal.amenalbackend.Dashboard.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactureResumeDto {
    private Integer id;
    private String reference;
    private LocalDate date;
    private Integer delai;
    private Double mntHt;
    private Double mntTtc;
}
