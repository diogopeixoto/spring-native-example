package com.github.diogopeixoto.repository.empresas;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.diogopeixoto.core.empresas.Empresa;
import com.github.diogopeixoto.core.empresas.Empresas;

@Service
public class EmpresasAdapter implements Empresas {

	private EmpresaMapper mapper;
	private EmpresasJpaRepository repository;

	public EmpresasAdapter(EmpresaMapper mapper, EmpresasJpaRepository repository) {
		super();
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Empresa add(Empresa empresa) {
		EmpresaEntity empresaEntity = mapper.mapToEntity(empresa);
		empresaEntity = repository.save(empresaEntity);

		return mapper.mapFromEntity(empresaEntity);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public Empresa findByCnpj(String cnpj) {
		EmpresaEntity empresaEntity = repository.findByCnpj(cnpj);

		return (empresaEntity == null ? null : mapper.mapFromEntity(empresaEntity));
	}

	@Override
	public Empresa findById(long id) {
		Optional<EmpresaEntity> empresaEntity = repository.findById(id);

		return (empresaEntity.isPresent() ? mapper.mapFromEntity(empresaEntity.get()) : null);
	}

	@Override
	public Empresa update(Empresa empresa) {
		return add(empresa);
	}
}
