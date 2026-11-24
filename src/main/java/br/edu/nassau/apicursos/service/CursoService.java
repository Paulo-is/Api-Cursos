
package br.edu.nassau.apicursos.service;

import br.edu.nassau.apicursos.model.Curso;
import br.edu.nassau.apicursos.repository.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Lista todos os cursos
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    // Busca um curso pelo ID
    public Curso buscarPorId(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Curso não encontrado"
                ));
    }

    // Cadastra um novo curso
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Atualiza um curso existente
    public Curso atualizar(Integer id, Curso curso) {
        if (!cursoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        curso.setId(id);
        return cursoRepository.save(curso);
    }

    // Exclui um curso existente
    public void excluir(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        cursoRepository.deleteById(id);
    }
}
