package com.amenal.amenalbackend.Dashboard.config;

import com.amenal.amenalbackend.Dashboard.Services.HomeDashboardService;
import com.amenal.amenalbackend.achat.core.port.out.CommandeDao;
import com.amenal.amenalbackend.achat.core.port.out.FactureDao;
import com.amenal.amenalbackend.achat.core.port.out.PaiementDao;
import com.amenal.amenalbackend.achat.core.port.out.ReceptionDao;
import com.amenal.amenalbackend.budget.core.port.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HomeDashboardConfig {

    @Bean
    public HomeDashboardService homeDashboardService(
            DetailChargeDao detailChargeDao,
            DetailProduitDao detailProduitDao,
            PaiementDao paiementDao,
            TacheDao tacheDao,
            ProjectDao projectDao,
            FactureDao factureDao,
            ReceptionDao receptionDao,
            DetailQualiteDao detailQualiteDao,
            AvenantDao avenantDao,
            CommandeDao commandeDao) {
        return new HomeDashboardService(detailChargeDao, detailProduitDao, paiementDao,
                tacheDao, projectDao, factureDao, receptionDao, detailQualiteDao,
                avenantDao, commandeDao);
    }
}
