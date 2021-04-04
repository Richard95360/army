package army.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class Medecin {
	
	private Long id;
	private String specialite;
	private String soin;
	private String nom;
	private int version;
	
	
	public Medecin(String specialite, String soin, String nom) {
		super();
		this.specialite = specialite;
		this.soin = soin;
		this.nom = nom;
	}
	public Medecin() {
		
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
	@Column(length = 100)
	@JsonView(Views.Common.class)
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	@Column(length = 100)
	@JsonView(Views.Common.class)
	public String getSoin() {
		return soin;
	}
	public void setSoin(String soin) {
		this.soin = soin;
	}
	@Column(length = 100)
	@JsonView(Views.Common.class)
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Version
	@JsonView(Views.Common.class)
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Medecin [specialite=" + specialite + ", soin=" + soin + ", nom=" + nom + "]";
	}
	
	
	
	

}
