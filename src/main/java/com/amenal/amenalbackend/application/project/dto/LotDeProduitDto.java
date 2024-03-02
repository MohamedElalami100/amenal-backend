package com.amenal.amenalbackend.application.project.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LotDeProduitDto {
	private Integer id;
	private Integer ordre;
	private String lot;
	private Double mpm;
	private Double mpb;
	private Double mrg;
	private Double prcRg;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;
	private List<ActivitePrincipaleDto> activitePrincipale;

	

	public LotDeProduitDto(Integer id, Integer ordre, String lot, Double mpm, Double mpb, Double mrg, Double prcRg,
			LocalDate ddb, Integer dlb, LocalDate dfb, List<ActivitePrincipaleDto> activitePrincipale) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.lot = lot;
		this.mpm = mpm;
		this.mpb = mpb;
		this.mrg = mrg;
		this.prcRg = prcRg;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
		this.activitePrincipale = activitePrincipale;
	}

	public LotDeProduitDto() {
		super();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Double getMpm() {
		return mpm;
	}

	public void setMpm(Double mpm) {
		this.mpm = mpm;
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

	public List<ActivitePrincipaleDto> getActivitePrincipale() {
		return activitePrincipale;
	}

	public void setActivitePrincipale(List<ActivitePrincipaleDto> activitePrincipale) {
		this.activitePrincipale = activitePrincipale;
	}

	private LotDeProduitDto sum(LotDeProduitDto lotDeProduitDto) {
		Double sumMpm = this.mpm + lotDeProduitDto.getMpm();
		Double sumMpb = this.mpb + lotDeProduitDto.getMpb();
		Double sumMrg = this.mrg + lotDeProduitDto.getMrg();
		Double sumPrcRg = sumMrg / sumMpm;
		LocalDate minDdb = (this.ddb.isAfter(lotDeProduitDto.getDdb())) ? lotDeProduitDto.getDdb() : this.ddb;
		LocalDate maxDfb = (this.dfb.isAfter(lotDeProduitDto.getDfb())) ? this.dfb : lotDeProduitDto.getDfb();
		Integer sumDlb = (int) ChronoUnit.DAYS.between(minDdb, maxDfb) + 1;
		List<ActivitePrincipaleDto> allActivitePrincipales = new ArrayList<>();
		allActivitePrincipales.addAll(this.activitePrincipale);
		allActivitePrincipales.addAll(lotDeProduitDto.getActivitePrincipale());

		return new LotDeProduitDto(this.id, this.ordre, this.lot, sumMpm, sumMpb, sumMrg, sumPrcRg, minDdb,
				sumDlb, maxDfb, allActivitePrincipales);
	}
	
	public static List<LotDeProduitDto> removeDuplicatesAndSum(List<LotDeProduitDto> lotDeProduitDtos){
		List<LotDeProduitDto> sigmaLotTableDtos = new ArrayList<>();
		
		if (lotDeProduitDtos.size() == 0) 
			return lotDeProduitDtos;

		// sort lotDeProduitDtos by designation:
		lotDeProduitDtos.sort(Comparator.comparing(LotDeProduitDto::getLot));
		
		LotDeProduitDto prevLot = lotDeProduitDtos.get(0);

		for (int i = 1; i < lotDeProduitDtos.size(); i++) {
			LotDeProduitDto curLot = lotDeProduitDtos.get(i);

			if (prevLot.getLot() == curLot.getLot()) {
				prevLot = prevLot.sum(curLot);
			}
			else {
				sigmaLotTableDtos.add(prevLot);
				prevLot = curLot;
			}
		}
		
		sigmaLotTableDtos.add(prevLot);

		return sigmaLotTableDtos;		
	}

}
