package net.hex3l.silph.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.hex3l.silph.model.data.Photo;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Long> {

}
