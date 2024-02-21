package entities;

import java.io.Serializable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="DEVLP")
public class Developpeur extends Personnel implements Serializable {

	
	private String langage;
	private static final long serialVersionUID = 1L;

	public Developpeur() {
		super();
	}   
	public String getLangage() {
		return this.langage;
	}

	public void setLangage(String langage) {
		this.langage = langage;
	}
   
}
