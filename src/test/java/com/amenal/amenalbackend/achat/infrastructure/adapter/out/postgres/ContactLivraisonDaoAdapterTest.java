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

import com.amenal.amenalbackend.achat.core.domain.ContactLivraison;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ContactLivraisonEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ContactLivraisonRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ContactLivraisonDaoAdapterTest {

    @Mock
    private ContactLivraisonRepository contactLivraisonRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ContactLivraisonDaoAdapter contactLivraisonDaoAdapter;

    @Test
    void testFindContactLivraisonById() {
        ContactLivraisonEntity entity = new ContactLivraisonEntity();
        entity.setId(1);

        when(contactLivraisonRepository.findById(1)).thenReturn(Optional.of(entity));

        ContactLivraison result = contactLivraisonDaoAdapter.findContactLivraisonById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllContactLivraisons() {
        when(contactLivraisonRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContactLivraison> result = contactLivraisonDaoAdapter.findAllContactLivraisons();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveContactLivraison() {
        ContactLivraison contactLivraison = new ContactLivraison();
        contactLivraison.setId(1);
        ContactLivraisonEntity entity = new ContactLivraisonEntity();
        entity.setId(1);

        when(contactLivraisonRepository.save(any(ContactLivraisonEntity.class))).thenReturn(entity);

        ContactLivraison result = contactLivraisonDaoAdapter.saveContactLivraison(contactLivraison);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateContactLivraison() {
        ContactLivraison contactLivraison = new ContactLivraison();
        contactLivraison.setId(1);
        ContactLivraisonEntity entity = new ContactLivraisonEntity();
        entity.setId(1);
        when(contactLivraisonRepository.findById(1)).thenReturn(Optional.of(entity));
        when(contactLivraisonRepository.save(any(ContactLivraisonEntity.class))).thenReturn(entity);

        ContactLivraison result = contactLivraisonDaoAdapter.updateContactLivraison(contactLivraison);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteContactLivraison() {
        ContactLivraisonEntity entity = new ContactLivraisonEntity();
        entity.setId(1);
        when(contactLivraisonRepository.findById(1)).thenReturn(Optional.of(entity));

        contactLivraisonDaoAdapter.deleteContactLivraison(1);

        verify(contactLivraisonRepository, times(1)).delete(entity);
    }
}
