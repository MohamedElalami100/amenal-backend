package com.amenal.amenalbackend.Dashboard.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepenseVsBudgetDto {
    private Integer idProjet;
    private String projet;
    private Double chargeBudgetTotal;
    private Double depenseTotal;
}
