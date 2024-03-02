package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.BudgetAchatAv;
import com.amenal.amenalbackend.application.project.port.out.BudgetAchatAvDao;

public class BudgetAchatAvUseCase {
	
	private BudgetAchatAvDao budgetAchatAvDao;
	
	public BudgetAchatAvUseCase(BudgetAchatAvDao budgetAchatAvDao) {
		this.budgetAchatAvDao = budgetAchatAvDao;
	}

	public BudgetAchatAv findBudgetAchatAvById(Integer id) {
	    return budgetAchatAvDao.findBudgetAchatAvById(id);
	}

	public List<BudgetAchatAv> findAllBudgetAchatAvs() {
		return budgetAchatAvDao.findAllBudgetAchatAvs();
	}
	
	public BudgetAchatAv saveBudgetAchatAv(BudgetAchatAv budgetAchatAv) {
		return budgetAchatAvDao.saveBudgetAchatAv(budgetAchatAv);
	}
	
	public BudgetAchatAv updateBudgetAchatAv(BudgetAchatAv budgetAchatAv) {
		return budgetAchatAvDao.updateBudgetAchatAv(budgetAchatAv);
	}
	
	public void deleteBudgetAchatAv(Integer id) {
		budgetAchatAvDao.deleteBudgetAchatAv(id);
	}
	

}
