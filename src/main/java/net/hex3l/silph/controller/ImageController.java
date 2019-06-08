package net.hex3l.silph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController{

	@RequestMapping(value = "/image/{albumid}/{imageid}.{imageext}", method = RequestMethod.GET)
	public String getImage(@PathVariable("albumid") Long albumid, 
		@PathVariable("imageid") Long imageid, 
		@PathVariable("imageext") String ext) {
		
		return "";
	}

}
