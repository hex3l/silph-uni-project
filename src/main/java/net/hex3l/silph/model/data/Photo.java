package net.hex3l.silph.model.data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	@Getter @Setter
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	@Getter @Setter
	@ManyToOne
	private Photographer photographer;
}