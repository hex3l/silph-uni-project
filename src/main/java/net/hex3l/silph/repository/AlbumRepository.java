package net.hex3l.silph.repository;

import net.hex3l.silph.model.data.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
	
}
