package service;

import model.Etudiant;
import model.EtudiantHome;

public class EtudiantService {
	
	private EtudiantHome etudiantHome;
	private Etudiant etudiant;
	
	public EtudiantService() {
		this.etudiant=new Etudiant();
		this.etudiantHome = new EtudiantHome();
	}
	
	public Etudiant getEtudiantById(int id) {
		etudiant = etudiantHome.findById(id);
		return etudiant;
	}

}
