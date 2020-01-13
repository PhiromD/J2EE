package fr.onepiecetheorie.pojos;


import java.util.ArrayList;


import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.util.http.FastHttpDateFormat;
 
@Entity
@Table(name = "categorie")

public class Categorie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategorie")
    private Integer idCategorie;
	
	
	
	@Column(name = "nom")
	private String nom;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	@JoinColumn(name = "idTheorie")
	private Collection<Theorie> theories;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	@JoinColumn(name = "idMugiwara")
	private Collection<Mugiwara> mugiwaras;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private Collection<UserForum> users;
	
	public Categorie() {
		this.theories = new ArrayList<Theorie>();
    	
	}
	
	public Categorie(int idCategorie,String nom) {
		super();
		this.idCategorie = idCategorie;
		this.nom = nom;
		this.theories = new ArrayList<Theorie>();
		
	}
	public Categorie(String nom) {
		super();
		
		this.nom = nom;
		this.theories = new ArrayList<Theorie>();
		
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getIdCategorie() {
		return this.idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public void addRecette(Theorie theorie) {
		this.theories.add(theorie);
	}
	public Collection<UserForum> getUser() {
		return this.users ;
	}
	public void addUser(UserForum user) {
	this.users.add(user);

	}
	public Collection<Theorie> getTheorie() {
		return this.theories ;
	}
	public void addTheorie(Theorie theorie) {
	this.theories.add(theorie);
	}
	public Collection<Mugiwara> getMugi() {
		return this.mugiwaras ;
	}
	public void addMugi(Mugiwara mugiwara) {
	mugiwaras.add(mugiwara);
	}
	@Override
    public String toString() {
	
		
		
		return nom; 
        		
    			
    }

}




