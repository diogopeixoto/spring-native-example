package com.github.diogopeixoto.repository.empresas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "empresas")
public class EmpresaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String cnpj;
	private String nomeFantasia;
	private String razaoSocial;

	EmpresaEntity() {

	}

	EmpresaEntity(long id, String cnpj, String nomeFantasia, String razaoSocial) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
	}

	EmpresaEntity(String cnpj, String nomeFantasia, String razaoSocial) {
		this(0, cnpj, nomeFantasia, razaoSocial);
	}

	public String getCnpj() {
		return cnpj;
	}

	public long getId() {
		return id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}
}
