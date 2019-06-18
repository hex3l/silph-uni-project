package net.hex3l.silph.controller.catalog;

import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.AlbumService;
import net.hex3l.silph.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class PhotosController {
	
	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value = "/catalog/photos/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayPhotos(@PathVariable("pageNumber") Integer pageNumber, HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photos");

		return getPhotoPage(photoSelection(mav, session), pageNumber);
	}
	
	@RequestMapping(value = "/catalog/photos", method = RequestMethod.GET)
	public ModelAndView displayPhotos(HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photos");

		return getPhotoPage(photoSelection(mav, session), 0);
	}
	
	 ModelAndView getPhotoPage(ModelAndView mav, Integer pageNumber) {
		Page<Photo> page = photoService.photoPage(pageNumber);
        mav.addObject("catalog", page.getContent());
        mav.addObject("page", page.getNumber());
        mav.addObject("pages", page.getTotalPages());
		return mav;
	}
	
	ModelAndView photoSelection(ModelAndView mav, HttpSession session) {
		Object selection = session.getAttribute("photos");
        if(selection != null) {
        	List<Photo> list = (List<Photo>) photoService.findAllById((Set<Long>)selection);
        	mav.addObject("selection", list);
        }
		return mav;
	}
}
