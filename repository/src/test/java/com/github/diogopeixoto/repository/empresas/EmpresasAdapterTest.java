package com.github.diogopeixoto.repository.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.diogopeixoto.core.empresas.Empresa;

class EmpresasAdapterTest {

	private final String CNPJ_VALID = "04.470.781/0001-39";

	private EmpresasAdapter adapter;

	@Mock
	private EmpresaMapper mapper;

	@Mock
	private EmpresasJpaRepository repository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		adapter = new EmpresasAdapter(mapper, repository);
	}

	@Test
	void add_shouldAdd() {
		Empresa empresa = buildEmpresa();
		EmpresaEntity empresaEntity = buildEmpresaEntity();
		Empresa empresaUpdated = buildEmpresa();

		when(mapper.mapToEntity(empresa)).thenReturn(empresaEntity);
		when(repository.save(any())).thenReturn(empresaEntity);
		when(mapper.mapFromEntity(empresaEntity)).thenReturn(empresaUpdated);

		adapter.add(empresa);

		verify(mapper, times(1)).mapToEntity(empresa);
		verify(repository, only()).save(empresaEntity);
		verify(mapper, times(1)).mapFromEntity(empresaEntity);
		compare(empresaUpdated, empresaEntity);
	}

	@Test
	void deleteById_ShouldDelete() {
		long id = 0;

		doNothing().when(repository).deleteById(id);

		adapter.deleteById(id);

		verify(repository, only()).deleteById(id);
	}

	@Test
	void findByCnpj_ShouldReturnNull_EmpresaNotFound() {
		String cnpj = "00.000.000/0000-00";

		when(repository.findByCnpj(cnpj)).thenReturn(null);

		Empresa result = adapter.findByCnpj(cnpj);

		verify(repository, only()).findByCnpj(cnpj);
		assertNull(result);
	}

	@Test
	void findByCnpj_ShouldReturnEmpresa_EmpresaFound() {
		String cnpj = "00.000.000/0000-00";
		Empresa empresa = buildEmpresa();
		EmpresaEntity empresaEntity = buildEmpresaEntity();

		when(repository.findByCnpj(cnpj)).thenReturn(empresaEntity);
		when(mapper.mapFromEntity(empresaEntity)).thenReturn(empresa);

		Empresa result = adapter.findByCnpj(cnpj);

		verify(repository, only()).findByCnpj(cnpj);
		assertEquals(empresa, result);
	}

	@Test
	void findById_ShouldReturnNull_EmpresaNotFound() {
		long id = 0;

		when(repository.findById(id)).thenReturn(Optional.empty());

		Empresa result = adapter.findById(id);

		verify(repository, only()).findById(id);
		assertNull(result);
	}

	@Test
	void findById_ShouldReturnEmpresa_EmpresaFound() {
		long id = 0;
		Empresa empresa = buildEmpresa();
		EmpresaEntity empresaEntity = buildEmpresaEntity();

		when(repository.findById(id)).thenReturn(Optional.of(empresaEntity));
		when(mapper.mapFromEntity(empresaEntity)).thenReturn(empresa);

		Empresa result = adapter.findById(id);

		verify(repository, only()).findById(id);
		assertEquals(empresa, result);
	}

	@Test
	void update_ShouldUpdate() {
		Empresa empresa = buildEmpresa();
		EmpresaEntity empresaEntity = buildEmpresaEntity();
		Empresa empresaUpdated = buildEmpresa();

		when(mapper.mapToEntity(empresa)).thenReturn(empresaEntity);
		when(repository.save(empresaEntity)).thenReturn(empresaEntity);
		when(mapper.mapFromEntity(empresaEntity)).thenReturn(empresaUpdated);

		Empresa result = adapter.update(empresa);

		verify(mapper, times(1)).mapToEntity(empresa);
		verify(repository, only()).save(empresaEntity);
		verify(mapper, times(1)).mapFromEntity(empresaEntity);
		assertEquals(empresaUpdated, result);
	}

	private Empresa buildEmpresa() {
		return new Empresa(0, CNPJ_VALID, null, null);
	}

	private EmpresaEntity buildEmpresaEntity() {
		return new EmpresaEntity(CNPJ_VALID, null, null);
	}

	private void compare(Empresa empresa, EmpresaEntity empresaEntity) {
		assertEquals(empresaEntity.getId(), empresa.id());
		assertEquals(empresaEntity.getCnpj(), empresa.cnpj());
		assertEquals(empresaEntity.getNomeFantasia(), empresa.nomeFantasia());
		assertEquals(empresaEntity.getRazaoSocial(), empresa.razaoSocial());
	}
}
