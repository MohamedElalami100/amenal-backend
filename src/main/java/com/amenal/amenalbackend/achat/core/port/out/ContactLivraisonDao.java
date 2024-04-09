package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.ContactLivraison;

public interface ContactLivraisonDao {
	ContactLivraison findContactLivraisonById(Integer id);
	
	List<ContactLivraison> findAllContactLivraisons();
	
	ContactLivraison saveContactLivraison(ContactLivraison contactLivraison);
	
	ContactLivraison updateContactLivraison(ContactLivraison contactLivraison);
	
	void deleteContactLivraison(Integer id);

}
