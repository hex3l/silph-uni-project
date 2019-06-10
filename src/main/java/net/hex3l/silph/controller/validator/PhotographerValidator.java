package net.hex3l.silph.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.hex3l.silph.model.data.Photographer;

@Component
public class PhotographerValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Photographer.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		//Validate album and image as numbers and imageext as png/jpeg
	}

}
