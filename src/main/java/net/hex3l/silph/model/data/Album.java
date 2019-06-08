package net.hex3l.silph.model.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	@OneToMany(mappedBy="album")
	private List<Photo> photos;
	@Getter @Setter
	@ManyToOne
	private Photographer photographer;

}
