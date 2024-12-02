package service;

import model.Enseignant;
import model.EnseignantHome;

public class EnseignantService {
	
	private Enseignant enseignant;
	private EnseignantHome enseignantHome;
	
	public EnseignantService() {
		this.enseignant = new Enseignant();
		this.enseignantHome = new EnseignantHome();
	}
	
	public Enseignant getEnseignantById(int id) {
		return enseignantHome.findById(id);
	}
	
	public void addEnseignant(Enseignant e) {
		enseignantHome.persist(e);
	}

}
