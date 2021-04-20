package com.github.diogopeixoto.core.empresas;

import com.github.diogopeixoto.core.exception.ObjectNotFound;

public class EmpresasServiceImpl implements EmpresasService {
	private Empresas empresas;

	public EmpresasServiceImpl(Empresas empresas) {
		super();
		this.empresas = empresas;
	}

	@Override
	public Empresa add(Empresa empresa) {
		Empresa empresaSearchByCnpj = empresas.findByCnpj(empresa.cnpj());

		if (empresaSearchByCnpj == null) {
			return empresas.add(empresa);
		} else {
			throw new ObjectNotFound(String.format("Empresa with cnpj = %s not found", empresa.cnpj()));
		}
	}

	@Override
	public void deleteById(long id) {
		empresas.deleteById(id);
	}

	@Override
	public Empresa findByCnpj(String cnpj) {
		return empresas.findByCnpj(cnpj);
	}

	@Override
	public Empresa findById(long id) {
		return empresas.findById(id);
	}

	@Override
	public Empresa update(Empresa empresa) {
		Empresa empresaSearchByCnpj = empresas.findByCnpj(empresa.cnpj());

		if (empresaSearchByCnpj == null || empresa.id() == empresaSearchByCnpj.id()) {
			return empresas.update(empresa);
		} else {
			throw new ObjectNotFound(String.format("Empresa with cnpj = %s not found", empresa.cnpj()));
		}
	}
}
