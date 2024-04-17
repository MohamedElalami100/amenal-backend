package com.amenal.amenalbackend.budget;

import com.amenal.amenalbackend.budget.core.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetTestDataUtil {
    public static DetailCharge createDetailChargeWithNonNullPriceAndQte() {
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setPcb(10.0);
        detailCharge.setQcb(5.0);

        return detailCharge;
    }

    public static DetailCharge createDetailChargeWithNullPrice() {
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setPcb(null);
        detailCharge.setQcb(5.0);

        return detailCharge;
    }

    public static DetailCharge createDetailChargeWithNullQte() {
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setPcb(10.0);
        detailCharge.setQcb(null);

        return detailCharge;
    }

    public static DetailCharge createDetailChargeWithNullPriceAndQte() {
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setPcb(null);
        detailCharge.setQcb(null);

        return detailCharge;
    }

    public static DetailProduit createDetailProduitWithNonNullDimensionsAndNbr() {
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setDim1(2.0);
        detailProduit.setDim2(3.0);
        detailProduit.setDim3(4.0);
        detailProduit.setNbr(5.0);

        return detailProduit;
    }



    public static DetailProduit createDetailProduitWithNonNullAndNullDimensionsAndNbr() {
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setDim1(2.0);
        detailProduit.setDim2(3.0);
        detailProduit.setDim3(null);
        detailProduit.setNbr(5.0);

        return detailProduit;
    }

    public static DetailProduit createDetailProduitWithNullDimensionsAndNbr() {
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setDim1(null);
        detailProduit.setDim2(null);
        detailProduit.setDim3(null);
        detailProduit.setNbr(null);

        return detailProduit;
    }

    public static Tache createTacheWithDetailChargesAndDetailProduits() {
        Tache tache = new Tache();
        tache.setCle(true);
        tache.setDlb(10);
        tache.setDdb(LocalDate.of(2024, 4, 1));

        Produit produit = new Produit();
        produit.setPpm(10.0);

        tache.setProduit(produit);

        List<DetailCharge> detailCharges = new ArrayList<>();

        DetailCharge detailCharge1 = new DetailCharge();
        detailCharge1.setPcb(10.0);
        detailCharge1.setQcb(2.0);
        DetailCharge detailCharge2 = new DetailCharge();
        detailCharge2.setPcb(15.0);
        detailCharge2.setQcb(3.0);

        detailCharges.add(detailCharge1);
        detailCharges.add(detailCharge2);

        tache.setDetailCharges(detailCharges);

        List<DetailProduit> detailProduits = new ArrayList<>();
        detailProduits.add(new DetailProduit(1, "Reference1", 2.0, 3.0, 4.0, 5.0, null, null, null, null));
        detailProduits.add(new DetailProduit(2, "Reference2", 3.0, 4.0, 5.0, 6.0, null, null, null, null));
        tache.setDetailProduits(detailProduits);

        return tache;
    }

    public static Tache createTacheWithNullDetailChargesAndDetailProduits() {
        Tache tache = new Tache();
        tache.setDlb(10);
        tache.setDdb(LocalDate.of(2024, 4, 1));

        Produit produit = new Produit();
        produit.setPpm(10.0);

        tache.setProduit(produit);

        tache.setDetailCharges(null);
        tache.setDetailProduits(null);
        return tache;
    }

    public static Tache createSecondaryTacheWithDetailChargesAndDetailProduits() {
        Tache tache = createTacheWithDetailChargesAndDetailProduits();
        tache.setCle(false);
        return tache;
    }

    public static Lot createLotWithTaches() {
        Lot lot = new Lot();

        // Add taches
        List<Tache> taches = new ArrayList<>();
        taches.add(createTacheWithDetailChargesAndDetailProduits());
        taches.add(createTacheWithDetailChargesAndDetailProduits());
        lot.setTaches(taches);

        return lot;
    }

    public static Lot createLotWithSecondaryTaches() {
        Lot lot = new Lot();

        // Add taches
        List<Tache> taches = new ArrayList<>();
        taches.add(createSecondaryTacheWithDetailChargesAndDetailProduits());
        taches.add(createSecondaryTacheWithDetailChargesAndDetailProduits());
        lot.setTaches(taches);

        return lot;
    }

    public static Lot createLotWithNullTaches() {
        Lot lot = new Lot();

        // Add taches
        List<Tache> taches = new ArrayList<>();
        taches.add(createTacheWithDetailChargesAndDetailProduits());
        taches.add(createTacheWithNullDetailChargesAndDetailProduits());
        taches.add(new Tache());
        taches.add(null);
        lot.setTaches(taches);

        return lot;
    }

    public static Produit createProduitWithTaches() {
        Produit produit = new Produit();
        produit.setQpm(100.0);
        produit.setPpm(2.0);

        // Add taches
        List<Tache> taches = new ArrayList<>();
        taches.add(createTacheWithDetailChargesAndDetailProduits());
        taches.add(createTacheWithDetailChargesAndDetailProduits());
        produit.setTaches(taches);

        return produit;
    }

    public static Produit createProduitWithSecondaryTaches() {
        Produit produit = new Produit();

        // Add taches
        List<Tache> taches = new ArrayList<>();
        taches.add(createSecondaryTacheWithDetailChargesAndDetailProduits());
        taches.add(createSecondaryTacheWithDetailChargesAndDetailProduits());
        produit.setTaches(taches);

        return produit;
    }

    public static Produit createProduitWithNullTaches() {
        Produit produit = new Produit();

        // Add taches
        List<Tache> taches = new ArrayList<>();
        taches.add(new Tache());
        taches.add(null);
        produit.setTaches(taches);

        return produit;
    }



}
