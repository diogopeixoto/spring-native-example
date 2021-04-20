package com.github.diogopeixoto.controller.empresas;

import org.springframework.stereotype.Service;

import com.github.diogopeixoto.core.empresas.Empresa;

@Service(value = "empresasMapperDTO")
public class EmpresaMapper {

	public Empresa fromDTO(EmpresaDTO dto) {
		return new Empresa(dto.getId(), dto.getCnpj(), dto.getNomeFantasia(), dto.getRazaoSocial());
	}

	public EmpresaDTO toDTO(Empresa empresa) {
		return new EmpresaDTO(empresa.id(), empresa.cnpj(), empresa.nomeFantasia(), empresa.razaoSocial());
	}
}
