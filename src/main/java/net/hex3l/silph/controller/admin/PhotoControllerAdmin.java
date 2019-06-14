package net.hex3l.silph.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hex3l.silph.controller.validator.PhotoValidator;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.PhotoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PhotoControllerAdmin {
	@Autowired
	private PhotoService photoService; 
	
	@Autowired
	private PhotoValidator photoValidator;

	
	@RequestMapping(value="/admin/photo/new",method= RequestMethod.POST)
	public String newPhoto(@Valid @RequestParam("image") MultipartFile[] image,
			Model model) {
		Photo photo = new Photo();
		try {
			if(image[0].getContentType().contains("image/")) {
				photo.setName(image[0].getOriginalFilename());
				photo.setImage(image[0].getBytes());
				photo.setExtension(image[0].getContentType().replace("image/", ""));

				BindingResult bindingResult = new BindException(photo, "Photo");
				this.photoValidator.validate(photo, bindingResult);
				if (!bindingResult.hasErrors()) {
					this.photoService.inserisci(photo);
					model.addAttribute("photo", photo);
					return "admin/photo/photoUploadConfirm";
				} else {
					return "error";
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping(value="/admin/photo/new",method= RequestMethod.GET)
	public String newPhoto() {
		return "admin/photo/newPhoto";
	}

}
