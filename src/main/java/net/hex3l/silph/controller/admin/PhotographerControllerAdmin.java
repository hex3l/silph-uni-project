package net.hex3l.silph.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hex3l.silph.controller.validator.PhotographerValidator;
import net.hex3l.silph.model.data.Photographer;
import net.hex3l.silph.services.PhotographerService;

@Controller
public class PhotographerControllerAdmin {
	@Autowired
	private PhotographerService photographerService; 
	
	@Autowired
	private PhotographerValidator photographerValidator;

	
	@RequestMapping(value="/admin/photographer/new",method= RequestMethod.POST)
	public String createPhotographer(@Valid @ModelAttribute("photographer") Photographer photographer, 
			Model model, BindingResult bindingResult) {
		this.photographerValidator.validate(photographer, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.photographerService.add(photographer);
			model.addAttribute(photographer);
			return "admin/photographer/photographerConfirm";
		} else {
			model.addAttribute("photographer", photographer);
			return "admin/photographer/newPhotographer";
		}
	}
	
	@RequestMapping(value="/admin/photographer/new",method= RequestMethod.GET)
	public String newPhotographer(Model model) {
		model.addAttribute("photographer", new Photographer());
		return "admin/photographer/newPhotographer";
	}

}
