package net.hex3l.silph.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
	public String newRequest(@Valid @ModelAttribute("customer") Customer customer, 
			Model model, BindingResult bindingResult, HttpSession session) {
		this.customerValidator.validate(customer, bindingResult);
		UsageRequest request = new UsageRequest();
		List<Photo> photos = (List<Photo>) photoService.findAllById((Set<Long>)session.getAttribute("photos"));
		request.setPhotos(photos);
		request.setCustomer(customer);
		this.usageRequestValidator.validate(request, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.customerService.add(customer);
			this.usageRequestService.add(request);
			model.addAttribute("request", request);
			session.removeAttribute("photos");
			return "requests/requestConfirm";
		} else {
			return "requests/requestForm";
		}
	}

	@RequestMapping(value="/googleRequest",method= RequestMethod.GET)
	public String newGoogleRequest( Model model, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof OAuth2User) {
			OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UsageRequest request = new UsageRequest();
			Customer customer = new Customer();
			customer.setFirstName((String) user.getAttributes().get("given_name"));
			customer.setFirstName((String) user.getAttributes().get("family_name"));
			customer.setMail((String) user.getAttributes().get("email"));

			BindingResult customerBindingResult = new BindException(customer, "Customer");
			this.customerValidator.validate(customer, customerBindingResult);

			List<Photo> photos = (List<Photo>) photoService.findAllById((Set<Long>)session.getAttribute("photos"));
			request.setPhotos(photos);
			request.setCustomer(customer);

			BindingResult usageRequestBindingResult = new BindException(request, "Customer");
			this.usageRequestValidator.validate(request, usageRequestBindingResult);

			if(!customerBindingResult.hasErrors() && !usageRequestBindingResult.hasErrors()) {
				this.customerService.add(customer);
				this.usageRequestService.add(request);
				model.addAttribute("request", request);
				session.removeAttribute("photos");
				return "requests/requestConfirm";
			}
		}

		return "requests/requestForm";
	}
	
	@RequestMapping("/newRequest")
	public ModelAndView addRequest(HttpSession session){
		ModelAndView mav = new ModelAndView("requests/requestForm");
		Customer customer = new Customer();
		mav.addObject("customer", customer);

		return cartService.photoSelection(mav, session);
	}
}
