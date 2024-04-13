package com.amenal.amenalbackend.achat;

import com.amenal.amenalbackend.achat.core.domain.*;

import java.util.ArrayList;
import java.util.List;

public class AchatTestDataUtil {
    public static Besoin createBesoinWhenChargeIsNull(){
        Besoin besoin = new Besoin();
        besoin.setCharge(null);

        return besoin;
    }

    public static Besoin createBesoinWhenPrixUnitaireIsNull(){
        ChargeStandard charge = new ChargeStandard();
        charge.setPrixUnitaire(null);

        Besoin besoin = new Besoin();
        besoin.setCharge(charge);
        besoin.setQte(5.0);

        return besoin;
    }

    public static Besoin createBesoin(){
        ChargeStandard charge = new ChargeStandard();
        charge.setPrixUnitaire(10.0);
        charge.setTva(20.0); // 20%

        Besoin besoin = new Besoin();
        besoin.setCharge(charge);
        besoin.setQte(5.0);

        return besoin;
    }

    public static DetailDevis createDetailDevisWithValues(Double prixUnitaire, Double qte) {
        DetailDevis detailDevis = new DetailDevis();
        detailDevis.setPrixUnitaire(prixUnitaire);
        detailDevis.setQte(qte);
        return detailDevis;
    }

    public static DetailDevis createDetailDevisWithNullValues(Double prixUnitaire, Double qte) {
        DetailDevis detailDevis = new DetailDevis();
        detailDevis.setPrixUnitaire(prixUnitaire);
        detailDevis.setQte(qte);
        return detailDevis;
    }

    public static DetailReception createDetailReceptionWithValues(Double prixUnitaire, Double qte) {
        DetailDevis detailDevis = new DetailDevis();
        detailDevis.setPrixUnitaire(prixUnitaire);

        DetailReception detailReception = new DetailReception();
        detailReception.setDetailCommande(detailDevis);
        detailReception.setQte(qte);

        return detailReception;
    }

    public static DetailReception createDetailReceptionWithNullValues(Double prixUnitaire, Double qte) {
        DetailDevis detailDevis = new DetailDevis();
        detailDevis.setPrixUnitaire(prixUnitaire);

        DetailReception detailReception = new DetailReception();
        detailReception.setDetailCommande(detailDevis);
        detailReception.setQte(qte);

        return detailReception;
    }

    public static Devis createDevisWithNullDetailDevis() {
        Devis devis = new Devis();
        devis.setDetailDevis(null);
        return devis;
    }

    public static Devis createDevisWithDetailDevis(List<DetailDevis> detailDevisList) {
        Devis devis = new Devis();
        devis.setDetailDevis(detailDevisList);
        return devis;
    }

    public static List<DetailDevis> createDetailDevisList() {
        List<DetailDevis> detailDevisList = new ArrayList<>();
        detailDevisList.add(new DetailDevis(null, null, 5.0, 10.0));
        detailDevisList.add(new DetailDevis(null, null, 3.0, 15.0));
        return detailDevisList;
    }

    public static Reception createReceptionWithNullDetailReceptions() {
        Reception reception = new Reception();
        reception.setDetailReceptions(null);
        return reception;
    }

    public static Reception createReceptionWithDetailReceptions(List<DetailReception> detailReceptionList) {
        Reception reception = new Reception();
        reception.setDetailReceptions(detailReceptionList);
        return reception;
    }

    public static List<DetailReception> createDetailReceptionList() {
        List<DetailReception> detailReceptionList = new ArrayList<>();
        detailReceptionList.add(createDetailReceptionWithValues(5.0, 10.0));
        detailReceptionList.add(createDetailReceptionWithValues(3.0, 15.0));
        return detailReceptionList;
    }
}
