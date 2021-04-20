package com.github.diogopeixoto.repository.empresas;

import org.springframework.stereotype.Component;

import com.github.diogopeixoto.core.empresas.Empresa;

@Component
public class EmpresaMapper {

	public Empresa mapFromEntity(EmpresaEntity empresaEntity) {
		return new Empresa(empresaEntity.getId(), empresaEntity.getCnpj(), empresaEntity.getNomeFantasia(),
				empresaEntity.getRazaoSocial());
	}

	public EmpresaEntity mapToEntity(Empresa empresa) {
		return new EmpresaEntity(empresa.id(), empresa.cnpj(), empresa.nomeFantasia(), empresa.razaoSocial());
	}
}
