package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoMorte;
    @ManyToOne
    private Livro livro;

    public Autor(){}

    public Autor(Autor autorDados){};

    public Autor(String nome, int anoNascimento, int anoMorte, Livro livro) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoMorte = anoMorte;
        this.livro = livro;
    }

    public Autor(DadosAutor dadosAutor, Livro dadosLivro) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoMorte = dadosAutor.anoMorte();
        this.livro = dadosLivro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(int anoMorte) {
        this.anoMorte = anoMorte;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return  "\n************************" +
                "\nNome: " + nome +
                "\nAno de Nascimento: " + anoNascimento +
                "\nAno de Falecimento: " + anoMorte +
                "\n***********************";
    }
}
