package com.amenal.amenalbackend.application.project.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DetailDelaiTableDto {
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;
	private Integer produitId;
	private Integer lotId;
	private Integer activiteId;

	public DetailDelaiTableDto(String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			LocalDate ddb, Integer dlb, LocalDate dfb, Integer produitId, Integer lotId, Integer activiteId) {
		super();
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
		this.produitId = produitId;
		this.lotId = lotId;
		this.activiteId = activiteId;
	}

	public DetailDelaiTableDto() {
		super();
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Boolean getCle() {
		return cle;
	}

	public void setCle(Boolean cle) {
		this.cle = cle;
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

	public Integer getProduitId() {
		return produitId;
	}

	public void setProduitId(Integer produitId) {
		this.produitId = produitId;
	}

	public Integer getLotId() {
		return lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public Integer getActiviteId() {
		return activiteId;
	}

	public void setActiviteId(Integer activiteId) {
		this.activiteId = activiteId;
	}

	// trannsform a list of DetailDelaiTable to a list of RowDelai:
	public static List<RowDelaiDto> transformToRowDelais(List<DetailDelaiTableDto> detailDelaiTableDtos) {
		List<RowDelaiDto> rowDelaiDtos = new ArrayList<>();
		
		if (detailDelaiTableDtos.isEmpty()) {
			return rowDelaiDtos;
		}

		// Remove DetailDelai with null attributes
		detailDelaiTableDtos = detailDelaiTableDtos.stream()
				.filter(detail -> detail.getDdb() != null && detail.getDfb() != null && detail.getDlb() != null)
				.collect(Collectors.toList());

		// Sorting the list by dateDebut
		Collections.sort(detailDelaiTableDtos, Comparator.comparing(DetailDelaiTableDto::getDdb));

		LocalDate prevDdb = detailDelaiTableDtos.get(0).getDdb();
		LocalDate prevDfb = detailDelaiTableDtos.get(0).getDfb();
		Integer prevDlb = detailDelaiTableDtos.get(0).getDlb();

		for (int i = 1; i < detailDelaiTableDtos.size(); i++) {
			LocalDate curDdb = detailDelaiTableDtos.get(i).getDdb();
			LocalDate curDfb = detailDelaiTableDtos.get(i).getDfb();
			Integer curDlb = detailDelaiTableDtos.get(i).getDlb();

			if ((int) ChronoUnit.DAYS.between(prevDfb, curDdb) <= 1) {
				prevDfb = (prevDfb.isAfter(curDfb)) ? prevDfb : curDfb;
				prevDlb = (int) ChronoUnit.DAYS.between(prevDdb, prevDfb) + 1;
			} else {
				// add the white dates:
				rowDelaiDtos.add(new RowDelaiDto(prevDdb, prevDlb, prevDfb));

				// add the red dates:
				LocalDate redDdb = prevDfb.plusDays(1);
				LocalDate redDfb = curDdb.plusDays(-1);
				Integer redDlb = (int) ChronoUnit.DAYS.between(redDdb, redDfb) + 1;
				rowDelaiDtos.add(new RowDelaiDto(redDdb, redDlb, redDfb));

				prevDdb = curDdb;
				prevDfb = curDfb;
				prevDlb = curDlb;
			}

		}
		// add the last white date:
		rowDelaiDtos.add(new RowDelaiDto(prevDdb, prevDlb, prevDfb));

		return rowDelaiDtos;

	}
}
