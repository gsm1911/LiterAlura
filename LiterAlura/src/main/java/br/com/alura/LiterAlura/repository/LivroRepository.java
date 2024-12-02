package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Idioma;
import br.com.alura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(Idioma idioma);
}
