package net.hex3l.silph.controller.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.hex3l.silph.model.UsageRequest;

@Component
public class UsageRequestValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return UsageRequest.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UsageRequest request = (UsageRequest) target;
		if(request.getPhotos()==null || request.getPhotos().isEmpty()) {
			errors.rejectValue("photos", "required");
		}
	}

}
