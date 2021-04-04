package army.model;



import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Guerrier {
	
	private Long id;
	private String nom;
	private String description;
	private byte[] photo;
	private String nomPhoto;
	private List<Arme> armes;
	private Guerrier mere;
	private List<Guerrier> filles;
	
	private int version;

    
	public Guerrier() {
		
	}
	@Id
	@GeneratedValue
	@JsonView(Views.Common.class)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Size(min=4,max=30)
	@JsonView(Views.Common.class)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@JsonView(Views.Common.class)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Lob
	@JsonView(Views.GuerrierAvecPhoto.class)
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@JsonView(Views.Guerrier.class)
	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	@OneToMany(mappedBy = "guerrier", fetch = FetchType.LAZY)
	public List<Arme> getArmes() {
		return armes;
	}

	public void setArmes(List<Arme> armes) {
		this.armes = armes;
	}

	@ManyToOne
	@JoinColumn(name = "mere_id")
	@JsonView(Views.Guerrier.class)
	public Guerrier getMere() {
		return mere;
	}

	public void setMere(Guerrier mere) {
		this.mere = mere;
	}

	@OneToMany(mappedBy = "mere", fetch = FetchType.LAZY)
	public List<Guerrier> getFilles() {
		return filles;
	}

	public void setFilles(List<Guerrier> filles) {
		this.filles = filles;
	}

	@Version
	@JsonView(Views.Common.class)
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
