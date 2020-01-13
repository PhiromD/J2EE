package fr.onepiecetheorie.pojos;
import java.util.ArrayList;


import java.util.Collection;
import java.util.Date;


import javax.persistence.*;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
 
@Entity
@Table(name = "userforum")
public class UserForum {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;
     
    @Column(name = "name")
    private String name;
    
    @Column(name = "pseudo")
    private String pseudo;
    
    @Column(name = "mdp")
    private String mdp;
     
    @Temporal(TemporalType.DATE)
    protected Date dateInscription;
    
    @Column(name="email")
    private String email;
    
    @Column(name="bounty")
    private int bounty;
    
    @OneToOne
    @JoinColumn(name = "idUser")
	 private Image image;
    
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "idTheorie")
    @Fetch(FetchMode.SELECT)
	private Collection<Theorie> theories;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "idCommentaire")
	private Collection<Commentaire> commentaires;
    
    @ManyToOne(optional = false)
	private Categorie categorie;
     
    public UserForum() {
    	this.theories = new ArrayList<Theorie>();
    	this.commentaires = new ArrayList<Commentaire>();
    }
 
    public UserForum(Integer id, String nom,String pseudo,String mdp,String email, Date dateInscription) {
        this.idUser = id;
        this.name = nom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.email = email;
        this.dateInscription = dateInscription;
        this.theories = new ArrayList<Theorie>();
    	this.commentaires = new ArrayList<Commentaire>();
    	this.bounty = 0;
    }
    
     
    public UserForum( String nom,String pseudo,String mdp,String email, Date dateInscription) {
    	 this.name = nom;
         this.pseudo = pseudo;
         this.mdp = mdp;
         this.email = email;
         this.dateInscription = dateInscription;
         this.theories = new ArrayList<Theorie>();
     	this.commentaires = new ArrayList<Commentaire>();
     	this.bounty = 0;
     
    }
    public Image getImage() {
        return this.image;
    }
    
    public Integer getIdUser() {
        return idUser;
    }
 
    public void setIdUser(Integer id) {
        this.idUser = id;
    }
 
    public String getNom() {
        return name;
    }
 
    public void setNom(String nom) {
        this.name = nom;
    }
 
    public String getPseudo() {
        return pseudo;
    }
 
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getMdp() {
        return mdp;
    }
 
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDateInscription() {
        return dateInscription;
    }
 
    public void setDateInscription(Date date) {
        this.dateInscription = date;
    }
   
	public Collection<Commentaire> getCommentaire() {
		return this.commentaires ;
	}
	public void addCommentaire(Commentaire commentaire) {
	commentaires.add(commentaire);
	
	}
	public void addTheorie(Theorie theorie) {
		theories.add(theorie);
		
		}
	public Collection<Theorie> getRecettes() {
		return this.theories ;
	}
	public void removeRecette(Theorie recette) {
		theories.remove(recette);
	}
	public void addBounty(int bounty) {
		this.bounty = this.bounty + bounty;
		
		}
	public int getBounty() {
		return this.bounty;
	}
	public void setBounty(int bounty) {
		this.bounty = bounty;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
     
    @Override
    public String toString() {
        return "Membre: " + this.idUser + ", " + this.name + ", " + this.pseudo +" , " + this.email+" , " +this.bounty;
    }
    
}

