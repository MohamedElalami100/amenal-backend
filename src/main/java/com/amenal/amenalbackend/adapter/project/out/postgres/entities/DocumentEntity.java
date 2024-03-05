package com.amenal.amenalbackend.adapter.project.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "document")
public class DocumentEntity {

    @Id
    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "size")
    private Long size;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avenant")
    private AvenantEntity avenant;

	public DocumentEntity(String url, String name, String type, Long size, AvenantEntity avenant) {
		super();
		this.url = url;
		this.name = name;
		this.type = type;
		this.size = size;
		this.avenant = avenant;
	}

	public DocumentEntity() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public AvenantEntity getAvenant() {
		return avenant;
	}

	public void setAvenant(AvenantEntity avenant) {
		this.avenant = avenant;
	}

}
