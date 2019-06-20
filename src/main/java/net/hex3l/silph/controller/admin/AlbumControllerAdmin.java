package net.hex3l.silph.controller.admin;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hex3l.silph.controller.validator.AlbumValidator;
import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.AlbumService;
import net.hex3l.silph.services.PhotoService;

@Controller
public class AlbumControllerAdmin {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private AlbumService albumService; 
	
	@Autowired
	private AlbumValidator albumValidator;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/album/new",method= RequestMethod.POST)
	public String createAlbum(@ModelAttribute("album") Album album, 
			Model model, HttpSession session, BindingResult bindingResult) {
		Object obj = session.getAttribute("albumSelection");
		if(obj != null) {
			List<Photo> photos = (List<Photo>) photoService.findAllById((Set<Long>)obj);
			album.setPhotos(photos);
		}
		this.albumValidator.validate(album, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.albumService.add(album);
			model.addAttribute(album);
			return "admin/album/albumConfirm";
		}
		model.addAttribute(bindingResult);
		return "admin/album/newAlbum";
	
	}
	
	@RequestMapping(value="/admin/album/new",method= RequestMethod.GET)
	public String newAlbum(Model model, HttpSession session) {
		model.addAttribute("album", new Album());
		session.removeAttribute("albumSelection");
		return "admin/album/newAlbum";
	}

}
