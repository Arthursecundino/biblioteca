package modelo;

public class Livro extends Item {

    private final int paginas;

    public Livro(String titulo, String autor, Categoria categoria, int paginas) {
        super(titulo, autor, categoria);
        this.paginas = paginas;
    }

    public int getPaginas() {
        return paginas;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public String getInformacaoAdicional() {
        return "Páginas: " + paginas;
    }
}
