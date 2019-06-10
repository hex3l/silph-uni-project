package net.hex3l.silph.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hex3l.silph.controller.validator.AlbumValidator;
import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.services.AlbumService;

@Controller
public class AlbumControllerAdmin {
	@Autowired
	private AlbumService albumService; 
	
	@Autowired
	private AlbumValidator albumValidator;

	
	@RequestMapping(value="/admin/album/new",method= RequestMethod.POST)
	public String newAlbum(@Valid @ModelAttribute("album") Album album, 
			Model model, BindingResult bindingResult) {
		this.albumValidator.validate(album, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.albumService.inserisci(album);
			return "/album";
		}
		else {
			return "/album";
		}
	}

}
