
package br.edu.nassau.apicursos.service;

import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    // TESTE 1: Listar todos os cursos
    @Test
    void deveListarTodosOsCursos() {

        List<Curso> cursos = List.of(
                new Curso(),
                new Curso()
        );

        when(cursoRepository.findAll()).thenReturn(cursos);

        List<Curso> resultado = cursoService.listarTodos();

        assertEquals(2, resultado.size());
        verify(cursoRepository).findAll();
    }

    // TESTE 2: Buscar curso existente
    @Test
    void deveBuscarCursoPorId() {

        Curso curso = new Curso();
        curso.setId(1);
        curso.setNome("Java");
        curso.setCargaHoraria(40);

        when(cursoRepository.findById(1))
                .thenReturn(Optional.of(curso));

        Curso resultado = cursoService.buscarPorId(1);

        assertEquals(1, resultado.getId());
        assertEquals("Java", resultado.getNome());
        assertEquals(40, resultado.getCargaHoraria());
    }

    // TESTE 3: Salvar curso
    @Test
    void deveSalvarCurso() {

        Curso curso = new Curso();
        curso.setNome("Spring Boot");
        curso.setCargaHoraria(60);

        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso resultado = cursoService.salvar(curso);

        assertEquals("Spring Boot", resultado.getNome());
        assertEquals(60, resultado.getCargaHoraria());
        verify(cursoRepository).save(curso);
    }

    // TESTE 4: Atualizar curso existente
    @Test
    void deveAtualizarCurso() {

        Curso curso = new Curso();
        curso.setNome("Java Avançado");
        curso.setCargaHoraria(80);

        when(cursoRepository.existsById(1)).thenReturn(true);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso resultado = cursoService.atualizar(1, curso);

        assertEquals(1, resultado.getId());
        assertEquals("Java Avançado", resultado.getNome());
        assertEquals(80, resultado.getCargaHoraria());
    }

    // TESTE 5: Excluir curso existente
    @Test
    void deveExcluirCurso() {

        when(cursoRepository.existsById(1)).thenReturn(true);

        cursoService.excluir(1);

        verify(cursoRepository).existsById(1);
        verify(cursoRepository).deleteById(1);
    }

    // TESTE 6: Buscar curso inexistente
    @Test
    void deveRetornarErroQuandoCursoNaoExistirNaBusca() {

        when(cursoRepository.findById(99))
                .thenReturn(Optional.empty());

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> cursoService.buscarPorId(99)
        );

        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
    }

    // TESTE 7: Atualizar curso inexistente
    @Test
    void deveRetornarErroQuandoCursoNaoExistirNaAtualizacao() {

        when(cursoRepository.existsById(99)).thenReturn(false);

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> cursoService.atualizar(99, new Curso())
        );

        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
        verify(cursoRepository, never()).save(any());
    }

    // TESTE 8: Excluir curso inexistente
    @Test
    void deveRetornarErroQuandoCursoNaoExistirNaExclusao() {

        when(cursoRepository.existsById(99)).thenReturn(false);

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> cursoService.excluir(99)
        );

        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
        verify(cursoRepository, never()).deleteById(anyInt());
    }
}
