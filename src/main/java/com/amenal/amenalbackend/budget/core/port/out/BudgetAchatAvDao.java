package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.BudgetAchatAv;

public interface BudgetAchatAvDao {
	BudgetAchatAv findBudgetAchatAvById(Integer id);
	
	List<BudgetAchatAv> findAllBudgetAchatAvs();
	
	BudgetAchatAv saveBudgetAchatAv(BudgetAchatAv budgetAchatAv);
	
	BudgetAchatAv updateBudgetAchatAv(BudgetAchatAv budgetAchatAv);
	
	void deleteBudgetAchatAv(Integer id);

	BudgetAchatAv getOneBudgetAchatByAvenantId(Integer id);
	
}
