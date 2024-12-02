package br.com.alura.LiterAlura.model;

public enum Idioma {
    PT("pt"),
    ES("es"),
    EN("en"),
    FR("fr"),
    RU("ru");
    private String idiomaGutendex;
    private
    Idioma(String idiomaGutendex){
        this.idiomaGutendex = idiomaGutendex;
    }

    public static Idioma fromString(String text) {
        for (Idioma categoria : Idioma.values()) {
            if (categoria.idiomaGutendex.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
