package army.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import army.model.Views;



@Entity
public class Civil {
	
	private Long id;
	private String nom;
	private String metier;
	private int version;
	
	
	
	public Civil(String nom, String metier) {
		super();
		this.nom = nom;
		this.metier = metier;
	}
	public  Civil(){
		
		
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
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
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
		return "Civil [nom=" + nom + "metier" + metier +"]";
	}
	
	
	

}
