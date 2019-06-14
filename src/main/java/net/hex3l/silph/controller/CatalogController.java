package net.hex3l.silph.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.repository.AlbumRepository;
import net.hex3l.silph.repository.PhotoRepository;
import net.hex3l.silph.repository.PhotographerRepository;
import net.hex3l.silph.services.PhotoService;

@Controller
public class CatalogController{
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private PhotographerRepository photographerRepository;
	
	@RequestMapping(value = "/catalog/photos/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayPhotos(@PathVariable("pageNumber") Integer pageNumber, HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photos");
        mav.addObject("catalog", photoService.photoPage(pageNumber));
        return photoSelection(mav, session);
	}
	
	@RequestMapping(value = "/catalog/photos", method = RequestMethod.GET)
	public ModelAndView displayPhotos(HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photos");
		mav.addObject("catalog", photoService.photoPage(0));
		return photoSelection(mav, session);
	}
	
	public ModelAndView photoSelection(ModelAndView mav, HttpSession session) {
		Object selection = session.getAttribute("photos");
        if(selection != null) {
        	List<Photo> list = (List<Photo>) photoService.findAllById((Set<Long>)selection);
        	mav.addObject("selection", list);
        }
		return mav;
	}
	
	@RequestMapping(value = "/catalog/albums/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayAlbums(@PathVariable("pageNumber") Integer pageNumber) {
		ModelAndView mav = new ModelAndView("albumlist");
		
		return mav;
	}
	
	@RequestMapping(value = "/catalog/photographers/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayPhotographers(@PathVariable("pageNumber") Integer pageNumber) {
		ModelAndView mav = new ModelAndView("photographerslist");
		
		return mav;
	}
}
