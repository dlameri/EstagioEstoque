package com.ideais.stock.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ideais.stock.json.ProductJSON;

@Entity
@Table(name = "PRODUTO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product {
	@Id
	@SequenceGenerator(name = "product_id", sequenceName = "product_id")
	@GeneratedValue(generator = "product_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_PRODUTO")
	private Long id;

	@Column(name = "NM_NOME", nullable = false)
	private String name;

	@Column(name = "NM_DESCRICAO_LONGA", nullable = false)
	private String longDescription;

	@Column(name = "NM_DESCRICAO_CURTA", nullable = false)
	private String shortDescription;

	@Column(name = "NR_PESO", nullable = false)
	private Integer weight;

	@Column(name = "NR_GARANTIA", nullable = false)
	private Integer warranty;

	@Column(name = "NM_MARCA", nullable = false)
	private String brand;

	@Column(name = "NM_MODELO", nullable = false)
	private String model;
	
	@Column(name = "BO_ATIVO", nullable = false)
	private Boolean active;

	@Column(name = "NR_RANK", nullable = false)
	private Integer rank;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CD_SUBCATEGORIA", referencedColumnName = "CD_SUBCATEGORIA", nullable = false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Subcategory subcategory;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CD_CATEGORIA", referencedColumnName = "CD_CATEGORIA", nullable = false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Category category;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private List<Item> items;

	@OneToOne
	@JoinColumn(name = "CD_DIMENSOES", referencedColumnName = "CD_DIMENSOES", nullable = false)
	@Cascade(CascadeType.ALL)
	private Dimensions dimensions;
	
	public void softDelete() {
		active = false;
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public List<Item> getItems() {
	    return items;
	}

	public void setItems(List<Item> items) {
	    this.items = items;
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

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public ProductJSON toJSON() {
		return new ProductJSON(this);
	}
}