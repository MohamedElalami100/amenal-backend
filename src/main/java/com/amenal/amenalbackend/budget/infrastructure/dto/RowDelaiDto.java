package com.amenal.amenalbackend.budget.infrastructure.dto;

import java.time.LocalDate;

public class RowDelaiDto {
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;

	public RowDelaiDto(LocalDate ddb, Integer dlb, LocalDate dfb) {
		super();
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
	}

	public RowDelaiDto() {
		super();
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
