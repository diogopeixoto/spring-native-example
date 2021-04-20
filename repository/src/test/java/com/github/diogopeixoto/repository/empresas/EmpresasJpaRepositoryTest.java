package com.github.diogopeixoto.repository.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmpresasJpaRepositoryTest {

	private final String CNPJ_INVALID = "04.470.781/0001-38";
	private final String CNPJ_VALID = "04.470.781/0001-39";

	@Autowired
	private EmpresasJpaRepository repository;

	@AfterEach
	void teardown() {
		repository.deleteAll();
	}

	@Test
	void findByCnpj_ShouldReturnNull_EmpresaNotFound() {
		EmpresaEntity empresaEntity = repository.findByCnpj(CNPJ_INVALID);

		assertNull(empresaEntity);
	}

	@Test
	void findByCnpj_ShouldReturnEmpresa_EmpresaFound() {
		EmpresaEntity empresa = buildEmpresa(CNPJ_VALID);
		empresa = repository.save(empresa);

		EmpresaEntity result = repository.findByCnpj(CNPJ_VALID);

		assertEquals(empresa, result);
	}

	private EmpresaEntity buildEmpresa(String cnpj) {
		return new EmpresaEntity(cnpj, "Nome Fantasia", "Razão Social");
	}
}
