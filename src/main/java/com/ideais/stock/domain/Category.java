package main.java.com.ideais.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
}
