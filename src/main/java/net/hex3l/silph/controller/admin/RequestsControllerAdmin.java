package net.hex3l.silph.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.hex3l.silph.repository.UsageRequestRepository;
import net.hex3l.silph.services.UsageRequestService;

@Controller
public class RequestsControllerAdmin {
	@Autowired
	private UsageRequestService usageRequestService;
	
	@RequestMapping(value = "/admin/requests", method = RequestMethod.GET)
	public ModelAndView displayRequests() {
		ModelAndView mav = new ModelAndView("admin/requests/usageRequests");
		mav.addObject("requests", usageRequestService.all());
		return mav;
	}
	
	@RequestMapping(value= "/admin/request/{id}",method = RequestMethod.GET)
	public String getUsageRequest(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("request", this.usageRequestService.findById(id));
			return "admin/requests/usageRequest";
		}
		else {
			model.addAttribute("requests", this.usageRequestService.all());
			return "admin/requests/usageRequests";	
		}	
	}


}
