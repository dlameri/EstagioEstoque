package com.ideais.stock.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="CATEGORIA")
public class Category {
	@Id
	@SequenceGenerator(name="category_id", sequenceName="category_id")
	@GeneratedValue(generator="category_id", strategy=GenerationType.AUTO)
	@Column(name="CD_CATEGORIA")
	private Long id;
	
	@Column(name="NM_NOME", nullable=false, unique=true)
	private String name;
	
	@OneToMany(mappedBy="category", fetch = FetchType.EAGER, orphanRemoval=true)
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private List<Subcategory> subcategories;

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
