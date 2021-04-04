package army.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Commande {
	private Long id;
	private int nbArmes;
	private Double prixTotal;
	private Date date;
	private List<LigneCommande> armes;
	
	private int version;

	public Commande() {
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
	public int getNbArmes() {
		return nbArmes;
	}

	public void setNbArmes(int nbArmes) {
		this.nbArmes = nbArmes;
	}

	@JsonView(Views.Common.class)
	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
	@JsonView(Views.CommandeAvecLigneCommande.class)
	public List<LigneCommande> getArmes() {
		return armes;
	}

	public void setArmes(List<LigneCommande> armes) {
		this.armes = armes;
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
