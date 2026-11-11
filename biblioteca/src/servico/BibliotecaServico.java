package servico;

import modelo.AlbumMusica;
import modelo.Categoria;
import modelo.Item;
import modelo.Livro;
import modelo.Revista;
import repositorio.ItemRepositorio;

import java.util.ArrayList;
import java.util.List;

/*
 * Regras do sistema. Os métodos de cadastro/remoção retornam:
 *  - texto -> mensagem explicando o erro
 */
public class BibliotecaServico {

    private final ItemRepositorio repositorio = new ItemRepositorio();

    public String adicionarLivro(String titulo, String autor, String categoria, String paginas) {
        String erro = validarBasico(titulo, autor, categoria);
        if (erro != null) {
            return erro;
        }
        if (!Validador.inteiroPositivo(paginas)) {
            return "O número de páginas deve ser um número inteiro maior que zero.";
        }
        Livro livro = new Livro(titulo.trim(), autor.trim(), new Categoria(categoria),
                Integer.parseInt(paginas.trim()));
        repositorio.adicionar(livro);
        return null;
    }

    public String adicionarRevista(String titulo, String editora, String categoria, String edicao) {
        String erro = validarBasico(titulo, editora, categoria);
        if (erro != null) {
            return erro;
        }
        if (!Validador.inteiroPositivo(edicao)) {
            return "A edição deve ser um número inteiro maior que zero.";
        }
        Revista revista = new Revista(titulo.trim(), editora.trim(), new Categoria(categoria),
                Integer.parseInt(edicao.trim()));
        repositorio.adicionar(revista);
        return null;
    }

    public String adicionarAlbum(String titulo, String artista, String categoria, String faixas) {
        String erro = validarBasico(titulo, artista, categoria);
        if (erro != null) {
            return erro;
        }
        if (!Validador.inteiroPositivo(faixas)) {
            return "O número de faixas deve ser um número inteiro maior que zero.";
        }
        AlbumMusica album = new AlbumMusica(titulo.trim(), artista.trim(), new Categoria(categoria),
                Integer.parseInt(faixas.trim()));
        repositorio.adicionar(album);
        return null;
    }

    public List<Item> listarTodos() {
        return repositorio.listarTodos();
    }

    public List<Item> listarPorCategoria(String categoria) {
        if (!Validador.textoValido(categoria)) {
            return new ArrayList<>();
        }
        return repositorio.listarPorCategoria(categoria);
    }

    // Lista os nomes de categorias já usadas (sem repetir)
    public List<String> listarCategorias() {
        List<String> nomes = new ArrayList<>();
        for (Item item : repositorio.listarTodos()) {
            String nome = item.getCategoria().getNome();
            boolean jaExiste = false;
            for (String n : nomes) {
                if (n.equalsIgnoreCase(nome)) {
                    jaExiste = true;
                }
            }
            if (!jaExiste) {
                nomes.add(nome);
            }
        }
        return nomes;
    }
//Remover
    public String remover(String idTexto) {
        if (!Validador.inteiroPositivo(idTexto)) {
            return "O ID deve ser um número inteiro maior que zero.";
        }
        boolean removeu = repositorio.remover(Integer.parseInt(idTexto.trim()));
        if (!removeu) {
            return "Nenhum item encontrado com esse ID.";
        }
        return null;
    }

    // Validação dos campos comuns a todos os itens
    private String validarBasico(String titulo, String autorArtista, String categoria) {
        if (!Validador.textoValido(titulo)) {
            return "O título é obrigatório.";
        }
        if (!Validador.textoValido(autorArtista)) {
            return "O autor/artista é obrigatório.";
        }
        if (!Validador.textoValido(categoria)) {
            return "A categoria é obrigatória.";
        }
        return null;
    }
}
