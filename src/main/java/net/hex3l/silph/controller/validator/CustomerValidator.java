package net.hex3l.silph.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.hex3l.silph.model.Customer;

@Component
public class CustomerValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "required");
		
		}

}
