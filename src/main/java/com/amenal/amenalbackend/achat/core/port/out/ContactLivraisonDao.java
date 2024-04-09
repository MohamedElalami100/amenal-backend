package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ContactLivraison;

public interface ContactLivraisonDao {
	ContactLivraison findContactLivraisonById(Integer id);
	
	List<ContactLivraison> findAllContactLivraisons();
	
	ContactLivraison saveContactLivraison(ContactLivraison contactLivraison);
	
	ContactLivraison updateContactLivraison(ContactLivraison contactLivraison);
	
	void deleteContactLivraison(Integer id);

}
