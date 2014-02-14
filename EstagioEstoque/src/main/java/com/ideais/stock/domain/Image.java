package com.ideais.stock.domain;

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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="IMAGENS")
public class Image {
	@Id
	@SequenceGenerator(name="image_id", sequenceName="image_id")
	@GeneratedValue(generator="image_id", strategy=GenerationType.AUTO)
	@Column(name="CD_IMAGEM")
	private Long id;
	
	@Column(name="NM_CAMINHO_VITRINE")
	private String showcaseUrl;
	
	@Column(name="NM_CAMINHO_PRODUTO")
	private String productUrl;
	
	@Column(name="NM_CAMINHO_SUPERZOOM")
	private String superzoomUrl;
	
	@Column(name="NM_CAMINHO_CARRINHO")
	private String shoppingCartUrl;
	
	@Column(name="NM_CAMINHO_ANDROID_VITRINE")
	private String androidShowcaseUrl;
	
	@Column(name="NM_CAMINHO_ANDROID_PRODUTO")
	private String androidProductUrl;
	
	@Column(name="BO_PRINCIPAL")
	private Boolean main;
	
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="CD_ITEM", referencedColumnName="CD_ITEM", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Item item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowcaseUrl() {
		return showcaseUrl;
	}

	public void setShowcaseUrl(String showcaseUrl) {
		this.showcaseUrl = showcaseUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getSuperzoomUrl() {
		return superzoomUrl;
	}

	public void setSuperzoomUrl(String superzoomUrl) {
		this.superzoomUrl = superzoomUrl;
	}

	public String getShoppingCartUrl() {
		return shoppingCartUrl;
	}

	public void setShoppingCartUrl(String shoppingCartUrl) {
		this.shoppingCartUrl = shoppingCartUrl;
	}

	public String getAndroidShowcaseUrl() {
		return androidShowcaseUrl;
	}

	public void setAndroidShowcaseUrl(String androidShowcaseUrl) {
		this.androidShowcaseUrl = androidShowcaseUrl;
	}

	public String getAndroidProductUrl() {
		return androidProductUrl;
	}

	public void setAndroidProductUrl(String androidProductUrl) {
		this.androidProductUrl = androidProductUrl;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public Item getProduct() {
		return item;
	}

	public void setProduct(Item product) {
		this.item = product;
	}
	
	@Override
	public String toString() {
		return productUrl;
	}
}