package net.hex3l.silph.repository;

import org.springframework.data.repository.CrudRepository;

import net.hex3l.silph.model.data.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {
	
}
