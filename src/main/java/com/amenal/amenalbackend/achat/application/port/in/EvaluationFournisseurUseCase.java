package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.EvaluationFournisseur;
import com.amenal.amenalbackend.achat.application.port.out.EvaluationFournisseurDao;

public class EvaluationFournisseurUseCase {
	
	private EvaluationFournisseurDao evaluationFournisseurDao;
	
	public EvaluationFournisseurUseCase(EvaluationFournisseurDao evaluationFournisseurDao) {
		this.evaluationFournisseurDao = evaluationFournisseurDao;
	}

	public EvaluationFournisseur findEvaluationFournisseurById(Integer id) {
	    return evaluationFournisseurDao.findEvaluationFournisseurById(id);
	}

	public List<EvaluationFournisseur> findAllEvaluationFournisseurs() {
		return evaluationFournisseurDao.findAllEvaluationFournisseurs();
	}
	
	public EvaluationFournisseur saveEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur) {
		return evaluationFournisseurDao.saveEvaluationFournisseur(evaluationFournisseur);
	}
	
	public EvaluationFournisseur updateEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur) {
		return evaluationFournisseurDao.updateEvaluationFournisseur(evaluationFournisseur);
	}
	
	public void deleteEvaluationFournisseur(Integer id) {
		evaluationFournisseurDao.deleteEvaluationFournisseur(id);
	}	

}
