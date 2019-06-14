package net.hex3l.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.hex3l.silph.model.data.Photo;
import net.hex3l.silph.repository.PhotoRepository;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository photoRepository;
	
	@Transactional
	public Photo inserisci(Photo photo) {
		return this.photoRepository.save(photo);
	}
	
	@Transactional
	public Iterable<Photo> findAllById(Iterable<Long> ids) {
		return this.photoRepository.findAllById(ids);
	}
	
	@Transactional 
	public List<Photo> tutte() {
		return (List<Photo>) photoRepository.findAll();
	}
	
	@Transactional
	public List<Photo> photoPage(int page) {
		return photoRepository.findAll(PageRequest.of(page, 4)).getContent();
	}

}
