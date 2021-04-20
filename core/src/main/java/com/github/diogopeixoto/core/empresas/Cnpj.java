package com.github.diogopeixoto.core.empresas;

public class Cnpj {

	private static final int[] PESO_CNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private String value;

	public Cnpj(String value) {
		super();
		validateAndSet(value);
	}

	public String get() {
		return value;
	}

	private void validateAndSet(String value) {
		if (validate(value)) {
			this.value = value;
		} else {
			throw new IllegalArgumentException(String.format("Cnpj %s invalid", value));
		}
	}

	public boolean validate(String value) {
		boolean result = false;

		if (value != null) {
			String valueOnlyNumbers = clearFormat(value);

			result = isLengthValid(valueOnlyNumbers) && !isEqualSequenceNumbers(valueOnlyNumbers)
					&& isDigitsValid(valueOnlyNumbers);
		}

		return result;
	}

	private int calculateDigit(String cnpj) {
		int sum = 0;
		for (int index = cnpj.length() - 1, digit; index >= 0; index--) {
			digit = Integer.parseInt(cnpj.substring(index, index + 1));
			sum += digit * PESO_CNPJ[PESO_CNPJ.length - cnpj.length() + index];
		}
		sum = 11 - sum % 11;

		return sum > 9 ? 0 : sum;
	}

	private String clearFormat(String value) {
		return value.trim().replace(".", "").replace("-", "").replace("/", "");
	}

	private boolean isDigitsValid(String value) {
		Integer digito1 = calculateDigit(value.substring(0, 12));
		Integer digito2 = calculateDigit(value.substring(0, 12) + digito1);

		return value.equals(value.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	private boolean isEqualSequenceNumbers(String value) {
		return value.equals("00000000000000") || value.equals("11111111111111") || value.equals("22222222222222")
				|| value.equals("33333333333333") || value.equals("44444444444444") || value.equals("55555555555555")
				|| value.equals("66666666666666") || value.equals("77777777777777") || value.equals("88888888888888")
				|| value.equals("99999999999999");
	}

	private boolean isLengthValid(String value) {
		return value.length() == 14;
	}
}
