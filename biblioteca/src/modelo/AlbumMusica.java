package modelo;

public class AlbumMusica extends Item {

    private final int numeroFaixas;

    public AlbumMusica(String titulo, String artista, Categoria categoria, int numeroFaixas) {
        super(titulo, artista, categoria);
        this.numeroFaixas = numeroFaixas;
    }

    public int getNumeroFaixas() {
        return numeroFaixas;
    }

    @Override
    public String getTipo() {
        return "Álbum";
    }

    @Override
    public String getInformacaoAdicional() {
        return "Faixas: " + numeroFaixas;
    }
}
