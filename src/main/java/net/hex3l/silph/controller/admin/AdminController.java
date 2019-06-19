package net.hex3l.silph.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin")
	public String adminManagement(Model model) {
		return "admin/adminHome";
	}
	
}
