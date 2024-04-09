package com.amenal.amenalbackend.budget.infrastructure.dto;

import java.time.LocalDate;

public class ChartDataDto {
	private Double[] qteJourProduitPonctuelle;
    private Double[] qteJourProduitLisse;
	private Double[] qteJourChargePonctuelle;
    private Double[] qteJourChargeLisse;
	private Double[] qteCumProduitPonctuelle;
    private Double[] qteCumProduitLisse;
	private Double[] qteCumChargePonctuelle;
    private Double[] qteCumChargeLisse;
   	private Double[] mtJourProduitPonctuelle;
    private Double[] mtJourProduitLisse;
	private Double[] mtJourChargePonctuelle;
    private Double[] mtJourChargeLisse;
	private Double[] mtCumProduitPonctuelle;
    private Double[] mtCumProduitLisse;
	private Double[] mtCumChargePonctuelle;
    private Double[] mtCumChargeLisse;
    private LocalDate[] labels; 
    private Integer length;

    // Constructor to initialize arrays with zeros of the specified length
    public ChartDataDto(Integer length) {
        this.length = length;
        initializeArrays(length);
    }

    // Helper method to initialize all arrays with zeros of the specified length
    private void initializeArrays(Integer length) {
        qteJourProduitPonctuelle = new Double[length];
        qteJourProduitLisse = new Double[length];
        qteJourChargePonctuelle = new Double[length];
        qteJourChargeLisse = new Double[length];
        qteCumProduitPonctuelle = new Double[length];
        qteCumProduitLisse = new Double[length];
        qteCumChargePonctuelle = new Double[length];
        qteCumChargeLisse = new Double[length];
        mtJourProduitPonctuelle = new Double[length];
        mtJourProduitLisse = new Double[length];
        mtJourChargePonctuelle = new Double[length];
        mtJourChargeLisse = new Double[length];
        mtCumProduitPonctuelle = new Double[length];
        mtCumProduitLisse = new Double[length];
        mtCumChargePonctuelle = new Double[length];
        mtCumChargeLisse = new Double[length];
        labels = new LocalDate[length];

        // Initialize all values with zeros
        for (int i = 0; i < length; i++) {
            qteJourProduitPonctuelle[i] = 0.0;
            qteJourProduitLisse[i] = 0.0;
            qteJourChargePonctuelle[i] = 0.0;
            qteJourChargeLisse[i] = 0.0;
            qteCumProduitPonctuelle[i] = 0.0;
            qteCumProduitLisse[i] = 0.0;
            qteCumChargePonctuelle[i] = 0.0;
            qteCumChargeLisse[i] = 0.0;
            mtJourProduitPonctuelle[i] = 0.0;
            mtJourProduitLisse[i] = 0.0;
            mtJourChargePonctuelle[i] = 0.0;
            mtJourChargeLisse[i] = 0.0;
            mtCumProduitPonctuelle[i] = 0.0;
            mtCumProduitLisse[i] = 0.0;
            mtCumChargePonctuelle[i] = 0.0;
            mtCumChargeLisse[i] = 0.0;
        }
    }

	public LocalDate[] getLabels() {
		return labels;
	}

	public void setLabels(LocalDate[] labels) {
		this.labels = labels;
	}

	public Double[] getQteJourProduitPonctuelle() {
		return qteJourProduitPonctuelle;
	}

	public void setQteJourProduitPonctuelle(Double[] qteJourProduitPonctuelle) {
		this.qteJourProduitPonctuelle = qteJourProduitPonctuelle;
	}

	public Double[] getQteJourProduitLisse() {
		return qteJourProduitLisse;
	}

	public void setQteJourProduitLisse(Double[] qteJourProduitLisse) {
		this.qteJourProduitLisse = qteJourProduitLisse;
	}

	public Double[] getQteJourChargePonctuelle() {
		return qteJourChargePonctuelle;
	}

	public void setQteJourChargePonctuelle(Double[] qteJourChargePonctuelle) {
		this.qteJourChargePonctuelle = qteJourChargePonctuelle;
	}

	public Double[] getQteJourChargeLisse() {
		return qteJourChargeLisse;
	}

	public void setQteJourChargeLisse(Double[] qteJourChargeLisse) {
		this.qteJourChargeLisse = qteJourChargeLisse;
	}

	public Double[] getQteCumProduitPonctuelle() {
		return qteCumProduitPonctuelle;
	}

	public void setQteCumProduitPonctuelle(Double[] qteCumProduitPonctuelle) {
		this.qteCumProduitPonctuelle = qteCumProduitPonctuelle;
	}

	public Double[] getQteCumProduitLisse() {
		return qteCumProduitLisse;
	}

	public void setQteCumProduitLisse(Double[] qteCumProduitLisse) {
		this.qteCumProduitLisse = qteCumProduitLisse;
	}

	public Double[] getQteCumChargePonctuelle() {
		return qteCumChargePonctuelle;
	}

	public void setQteCumChargePonctuelle(Double[] qteCumChargePonctuelle) {
		this.qteCumChargePonctuelle = qteCumChargePonctuelle;
	}

	public Double[] getQteCumChargeLisse() {
		return qteCumChargeLisse;
	}

	public void setQteCumChargeLisse(Double[] qteCumChargeLisse) {
		this.qteCumChargeLisse = qteCumChargeLisse;
	}

	public Double[] getMtJourProduitPonctuelle() {
		return mtJourProduitPonctuelle;
	}

	public void setMtJourProduitPonctuelle(Double[] mtJourProduitPonctuelle) {
		this.mtJourProduitPonctuelle = mtJourProduitPonctuelle;
	}

	public Double[] getMtJourProduitLisse() {
		return mtJourProduitLisse;
	}

	public void setMtJourProduitLisse(Double[] mtJourProduitLisse) {
		this.mtJourProduitLisse = mtJourProduitLisse;
	}

	public Double[] getMtJourChargePonctuelle() {
		return mtJourChargePonctuelle;
	}

	public void setMtJourChargePonctuelle(Double[] mtJourChargePonctuelle) {
		this.mtJourChargePonctuelle = mtJourChargePonctuelle;
	}

	public Double[] getMtJourChargeLisse() {
		return mtJourChargeLisse;
	}

	public void setMtJourChargeLisse(Double[] mtJourChargeLisse) {
		this.mtJourChargeLisse = mtJourChargeLisse;
	}

	public Double[] getMtCumProduitPonctuelle() {
		return mtCumProduitPonctuelle;
	}

	public void setMtCumProduitPonctuelle(Double[] mtCumProduitPonctuelle) {
		this.mtCumProduitPonctuelle = mtCumProduitPonctuelle;
	}

	public Double[] getMtCumProduitLisse() {
		return mtCumProduitLisse;
	}

	public void setMtCumProduitLisse(Double[] mtCumProduitLisse) {
		this.mtCumProduitLisse = mtCumProduitLisse;
	}

	public Double[] getMtCumChargePonctuelle() {
		return mtCumChargePonctuelle;
	}

	public void setMtCumChargePonctuelle(Double[] mtCumChargePonctuelle) {
		this.mtCumChargePonctuelle = mtCumChargePonctuelle;
	}

	public Double[] getMtCumChargeLisse() {
		return mtCumChargeLisse;
	}

	public void setMtCumChargeLisse(Double[] mtCumChargeLisse) {
		this.mtCumChargeLisse = mtCumChargeLisse;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
  
}
