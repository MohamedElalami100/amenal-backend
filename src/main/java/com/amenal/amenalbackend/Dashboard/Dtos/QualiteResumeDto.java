package com.amenal.amenalbackend.Dashboard.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QualiteResumeDto {
    private String Qualite;
    private String groupe;
    private String tacheEtProjetId;
    private Integer importance;
}
