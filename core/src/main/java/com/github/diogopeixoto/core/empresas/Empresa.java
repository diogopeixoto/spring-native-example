package com.github.diogopeixoto.core.empresas;

public class Empresa {

	private long id;
	private Cnpj cnpj;
	private String nomeFantasia;
	private String razaoSocial;

	public Empresa(long id, String cnpj, String nomeFantasia, String razaoSocial) {
		super();
		this.id = id;
		this.cnpj = new Cnpj(cnpj);
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
	}

	public String cnpj() {
		return cnpj.get();
	}

	public long id() {
		return id;
	}

	public String nomeFantasia() {
		return nomeFantasia;
	}

	public String razaoSocial() {
		return razaoSocial;
	}
}
