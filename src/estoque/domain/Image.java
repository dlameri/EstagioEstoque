package estoque.domain;

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
@Table(name="IMAGENS")
public class Image {
	@Id
	@SequenceGenerator(name="image_id", sequenceName="image_id")
	@GeneratedValue(generator="image_id", strategy=GenerationType.AUTO)
	@Column(name="CD_IMAGEM")
	private Long id;
	
	@Column(name="NM_CAMINHO")
	private String path;
	
	@Column(name="BO_PRINCIPAL")
	private Boolean main;
	
	@ManyToOne
	@JoinColumn(name="CD_PRODUTO")
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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