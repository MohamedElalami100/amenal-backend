package com.amenal.amenalbackend.Dashboard.Services;

import com.amenal.amenalbackend.Dashboard.Dtos.*;
import com.amenal.amenalbackend.achat.core.domain.Commande;
import com.amenal.amenalbackend.achat.core.domain.Facture;
import com.amenal.amenalbackend.achat.core.domain.Paiement;
import com.amenal.amenalbackend.achat.core.domain.Reception;
import com.amenal.amenalbackend.achat.core.port.out.CommandeDao;
import com.amenal.amenalbackend.achat.core.port.out.FactureDao;
import com.amenal.amenalbackend.achat.core.port.out.PaiementDao;
import com.amenal.amenalbackend.achat.core.port.out.ReceptionDao;
import com.amenal.amenalbackend.achat.infrastructure.dto.FactureDto;
import com.amenal.amenalbackend.achat.infrastructure.dto.ReceptionDto;
import com.amenal.amenalbackend.budget.core.domain.DetailCharge;
import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeDashboardService {
    private DetailChargeDao detailChargeDao;
    private DetailProduitDao detailProduitDao;
    private PaiementDao paiementDao;
    private TacheDao tacheDao;
    private ProjectDao projectDao;
    private FactureDao factureDao;
    private ReceptionDao receptionDao;
    private DetailQualiteDao detailQualiteDao;
    private AvenantDao avenantDao;
    private CommandeDao commandeDao;

    public HomeDashboardService(DetailChargeDao detailChargeDao, DetailProduitDao detailProduitDao, PaiementDao paiementDao, TacheDao tacheDao, ProjectDao projectDao, FactureDao factureDao, ReceptionDao receptionDao,
                                DetailQualiteDao detailQualiteDao, AvenantDao avenantDao, CommandeDao commandeDao) {
        this.detailChargeDao = detailChargeDao;
        this.detailProduitDao = detailProduitDao;
        this.paiementDao = paiementDao;
        this.tacheDao = tacheDao;
        this.projectDao = projectDao;
        this.factureDao = factureDao;
        this.receptionDao = receptionDao;
        this.detailQualiteDao = detailQualiteDao;
        this.avenantDao = avenantDao;
        this.commandeDao = commandeDao;
    }

    public HomeDashboardDataDto getData(){
        HomeDashboardDataDto data = new HomeDashboardDataDto();

        //totalProjets
        List<Project> projects = projectDao.findAllProjects();
        data.setTotalProjets(projects.size());

        //totalTachesAVenir
        List<Tache> tachesAVenir = tacheDao.findAllTaches().stream()
                .filter(tache -> tache.getDateFin() != null
                        && tache.getDateFin().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
        data.setTotalTachesAVenir(tachesAVenir.size());

        //totalChargesBudgetise
        List<DetailCharge> detailCharges = detailChargeDao.findAllDetailCharges();
        data.setTotalChargesBudgetise(detailCharges.stream()
                .filter(detailCharge -> detailCharge.getMontant() != null)
                .mapToDouble(DetailCharge::getMontant)
                .sum());

        //totalDepenses
        List<Paiement> paiements = paiementDao.findAllPaiements();
        data.setTotalDepenses(paiements.stream()
                .filter(paiement -> paiement.getMontant() != null)
                .mapToDouble(Paiement::getMontant)
                .sum());

        //budgetsNonFinalise
        List<ProjetResumeDto> projetResumeDtos = new ArrayList<>();
        List<Project> projectNonFinalise = projects.stream()
                .filter(project -> project.getStatus() != null && project.getStatus() < 2)
                .collect(Collectors.toList());
        for (var project: projectNonFinalise) {
            ProjetResumeDto projetResumeDto = ProjetResumeDto.builder()
                    .id(project.getId())
                    .budget(project.getProject())
                    .nombreAvenant(avenantDao.getAvenantsByProjectId(project.getId()).size())
                    .build();
            projetResumeDtos.add(projetResumeDto);
        }
        data.setBudgetsNonFinalise(projetResumeDtos);

        //tachesEnCours
        List<TacheResumeDto> tacheResumeDtos = new ArrayList<>();
        List<Tache> tachesEnCours = tacheDao.findAllTaches().stream()
                .filter(tache -> tache.getDdb() != null && tache.getDateFin() != null
                        && tache.getDateFin().isAfter(LocalDate.now())
                        && tache.getDdb().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        for (Tache tache: tachesEnCours) {
            if (tache.getLot() == null || tache.getLot().getProject() == null)
                continue;
            TacheResumeDto tacheResumeDto = TacheResumeDto.builder()
                    .id(tache.getId())
                    .tache(tache.getTitreActivite())
                    .lot(tache.getLot().getDesignation())
                    .dateDebut(tache.getDdb())
                    .delai(tache.getDlb())
                    .dateFin(tache.getDateFin())
                    .projetNumber(tache.getLot().getProject().getId())
                    .build();
            tacheResumeDtos.add(tacheResumeDto);
        }
        data.setTachesEnCours(tacheResumeDtos);

        //facturesAPayer
        List<FactureResumeDto> factureResumeDtos = new ArrayList<>();
        List<FactureDto> facturesAPayer = factureDao.findAllFactures().stream()
                .filter(facture -> !facture.getPayee())
                .collect(Collectors.toList());
        for (FactureDto facture: facturesAPayer) {
            FactureResumeDto factureResumeDto = FactureResumeDto.builder()
                    .id(facture.getId())
                    .mntHt(facture.getMntHt())
                    .date(facture.getDateFcf())
                    .delai(facture.getDelaiFacture())
                    .mntTtc(facture.getMntTtc())
                    .reference(facture.getReference())
                    .build();
            factureResumeDtos.add(factureResumeDto);
        }
        data.setFacturesAPayer(factureResumeDtos);

        //receptionsDeCetteSemaine
        List<ReceptionResumeDto> receptionResumeDtos = new ArrayList<>();
        List<ReceptionDto> receptionsDeCetteSemaine = receptionDao.findAllReceptions().stream()
                .filter(reception -> reception.getDateRcf() != null
                        && reception.getDateRcf().isBefore(LocalDate.now())
                        && reception.getDateRcf().isAfter(LocalDate.now().minusDays(7)))
                .collect(Collectors.toList());
        for (ReceptionDto reception: receptionsDeCetteSemaine) {
            ReceptionResumeDto receptionResumeDto = ReceptionResumeDto.builder()
                    .id(reception.getId())
                    .reference(reception.getReference())
                    .commande(reception.getCommande().getId())
                    .build();
            receptionResumeDtos.add(receptionResumeDto);
        }
        data.setReceptionsDeCetteSemaine(receptionResumeDtos);

        //depensesVsBudgets
        List<DepenseVsBudgetDto> depenseVsBudgetDtos = new ArrayList<>();
        for (Project project: projects) {
            DepenseVsBudgetDto depenseVsBudgetDto = new DepenseVsBudgetDto();
            depenseVsBudgetDto.setIdProjet(project.getId());
            depenseVsBudgetDto.setProjet(project.getProject());

            //Budgets
            List<DetailCharge> projectDetailCharges = detailChargeDao
                    .getDetailChargesByProjectId(project.getId());
            depenseVsBudgetDto.setChargeBudgetTotal(projectDetailCharges.stream()
                    .filter(detailCharge -> detailCharge.getMontant() != null)
                    .mapToDouble(DetailCharge::getMontant)
                    .sum());

            //depenses
            List<Commande> projectCommandes = commandeDao
                    .getCommandesByProjectId(project.getId());
            depenseVsBudgetDto.setDepenseTotal(projectCommandes.stream()
                    .filter(commande -> commande.getDevis() != null
                            && commande.getDevis().getMntTtc() != null)
                    .mapToDouble(commande -> commande.getDevis().getMntTtc())
                    .sum());

            depenseVsBudgetDtos.add(depenseVsBudgetDto);
        }
        data.setDepensesVsBudgets(depenseVsBudgetDtos);


        return data;

    }
}
