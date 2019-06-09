package net.hex3l.silph.model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	//temporary using base64 (gonna use @Lob byte[] to store images)
	@Getter @Setter
	@Column(columnDefinition = "TEXT")
	private String image;
	@Getter @Setter
	@ManyToOne
	private Album album;
}
