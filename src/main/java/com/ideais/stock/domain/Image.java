package main.java.com.ideais.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String showcasePath;
	
	@Column(name="NM_CAMINHO_PRODUTO")
	private String productPath;
	
	@Column(name="NM_CAMINHO_SUPERZOOM")
	private String superzoomPath;
	
	@Column(name="NM_CAMINHO_CARRINHO")
	private String shoppingCartPath;
	
	@Column(name="NM_CAMINHO_ANDROID_VITRINE")
	private String androidShowcasePath;
	
	@Column(name="NM_CAMINHO_ANDROID_PRODUTO")
	private String androidProductPath;
	
	@Column(name="BO_PRINCIPAL")
	private Boolean main;
	
	@ManyToOne
	@JoinColumn(name="CD_PRODUTO", referencedColumnName="CD_PRODUTO", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowcasePath() {
		return showcasePath;
	}

	public void setShowcasePath(String showcasePath) {
		this.showcasePath = showcasePath;
	}

	public String getProductPath() {
		return productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}

	public String getSuperzoomPath() {
		return superzoomPath;
	}

	public void setSuperzoomPath(String superzoomPath) {
		this.superzoomPath = superzoomPath;
	}

	public String getShoppingCartPath() {
		return shoppingCartPath;
	}

	public void setShoppingCartPath(String shoppingCartPath) {
		this.shoppingCartPath = shoppingCartPath;
	}

	public String getAndroidShowcasePath() {
		return androidShowcasePath;
	}

	public void setAndroidShowcasePath(String androidShowcasePath) {
		this.androidShowcasePath = androidShowcasePath;
	}

	public String getAndroidProductPath() {
		return androidProductPath;
	}

	public void setAndroidProductPath(String androidProductPath) {
		this.androidProductPath = androidProductPath;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}