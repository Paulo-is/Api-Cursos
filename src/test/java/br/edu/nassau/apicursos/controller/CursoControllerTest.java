
package br.edu.nassau.apicursos.controller;

import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.service.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CursoService cursoService;

    // TESTE 1: Listar cursos
    @Test
    void deveListarCursos() throws Exception {

        Curso curso = new Curso();
        curso.setId(1);
        curso.setNome("Java");
        curso.setCargaHoraria(40);
        curso.setDescricao("Curso de Java");
        curso.setModalidade("Online");

        when(cursoService.listarTodos()).thenReturn(List.of(curso));

        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Java"))
                .andExpect(jsonPath("$[0].cargaHoraria").value(40));
    }

    // TESTE 2: Buscar curso por ID
    @Test
    void deveBuscarCursoPorId() throws Exception {

        Curso curso = new Curso();
        curso.setId(1);
        curso.setNome("Java");
        curso.setCargaHoraria(40);
        curso.setDescricao("Curso de Java");
        curso.setModalidade("Online");

        when(cursoService.buscarPorId(1)).thenReturn(curso);

        mockMvc.perform(get("/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Java"))
                .andExpect(jsonPath("$.modalidade").value("Online"));
    }

    // TESTE 3: Cadastrar curso
    @Test
    void deveCadastrarCurso() throws Exception {

        Curso curso = new Curso();
        curso.setId(1);
        curso.setNome("Spring Boot");
        curso.setCargaHoraria(60);
        curso.setDescricao("Curso de desenvolvimento backend");
        curso.setModalidade("Online");

        when(cursoService.salvar(org.mockito.ArgumentMatchers.any(Curso.class)))
                .thenReturn(curso);

        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Spring Boot",
                                    "cargaHoraria": 60,
                                    "descricao": "Curso de desenvolvimento backend",
                                    "modalidade": "Online"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Spring Boot"))
                .andExpect(jsonPath("$.modalidade").value("Online"));
    }

    // TESTE 4: Buscar curso inexistente
    @Test
    void deveRetornar404QuandoCursoNaoExistir() throws Exception {

        when(cursoService.buscarPorId(99))
                .thenThrow(new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Curso não encontrado"
                ));

        mockMvc.perform(get("/cursos/99"))
                .andExpect(status().isNotFound());
    }
}
