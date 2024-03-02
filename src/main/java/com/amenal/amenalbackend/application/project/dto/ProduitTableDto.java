package com.amenal.amenalbackend.application.project.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProduitTableDto {
	private Integer id;	
	private String art;
	private String designation;
	private String upb;
	private Double qpm;
	private Double ppm;
	private Double mpm;
	private Double ppb;
	private Double qpb;
	private Double mpb;
	private Double mrg;
	private Double prcRg;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;
	private List<LotDeProduitDto> lotsDeProduit;

	public ProduitTableDto(Integer id,String art, String designation, String upb, Double qpm, Double ppm, Double mpm, Double qpb,
			Double ppb, Double mpb, Double mrg, Double prcRg, LocalDate ddb, Integer dlb, LocalDate dfb,
			List<LotDeProduitDto> lotsDeProduit) {
		super();
		this.id = id;
		this.art = art;
		this.designation = designation;
		this.upb = upb;
		this.qpm = qpm;
		this.qpb = qpb;
		this.ppm = ppm;
		this.mpm = mpm;
		this.ppb = ppb;
		this.mpb = mpb;
		this.mrg = mrg;
		this.prcRg = prcRg;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
		this.lotsDeProduit = lotsDeProduit;
	}

	public ProduitTableDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<LotDeProduitDto> getLotsDeProduit() {
		return lotsDeProduit;
	}

	public void setLotsDeProduit(List<LotDeProduitDto> lotsDeProduit) {
		this.lotsDeProduit = lotsDeProduit;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Double getQpm() {
		return qpm;
	}

	public void setQpm(Double qpm) {
		this.qpm = qpm;
	}

	public Double getQpb() {
		return qpb;
	}

	public void setQpb(Double qpb) {
		this.qpb = qpb;
	}

	public Double getPpm() {
		return ppm;
	}

	public void setPpm(Double ppm) {
		this.ppm = ppm;
	}

	public Double getMpm() {
		return mpm;
	}

	public void setMpm(Double mpm) {
		this.mpm = mpm;
	}

	public Double getPpb() {
		return ppb;
	}

	public void setPpb(Double ppb) {
		this.ppb = ppb;
	}

	public Double getMpb() {
		return mpb;
	}

	public void setMpb(Double mpb) {
		this.mpb = mpb;
	}

	public Double getMrg() {
		return mrg;
	}

	public void setMrg(Double mrg) {
		this.mrg = mrg;
	}

	public Double getPrcRg() {
		return prcRg;
	}

	public void setPrcRg(Double prcRg) {
		this.prcRg = prcRg;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public Integer getDlb() {
		return dlb;
	}

	public void setDlb(Integer dlb) {
		this.dlb = dlb;
	}

	public LocalDate getDfb() {
		return dfb;
	}

	public void setDfb(LocalDate dfb) {
		this.dfb = dfb;
	}

	public List<LotDeProduitDto> getLotDeProduit() {
		return lotsDeProduit;
	}

	public void setLotDeProduit(List<LotDeProduitDto> lotDeProduit) {
		this.lotsDeProduit = lotDeProduit;
	}

	// for sigma functionality
	private ProduitTableDto sum(ProduitTableDto produitTableDto) {
		Double sumQpm = this.qpm + produitTableDto.getQpm();
		Double sumMpm = this.mpm + produitTableDto.getMpm();
		Double sumPpm = sumMpm / sumQpm;
		Double sumQpb = this.qpb + produitTableDto.getQpb();
		Double sumMpb = this.mpb + produitTableDto.getMpb();
		Double sumPpb = sumMpb / sumQpb;
		Double sumMrg = this.mrg + produitTableDto.getMrg();
		Double sumPrcRg = sumMrg / sumMpm;
		LocalDate minDdb = (this.ddb.isAfter(produitTableDto.getDdb())) ? produitTableDto.getDdb() : this.ddb;
		LocalDate maxDfb = (this.dfb.isAfter(produitTableDto.getDfb())) ? this.dfb : produitTableDto.getDfb();
		Integer sumDlb = (int) ChronoUnit.DAYS.between(minDdb, maxDfb) + 1;
		List<LotDeProduitDto> allLotDeProduits = new ArrayList<>();
		allLotDeProduits.addAll(this.lotsDeProduit);
		allLotDeProduits.addAll(produitTableDto.getLotDeProduit());
		allLotDeProduits = LotDeProduitDto.removeDuplicatesAndSum(allLotDeProduits);

		return new ProduitTableDto(this.id, this.art, this.designation, this.upb, sumQpm, sumPpm, sumMpm, sumQpb, sumPpb, sumMpb,
				sumMrg, sumPrcRg, minDdb, sumDlb, maxDfb, allLotDeProduits);
	}

	public static List<ProduitTableDto> removeDuplicatesAndSum(List<ProduitTableDto> produitTableDtos) {
		List<ProduitTableDto> sigmaProduitTableDtos = new ArrayList<>();
		
		if (produitTableDtos.size() == 0) 
			return sigmaProduitTableDtos;
		
		// sort produitTableDtos by designation:
		produitTableDtos.sort(Comparator.comparing(ProduitTableDto::getDesignation));
		
		ProduitTableDto prevProduit = produitTableDtos.get(0);

		for (int i = 1; i < produitTableDtos.size(); i++) {
			ProduitTableDto curProduit = produitTableDtos.get(i);

			if (prevProduit.getDesignation().equalsIgnoreCase(curProduit.getDesignation())) {
				prevProduit = prevProduit.sum(curProduit);
			}
			else {
				sigmaProduitTableDtos.add(prevProduit);
				prevProduit = curProduit;
			}
		}
		
		sigmaProduitTableDtos.add(prevProduit);

		return sigmaProduitTableDtos;
	}

}
