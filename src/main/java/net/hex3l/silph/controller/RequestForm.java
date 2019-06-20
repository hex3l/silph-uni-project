package net.hex3l.silph.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.controller.validator.CustomerValidator;
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
	private CustomerValidator customerValidator;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/request",method= RequestMethod.POST)
	public String newRequest(@ModelAttribute("customer") Customer customer, 
			Model model, BindingResult bindingResult, HttpSession session, HttpServletRequest httpServletRequest) {
		UsageRequest request = new UsageRequest();

		Object obj = session.getAttribute("photos");
		if(obj != null) {
			List<Photo> photos = (List<Photo>) photoService.findAllById((Set<Long>)obj);

			request.setPhotos(photos);
		}
		customer.setUsageRequest(request);
		this.customerValidator.validate(customer, bindingResult);
		if(!bindingResult.hasErrors()) {
			request.setCustomer(customer);
			this.customerService.add(customer);
			this.usageRequestService.add(request);
			session.removeAttribute("photos");
			return "requests/requestConfirm";
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof OAuth2User) {
			model.addAttribute("confirm", "/googleRequest");
		} else {
			model.addAttribute("confirm", "/oauth2/authorization/google");
		}
		model.addAttribute("request", new UsageRequest());
		return "requests/requestForm";
	}

	@RequestMapping(value="/googleRequest",method= RequestMethod.GET)
	public String newGoogleRequest(@ModelAttribute("customer") Customer customer, Model model, HttpSession session, HttpServletRequest httpServletRequest) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof OAuth2User) {
			OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			customer.setFirstName((String) user.getAttributes().get("given_name"));
			customer.setLastName((String) user.getAttributes().get("family_name"));
			customer.setMail((String) user.getAttributes().get("email"));

			UsageRequest request = new UsageRequest();
			Object obj = session.getAttribute("photos");
			if(obj != null) {
				List<Photo> photos = (List<Photo>) photoService.findAllById((Set<Long>)obj);
				request.setPhotos(photos);
				request.setCustomer(customer);
			}
			customer.setUsageRequest(request);
			BindingResult bindingResult = new BindException(customer, "Customer");
			this.customerValidator.validate(customer, bindingResult);
			if(!bindingResult.hasErrors()) {
				this.customerService.add(customer);
				this.usageRequestService.add(request);
				model.addAttribute("customer", customer);
				
				session.removeAttribute("photos");
				return "requests/requestConfirm";
			}
			model.addAttribute(bindingResult);
		}
		if(principal instanceof OAuth2User) {
			model.addAttribute("confirm", "/googleRequest");
		} else {
			model.addAttribute("confirm", "/oauth2/authorization/google");
		}
		model.addAttribute("customer", new Customer());
		return "requests/requestForm";
	}
	
	@RequestMapping("/newRequest")
	public ModelAndView addRequest(HttpSession session, HttpServletRequest httpServletRequest){
		ModelAndView mav = new ModelAndView("requests/requestForm");
		mav.addObject("customer", new Customer());
		mav.addObject("request", new UsageRequest());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof OAuth2User) {
			mav.addObject("confirm", "/googleRequest");
		} else {
			mav.addObject("confirm", "/oauth2/authorization/google");
		}
		
		return cartService.photoSelection(mav, session);
	}
}
