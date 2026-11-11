package modelo;

/**
 * Classe base: representa qualquer item da biblioteca.
 * Livro, Revista, AlbumMusica (e futuros Filme, Jogo...) herdam dela.
 */
public abstract class Item {

    // Atributos privados: só mudam por métodos da própria classe
    private int id;
    private final String titulo;
    private final String autorArtista;
    private final Categoria categoria;

    protected Item(String titulo, String autorArtista, Categoria categoria) {
        this.titulo = titulo;
        this.autorArtista = autorArtista;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    // O id só pode ser definido uma vez (quando o repositório salva o item)
    public void definirId(int novoId) {
        if (this.id == 0) {
            this.id = novoId;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutorArtista() {
        return autorArtista;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // Cada tipo de item informa o seu próprio tipo e dados extras
    public abstract String getTipo();

    public abstract String getInformacaoAdicional();

    @Override
    public String toString() {
        return "[" + id + "] " + getTipo() + " | " + titulo + " | " + autorArtista
                + " | Categoria: " + categoria + " | " + getInformacaoAdicional();
    }
}
