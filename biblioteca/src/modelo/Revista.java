package modelo;

public class Revista extends Item {

    private final int edicao;

    public Revista(String titulo, String editora, Categoria categoria, int edicao) {
        super(titulo, editora, categoria);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String getInformacaoAdicional() {
        return "Edição: " + edicao;
    }
}
