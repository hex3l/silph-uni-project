package net.hex3l.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
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
	
	public Iterable<Photo> findAllById(Iterable<Long> ids) {
		return this.photoRepository.findAllById(ids);
	}
	
	public List<Photo> tutte() {
		return (List<Photo>) photoRepository.findAll();
	}
	
	public Page<Photo> photoPage(int page) {
		return photoRepository.findAll(PageRequest.of(page, 4));
	}
}
