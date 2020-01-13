package fr.onepiecetheorie.pojos;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
 
@Entity
@Table(name = "commentaire")
/**
 * @author phirom
 *
 */
public class Commentaire {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCommentaire")
    private Integer idCommentaire;

	 
	 @Column(name = "contenu")
	 @Type(type="text")
	private String contenu;
	 
	 @Column(name = "note")
	private int note;
	 
     @ManyToOne( optional = false,cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	private Theorie theorie;
	 
	 @ManyToOne( optional = false,cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	private UserForum user;
	
	@Temporal(TemporalType.DATE)
    protected Date dateCreation;
	
	public Commentaire() {
		
	}
	public Commentaire(String contenu,int note,Date dateCreation) {
		
		
		this.contenu = contenu;
		this.dateCreation = dateCreation;
		this.note = note;
	}
	
	public Commentaire(int idCommentaire, String contenu,int note,Date dateCreation) {
		super();
		this.idCommentaire = idCommentaire;
		this.contenu = contenu;
		this.dateCreation = new Date();
		this.note = note;
	}
	
	public String getContenu() {
		return this.contenu;
	}

	public int getNote() {
		return this.note;
	}

    public Date getDateCreation() {
		return this.dateCreation;
	}
    
    public void setContenu(String contenu) {
    	this.contenu=contenu;
    }
    public void setDateCreation(Date dateCreation) {
    	this.dateCreation=dateCreation;
    }
    public void setNote(int note) {
    	this.note=note;
    }
 
    public int getIdCommentaire() {
    	return this.idCommentaire;
    }
    public int getIdTheorie() {
    	return this.theorie.getIdTheorie();
    }
    public String getPseudoCommentaire() {
    	return this.user.getPseudo();
    }
    public void setIdCommentaire(int idcommentaire) {
    	this.idCommentaire = idcommentaire;
    	
    }
   
    public void setMembre(UserForum user) {
		this.user = user;
	}
	public UserForum getMembre() {
		return this.user;
	}
	public void setCommentaireTheorie(Theorie theorie) {
		this.theorie = theorie;
	}
	public Theorie getTheorie() {
		return this.theorie;
	}
	
	@Override
    public String toString() {
        return "Commentaire: " + this.idCommentaire +  ", " + this.contenu +" , "+ this.note+"," + this.theorie.getIdTheorie()+","+this.user.getPseudo(); 
    }

}
