package com.ideais.stock.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="SUBCATEGORIA")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Subcategory {
	@Id
	@SequenceGenerator(name="subcategory_id", sequenceName="subcategory_id")
	@GeneratedValue(generator="subcategory_id", strategy=GenerationType.AUTO)
	@Column(name="CD_SUBCATEGORIA")
	private Long id;
	
	@Column(name="NM_NOME", nullable=false)
	private String name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="CD_CATEGORIA", referencedColumnName="CD_CATEGORIA", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Category category;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
