package com.github.diogopeixoto.core.empresas;

public class EmpresaCreatorUtil {

	public static Empresa create() {
		long id = 0;
		String cnpj = "04.470.781/0001-39";
		String nomeFantasia = "Nome Fantasia";
		String razaoSocial = "Razão Social";

		return new Empresa(id, cnpj, nomeFantasia, razaoSocial);
	}
}
