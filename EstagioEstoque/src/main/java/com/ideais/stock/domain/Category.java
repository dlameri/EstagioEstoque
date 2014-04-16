package com.ideais.stock.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ideais.stock.json.CategoryJSON;

@Entity
@Table(name="CATEGORIA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="category_id", sequenceName="category_id")
	@GeneratedValue(generator="category_id", strategy=GenerationType.AUTO)
	@Column(name="CD_CATEGORIA")
	private Long id;
	
	@Column(name="NM_NOME", nullable=false, unique=true)
	private String name;
	
	@Column(name="BO_ATIVO", nullable=false)
	private Boolean active;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy="category")
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private List<Subcategory> subcategories;
	
	public void softDelete() {
		active = false;
	}

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return name;
	}
}
