package com.github.diogopeixoto.core.empresas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CnpjTest {

	@Test
	void validate_ShouldThrowsIllegalArgumentException_DigitInvalid() {
		String cnpj = "04.470.781/0001-31";

		assertThrows(IllegalArgumentException.class, () -> new Cnpj(cnpj));
	}

	@Test
	void validate_ShouldThrowsIllegalArgumentException_EmptyString() {
		String cnpj = "";

		assertThrows(IllegalArgumentException.class, () -> new Cnpj(cnpj));
	}

	@Test
	void validate_ShouldThrowsIllegalArgumentException_Null() {
		String cnpj = null;

		assertThrows(IllegalArgumentException.class, () -> new Cnpj(cnpj));
	}

	@Test
	void validate_ShouldThrowsIllegalArgumentException_SequenceOfEqualNumbers() {
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("00.000.000/0000-00"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("11.111.111/1111-11"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("22.222.222/2222-22"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("33.333.333/3333-33"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("44.444.444/4444-44"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("55.555.555/5555-55"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("66.666.666/6666-66"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("77.777.777/7777-77"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("88.888.888/8888-88"));
		assertThrows(IllegalArgumentException.class, () -> new Cnpj("99.999.999/9999-99"));
	}

	@Test
	void validate_ShouldThrowsIllegalArgumentException_SizeInvalid() {
		String cnpj = "04.470.78/0001-39";

		assertThrows(IllegalArgumentException.class, () -> new Cnpj(cnpj));
	}

	@Test
	void validate_ShouldReturnTrue_CnpjValid() {
		String cnpj = "04.470.781/0001-39";
		Cnpj cnpjVO = new Cnpj(cnpj);

		assertEquals(cnpj, cnpjVO.get());
	}
}
