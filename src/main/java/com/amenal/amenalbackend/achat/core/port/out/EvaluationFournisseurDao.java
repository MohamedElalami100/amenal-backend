package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.EvaluationFournisseur;

public interface EvaluationFournisseurDao {
	EvaluationFournisseur findEvaluationFournisseurById(Integer id);
	
	List<EvaluationFournisseur> findAllEvaluationFournisseurs();
	
	EvaluationFournisseur saveEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur);
	
	EvaluationFournisseur updateEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur);
	
	void deleteEvaluationFournisseur(Integer id);

}
