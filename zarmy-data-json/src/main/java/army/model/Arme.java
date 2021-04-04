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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Arme {

	private Long id;
	private String nom;
	private String description;
	private int stock;
	private Double prix;
	private boolean selectionne;
	private String nomPhoto;
	private byte[] photo;
	private Guerrier guerrier;
	private List<LigneCommande> commandes;

	private int version;
	
	public Arme(){
		
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

	@JsonView(Views.Common.class)
	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	@JsonView(Views.Common.class)
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@JsonView(Views.Common.class)
	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	@Lob
	@JsonView(Views.ArmeAvecPhoto.class)
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@JsonView(Views.Arme.class)
	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	@ManyToOne
	@JoinColumn(name = "guerrier_id")
	@JsonView(Views.Arme.class)
	public Guerrier getGuerrier() {
		return guerrier;
	}

	public void setGuerrier(Guerrier guerrier) {
		this.guerrier= guerrier;
	}

	@OneToMany(mappedBy = "arme", fetch = FetchType.LAZY)
	public List<LigneCommande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<LigneCommande> commandes) {
		this.commandes = commandes;
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
