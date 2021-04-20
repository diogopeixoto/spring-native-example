package com.github.diogopeixoto.controller.empresas;

public class EmpresaDTO {

	private long id;
	private String cnpj;
	private String nomeFantasia;
	private String razaoSocial;

	public EmpresaDTO() {
		this.id = 0;
		this.cnpj = "";
		this.nomeFantasia = "";
		this.razaoSocial = "";
	}

	public EmpresaDTO(long id, String cnpj, String nomeFantasia, String razaoSocial) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
	}

	public long getId() {
		return id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setId(long id) {
		this.id = id;
	}
}
