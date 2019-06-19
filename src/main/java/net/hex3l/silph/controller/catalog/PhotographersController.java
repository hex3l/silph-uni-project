package net.hex3l.silph.controller.catalog;

import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.model.data.Photographer;
import net.hex3l.silph.services.CartService;
import net.hex3l.silph.services.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PhotographersController {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private PhotographerService photographerService;

	@RequestMapping(value = "/catalog/photographers", method = RequestMethod.GET)
	public ModelAndView displayAlbums(HttpSession session) {
		ModelAndView mav = new ModelAndView("catalog/photographers");
		mav.addObject("photographers", photographerService.all());
		return cartService.photoSelection(mav, session);
	}

	@RequestMapping(value = "/catalog/photographer/{photographerId}/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayAlbum(@PathVariable("photographerId") Long photographerId, @PathVariable("pageNumber") Integer pageNumber, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("catalog/photographer");

		return getPhotographerPage(cartService.photoSelection(mav, httpSession), photographerService.findById(photographerId), pageNumber);
	}

	@RequestMapping(value = "/catalog/photographer/{photographerId}", method = RequestMethod.GET)
	public ModelAndView displayAlbum(@PathVariable("photographerId") Long photographerId, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("catalog/photographer");

		return getPhotographerPage(cartService.photoSelection(mav, httpSession), photographerService.findById(photographerId), 0);
	}


	ModelAndView getPhotographerPage(ModelAndView mav, Photographer photographer, Integer pageNumber) {
		List<Photo> photos = photographer.getPhotos();
		PagedListHolder<Photo> page = new PagedListHolder<>(photos);
		page.setPageSize(9);
		page.setPage(pageNumber);
		mav.addObject("photographer", photographer);
		mav.addObject("catalog", page.getPageList());
		mav.addObject("page", page.getPage());
		mav.addObject("pages", page.getPageCount());
		return mav;
	}
}
