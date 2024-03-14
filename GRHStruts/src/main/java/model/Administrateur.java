package model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Administrateur extends Personnel {
	private String fonction;

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	@Override
	public String toString() {
		return getNom() + ' '  + getPrenom();
	}
}
