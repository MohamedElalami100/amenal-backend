package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.DetailReceptionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.DetailReception;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailReceptionEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DetailReceptionRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DetailReceptionDaoAdapterTest {

    @Mock
    private DetailReceptionRepository detailReceptionRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailReceptionDaoAdapter detailReceptionDaoAdapter;

    @Test
    void testFindDetailReceptionById() {
        DetailReceptionEntity entity = new DetailReceptionEntity();
        entity.setId(1);

        when(detailReceptionRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailReceptionDto result = detailReceptionDaoAdapter.findDetailReceptionById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailReceptions() {
        when(detailReceptionRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailReceptionDto> result = detailReceptionDaoAdapter.findAllDetailReceptions();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

   

    @Test
    void testUpdateDetailReception() {
        DetailReception detailReception = new DetailReception();
        detailReception.setId(1);
        DetailReceptionEntity entity = new DetailReceptionEntity();
        entity.setId(1);
        when(detailReceptionRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailReceptionRepository.save(any(DetailReceptionEntity.class))).thenReturn(entity);

        DetailReception result = detailReceptionDaoAdapter.updateDetailReception(detailReception);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailReception() {
        DetailReceptionEntity entity = new DetailReceptionEntity();
        entity.setId(1);
        when(detailReceptionRepository.findById(1)).thenReturn(Optional.of(entity));

        detailReceptionDaoAdapter.deleteDetailReception(1);

        verify(detailReceptionRepository, times(1)).delete(entity);
    }
}
