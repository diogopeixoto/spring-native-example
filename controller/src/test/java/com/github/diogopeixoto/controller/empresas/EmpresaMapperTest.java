package com.github.diogopeixoto.controller.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.diogopeixoto.core.empresas.Empresa;

class EmpresaMapperTest {

	private final String CNPJ_INVALID = "04.470.781/0001-31";
	private final String CNPJ_VALID = "04.470.781/0001-39";

	private EmpresaMapper mapper = new EmpresaMapper();

	@Test
	void fromDTO_ShouldNotSetAllAttributes_CnpjInvalid() {
		EmpresaDTO dto = buildEmpresaDTO(CNPJ_INVALID);

		assertThrows(IllegalArgumentException.class, () -> {
			mapper.fromDTO(dto);
		});
	}

	@Test
	void fromDTO_ShouldSetAllAttributes() {
		EmpresaDTO dto = buildEmpresaDTO(CNPJ_VALID);
		Empresa empresa = mapper.fromDTO(dto);

		assertAttributes(empresa, dto);
	}

	@Test
	void toDTO_ShouldSetAllAttributes() {
		Empresa empresa = buildEmpresa(CNPJ_VALID);
		EmpresaDTO dto = mapper.toDTO(empresa);

		assertAttributes(empresa, dto);
	}

	private void assertAttributes(Empresa empresa, EmpresaDTO dto) {
		assertEquals(empresa.id(), dto.getId());
		assertEquals(empresa.cnpj(), dto.getCnpj());
		assertEquals(empresa.nomeFantasia(), dto.getNomeFantasia());
		assertEquals(empresa.razaoSocial(), dto.getRazaoSocial());
	}

	private Empresa buildEmpresa(String cnpj) {
		return new Empresa(1, cnpj, "Nome Fantasia", "Razão Social");
	}

	private EmpresaDTO buildEmpresaDTO(String cnpj) {
		return new EmpresaDTO(1, cnpj, "Nome Fantasia", "Razão Social");
	}
}
