package net.hex3l.silph.controller.catalog;

import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.model.data.Photographer;
import net.hex3l.silph.services.AlbumService;
import net.hex3l.silph.services.PhotoService;
import net.hex3l.silph.services.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class PhotographersController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private PhotographerService photographerService;

	@RequestMapping(value = "/catalog/photographers", method = RequestMethod.GET)
	public ModelAndView displayAlbums() {
		ModelAndView mav = new ModelAndView("catalog/photographers");
		mav.addObject("photographers", photographerService.tutte());
		return mav;
	}

	@RequestMapping(value = "/catalog/photographer/{photographerId}/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayAlbum(@PathVariable("photographerId") Long photographerId, @PathVariable("pageNumber") Integer pageNumber, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("catalog/photographer");

		return getPhotographerPage(photoSelection(mav, httpSession), photographerService.findById(photographerId), pageNumber);
	}

	@RequestMapping(value = "/catalog/photographer/{photographerId}", method = RequestMethod.GET)
	public ModelAndView displayAlbum(@PathVariable("photographerId") Long photographerId, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("catalog/photographer");

		return getPhotographerPage(photoSelection(mav, httpSession), photographerService.findById(photographerId), 0);
	}


	ModelAndView getPhotographerPage(ModelAndView mav, Photographer photographer, Integer pageNumber) {
		List<Photo> photos = photographer.getPhotos();
		Page<Photo> page = new PageImpl<>(photos, PageRequest.of(pageNumber, 6), photos.size());
		mav.addObject("photographer", photographer);
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
