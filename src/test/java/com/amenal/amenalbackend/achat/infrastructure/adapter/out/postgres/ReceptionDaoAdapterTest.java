package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.ReceptionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.Reception;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ReceptionEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ReceptionRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ReceptionDaoAdapterTest {

    @Mock
    private ReceptionRepository receptionRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ReceptionDaoAdapter receptionDaoAdapter;

    @Test
    void testFindReceptionById() {
        ReceptionEntity entity = new ReceptionEntity();
        entity.setId(1);

        when(receptionRepository.findById(1)).thenReturn(Optional.of(entity));

        ReceptionDto result = receptionDaoAdapter.findReceptionById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllReceptions() {
        when(receptionRepository.findAll()).thenReturn(Collections.emptyList());

        List<ReceptionDto> result = receptionDaoAdapter.findAllReceptions();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveReception() {
        Reception reception = new Reception();
        reception.setId(1);
        ReceptionEntity entity = new ReceptionEntity();
        entity.setId(1);

        when(receptionRepository.save(any(ReceptionEntity.class))).thenReturn(entity);

        Reception result = receptionDaoAdapter.saveReception(reception);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateReception() {
        Reception reception = new Reception();
        reception.setId(1);
        ReceptionEntity entity = new ReceptionEntity();
        entity.setId(1);
        when(receptionRepository.findById(1)).thenReturn(Optional.of(entity));
        when(receptionRepository.save(any(ReceptionEntity.class))).thenReturn(entity);

        Reception result = receptionDaoAdapter.updateReception(reception);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteReception() {
        ReceptionEntity entity = new ReceptionEntity();
        entity.setId(1);
        when(receptionRepository.findById(1)).thenReturn(Optional.of(entity));

        receptionDaoAdapter.deleteReception(1);

        verify(receptionRepository, times(1)).delete(entity);
    }
}
