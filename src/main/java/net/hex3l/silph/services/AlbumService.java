package net.hex3l.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hex3l.silph.model.data.Album;
import net.hex3l.silph.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public Album inserisci(Album album) {
		return this.albumRepository.save(album);
	}
	
	@Transactional 
	public List<Album> tutte() {
		return (List<Album>) albumRepository.findAll();
	}

}
