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

import com.amenal.amenalbackend.achat.core.domain.Commande;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.CommandeEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.CommandeRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CommandeDaoAdapterTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private CommandeDaoAdapter commandeDaoAdapter;

    @Test
    void testFindCommandeById() {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(1);

        when(commandeRepository.findById(1)).thenReturn(Optional.of(entity));

        Commande result = commandeDaoAdapter.findCommandeById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllCommandes() {
        when(commandeRepository.findAll()).thenReturn(Collections.emptyList());

        List<Commande> result = commandeDaoAdapter.findAllCommandes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveCommande() {
        Commande commande = new Commande();
        commande.setId(1);
        CommandeEntity entity = new CommandeEntity();
        entity.setId(1);

        when(commandeRepository.save(any(CommandeEntity.class))).thenReturn(entity);

        Commande result = commandeDaoAdapter.saveCommande(commande);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateCommande() {
        Commande commande = new Commande();
        commande.setId(1);
        CommandeEntity entity = new CommandeEntity();
        entity.setId(1);
        when(commandeRepository.findById(1)).thenReturn(Optional.of(entity));
        when(commandeRepository.save(any(CommandeEntity.class))).thenReturn(entity);

        Commande result = commandeDaoAdapter.updateCommande(commande);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteCommande() {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(1);
        when(commandeRepository.findById(1)).thenReturn(Optional.of(entity));

        commandeDaoAdapter.deleteCommande(1);

        verify(commandeRepository, times(1)).delete(entity);
    }
}
