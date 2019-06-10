package net.hex3l.silph.repository;

import org.springframework.data.repository.CrudRepository;

import net.hex3l.silph.model.data.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
	//Method that gets elements for each page - offsets
}
