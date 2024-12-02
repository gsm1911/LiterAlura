package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Integer downloads;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;

    public Livro(String titulo, List<String> idioma, int downloads, List<Autor> autor) {
        this.titulo = titulo;
        this.idioma = idioma.get(0);
        this.downloads = downloads;
        this.autor = new ArrayList<>();
        for (Autor autorDados : autor){
            Autor autors = new Autor(autorDados.getNome(), autorDados.getAnoNascimento(), autorDados.getAnoMorte(), this);
            this.autor.add(autors);
        }
    }

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        List<DadosAutor> dadosAutor = Collections.singletonList(dados.autor().get(0));
        this.autor = dadosAutor.stream().map(autor -> new Autor(autor, this)).toList();
        this.idioma = dados.idioma().get(0);
        this.downloads = dados.downloads();
    }

    public Livro(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "\n********************" +
                "\nTitulo: " + titulo +
                "\nAutor: " + autor.get(0).getNome() +
                "\nIdioma: " + idioma +
                "\nDownloads: " + downloads +
                "\n********************\n";
    }
}
