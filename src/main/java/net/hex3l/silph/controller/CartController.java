package net.hex3l.silph.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> addPhoto(@PathVariable("photoId") Long photoId, HttpSession session) {
		String body = "";
		Optional<Photo> opt = photoRepository.findById(photoId);
		if(opt.isPresent()) {
			Photo photo = opt.get();
			String name = photo.getName();
			Set<Long> photosCart;
			if(session.getAttribute("photos")==null) {
				photosCart = new HashSet<>();
			} else {
				photosCart = (Set<Long>) session.getAttribute("photos");
			}
			photosCart.add(photoId);
			session.setAttribute("photos", photosCart);
			
			body = "{\"name\":\"" + name + "\"}";
			return new ResponseEntity<String>(body, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(body, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/cart/remove/{photoId}", method = RequestMethod.POST)
	public @ResponseBody byte[] removePhoto(@PathVariable("photoId") Long photoId, HttpSession session) {
		String body = "";
		
		//Session handler here
		Set<Long> photosCart = (Set<Long>) session.getAttribute("photos");
		photosCart.remove(photoId);
		session.setAttribute("photos", photosCart);
		
		
		return body.getBytes();
	}
}
