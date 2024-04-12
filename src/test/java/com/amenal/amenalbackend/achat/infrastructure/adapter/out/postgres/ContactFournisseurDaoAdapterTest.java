package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.ContactFournisseur;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ContactFournisseurEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ContactFournisseurRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ContactFournisseurDaoAdapterTest {

    @Mock
    private ContactFournisseurRepository contactFournisseurRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ContactFournisseurDaoAdapter contactFournisseurDaoAdapter;

    @Test
    void testFindContactFournisseurById() {
        ContactFournisseurEntity entity = new ContactFournisseurEntity();
        entity.setId(1);

        when(contactFournisseurRepository.findById(1)).thenReturn(Optional.of(entity));

        ContactFournisseur result = contactFournisseurDaoAdapter.findContactFournisseurById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllContactFournisseurs() {
        when(contactFournisseurRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContactFournisseur> result = contactFournisseurDaoAdapter.findAllContactFournisseurs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveContactFournisseur() {
        ContactFournisseur contactFournisseur = new ContactFournisseur();
        contactFournisseur.setId(1);
        ContactFournisseurEntity entity = new ContactFournisseurEntity();
        entity.setId(1);

        when(contactFournisseurRepository.save(any(ContactFournisseurEntity.class))).thenReturn(entity);

        ContactFournisseur result = contactFournisseurDaoAdapter.saveContactFournisseur(contactFournisseur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateContactFournisseur() {
        ContactFournisseur contactFournisseur = new ContactFournisseur();
        contactFournisseur.setId(1);
        ContactFournisseurEntity entity = new ContactFournisseurEntity();
        entity.setId(1);
        when(contactFournisseurRepository.findById(1)).thenReturn(Optional.of(entity));
        when(contactFournisseurRepository.save(any(ContactFournisseurEntity.class))).thenReturn(entity);

        ContactFournisseur result = contactFournisseurDaoAdapter.updateContactFournisseur(contactFournisseur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteContactFournisseur() {
        ContactFournisseurEntity entity = new ContactFournisseurEntity();
        entity.setId(1);
        when(contactFournisseurRepository.findById(1)).thenReturn(Optional.of(entity));

        contactFournisseurDaoAdapter.deleteContactFournisseur(1);

        verify(contactFournisseurRepository, times(1)).delete(entity);
    }
}
