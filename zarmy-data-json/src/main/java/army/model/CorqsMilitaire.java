package army.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class CorqsMilitaire {
	
	private Long id;
	private String camp;
	private String nom;
	private int version;
	
	public CorqsMilitaire(String camp, String nom) {
		super();
		this.camp = camp;
		this.nom = nom;
	}
	public CorqsMilitaire() {
		
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
	public String getCamp() {
		return camp;
	}
	public void setCamp(String camp) {
		this.camp = camp;
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
		return "CorpsMilitaire [camp=" + camp + ", nom=" + nom + "]";
	}
	
	
	

}
