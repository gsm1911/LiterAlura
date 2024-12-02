package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a.nome FROM Autor a WHERE :ano BETWEEN a.anoNascimento AND a.anoMorte")
    List<String> listarAutoresVivosEmDeterminadoAno(int ano);

}
