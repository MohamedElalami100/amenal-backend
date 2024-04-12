package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Client;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ClientEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ClientDaoAdapterTest {

    @Mock
    private ClientRepository clientRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ClientDaoAdapter clientDaoAdapter;

    @Test
    void testFindClientById() {
        ClientEntity entity = new ClientEntity();
        entity.setId(1);

        when(clientRepository.findById(1)).thenReturn(Optional.of(entity));

        Client result = clientDaoAdapter.findClientById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllClients() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Client> result = clientDaoAdapter.findAllClients();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveClient() {
        Client client = new Client();
        client.setId(1);
        ClientEntity entity = new ClientEntity();
        entity.setId(1);

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(entity);

        Client result = clientDaoAdapter.saveClient(client);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateClient() {
        Client client = new Client();
        client.setId(1);
        ClientEntity entity = new ClientEntity();
        entity.setId(1);
        when(clientRepository.findById(1)).thenReturn(Optional.of(entity));
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(entity);

        Client result = clientDaoAdapter.updateClient(client);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteClient() {
        ClientEntity entity = new ClientEntity();
        entity.setId(1);
        when(clientRepository.findById(1)).thenReturn(Optional.of(entity));

        clientDaoAdapter.deleteClient(1);

        verify(clientRepository, times(1)).delete(entity);
    }
}
