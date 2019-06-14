package net.hex3l.silph.model.data;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String extension;
	@Getter @Setter
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	@Getter @Setter
	@ManyToOne
	private Album album;
}
