package com.amenal.amenalbackend.Dashboard.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeDashboardDataDto {
    private Integer totalProjets;
    private Integer totalTachesAVenir;
    private Double totalChargesBudgetise;
    private Double totalDepenses;

    private List<ProjetResumeDto> budgetsNonFinalise;

    private List<TacheResumeDto> tachesEnCours;

    private List<FactureResumeDto> facturesAPayer;

    private List<ReceptionResumeDto> receptionsDeCetteSemaine;

    private List<DepenseVsBudgetDto> depensesVsBudgets;

}
