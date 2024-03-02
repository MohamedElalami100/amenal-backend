package com.amenal.amenalbackend.application.project.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LotTableDto {
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
	private List<ActiviteDeLotDto> activite;

	public LotTableDto(Integer id, Integer ordre, String lot, Double mpm, Double mpb, Double mrg, Double prcRg,
			LocalDate ddb, Integer dlb, LocalDate dfb, List<ActiviteDeLotDto> activite) {
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
		this.activite = activite;
	}

	public LotTableDto() {
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

	public List<ActiviteDeLotDto> getActivite() {
		return activite;
	}

	public void setActivite(List<ActiviteDeLotDto> activite) {
		this.activite = activite;
	}

	private LotTableDto sum(LotTableDto lotTableDto) {
		Double sumMpm = this.mpm + lotTableDto.getMpm();
		Double sumMpb = this.mpb + lotTableDto.getMpb();
		Double sumMrg = this.mrg + lotTableDto.getMrg();
		Double sumPrcRg = sumMrg / sumMpm;
		LocalDate minDdb = (this.ddb.isAfter(lotTableDto.getDdb())) ? lotTableDto.getDdb() : this.ddb;
		LocalDate maxDfb = (this.dfb.isAfter(lotTableDto.getDfb())) ? this.dfb : lotTableDto.getDfb();
		Integer sumDlb = (int) ChronoUnit.DAYS.between(minDdb, maxDfb) + 1;
		List<ActiviteDeLotDto> allActivites = new ArrayList<>();
		allActivites.addAll(this.activite);
		allActivites.addAll(lotTableDto.getActivite());

		return new LotTableDto(this.id, this.ordre, this.lot, sumMpm, sumMpb, sumMrg, sumPrcRg, minDdb, sumDlb, maxDfb,
				allActivites);
	}

	public static List<LotTableDto> removeDuplicatesAndSum(List<LotTableDto> lotTableDtos) {
		List<LotTableDto> sigmaLotTableDtos = new ArrayList<>();

		if (lotTableDtos.size() == 0)
			return sigmaLotTableDtos;

		// sort lotTableDtos by designation:
		lotTableDtos.sort(Comparator.comparing(LotTableDto::getLot));

		LotTableDto prevLot = lotTableDtos.get(0);

		for (int i = 1; i < lotTableDtos.size(); i++) {
			LotTableDto curLot = lotTableDtos.get(i);

			if (prevLot.getLot().equalsIgnoreCase(curLot.getLot())) {
				prevLot = prevLot.sum(curLot);
			} else {
				sigmaLotTableDtos.add(prevLot);
				prevLot = curLot;
			}
		}

		sigmaLotTableDtos.add(prevLot);

		return sigmaLotTableDtos;
	}

}
