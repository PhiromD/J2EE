package fr.onepiecetheorie.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="imagetheorie")
public class ImageTheorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idImageTheorie;


	@Column(name="chemin")
	private String chemin;

	@Column(name="nameImage")
	private String nameImage;
	
	@ManyToOne(optional = false,cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	private Theorie theorie;

    

	public ImageTheorie() {


	}

	public ImageTheorie(Integer id, String chemin) {
		this.idImageTheorie = id;
		this.chemin = chemin;

	}


	public ImageTheorie( String chemin,String nom) {

		this.chemin = chemin;
		this.nameImage=nom;


	}
	
	public int getId() {
		return this.idImageTheorie;
	}

	public void setId(int id) {
		this.idImageTheorie = id;
	}
	public String getNameImage() {
		return this.nameImage;
	}

	public void setNameimage(String nom) {
		this.nameImage = nom;
	}
	public String getChemin() {
		return chemin;
	}

	public void setChemin(String name) {
		this.chemin = name;
	}
	public void setTheorie(Theorie theorie) {
		this.theorie = theorie;
	}
	public Theorie getTheorie() {
		return this.theorie;
	}




}


