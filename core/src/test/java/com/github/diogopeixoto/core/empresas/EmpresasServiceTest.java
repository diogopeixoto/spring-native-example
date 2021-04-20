package com.github.diogopeixoto.core.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmpresasServiceTest {

	private final String CNPJ_VALID = "04.470.781/0001-39";

	private EmpresasService service;

	@Mock
	private Empresas empresas;

	@BeforeEach
	void setup() {
		service = new EmpresasServiceImpl(empresas);
	}

	@Test
	void add_ShouldAdd() {
		long id = 1;

		Empresa empresaToBeAdded = build(id);
		Empresa empresaToBeReturned = build(id);

		when(empresas.findByCnpj(empresaToBeAdded.cnpj())).thenReturn(null);
		when(empresas.add(empresaToBeAdded)).thenReturn(empresaToBeReturned);

		Empresa result = service.add(empresaToBeAdded);

		assertEquals(empresaToBeReturned, result);
		verify(empresas, times(1)).findByCnpj(empresaToBeAdded.cnpj());
		verify(empresas, times(1)).add(empresaToBeAdded);
	}

	@Test
	void add_ShouldNotAdd_EmpresaWithCnpjAlreadyExists() {
		long id = 1;

		Empresa empresaToBeAdded = build(id);
		Empresa empresaSearchedByCnpj = build(id);

		when(empresas.findByCnpj(empresaToBeAdded.cnpj())).thenReturn(empresaSearchedByCnpj);

		Assertions.assertThrows(RuntimeException.class, () -> {
			service.add(empresaToBeAdded);
		});

		verify(empresas, only()).findByCnpj(empresaToBeAdded.cnpj());
		verify(empresas, never()).add(empresaToBeAdded);
	}

	@Test
	void deleteById_ShouldCallEmpresasDeleteById() {
		long id = 1;

		doNothing().when(empresas).deleteById(id);

		service.deleteById(id);

		verify(empresas, only()).deleteById(id);
	}

	@Test
	void findByCnpj_ShouldReturnEmpresaFound() {
		long id = 1;
		String cnpj = "00.000.000/0000-00";
		Empresa empresaToBeReturned = build(id);

		when(empresas.findByCnpj(cnpj)).thenReturn(empresaToBeReturned);

		Empresa result = service.findByCnpj(cnpj);

		assertEquals(empresaToBeReturned, result);
		verify(empresas, times(1)).findByCnpj(cnpj);
	}

	@Test
	void findById_ShouldReturnEmpresaFound() {
		long id = 1;
		Empresa empresaToBeReturned = build(id);

		when(empresas.findById(id)).thenReturn(empresaToBeReturned);

		Empresa result = service.findById(id);

		assertEquals(empresaToBeReturned, result);
		verify(empresas, times(1)).findById(id);
	}

	@Test
	void update_ShouldReturnEmpresaUpdated_ChangeCnpjValue() {
		long id = 1;

		Empresa empresaToBeUpdated = build(id);
		Empresa empresaToBeReturned = build(id);

		when(empresas.findByCnpj(empresaToBeUpdated.cnpj())).thenReturn(null);
		when(empresas.update(empresaToBeUpdated)).thenReturn(empresaToBeReturned);

		Empresa result = service.update(empresaToBeUpdated);

		assertEquals(empresaToBeReturned, result);
		verify(empresas, times(1)).findByCnpj(empresaToBeUpdated.cnpj());
		verify(empresas, times(1)).update(empresaToBeUpdated);
	}

	@Test
	void update_ShouldReturnEmpresaUpdated_DontChangeCnpjValue() {
		long id = 1;

		Empresa empresaToBeUpdated = build(id);
		Empresa empresaToBeReturned = build(id);

		when(empresas.findByCnpj(empresaToBeUpdated.cnpj())).thenReturn(empresaToBeUpdated);
		when(empresas.update(empresaToBeUpdated)).thenReturn(empresaToBeReturned);

		Empresa result = service.update(empresaToBeUpdated);

		assertEquals(empresaToBeReturned, result);
		verify(empresas, times(1)).findByCnpj(empresaToBeUpdated.cnpj());
		verify(empresas, times(1)).update(empresaToBeUpdated);
	}

	@Test
	void update_shouldNotAdd_EmpresaWithCnpjAlreadyExists() {
		long id = 1;

		Empresa empresaToBeAdded = build(id);
		Empresa empresaSearchedByCnpj = build(id + 1);

		when(empresas.findByCnpj(empresaToBeAdded.cnpj())).thenReturn(empresaSearchedByCnpj);

		Assertions.assertThrows(RuntimeException.class, () -> {
			service.update(empresaToBeAdded);
		});

		verify(empresas, only()).findByCnpj(empresaToBeAdded.cnpj());
		verify(empresas, never()).add(empresaToBeAdded);
	}

	private Empresa build(long id) {
		return new Empresa(id, CNPJ_VALID, "Nome Fantasia", "Razão Social");
	}
}
