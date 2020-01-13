package fr.onepiecetheorie.pojos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.persistence.*;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "mugiwara")
public class Mugiwara {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMugiwara")
    private Integer idMugiwara;
     
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "name")
    private String name;
    
    @Temporal(TemporalType.DATE)
    protected Date birthDate;
    
    @Column(name = "description")
	 @Type(type="text")
	private String description;
    
    @Column(name = "bounty")
    private String bounty;
    
    @ManyToOne( optional = false)
   	private Categorie categorie;
    
    
    public Mugiwara() {
    	
    }
 
    public Mugiwara(Integer id, String name,String lastname,Date birthDate,String description, String bounty) {
        this.idMugiwara = id;
        this.name = name;
        this.lastName = lastname;
        this.birthDate = birthDate;
        this.description = description;
        this.bounty = bounty;
        
    }
    
     
    public Mugiwara( String name,String lastname,Date birthDate,String description, String bounty) {
        
        this.name = name;
        this.lastName = lastname;
        this.birthDate = birthDate;
        this.description = description;
        this.bounty = bounty;
     
    }
    
    
    public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Categorie getCategorie() {
		return this.categorie;
	}
	public int getIdMugiwara() {
        return this.idMugiwara;
    }
	public void setIdMugiwara(int idMugiwara) {
		this.idMugiwara = idMugiwara;
	}
 
    
    public String getName() {
        return this.name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return this.lastName;
    }
 
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    public Date getDateB() {
        return this.birthDate;
    }
 
    public void setDateB(Date datebirth) {
        this.birthDate = datebirth;
    }
    
    public String getDescription() {
        return this.description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBounty() {
        return this.bounty;
    }
 
    public void setBounty(String bounty) {
        this.bounty = bounty;
    }
    @Override
    public String toString() {
        return "Mugiwara: " + this.idMugiwara + ", " + this.name + ", " + this.lastName +" , " +this.bounty;
    }
    
}
