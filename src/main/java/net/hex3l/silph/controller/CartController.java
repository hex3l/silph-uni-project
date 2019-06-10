package net.hex3l.silph.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.repository.PhotoRepository;

@Controller
public class CartController {
	
	@Autowired
	PhotoRepository photoRepository;
	
	@RequestMapping(value = "/cart/add/{photoId}", method = RequestMethod.POST)
	public @ResponseBody byte[] addPhoto(@PathVariable("photoId") Long photoId) {
		String body = "";
		String name = "";
		Optional<Photo> opt = photoRepository.findById(photoId);
		if(opt.isPresent()) {
			Photo photo = opt.get();
			name = photo.getName();

			//Session handler here
			
			body = "{\"name\":\"" + name + "\"}";
		} else {
			//Error
		}
		
		return body.getBytes();
	}
	
	@RequestMapping(value = "/cart/remove/{photoId}", method = RequestMethod.POST)
	public @ResponseBody byte[] removePhoto(@PathVariable("photoId") Long photoId) {
		String body = "";
		
		//Session handler here
		
		return body.getBytes();
	}
}
