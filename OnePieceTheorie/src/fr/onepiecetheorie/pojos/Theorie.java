package fr.onepiecetheorie.pojos;

import java.util.ArrayList;


import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.hibernate.annotations.Type;
 
@Entity
@Table(name = "theorie")

public class Theorie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTheorie")
    private Integer idTheorie;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "description")
	@Type(type="text")
	private String description;
	
	@Temporal(TemporalType.DATE)
    protected Date dateCreation;
	
	 @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	 @JoinColumn(name = "idImageTheorie")
     private Collection<ImageTheorie> imageTheories;
    
    
	
	@ManyToOne( optional = false)
	private Categorie categorie;
	
	@ManyToOne( optional = false)
	private UserForum userForum;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	@JoinColumn(name = "idCommentaire")
	private Collection<Commentaire> commentaires;
	
	 
	
	
	public Theorie() {
		
    	this.commentaires = new ArrayList<Commentaire>();
    }
		
	
	public Theorie(Integer idTheorie,String titre, String description,Date dateCreation) {
		super();
		this.idTheorie = idTheorie;
		this.titre = titre;
		this.description = description;
		this.dateCreation = new Date();
		
		
    	this.commentaires = new ArrayList<Commentaire>();
	}
	
	
	public Theorie(String titre, String description,Date dateCreation) {
		super();
		this.titre = titre;
		this.description = description;
		this.dateCreation = new Date();
		
		
    	this.commentaires = new ArrayList<Commentaire>();
	}
	public Collection<ImageTheorie> getImageTheorie() {
        return this.imageTheories;
    }
	public void addImageTheorie(ImageTheorie imageTheorie) {
		this.imageTheories.add(imageTheorie);
		
	}
	
	
	public int getIdTheorie() {
		return this.idTheorie;
	}
	public void setIdTheorie(int idrecette) {
		this.idTheorie = idrecette;
	}
	public String getTitre() {
		return this.titre;
	}
	public String getDescription() {
		return this.description;
	}

	

    public Date getDateCreation() {
		return this.dateCreation;
	}
    public void setTitre(String titre) {
		 this.titre = titre;
	}
	public void setDescription(String description) {
		 this.description = description;
	}

	

    public void setDateCreation(Date datecreation) {
		 this.dateCreation = datecreation;
	}
	public void setMembre(UserForum membre) {
		this.userForum = membre;
	}
	public UserForum getMembre() {
		return this.userForum;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Categorie getCategorie() {
		return this.categorie;
	}
	public Collection<Commentaire> getCommentaire() {
		return this.commentaires ;
	}
	public void addRecetteCommentaire(Commentaire commentaire) {
	commentaires.add(commentaire);
	commentaire.setCommentaireTheorie(this);
	}
	
	public void removeRecetteCommentaire(Commentaire commentaire) {
		commentaires.remove(commentaire);
	}
	
	@Override
    public String toString() {
        return "Membre: " + this.idTheorie + ", " + this.titre + ", " + this.description +" ,"+this.userForum.getPseudo() ;
    }

}

