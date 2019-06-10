package net.hex3l.silph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hex3l.silph.repository.AlbumRepository;
import net.hex3l.silph.repository.PhotoRepository;
import net.hex3l.silph.repository.PhotographerRepository;

@Controller
public class CatalogController{
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private PhotographerRepository photographerRepository;
	
	@RequestMapping(value = "/catlog/photos/{pageNumber}", method = RequestMethod.GET)
	public String displayPhotos(@PathVariable("pageNumber") Integer pageNumber) {
		
		return "catalog.html";
	}
	
	@RequestMapping(value = "/catlog/albums/{pageNumber}", method = RequestMethod.GET)
	public String displayAlbums(@PathVariable("pageNumber") Integer pageNumber) {
		
		return "catalog.html";
	}
	
	@RequestMapping(value = "/catlog/photographers/{pageNumber}", method = RequestMethod.GET)
	public String displayPhotographers(@PathVariable("pageNumber") Integer pageNumber) {
		
		return "catalog.html";
	}
}
