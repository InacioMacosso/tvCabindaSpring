package com.macossos.tvcabinda.entities.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.macossos.tvcabinda.entities.News;

public class NewsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String photo;
	private String video;
	private String slug;
	private String body;
	private Boolean isBest = false;
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	private Integer territory;
	private Integer category;
	private Integer autor;
	private String territoryName;
	private String categoryName;
	private String autorName;

	public NewsDTO() {
		super();
	}

	public NewsDTO(News news) {
		super();
		this.id = news.getId();
		this.title = news.getTitle();
		this.photo = news.getPhoto();
		this.video = news.getVideo();
		this.slug = news.getSlug();
		this.body = news.getBody();
		this.isBest = news.getIsBest();
		this.dataCriacao = news.getDataCriacao();
		this.territory = news.getTerritory().getId();
		this.category = news.getCategory().getId();
		this.autor = news.getAutor().getId();
		this.territoryName = news.getTerritory().getName();
		this.categoryName = news.getCategory().getName();
		this.autorName = news.getAutor().getFirstName() + " " + news.getAutor().getLastName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Boolean getIsBest() {
		return isBest;
	}

	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Integer getTerritory() {
		return territory;
	}

	public void setTerritory(Integer territory) {
		this.territory = territory;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getAutor() {
		return autor;
	}

	public void setAutor(Integer autor) {
		this.autor = autor;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAutorName() {
		return autorName;
	}

	public void setAutorName(String autorName) {
		this.autorName = autorName;
	}

}
