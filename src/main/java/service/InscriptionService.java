package service;

import model.CoursHome;
import model.InscriptionHome;
import java.util.List;
import model.Cours;
import model.Etudiant;
import model.Inscription;

public class InscriptionService {
	
	private InscriptionHome iHome;
	private CoursHome cHome;
	
	public InscriptionService() {
		this.cHome = new CoursHome();
		this.iHome = new InscriptionHome();
		
	}
	
	
	
	

}
