package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Document;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DocumentEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DocumentRepository;
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
public class DocumentDaoAdapterTest {

    @Mock
    private DocumentRepository documentRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DocumentDaoAdapter documentDaoAdapter;

    @Test
    void testFindDocumentById() {
        DocumentEntity entity = new DocumentEntity();
        entity.setUrl("url");

        when(documentRepository.findByUrl("url")).thenReturn(Optional.of(entity));

        Document result = documentDaoAdapter.findDocumentByUrl("url");
        assertNotNull(result);
        assertEquals("url", result.getUrl());
    }

    @Test
    void testFindAllDocuments() {
        when(documentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Document> result = documentDaoAdapter.findAllDocuments();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDocument() {
        Document document = new Document();
        document.setUrl("url");
        DocumentEntity entity = new DocumentEntity();
        entity.setUrl("url");

        when(documentRepository.save(any(DocumentEntity.class))).thenReturn(entity);

        Document result = documentDaoAdapter.saveDocument(document);
        assertNotNull(result);
        assertEquals("url", result.getUrl());
    }

    @Test
    void testUpdateDocument() {
        Document document = new Document();
        document.setUrl("url");
        DocumentEntity entity = new DocumentEntity();
        entity.setUrl("url");
        when(documentRepository.findByUrl("url")).thenReturn(Optional.of(entity));
        when(documentRepository.save(any(DocumentEntity.class))).thenReturn(entity);

        Document result = documentDaoAdapter.updateDocument(document);
        assertNotNull(result);
        assertEquals("url", result.getUrl());
    }

    @Test
    void testDeleteDocument() {
        DocumentEntity entity = new DocumentEntity();
        entity.setUrl("url");
        when(documentRepository.findByUrl("url")).thenReturn(Optional.of(entity));

        documentDaoAdapter.deleteDocument("url");

        verify(documentRepository, times(1)).delete(entity);
    }
}
