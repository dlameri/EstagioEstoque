package com.ideais.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DIMENSOES")
public class Dimensions {
	
	@Id
	@SequenceGenerator(name = "dimensions_id", sequenceName = "dimensions_id")
	@GeneratedValue(generator = "dimensions_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_DIMENSOES")
	private Long id;
	
	@Column(name = "NM_ALTURA", nullable = false)
	private Double height;
	
	@Column(name = "NM_LARGURA", nullable = false)
	private Double width;
	
	@Column(name = "NM_COMPRIMENTO", nullable = false)
	private Double depth;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}
}
