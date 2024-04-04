package com.amenal.amenalbackend.achat.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amenal.amenalbackend.achat.application.port.in.AttestationRgfUseCase;
import com.amenal.amenalbackend.achat.application.port.in.BesoinUseCase;
import com.amenal.amenalbackend.achat.application.port.in.ChargeStandardUseCase;
import com.amenal.amenalbackend.achat.application.port.in.CommandeUseCase;
import com.amenal.amenalbackend.achat.application.port.in.CompteBanquaireUseCase;
import com.amenal.amenalbackend.achat.application.port.in.ContactFournisseurUseCase;
import com.amenal.amenalbackend.achat.application.port.in.ContactLivraisonUseCase;
import com.amenal.amenalbackend.achat.application.port.in.ContratDlpUseCase;
import com.amenal.amenalbackend.achat.application.port.in.ContratPlafondUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DemandeDevisUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DetailBesoinUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DetailDemandeDevisUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DetailDevisUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DetailFactureUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DetailReceptionUseCase;
import com.amenal.amenalbackend.achat.application.port.in.DevisUseCase;
import com.amenal.amenalbackend.achat.application.port.in.EvaluationFournisseurUseCase;
import com.amenal.amenalbackend.achat.application.port.in.FactureUseCase;
import com.amenal.amenalbackend.achat.application.port.in.FournisseurUseCase;
import com.amenal.amenalbackend.achat.application.port.in.PaiementUseCase;
import com.amenal.amenalbackend.achat.application.port.in.ReceptionUseCase;
import com.amenal.amenalbackend.achat.application.port.in.RemiseUseCase;
import com.amenal.amenalbackend.achat.application.port.in.TransportUseCase;
import com.amenal.amenalbackend.achat.application.port.out.AttestationRgfDao;
import com.amenal.amenalbackend.achat.application.port.out.BesoinDao;
import com.amenal.amenalbackend.achat.application.port.out.ChargeStandardDao;
import com.amenal.amenalbackend.achat.application.port.out.CommandeDao;
import com.amenal.amenalbackend.achat.application.port.out.CompteBanquaireDao;
import com.amenal.amenalbackend.achat.application.port.out.ContactFournisseurDao;
import com.amenal.amenalbackend.achat.application.port.out.ContactLivraisonDao;
import com.amenal.amenalbackend.achat.application.port.out.ContratDlpDao;
import com.amenal.amenalbackend.achat.application.port.out.ContratPlafondDao;
import com.amenal.amenalbackend.achat.application.port.out.DemandeDevisDao;
import com.amenal.amenalbackend.achat.application.port.out.DetailBesoinDao;
import com.amenal.amenalbackend.achat.application.port.out.DetailDemandeDevisDao;
import com.amenal.amenalbackend.achat.application.port.out.DetailDevisDao;
import com.amenal.amenalbackend.achat.application.port.out.DetailFactureDao;
import com.amenal.amenalbackend.achat.application.port.out.DetailReceptionDao;
import com.amenal.amenalbackend.achat.application.port.out.DevisDao;
import com.amenal.amenalbackend.achat.application.port.out.EvaluationFournisseurDao;
import com.amenal.amenalbackend.achat.application.port.out.FactureDao;
import com.amenal.amenalbackend.achat.application.port.out.FournisseurDao;
import com.amenal.amenalbackend.achat.application.port.out.PaiementDao;
import com.amenal.amenalbackend.achat.application.port.out.ReceptionDao;
import com.amenal.amenalbackend.achat.application.port.out.RemiseDao;
import com.amenal.amenalbackend.achat.application.port.out.TransportDao;

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
	public DetailReceptionUseCase detailReceptionUseCase(DetailReceptionDao detailReceptionDao) {
		return new DetailReceptionUseCase(detailReceptionDao);
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
	public PaiementUseCase paiementUseCase(PaiementDao paiementDao) {
		return new PaiementUseCase(paiementDao);
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
