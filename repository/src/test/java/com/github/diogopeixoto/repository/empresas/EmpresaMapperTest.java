package com.github.diogopeixoto.repository.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.diogopeixoto.core.empresas.Empresa;

class EmpresaMapperTest {

	private final String CNPJ_INVALID = "04.470.781/0001-38";
	private final String CNPJ_VALID = "04.470.781/0001-39";

	private EmpresaMapper mapper = new EmpresaMapper();

	@Test
	void mapFromEntity_ShouldReturnEmpresa() {
		EmpresaEntity empresaEntity = buildEmpresaEntity(CNPJ_VALID);
		Empresa empresa = mapper.mapFromEntity(empresaEntity);

		compare(empresa, empresaEntity);
	}

	@Test
	void mapFromEntity_ShouldThrowIllegalArgumentException_CnpjInvalid() {
		EmpresaEntity empresaEntity = buildEmpresaEntity(CNPJ_INVALID);

		assertThrows(IllegalArgumentException.class, () -> {
			mapper.mapFromEntity(empresaEntity);
		});
	}

	@Test
	void mapToEntity_ShouldReturnEmpresaEntity() {
		Empresa empresa = buildEmpresa(CNPJ_VALID);
		EmpresaEntity empresaEntity = mapper.mapToEntity(empresa);

		compare(empresa, empresaEntity);
	}

	@Test
	void mapToEntity_ShouldThrowIllegalArgumentException_CnpjInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			buildEmpresa(CNPJ_INVALID);
		});
	}

	private void compare(Empresa empresa, EmpresaEntity empresaEntity) {
		assertEquals(empresa.id(), empresaEntity.getId());
		assertEquals(empresa.cnpj(), empresaEntity.getCnpj());
		assertEquals(empresa.nomeFantasia(), empresaEntity.getNomeFantasia());
		assertEquals(empresa.razaoSocial(), empresaEntity.getRazaoSocial());
	}

	private Empresa buildEmpresa(String cnpj) {
		return new Empresa(1, cnpj, "Nome Fantasia", "Razão Social");
	}

	private EmpresaEntity buildEmpresaEntity(String cnpj) {
		return new EmpresaEntity(cnpj, "Nome Fantasia", "Razão Social");
	}
}
