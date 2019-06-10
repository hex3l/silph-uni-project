package net.hex3l.silph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.repository.AlbumRepository;
import net.hex3l.silph.repository.PhotoRepository;
import net.hex3l.silph.repository.PhotographerRepository;

@Controller
public class CatalogController{
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private PhotographerRepository photographerRepository;
	
	@RequestMapping(value = "/catalog/photos/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayPhotos(@PathVariable("pageNumber") Integer pageNumber) {
		ModelAndView mav = new ModelAndView("photolist");
		//TODO: remove findAll() create new method in @PhotoRepository to extract pages
        mav.addObject("catalog", photoRepository.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/catalog/photos", method = RequestMethod.GET)
	public ModelAndView displayPhotos() {
		ModelAndView mav = new ModelAndView("photolist");
		//TODO: remove findAll() create new method in @PhotoRepository to extract pages
        mav.addObject("catalog", photoRepository.findAll());
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
