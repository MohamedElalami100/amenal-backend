package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.TacheEntity;

@Entity
@Table(name = "besoin")
public class BesoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_besoin")
    private Integer id;

    @Column(name = "date_prevu")
    private LocalDate datePrevu;

    @Column(name = "qte")
    private Double qte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_charge_standard")
    private ChargeStandardEntity charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tache")
    private TacheEntity tache;

    @OneToMany(mappedBy = "besoin")
    private List<DetailBesoinEntity> detailBesoins;

 	public BesoinEntity(Integer id, LocalDate datePrevu, Double qte, ChargeStandardEntity charge, TacheEntity tache,
			List<DetailBesoinEntity> detailBesoins) {
		super();
		this.id = id;
		this.datePrevu = datePrevu;
		this.qte = qte;
		this.charge = charge;
		this.tache = tache;
		this.detailBesoins = detailBesoins;
	}

	public BesoinEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatePrevu() {
		return datePrevu;
	}

	public void setDatePrevu(LocalDate datePrevu) {
		this.datePrevu = datePrevu;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public ChargeStandardEntity getCharge() {
		return charge;
	}

	public void setCharge(ChargeStandardEntity charge) {
		this.charge = charge;
	}

	public TacheEntity getTache() {
		return tache;
	}

	public void setTache(TacheEntity tache) {
		this.tache = tache;
	}

	public List<DetailBesoinEntity> getDetailBesoins() {
		return detailBesoins;
	}

	public void setDetailBesoins(List<DetailBesoinEntity> detailBesoins) {
		this.detailBesoins = detailBesoins;
	}
}
