package org.nttdata.ecomjstl.model;

import jakarta.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the produit database table.
 */
@Entity
@Table(name = "produit")
@NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String designation;

    private float prix;

    private int quantite;

    private int sdr;

	private String image;

	@ManyToOne
	@JoinColumn(name="idCateg")
	private Categorie categorie;

    public Produit() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getSdr() {
        return this.sdr;
    }

    public void setSdr(int sdr) {
        this.sdr = sdr;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Produit{" +
				"id=" + id +
				", designation='" + designation + '\'' +
				", prix=" + prix +
				", quantite=" + quantite +
				", sdr=" + sdr +
				", categorie=" + categorie +
				'}';
	}
}