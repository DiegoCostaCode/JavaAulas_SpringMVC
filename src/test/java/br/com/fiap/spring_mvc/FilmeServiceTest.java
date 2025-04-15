package br.com.fiap.spring_mvc;

import br.com.fiap.spring_mvc.model.Categoria;
import br.com.fiap.spring_mvc.model.Filme;
import br.com.fiap.spring_mvc.repository.FilmeRepository;
import br.com.fiap.spring_mvc.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    private Filme filmeExistente;

    @BeforeEach
    void setUp() {
        filmeExistente = new Filme();
        filmeExistente.setId(1L);
        filmeExistente.setTitulo("Filme Teste");
        filmeExistente.setDiretor("Diretor Teste");
        filmeExistente.setCategoria(Categoria.ACAO);
        filmeExistente.setStreaming("Netflix");
    }

    @Test
    @DisplayName("Deve retornar um filme existente")
    void testeFindFilmeById_retornaFilme() {
        when(filmeRepository.findById(anyLong())).thenReturn(Optional.of(filmeExistente));

        Optional<Filme> filmeAchado = filmeRepository.findById(1L);

        assertTrue(filmeAchado.isPresent());
        assertEquals(filmeExistente.getId(), filmeAchado.get().getId());
        assertEquals(filmeExistente.getTitulo(), filmeAchado.get().getTitulo());
        assertEquals(filmeExistente.getDiretor(), filmeAchado.get().getDiretor());
        assertEquals(filmeExistente.getCategoria(), filmeAchado.get().getCategoria());
        assertEquals(filmeExistente.getStreaming(), filmeAchado.get().getStreaming());

        verify(filmeRepository, times(1)).findById(1L);
    }
}

