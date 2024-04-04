package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.NatureArticle;

public interface NatureArticleDao {
	NatureArticle findNatureArticleById(Integer id);
	
	List<NatureArticle> findAllNatureArticles();
	
	NatureArticle saveNatureArticle(NatureArticle natureArticle);
	
	NatureArticle updateNatureArticle(NatureArticle natureArticle);
	
	void deleteNatureArticle(Integer id);
	
}
