package com.github.diogopeixoto.controller.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmpresaDTOTest {

	private final String CNPJ_VALID = "04.470.781/0001-39";

	@Test
	void constructor_ShouldSetAttributesValues() {
		long id = 0;
		String nomeFantasia = "Nome Fantasia";
		String razaoSocial = "Razão Social";

		EmpresaDTO dto = new EmpresaDTO(id, CNPJ_VALID, nomeFantasia, razaoSocial);

		assertEquals(id, dto.getId());
		assertEquals(CNPJ_VALID, dto.getCnpj());
		assertEquals(nomeFantasia, dto.getNomeFantasia());
		assertEquals(razaoSocial, dto.getRazaoSocial());
	}

	@Test
	void emptyConstructor_ShouldNotSetAttributesValues() {
		EmpresaDTO dto = new EmpresaDTO();

		assertEquals(0, dto.getId());
		assertEquals("", dto.getCnpj());
		assertEquals("", dto.getNomeFantasia());
		assertEquals("", dto.getRazaoSocial());
	}
}
