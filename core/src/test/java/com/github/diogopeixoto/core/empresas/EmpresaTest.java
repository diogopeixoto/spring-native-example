package com.github.diogopeixoto.core.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EmpresaTest {

	private final String CNPJ_INVALID = "04.470.781/0001-31";
	private final String CNPJ_VALID = "04.470.781/0001-39";

	@Test
	void constructor_ShouldSetAttributesValues() {
		long id = 1;
		String cnpj = CNPJ_VALID;
		String nomeFantasia = "Nome Fantasia";
		String razaoSocial = "Razão Social";

		Empresa empresa = new Empresa(id, cnpj, nomeFantasia, razaoSocial);

		assertAttributes(empresa, cnpj, id, nomeFantasia, razaoSocial);
	}

	@Test
	void constructor_ShouldThrowIllegalArgumentException_CnpjInvalid() {
		long id = 1;
		String cnpj = CNPJ_INVALID;
		String nomeFantasia = "Nome Fantasia";
		String razaoSocial = "Razão Social";

		assertThrows(IllegalArgumentException.class, () -> {
			new Empresa(id, cnpj, nomeFantasia, razaoSocial);
		});
	}

	private void assertAttributes(Empresa empresa, String cnpj, long idNovo, String nomeFantasia, String razaoSocial) {
		assertEquals(cnpj, empresa.cnpj());
		assertEquals(idNovo, empresa.id());
		assertEquals(nomeFantasia, empresa.nomeFantasia());
		assertEquals(razaoSocial, empresa.razaoSocial());
	}
}
