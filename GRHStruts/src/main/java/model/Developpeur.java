package model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="developer")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Developpeur extends Personnel {
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String langage) {
		this.language = langage;
	}

	@Override
	public String toString() {
		return getNom() + ' '  + getPrenom();
	}
}
