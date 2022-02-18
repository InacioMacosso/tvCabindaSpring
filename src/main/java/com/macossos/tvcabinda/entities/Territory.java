package com.macossos.tvcabinda.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.macossos.tvcabinda.entities.dtos.TerritoryDTO;

@Entity
public class Territory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "territory")
	private List<News> news = new ArrayList<>();

	public Territory() {
		super();
	}

	public Territory(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Territory(TerritoryDTO territoryDTO) {
		super();
		this.id = territoryDTO.getId();
		this.name = territoryDTO.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Territory other = (Territory) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
