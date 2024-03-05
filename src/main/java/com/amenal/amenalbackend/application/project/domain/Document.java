package com.amenal.amenalbackend.application.project.domain;

public class Document {

    private String url;
    private String name;
    private String type;
    private Long size;
    
    private Avenant avenant;

	public Document(String url, String name, String type, Long size, Avenant avenant) {
		super();
		this.url = url;
		this.name = name;
		this.type = type;
		this.size = size;
		this.avenant = avenant;
	}

	public Document() {
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

	public Avenant getAvenant() {
		return avenant;
	}

	public void setAvenant(Avenant avenant) {
		this.avenant = avenant;
	}

}
