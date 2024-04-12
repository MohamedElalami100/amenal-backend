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

import com.amenal.amenalbackend.achat.core.domain.Transport;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.TransportEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.TransportRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TransportDaoAdapterTest {

    @Mock
    private TransportRepository transportRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private TransportDaoAdapter transportDaoAdapter;

    @Test
    void testFindTransportById() {
        TransportEntity entity = new TransportEntity();
        entity.setId(1);

        when(transportRepository.findById(1)).thenReturn(Optional.of(entity));

        Transport result = transportDaoAdapter.findTransportById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllTransports() {
        when(transportRepository.findAll()).thenReturn(Collections.emptyList());

        List<Transport> result = transportDaoAdapter.findAllTransports();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveTransport() {
        Transport transport = new Transport();
        transport.setId(1);
        TransportEntity entity = new TransportEntity();
        entity.setId(1);

        when(transportRepository.save(any(TransportEntity.class))).thenReturn(entity);

        Transport result = transportDaoAdapter.saveTransport(transport);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateTransport() {
        Transport transport = new Transport();
        transport.setId(1);
        TransportEntity entity = new TransportEntity();
        entity.setId(1);
        when(transportRepository.findById(1)).thenReturn(Optional.of(entity));
        when(transportRepository.save(any(TransportEntity.class))).thenReturn(entity);

        Transport result = transportDaoAdapter.updateTransport(transport);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteTransport() {
        TransportEntity entity = new TransportEntity();
        entity.setId(1);
        when(transportRepository.findById(1)).thenReturn(Optional.of(entity));

        transportDaoAdapter.deleteTransport(1);

        verify(transportRepository, times(1)).delete(entity);
    }
}
