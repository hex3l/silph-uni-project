package net.hex3l.silph.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.hex3l.silph.model.data.Album;

@Component
public class AlbumValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Album.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		Album album = (Album) target;
		if(album.getPhotos() == null || album.getPhotos().isEmpty()) {
			errors.rejectValue("photos", "required");
		}
	}

}
