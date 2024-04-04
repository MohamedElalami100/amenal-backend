package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ContactFournisseur;

public interface ContactFournisseurDao {
	ContactFournisseur findContactFournisseurById(Integer id);
	
	List<ContactFournisseur> findAllContactFournisseurs();
	
	ContactFournisseur saveContactFournisseur(ContactFournisseur contactFournisseur);
	
	ContactFournisseur updateContactFournisseur(ContactFournisseur contactFournisseur);
	
	void deleteContactFournisseur(Integer id);

}
