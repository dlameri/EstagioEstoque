package main.java.com.ideais.stock.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {
	@Id
	@SequenceGenerator(name = "item_id", sequenceName = "item_id")
	@GeneratedValue(generator = "item_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_ITEM")
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

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CD_SUBCATEGORIA", referencedColumnName = "CD_SUBCATEGORIA", nullable = false)
	private Subcategory subcategory;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CD_CATEGORIA", referencedColumnName = "CD_CATEGORIA", nullable = false)
	private Category category;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CD_DIMENSOES", referencedColumnName = "CD_DIMENSOES", nullable = false)
	private Dimensions dimensions;

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
}