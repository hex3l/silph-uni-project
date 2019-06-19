package net.hex3l.silph.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.controller.validator.UsageRequestValidator;
import net.hex3l.silph.model.Customer;
import net.hex3l.silph.model.UsageRequest;
import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.services.CartService;
import net.hex3l.silph.services.CustomerService;
import net.hex3l.silph.services.PhotoService;
import net.hex3l.silph.services.UsageRequestService;

@Controller
public class RequestForm {
	
	@Autowired
	private UsageRequestService usageRequestService;
	
	@Autowired
	private UsageRequestValidator usageRequestValidator;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/request",method= RequestMethod.POST)
	public String newRequest(@Valid @ModelAttribute("customer") Customer customer, 
			Model model, BindingResult bindingResult, HttpSession session) {
		//this.customerValidator.validate(customer, bindingResult);
		UsageRequest request = new UsageRequest();
		List<Photo> photos = (List<Photo>) photoService.findAllById((Set<Long>)session.getAttribute("photos"));
		request.setPhotos(photos);
		request.setCustomer(customer);
		if(!bindingResult.hasErrors()) {
			this.customerService.add(customer);
			this.usageRequestService.add(request);
			model.addAttribute(request);
			return "requests/requestConfirm";
		} else {
			return "requests/requestForm";
		}
	}
	
	@RequestMapping("/newRequest")
	public ModelAndView addRequest(HttpSession session){
		ModelAndView mav = new ModelAndView("requests/requestForm");
		//UsageRequest request = new UsageRequest();
		//request.setPhotos((List<Photo>)session.getAttribute("photos"));
		//model.addAttribute("request", request);
		Customer customer = new Customer();
		mav.addObject("customer", customer);
		return cartService.photoSelection(mav, session);
	}

}
