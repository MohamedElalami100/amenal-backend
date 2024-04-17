package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

import com.amenal.amenalbackend.budget.core.domain.MetreAv;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produit")
public class ProduitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Integer id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "unite")
    private String unite;

    @Column(name = "article")
    private String article;

    @Column(name = "pu_ref")
    private Double puRef;

    @Column(name = "qte_ref")
    private Double qteRef;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metre_av")
    private MetreAvEntity metre;

	public ProduitEntity(Integer id, String article, String designation, String unite, Double puRef, Double qteRef, MetreAvEntity metre) {
		this.id = id;
		this.article = article;
		this.designation = designation;
		this.unite = unite;
		this.puRef = puRef;
		this.qteRef = qteRef;
		this.metre = metre;
	}

	public ProduitEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Double getPuRef() {
		return puRef;
	}

	public void setPuRef(Double puRef) {
		this.puRef = puRef;
	}

	public Double getQteRef() {
		return qteRef;
	}

	public void setQteRef(Double qteRef) {
		this.qteRef = qteRef;
	}

	public MetreAvEntity getMetre() {
		return metre;
	}

	public void setMetre(MetreAvEntity metre) {
		this.metre = metre;
	}

}
