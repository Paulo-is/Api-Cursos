package br.edu.nassau.apicursos.controller;

import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Listar todos os cursos
    @GetMapping
    public List<Curso> listarTodos() {
        return cursoService.listarTodos();
    }

    // Buscar curso por ID
    @GetMapping("/{id}")
    public Curso buscarPorId(@PathVariable Integer id) {
        return cursoService.buscarPorId(id);
    }

    // Cadastrar curso
    @PostMapping
    public Curso salvar(@RequestBody Curso curso) {
        return cursoService.salvar(curso);
    }

    // Atualizar curso
    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Integer id, @RequestBody Curso curso) {
        return cursoService.atualizar(id, curso);
    }

    // Excluir curso
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        cursoService.excluir(id);
    }
}