package net.hex3l.silph.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import net.hex3l.silph.model.data.Photo;

@Entity
public class UsageRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private Long id;
	@Getter @Setter
	@ManyToMany
	private List<Photo> photos;
	@Getter @Setter
	@OneToOne
	private Customer customer;
	

}
