package army.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class LigneCommande {
	private Long id;
	private int quantite;
	private Double montant;
	private Arme arme;
	private Commande commande;
	private int version;

	public LigneCommande() {
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

	@JsonView(Views.Common.class)
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@JsonView(Views.Common.class)
	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	@ManyToOne
	@JoinColumn(name = "armet_id")
	@JsonView(Views.LigneCommande.class)
	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	@ManyToOne
	@JoinColumn(name = "commande_id")
	@JsonView(Views.LigneCommande.class)
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	@Version
	@JsonView(Views.Common.class)
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void calculMontant() {
		this.setMontant(this.getArme().getPrix() * this.getQuantite());
	}

}
