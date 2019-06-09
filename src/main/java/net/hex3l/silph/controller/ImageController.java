package net.hex3l.silph.controller;

import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.repository.PhotoRepository;

@Controller
public class ImageController{
	
	@Autowired
	PhotoRepository photoRepository;
	
	@RequestMapping(value = "/photo/{imageid}.png", method = RequestMethod.GET, produces = "image/png")
	public @ResponseBody byte[] displayPngImage(@PathVariable("imageid") Long photoId) {
		String body = "<!doctype html><img src='" + retrieveImage(photoId) + "'>";
		return body.getBytes();
		
		//return retrieveImage(photoId);
	}
	
	@RequestMapping(value = "/photo/{imageid}.jpeg", method = RequestMethod.GET, produces = "image/jpeg")
	public @ResponseBody byte[] displayJpegImage(@PathVariable("imageid") Long photoId) {
		String body = "<!doctype html><img src='" + retrieveImage(photoId) + "'>";
		return body.getBytes();
		
		//return retrieveImage(photoId);
	}
	
	private String retrieveImage(Long photoId) {
		String data = "";
		//ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		Optional<Photo> opt = photoRepository.findById(photoId);
		if(opt.isPresent()) {
			Photo photo = opt.get();
			data = photo.getImage();
			//bao.write(photo.getImage(););
		}
		
		return data;
		
		//return bao.toByteArray();
	}

}
