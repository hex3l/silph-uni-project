package net.hex3l.silph.controller.catalog;

import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.CartService;
import net.hex3l.silph.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PhotosController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/catalog/photos/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayPhotos(@PathVariable("pageNumber") Integer pageNumber, HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photos");

		return getPhotoPage(cartService.photoSelection(mav, session), pageNumber);
	}
	
	@RequestMapping(value = "/catalog/photos", method = RequestMethod.GET)
	public ModelAndView displayPhotos(HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photos");

		return getPhotoPage(cartService.photoSelection(mav, session), 0);
	}
	
	 ModelAndView getPhotoPage(ModelAndView mav, Integer pageNumber) {
		Page<Photo> page = photoService.photoPage(pageNumber);
        mav.addObject("catalog", page.getContent());
        mav.addObject("page", page.getNumber());
        mav.addObject("pages", page.getTotalPages());
		return mav;
	}
	

}
