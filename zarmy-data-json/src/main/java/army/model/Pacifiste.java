package army.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class Pacifiste {
	
	private Long id;
	private String nom;
	private String noCombat;
	private int version;
	
	
	public Pacifiste(String nom, String noCombat) {
		super();
		this.nom = nom;
		this.noCombat = noCombat;
	}
	public Pacifiste() {
		
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Column(length = 100)
	@JsonView(Views.Common.class)
	public String getNoCombat() {
		return noCombat;
	}
	public void setNoCombat(String noCombat) {
		this.noCombat = noCombat;
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
		return "Pacifiste [nom=" + nom + ", noCombat=" + noCombat + "]";
	}
	
	
	

}
