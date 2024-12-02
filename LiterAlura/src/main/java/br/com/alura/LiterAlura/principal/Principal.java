package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.model.*;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoAPI;
import br.com.alura.LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String endereco = "http://gutendex.com/books/?search=";
    private Scanner leitor = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    @Autowired
    private LivroRepository livroRepositorio;
    @Autowired
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio){
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu(){
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    """;
            System.out.println(menu);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch(opcao){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    opcao = 1;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarLivrosEmDeterminadoIdioma() {
        System.out.println("Insira o idioma: ");
        String idiomaEscolhido = leitor.nextLine();
        Idioma idioma = Idioma.fromString(idiomaEscolhido);
        livros = livroRepositorio.findByIdioma(idioma);
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Insira o ano: ");
        int ano = leitor.nextInt();
        List<String> autoresString = autorRepositorio.listarAutoresVivosEmDeterminadoAno(ano);
        System.out.println(autoresString);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepositorio.findAll();
        autores.forEach(System.out::println);
    }

    private void listarLivrosRegistrados() {
        livros = livroRepositorio.findAll();
        livros.forEach(System.out::println);
    }

    private void buscarLivroPorTitulo() {
        DadosResultado dados = getDadosLivro();
        if(!dados.resultado().isEmpty()){
            Livro livro = new Livro(dados.resultado().get(0));
            livroRepositorio.save(livro);
            System.out.println(livro);
        }
        else {
            System.out.println("Não encontrado.");
        }

    }

    private DadosResultado getDadosLivro() {
        System.out.println("Insira o titulo do livro: ");
        String tituloLivro = leitor.nextLine();
        var json = consumo.obterDados(endereco + tituloLivro.replace(" ", "%20"));
        System.out.println(json);
        return conversor.obterDados(json, DadosResultado.class);
    }
}
