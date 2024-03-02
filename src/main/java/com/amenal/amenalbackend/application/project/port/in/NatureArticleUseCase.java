package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.NatureArticle;
import com.amenal.amenalbackend.application.project.port.out.NatureArticleDao;

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
