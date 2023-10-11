package com.statistics.vector.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for vector records.
 *
 */
@Entity
@Table(name = "VECTOR_DATA")
public class VectorData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vectorName;
	@Column(length = 8000) 
	private String numbers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVectorName() {
		return vectorName;
	}

	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
}
