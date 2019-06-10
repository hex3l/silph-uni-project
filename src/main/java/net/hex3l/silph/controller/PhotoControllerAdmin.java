package net.hex3l.silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hex3l.silph.controller.validator.PhotoValidator;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.PhotoService;

@Controller
public class PhotoControllerAdmin {
	@Autowired
	private PhotoService photoService; 
	
	@Autowired
	private PhotoValidator photoValidator;

	
	@RequestMapping(value="/admin/photo/new",method= RequestMethod.POST)
	public String newPhoto(@Valid @ModelAttribute("photo") Photo photo, 
			Model model, BindingResult bindingResult) {
		this.photoValidator.validate(photo, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.photoService.inserisci(photo);
			return "/photo";
		}
		else {
			return "/photo";
		}
	}

}
