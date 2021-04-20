package com.github.diogopeixoto.core.empresas;

public interface EmpresasService {

	Empresa add(Empresa empresa);

	void deleteById(long id);

	Empresa findByCnpj(String cnpj);

	Empresa findById(long id);

	Empresa update(Empresa empresa);
}
