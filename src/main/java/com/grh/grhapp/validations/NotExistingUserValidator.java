package com.grh.grhapp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.grh.grhapp.forms.UserForm;
import com.grh.grhapp.services.IUserService;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, UserForm> {

	private final IUserService userService;
	
	@Autowired
	public NotExistingUserValidator(IUserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean isValid(UserForm userForm, ConstraintValidatorContext context) {
		if (userService.userWithEmailExists(userForm.getEmail())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{UserAlreadyExisting}")
	            .addPropertyNode("email")
	            .addConstraintViolation();
				
			return false;
		}
		
		return true;
	}

}
