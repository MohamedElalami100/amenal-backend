package com.amenal.amenalbackend.Dashboard.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceptionResumeDto {
    private Integer id;
    private String reference;
    private Integer commande;

}
