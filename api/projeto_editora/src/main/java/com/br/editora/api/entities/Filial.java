package com.br.editora.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * @author Leonardo Gallotti
 *
 */
@Entity
public class Filial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
    
	
	@Column(length = 150, nullable = false)
	@NotEmpty(message = "{nome.obrigatorio}")
	private String nomeFilial;

	public Filial() {

	}

	public Filial(Integer id, String nomeFilial) {
		super();
		this.id = id;
		this.nomeFilial = nomeFilial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFilial() {
		return nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}

	@Override
	public String toString() {
		return "Filial [id=" + id + ", nomeFilial=" + nomeFilial + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeFilial == null) ? 0 : nomeFilial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filial other = (Filial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeFilial == null) {
			if (other.nomeFilial != null)
				return false;
		} else if (!nomeFilial.equals(other.nomeFilial))
			return false;
		return true;
	}

}
