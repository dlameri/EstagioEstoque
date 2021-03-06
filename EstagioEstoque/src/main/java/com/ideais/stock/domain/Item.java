package com.ideais.stock.domain;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="ITEM")
public class Item {
	@Id
	@SequenceGenerator(name="item_id", sequenceName="item_id")
	@GeneratedValue(generator="item_id", strategy=GenerationType.AUTO)
	@Column(name="CD_ITEM")
	private Long id;
	
	@Column(name="NR_SKU")
	private Long sku;
	
	@Column(name = "NR_PRECO_DE", precision = 7, scale = 2, nullable = false)
	private BigDecimal priceFrom;

	@Column(name = "NR_PRECO_POR", precision = 7, scale = 2, nullable = false)
	private BigDecimal priceFor;
	
	@Column(name="NM_NOME_OPCAO")
	private String optionName;
	
	@Column(name="NM_VALOR_OPCAO")
	private String optionValue;
	
	@Column(name="NR_ESTOQUE", nullable=false)
	private Integer stock;
	
	@Column(name="NM_RANK", nullable = false)
	private Integer rank;

	@Column(name="BO_ATIVO", nullable=false)
	private Boolean active;
	
	@Column(name="BO_PROMO", nullable=false)
	private Boolean promo = false;
	
	@Transient
	private Integer count;
	
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="CD_PRODUTO", referencedColumnName="CD_PRODUTO", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Product product;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy="item")
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private List<Image> images;
	
	public String valueFormatter(BigDecimal value) {
	    Locale Local = new Locale("pt", "BR");
	    DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Local));
	    return "R$ " + df.format(value);
	}
	
	public void softDelete() {
		active = false;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getRank() {
		return rank;
	}
	
	public Long getProductId() {
	    return product.getId();
	}

	public String getProductName() {
	    return product.getName();
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(BigDecimal priceFor) {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public String getFormattedPriceFrom() {
	    return valueFormatter(priceFrom);
	}

	public String getFormattedPriceFor() {
		return valueFormatter(priceFor);
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Boolean getPromo() {
		return promo;
	}

	public void setPromo(Boolean promo) {
		this.promo = promo;
	}

	@Override
	public String toString() {
		return id.toString();
	}
}