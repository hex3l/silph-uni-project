package net.hex3l.silph.controller;

import java.io.IOException;
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
public class PhotoController {
	
	@Autowired
	PhotoRepository photoRepository;
	
	@RequestMapping(value = "/photo/{imageid}.png", method = RequestMethod.GET, produces = "image/png")
	public @ResponseBody byte[] displayPngImage(@PathVariable("imageid") Long photoId) {
		return retrieveImage(photoId);
	}
	
	@RequestMapping(value = "/photo/{imageid}.jpeg", method = RequestMethod.GET, produces = "image/jpeg")
	public @ResponseBody byte[] displayJpegImage(@PathVariable("imageid") Long photoId) {
		return retrieveImage(photoId);
	}
	
	private byte[] retrieveImage(Long photoId) {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		Optional<Photo> opt = photoRepository.findById(photoId);
		if(opt.isPresent()) {
			Photo photo = opt.get();
			try{
				bao.write(photo.getImage());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		return bao.toByteArray();
	}

}
