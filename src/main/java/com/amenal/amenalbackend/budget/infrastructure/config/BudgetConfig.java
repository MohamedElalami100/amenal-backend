package com.amenal.amenalbackend.budget.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amenal.amenalbackend.budget.core.port.in.AvenantUseCase;
import com.amenal.amenalbackend.budget.core.port.in.BanqueUseCase;
import com.amenal.amenalbackend.budget.core.port.in.ClientUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailChargeAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailChargeTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailChargeUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailDelaiAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailDelaiTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailProduitAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailProduitTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailProduitUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailQualiteAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailQualiteTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DetailQualiteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DocumentUseCase;
import com.amenal.amenalbackend.budget.core.port.in.DossierUseCase;
import com.amenal.amenalbackend.budget.core.port.in.GetChartDataUseCase;
import com.amenal.amenalbackend.budget.core.port.in.GrpQualiteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.LotTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.LotUseCase;
import com.amenal.amenalbackend.budget.core.port.in.MetreAvUseCase;
import com.amenal.amenalbackend.budget.core.port.in.NatureArticleUseCase;
import com.amenal.amenalbackend.budget.core.port.in.PersonnelUseCase;
import com.amenal.amenalbackend.budget.core.port.in.ProduitStandardUseCase;
import com.amenal.amenalbackend.budget.core.port.in.ProduitTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.ProduitUseCase;
import com.amenal.amenalbackend.budget.core.port.in.ProjectUseCase;
import com.amenal.amenalbackend.budget.core.port.in.SaveViaDetailChargeAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.SaveViaDetailDelaiAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.SaveViaDetailProduitAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.SaveViaDetailQualiteAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.TacheTableUseCase;
import com.amenal.amenalbackend.budget.core.port.in.TacheUseCase;
import com.amenal.amenalbackend.budget.core.port.in.UtilisateurUseCase;
import com.amenal.amenalbackend.budget.core.port.out.AvenantDao;
import com.amenal.amenalbackend.budget.core.port.out.BanqueDao;
import com.amenal.amenalbackend.budget.core.port.out.ClientDao;
import com.amenal.amenalbackend.budget.core.port.out.CloudStorageDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeTableDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiTableDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitTableDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteTableDao;
import com.amenal.amenalbackend.budget.core.port.out.DocumentDao;
import com.amenal.amenalbackend.budget.core.port.out.DossierDao;
import com.amenal.amenalbackend.budget.core.port.out.GrpQualiteDao;
import com.amenal.amenalbackend.budget.core.port.out.LotDao;
import com.amenal.amenalbackend.budget.core.port.out.LotTableDao;
import com.amenal.amenalbackend.budget.core.port.out.MetreAvDao;
import com.amenal.amenalbackend.budget.core.port.out.NatureArticleDao;
import com.amenal.amenalbackend.budget.core.port.out.PersonnelDao;
import com.amenal.amenalbackend.budget.core.port.out.ProduitDao;
import com.amenal.amenalbackend.budget.core.port.out.ProduitStandardDao;
import com.amenal.amenalbackend.budget.core.port.out.ProduitTableDao;
import com.amenal.amenalbackend.budget.core.port.out.ProjectDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheTableDao;
import com.amenal.amenalbackend.budget.core.port.out.UtilisateurDao;

@Configuration
public class BudgetConfig {
	
	@Bean
	public AvenantUseCase avenantUseCase(AvenantDao avenantDao) {
		return new AvenantUseCase(avenantDao);
	}

	@Bean
	public BanqueUseCase banqueUseCase(BanqueDao banqueDao) {
		return new BanqueUseCase(banqueDao);
	}

	@Bean
	public ClientUseCase clientUseCase(ClientDao clientDao) {
		return new ClientUseCase(clientDao);
	}

	@Bean
	public DetailChargeUseCase detailChargeUseCase(DetailChargeDao detailChargeDao) {
		return new DetailChargeUseCase(detailChargeDao);
	}

	@Bean
	public DetailProduitUseCase detailProduitUseCase(DetailProduitDao detailProduitDao) {
		return new DetailProduitUseCase(detailProduitDao);
	}

	@Bean
	public DetailQualiteUseCase detailQualiteUseCase(DetailQualiteDao detailQualiteDao) {
		return new DetailQualiteUseCase(detailQualiteDao);
	}

	@Bean
	public DossierUseCase dossierUseCase(DossierDao dossierDao) {
		return new DossierUseCase(dossierDao);
	}

	@Bean
	public GrpQualiteUseCase grpQualiteUseCase(GrpQualiteDao grpQualiteDao) {
		return new GrpQualiteUseCase(grpQualiteDao);
	}

	@Bean
	public LotUseCase lotUseCase(LotDao lotDao) {
		return new LotUseCase(lotDao);
	}

	@Bean
	public MetreAvUseCase metreAvUseCase(MetreAvDao metreAvDao) {
		return new MetreAvUseCase(metreAvDao);
	}

	@Bean
	public NatureArticleUseCase natureArticleUseCase(NatureArticleDao natureArticleUseCase) {
		return new NatureArticleUseCase(natureArticleUseCase);
	}

	@Bean
	public PersonnelUseCase personnelUseCase(PersonnelDao personnelDao) {
		return new PersonnelUseCase(personnelDao);
	}

	@Bean
	public ProduitUseCase produitUseCase(ProduitDao produitDao) {
		return new ProduitUseCase(produitDao);
	}

	@Bean
	public ProduitStandardUseCase produitStandardUseCase(ProduitStandardDao produitStandardDao) {
		return new ProduitStandardUseCase(produitStandardDao);
	}

	@Bean
	public ProjectUseCase projectUseCase(ProjectDao projectDao, AvenantDao avenantDao, LotDao lotDao, TacheDao tacheDao,
										 ProduitDao produitDao, DetailProduitDao detailProduitDao, DetailChargeDao detailChargeDao) {
		return new ProjectUseCase(projectDao, avenantDao, lotDao, tacheDao,
				 produitDao, detailProduitDao, detailChargeDao);
	}

	@Bean
	public TacheUseCase tacheUseCase(TacheDao tacheDao) {
		return new TacheUseCase(tacheDao);
	}

	@Bean
	public UtilisateurUseCase utilisateurUseCase(UtilisateurDao utilisateurdao) {
		return new UtilisateurUseCase(utilisateurdao);
	}

	@Bean
	public ProduitTableUseCase produitTableUseCase(ProduitTableDao produitTableDao) {
		return new ProduitTableUseCase(produitTableDao);
	}

	@Bean
	public LotTableUseCase lotTableUseCase(LotTableDao lotTableDao) {
		return new LotTableUseCase(lotTableDao);
	}

	@Bean
	public TacheTableUseCase tacheTableUseCase(TacheTableDao tacheTableDao) {
		return new TacheTableUseCase(tacheTableDao);
	}

	@Bean
	public DetailProduitTableUseCase detailProduitTableUseCase(DetailProduitTableDao detailProduitTableDao) {
		return new DetailProduitTableUseCase(detailProduitTableDao);
	}

	@Bean
	public DetailChargeTableUseCase detailChargeTableUseCase(DetailChargeTableDao detailChargeTableDao) {
		return new DetailChargeTableUseCase(detailChargeTableDao);
	}

	@Bean
	public DetailDelaiTableUseCase detailDelaiTableUseCase(DetailDelaiTableDao detailDelaiTableDao) {
		return new DetailDelaiTableUseCase(detailDelaiTableDao);
	}

	@Bean
	public DetailQualiteTableUseCase detailQualiteTableUseCase(DetailQualiteTableDao detailQualiteTableDao) {
		return new DetailQualiteTableUseCase(detailQualiteTableDao);
	}

	@Bean
	public DetailProduitAttenteUseCase detailProduitAttenteUseCase(DetailProduitAttenteDao detailProduitAttenteDao) {
		return new DetailProduitAttenteUseCase(detailProduitAttenteDao);
	}

	@Bean
	public DetailChargeAttenteUseCase detailChargeAttenteUseCase(DetailChargeAttenteDao detailChargeAttenteDao) {
		return new DetailChargeAttenteUseCase(detailChargeAttenteDao);
	}

	@Bean
	public DetailDelaiAttenteUseCase detailDelaiAttenteUseCase(DetailDelaiAttenteDao detailDelaiAttenteDao) {
		return new DetailDelaiAttenteUseCase(detailDelaiAttenteDao);
	}

	@Bean
	public DetailQualiteAttenteUseCase detailQualiteAttenteUseCase(DetailQualiteAttenteDao detailQualiteAttenteDao) {
		return new DetailQualiteAttenteUseCase(detailQualiteAttenteDao);
	}

	@Bean
	public SaveViaDetailChargeAttenteUseCase saveViaDetailChargeAttenteUseCase(TacheDao tacheDao, LotDao lotDao,
			ProduitDao produitDao, DetailChargeDao detailChargeDao, DetailChargeAttenteDao detailChargeAttenteDao) {
		return new SaveViaDetailChargeAttenteUseCase(tacheDao, lotDao, produitDao, detailChargeDao,
				detailChargeAttenteDao);
	}

	@Bean
	public SaveViaDetailProduitAttenteUseCase saveViaDetailProduitAttenteUseCase(TacheDao tacheDao, LotDao lotDao,
			ProduitDao produitDao, DetailProduitDao detailProduitDao, DetailProduitAttenteDao detailProduitAttenteDao) {
		return new SaveViaDetailProduitAttenteUseCase(tacheDao, lotDao, produitDao, detailProduitDao,
				detailProduitAttenteDao);
	}

	@Bean
	public SaveViaDetailDelaiAttenteUseCase saveViaDetailDelaiAttenteUseCase(TacheDao tacheDao, LotDao lotDao,
			ProduitDao produitDao, DetailDelaiAttenteDao detailDelaiAttenteDao) {
		return new SaveViaDetailDelaiAttenteUseCase(tacheDao, lotDao, produitDao, detailDelaiAttenteDao);
	}

	@Bean
	public SaveViaDetailQualiteAttenteUseCase saveViaDetailQualiteAttenteUseCase(TacheDao tacheDao, LotDao lotDao,
			ProduitDao produitDao, DetailQualiteDao detailQualiteDao, GrpQualiteDao grpQualiteDao,
			DetailQualiteAttenteDao detailQualiteAttenteDao) {
		return new SaveViaDetailQualiteAttenteUseCase(tacheDao, lotDao, produitDao, detailQualiteDao, grpQualiteDao,
				detailQualiteAttenteDao);
	}

	@Bean
	public GetChartDataUseCase getChartDataUseCase(TacheTableDao tacheTableDao) {
		return new GetChartDataUseCase(tacheTableDao);
	}
	
	@Bean
	public DocumentUseCase documentUseCase(CloudStorageDao googleDriveDao, DocumentDao documentDao, AvenantDao avenantDao) {
		return new DocumentUseCase(googleDriveDao, documentDao, avenantDao);
	}

}
