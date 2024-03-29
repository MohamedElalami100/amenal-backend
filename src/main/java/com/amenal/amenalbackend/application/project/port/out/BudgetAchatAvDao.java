package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.BudgetAchatAv;

public interface BudgetAchatAvDao {
	BudgetAchatAv findBudgetAchatAvById(Integer id);
	
	List<BudgetAchatAv> findAllBudgetAchatAvs();
	
	BudgetAchatAv saveBudgetAchatAv(BudgetAchatAv budgetAchatAv);
	
	BudgetAchatAv updateBudgetAchatAv(BudgetAchatAv budgetAchatAv);
	
	void deleteBudgetAchatAv(Integer id);

	BudgetAchatAv getOneBudgetAchatByAvenantId(Integer id);
	
}
