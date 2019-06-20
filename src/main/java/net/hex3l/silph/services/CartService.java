package net.hex3l.silph.services;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.model.data.Photo;

@Service
public class CartService {
	
	@Autowired
	private PhotoService photoService;

	@SuppressWarnings("unchecked")
	public ModelAndView photoSelection(ModelAndView mav, HttpSession session) {
		Object selection = session.getAttribute("photos");
        if(selection != null) {
			List<Photo> list = (List<Photo>) photoService.findAllById((Set<Long>)selection);
        	mav.addObject("selection", list);
        }
		return mav;
	}
	@SuppressWarnings("unchecked")
	public Model photoSelection(Model model, HttpSession session) {
		Object selection = session.getAttribute("photos");
        if(selection != null) {
			List<Photo> list = (List<Photo>) photoService.findAllById((Set<Long>)selection);
        	model.addAttribute("selection", list);
        }
		return model;
	}
	
}
