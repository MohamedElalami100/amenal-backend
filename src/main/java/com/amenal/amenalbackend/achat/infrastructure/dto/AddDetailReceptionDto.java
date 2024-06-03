package com.amenal.amenalbackend.achat.infrastructure.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDetailReceptionDto {
    private Integer detailCommandeId;

    private Double qte;

    private Integer receptionId;
}
