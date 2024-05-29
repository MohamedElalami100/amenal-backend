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
public class TacheResumeDto {
    private Integer  id;
    private String tache;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer delai;
    private String lot;
    private Integer projetNumber;
}
