package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.NatureArticle;
import com.amenal.amenalbackend.budget.application.port.out.NatureArticleDao;

public class NatureArticleUseCase {
	
	private NatureArticleDao natureArticleDao;
	
	public NatureArticleUseCase(NatureArticleDao natureArticleDao) {
		this.natureArticleDao = natureArticleDao;
	}

	public NatureArticle findNatureArticleById(Integer id) {
	    return natureArticleDao.findNatureArticleById(id);
	}

	public List<NatureArticle> findAllNatureArticles() {
		return natureArticleDao.findAllNatureArticles();
	}
	
	public NatureArticle saveNatureArticle(NatureArticle natureArticle) {
		return natureArticleDao.saveNatureArticle(natureArticle);
	}
	
	public NatureArticle updateNatureArticle(NatureArticle natureArticle) {
		return natureArticleDao.updateNatureArticle(natureArticle);
	}
	
	public void deleteNatureArticle(Integer id) {
		natureArticleDao.deleteNatureArticle(id);
	}
	

}
