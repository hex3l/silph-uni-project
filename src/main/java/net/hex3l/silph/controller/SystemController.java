package net.hex3l.silph.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.services.CartService;

@Controller
public class SystemController{
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex() {
		return "redirect:catalog";
	}

	@RequestMapping(value = "/catalog", method = RequestMethod.GET)
	public ModelAndView showCatalog(HttpSession session) {
		ModelAndView mav = new ModelAndView("index");

		return cartService.photoSelection(mav, session);
	}
}
