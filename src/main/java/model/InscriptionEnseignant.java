package model;
// Generated 6 nov. 2024, 13:56:39 by Hibernate Tools 5.5.9.Final

/**
 * InscriptionEnseignant generated by hbm2java
 */
public class InscriptionEnseignant implements java.io.Serializable {

	private Integer idIns;
	private Cours cours;
	private Enseignant enseignant;

	public InscriptionEnseignant() {
	}

	public InscriptionEnseignant(Cours cours, Enseignant enseignant) {
		this.cours = cours;
		this.enseignant = enseignant;
	}

	public Integer getIdIns() {
		return this.idIns;
	}

	public void setIdIns(Integer idIns) {
		this.idIns = idIns;
	}

	public Cours getCours() {
		return this.cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}
