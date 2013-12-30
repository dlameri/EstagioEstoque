package com.ideais.stock.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRODUTO")
public class Product {
	@Id
	@SequenceGenerator(name="product_id", sequenceName="product_id")
	@GeneratedValue(generator="product_id", strategy=GenerationType.AUTO)
	@Column(name="CD_PRODUTO")
	private Long id;
	
	@Column(name="CD_SKU")
	private Long sku;
	
	@Column(name="CD_PRECO_DE", nullable=false)
	private double priceFrom;
	
	@Column(name="CD_PRECO_POR", nullable=false)
	private double priceFor;
	
	@Column(name="NM_NOME_OPCAO")
	private String optionName;
	
	@Column(name="NM_VALOR_OPCAO")
	private String optionValue;
	
	@Column(name="CD_ESTOQUE", nullable=false)
	private Integer stock;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CD_ITEM", referencedColumnName="CD_ITEM", nullable=false)
	private Item item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}

	public double getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(double priceFor) {
		this.priceFor = priceFor;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
