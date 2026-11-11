package modelo;

public class Categoria {

    private final String nome;

    public Categoria(String nome) {
        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }

    // Duas categorias são iguais se tiverem o mesmo nome (ignorando maiúsculas/minúsculas)
    public boolean temMesmoNome(String outroNome) {
        return nome.equalsIgnoreCase(outroNome.trim());
    }

    @Override
    public String toString() {
        return nome;
    }
}
