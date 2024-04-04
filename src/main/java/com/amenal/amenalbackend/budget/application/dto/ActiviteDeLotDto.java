package com.amenal.amenalbackend.budget.application.dto;

import java.time.LocalDate;

public class ActiviteDeLotDto {
	private Integer id;
	private Boolean c;
	private Integer ordre;
	private String activite;
	private String upb;
	private Double qpm;
	private Double ppm;
	private Double mpm;
	private Double qpb;
	private Double ppb;
	private Double mpb;
	private Double mrg;
	private Double prcRg;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;

	public ActiviteDeLotDto(Integer id, Boolean c, Integer ordre, String activite, String upb, Double qpm, Double ppm, Double mpm,
			Double qpb, Double ppb, Double mpb, Double mrg, Double prcRg, LocalDate ddb, Integer dlb, LocalDate dfb) {
		super();
		this.id =id;
		this.c = c;
		this.ordre = ordre;
		this.activite = activite;
		this.upb = upb;
		this.qpm = qpm;
		this.ppm = ppm;
		this.mpm = mpm;
		this.qpb = qpb;
		this.ppb = ppb;
		this.mpb = mpb;
		this.mrg = mrg;
		this.prcRg = prcRg;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
	}

	public ActiviteDeLotDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getC() {
		return c;
	}

	public void setC(Boolean c) {
		this.c = c;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
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

	public Double getQpm() {
		return qpm;
	}

	public void setQpm(Double qpm) {
		this.qpm = qpm;
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

	public Double getQpb() {
		return qpb;
	}

	public void setQpb(Double qpb) {
		this.qpb = qpb;
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

}
