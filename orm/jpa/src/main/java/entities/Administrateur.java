package entities;

import java.io.Serializable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="ADMIN")
public class Administrateur extends Personnel implements Serializable {
	private String fonction;
	
	private static final long serialVersionUID = 1L;

	public Administrateur() {
		super();
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
}
