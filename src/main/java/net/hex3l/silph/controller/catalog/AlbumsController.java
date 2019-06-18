package net.hex3l.silph.controller.catalog;

import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.AlbumService;
import net.hex3l.silph.services.PhotoService;
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
public class AlbumsController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private PhotoService photoService;

	@RequestMapping(value = "/catalog/albums/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayAlbums(@PathVariable("pageNumber") Integer pageNumber) {
		ModelAndView mav = new ModelAndView("catalog/albums");
		
		return getAlbumsPage(mav, pageNumber);
	}

	@RequestMapping(value = "/catalog/albums", method = RequestMethod.GET)
	public ModelAndView displayAlbums() {
		ModelAndView mav = new ModelAndView("catalog/albums");

		return getAlbumsPage(mav,0);
	}

	@RequestMapping(value = "/catalog/album/{albumId}/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView displayAlbum(@PathVariable("albumId") Long albumId, @PathVariable("pageNumber") Integer pageNumber, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("catalog/album");

		return getAlbumPage(photoSelection(mav, httpSession), albumService.findById(albumId), pageNumber);
	}

	@RequestMapping(value = "/catalog/album/{albumId}", method = RequestMethod.GET)
	public ModelAndView displayAlbum(@PathVariable("albumId") Long albumId, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("catalog/album");

		return getAlbumPage(photoSelection(mav, httpSession), albumService.findById(albumId), 0);
	}

	ModelAndView getAlbumPage(ModelAndView mav, Album album, Integer pageNumber) {
		List<Photo> photos = album.getPhotos();
		Page<Photo> page = new PageImpl<>(photos, PageRequest.of(pageNumber, 9), photos.size());
		mav.addObject("album", album);
		mav.addObject("catalog", page.getContent());
		mav.addObject("page", page.getNumber());
		mav.addObject("pages", page.getTotalPages());
		return mav;
	}

	ModelAndView getAlbumsPage(ModelAndView mav, Integer pageNumber) {
		Page<Album> page = albumService.albumPage(pageNumber);
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
