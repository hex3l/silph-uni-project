package net.hex3l.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hex3l.silph.model.data.Photographer;
import net.hex3l.silph.repository.PhotographerRepository;

@Service
public class PhotographerService {
	@Autowired
	private PhotographerRepository photographerRepository;
	
	@Transactional
	public Photographer inserisci(Photographer photographer) {
		return this.photographerRepository.save(photographer);
	}
	
	@Transactional 
	public List<Photographer> tutte() {
		return (List<Photographer>) photographerRepository.findAll();
	}

	@Transactional 
	public Photographer findById(Long id) {
		return photographerRepository.findById(id).get();
	}
}
