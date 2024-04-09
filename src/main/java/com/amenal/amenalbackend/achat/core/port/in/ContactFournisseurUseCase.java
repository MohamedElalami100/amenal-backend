package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ContactFournisseur;
import com.amenal.amenalbackend.achat.application.port.out.ContactFournisseurDao;

public class ContactFournisseurUseCase {
	
	private ContactFournisseurDao contactFournisseurDao;
	
	public ContactFournisseurUseCase(ContactFournisseurDao contactFournisseurDao) {
		this.contactFournisseurDao = contactFournisseurDao;
	}

	public ContactFournisseur findContactFournisseurById(Integer id) {
	    return contactFournisseurDao.findContactFournisseurById(id);
	}

	public List<ContactFournisseur> findAllContactFournisseurs() {
		return contactFournisseurDao.findAllContactFournisseurs();
	}
	
	public ContactFournisseur saveContactFournisseur(ContactFournisseur contactFournisseur) {
		return contactFournisseurDao.saveContactFournisseur(contactFournisseur);
	}
	
	public ContactFournisseur updateContactFournisseur(ContactFournisseur contactFournisseur) {
		return contactFournisseurDao.updateContactFournisseur(contactFournisseur);
	}
	
	public void deleteContactFournisseur(Integer id) {
		contactFournisseurDao.deleteContactFournisseur(id);
	}	

}
