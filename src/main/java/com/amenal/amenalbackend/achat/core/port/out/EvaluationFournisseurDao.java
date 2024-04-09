package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.EvaluationFournisseur;

public interface EvaluationFournisseurDao {
	EvaluationFournisseur findEvaluationFournisseurById(Integer id);
	
	List<EvaluationFournisseur> findAllEvaluationFournisseurs();
	
	EvaluationFournisseur saveEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur);
	
	EvaluationFournisseur updateEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur);
	
	void deleteEvaluationFournisseur(Integer id);

}
