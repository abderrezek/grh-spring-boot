package com.grh.grhapp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.isEmpty() || value == null) {
			return true;
		}
		
		if (value.matches("^0(5|6|7)[0-9]{8}$")) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("{MobileNotValidate}")
			.addConstraintViolation();
		
		return false;
	}

}
