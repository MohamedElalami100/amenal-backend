package com.amenal.amenalbackend.achat.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amenal.amenalbackend.achat.core.port.in.AttestationRgfUseCase;
import com.amenal.amenalbackend.achat.core.port.in.BesoinUseCase;
import com.amenal.amenalbackend.achat.core.port.in.ChargeStandardUseCase;
import com.amenal.amenalbackend.achat.core.port.in.CommandeUseCase;
import com.amenal.amenalbackend.achat.core.port.in.CompteBanquaireUseCase;
import com.amenal.amenalbackend.achat.core.port.in.ContactFournisseurUseCase;
import com.amenal.amenalbackend.achat.core.port.in.ContactLivraisonUseCase;
import com.amenal.amenalbackend.achat.core.port.in.ContratDlpUseCase;
import com.amenal.amenalbackend.achat.core.port.in.ContratPlafondUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DemandeDevisUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DetailBesoinUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DetailDemandeDevisUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DetailDevisUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DetailFactureUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DetailReceptionUseCase;
import com.amenal.amenalbackend.achat.core.port.in.DevisUseCase;
import com.amenal.amenalbackend.achat.core.port.in.EvaluationFournisseurUseCase;
import com.amenal.amenalbackend.achat.core.port.in.FactureUseCase;
import com.amenal.amenalbackend.achat.core.port.in.FournisseurUseCase;
import com.amenal.amenalbackend.achat.core.port.in.PaiementUseCase;
import com.amenal.amenalbackend.achat.core.port.in.ReceptionUseCase;
import com.amenal.amenalbackend.achat.core.port.in.RemiseUseCase;
import com.amenal.amenalbackend.achat.core.port.in.TransportUseCase;
import com.amenal.amenalbackend.achat.core.port.out.AttestationRgfDao;
import com.amenal.amenalbackend.achat.core.port.out.BesoinDao;
import com.amenal.amenalbackend.achat.core.port.out.ChargeStandardDao;
import com.amenal.amenalbackend.achat.core.port.out.CommandeDao;
import com.amenal.amenalbackend.achat.core.port.out.CompteBanquaireDao;
import com.amenal.amenalbackend.achat.core.port.out.ContactFournisseurDao;
import com.amenal.amenalbackend.achat.core.port.out.ContactLivraisonDao;
import com.amenal.amenalbackend.achat.core.port.out.ContratDlpDao;
import com.amenal.amenalbackend.achat.core.port.out.ContratPlafondDao;
import com.amenal.amenalbackend.achat.core.port.out.DemandeDevisDao;
import com.amenal.amenalbackend.achat.core.port.out.DetailBesoinDao;
import com.amenal.amenalbackend.achat.core.port.out.DetailDemandeDevisDao;
import com.amenal.amenalbackend.achat.core.port.out.DetailDevisDao;
import com.amenal.amenalbackend.achat.core.port.out.DetailFactureDao;
import com.amenal.amenalbackend.achat.core.port.out.DetailReceptionDao;
import com.amenal.amenalbackend.achat.core.port.out.DevisDao;
import com.amenal.amenalbackend.achat.core.port.out.EvaluationFournisseurDao;
import com.amenal.amenalbackend.achat.core.port.out.FactureDao;
import com.amenal.amenalbackend.achat.core.port.out.FournisseurDao;
import com.amenal.amenalbackend.achat.core.port.out.PaiementDao;
import com.amenal.amenalbackend.achat.core.port.out.ReceptionDao;
import com.amenal.amenalbackend.achat.core.port.out.RemiseDao;
import com.amenal.amenalbackend.achat.core.port.out.TransportDao;

@Configuration
public class AchatConfig {
	@Bean
	public AttestationRgfUseCase attestationRgfUseCase(AttestationRgfDao attestationRgfDao) {
		return new AttestationRgfUseCase(attestationRgfDao);
	}

	@Bean
	public BesoinUseCase besoinUseCase(BesoinDao besoinDao) {
		return new BesoinUseCase(besoinDao);
	}

	@Bean
	public ChargeStandardUseCase chargeStandardUseCase(ChargeStandardDao chargeStandardDao) {
		return new ChargeStandardUseCase(chargeStandardDao);
	}

	@Bean
	public CommandeUseCase commandeUseCase(CommandeDao commandeDao) {
		return new CommandeUseCase(commandeDao);
	}

	@Bean
	public CompteBanquaireUseCase compteBanquaireUseCase(CompteBanquaireDao compteBanquaireDao) {
		return new CompteBanquaireUseCase(compteBanquaireDao);
	}

	@Bean
	public ContactFournisseurUseCase contactFournisseurUseCase(ContactFournisseurDao contactFournisseurDao) {
		return new ContactFournisseurUseCase(contactFournisseurDao);
	}

	@Bean
	public ContactLivraisonUseCase contactLivraisonUseCase(ContactLivraisonDao contactLivraisonDao) {
		return new ContactLivraisonUseCase(contactLivraisonDao);
	}

	@Bean
	public ContratDlpUseCase contratDlpUseCase(ContratDlpDao contratDlpDao) {
		return new ContratDlpUseCase(contratDlpDao);
	}

	@Bean
	public ContratPlafondUseCase contratPlafondUseCase(ContratPlafondDao contratPlafondDao) {
		return new ContratPlafondUseCase(contratPlafondDao);
	}

	@Bean
	public DemandeDevisUseCase demandeDevisUseCase(DemandeDevisDao demandeDevisDao) {
		return new DemandeDevisUseCase(demandeDevisDao);
	}

	@Bean
	public DetailBesoinUseCase detailBesoinUseCase(DetailBesoinDao detailBesoinDao) {
		return new DetailBesoinUseCase(detailBesoinDao);
	}

	@Bean
	public DetailDemandeDevisUseCase detailDemandeDevisUseCase(DetailDemandeDevisDao detailDemandeDevisDao) {
		return new DetailDemandeDevisUseCase(detailDemandeDevisDao);
	}

	@Bean
	public DetailDevisUseCase detailDevisUseCase(DetailDevisDao detailDevisDao) {
		return new DetailDevisUseCase(detailDevisDao);
	}

	@Bean
	public DetailFactureUseCase detailFactureUseCase(DetailFactureDao detailFactureDao) {
		return new DetailFactureUseCase(detailFactureDao);
	}

	@Bean
	public DetailReceptionUseCase detailReceptionUseCase(DetailReceptionDao detailReceptionDao, DetailDevisDao detailDevisDao) {
		return new DetailReceptionUseCase(detailReceptionDao, detailDevisDao);
	}

	@Bean
	public DevisUseCase devisUseCase(DevisDao devisDao) {
		return new DevisUseCase(devisDao);
	}

	@Bean
	public EvaluationFournisseurUseCase evaluationFournisseurUseCase(
			EvaluationFournisseurDao evaluationFournisseurDao) {
		return new EvaluationFournisseurUseCase(evaluationFournisseurDao);
	}

	@Bean
	public FactureUseCase factureUseCase(FactureDao factureDao) {
		return new FactureUseCase(factureDao);
	}

	@Bean
	public FournisseurUseCase fournisseurUseCase(FournisseurDao fournisseurDao) {
		return new FournisseurUseCase(fournisseurDao);
	}

	@Bean
	public PaiementUseCase paiementUseCase(PaiementDao paiementDao, FactureDao factureDao) {
		return new PaiementUseCase(paiementDao, factureDao);
	}

	@Bean
	public ReceptionUseCase receptionUseCase(ReceptionDao receptionDao) {
		return new ReceptionUseCase(receptionDao);
	}

	@Bean
	public RemiseUseCase remiseUseCase(RemiseDao remiseDao) {
		return new RemiseUseCase(remiseDao);
	}

	@Bean
	public TransportUseCase transportUseCase(TransportDao transportDao) {
		return new TransportUseCase(transportDao);
	}
}
