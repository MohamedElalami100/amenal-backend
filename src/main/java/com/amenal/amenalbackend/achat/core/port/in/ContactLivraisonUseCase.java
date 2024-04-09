package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.ContactLivraison;
import com.amenal.amenalbackend.achat.core.port.out.ContactLivraisonDao;

public class ContactLivraisonUseCase {
	
	private ContactLivraisonDao contactLivraisonDao;
	
	public ContactLivraisonUseCase(ContactLivraisonDao contactLivraisonDao) {
		this.contactLivraisonDao = contactLivraisonDao;
	}

	public ContactLivraison findContactLivraisonById(Integer id) {
	    return contactLivraisonDao.findContactLivraisonById(id);
	}

	public List<ContactLivraison> findAllContactLivraisons() {
		return contactLivraisonDao.findAllContactLivraisons();
	}
	
	public ContactLivraison saveContactLivraison(ContactLivraison contactLivraison) {
		return contactLivraisonDao.saveContactLivraison(contactLivraison);
	}
	
	public ContactLivraison updateContactLivraison(ContactLivraison contactLivraison) {
		return contactLivraisonDao.updateContactLivraison(contactLivraison);
	}
	
	public void deleteContactLivraison(Integer id) {
		contactLivraisonDao.deleteContactLivraison(id);
	}	

}
