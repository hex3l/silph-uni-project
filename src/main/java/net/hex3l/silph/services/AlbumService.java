package net.hex3l.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public Album add(Album album) {
		return this.albumRepository.save(album);
	}

	public List<Album> all() {
		return (List<Album>) albumRepository.findAll();
	}

	public Album findById(Long id) {
		return albumRepository.findById(id).get();
	}

	public Page<Album> albumPage(int page) {
		return albumRepository.findAll(PageRequest.of(page, 6));
	}
}
