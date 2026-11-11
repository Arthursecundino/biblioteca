package aplicacao;

import modelo.Item;
import servico.BibliotecaServico;
import servico.Validador;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final BibliotecaServico servico = new BibliotecaServico();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> adicionarItem();
                case 2 -> listarTodos();
                case 3 -> listarPorCategoria();
                case 4 -> removerItem();
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== BIBLIOTECA PESSOAL ===");
        System.out.println("1 - Adicionar item");
        System.out.println("2 - Listar todos os itens");
        System.out.println("3 - Listar por categoria");
        System.out.println("4 - Remover item");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // Lê a opção sem usar exceções: confere o texto antes de converter
    private static int lerOpcao() {
        String texto = teclado.nextLine().trim();
        if (texto.matches("\\d{1,3}")) {
            return Integer.parseInt(texto);
        }
        return -1;
    }

    private static String ler(String mensagem) {
        System.out.print(mensagem);
        return teclado.nextLine();
    }

    private static void adicionarItem() {
        System.out.println("Tipo: 1 - Livro | 2 - Revista | 3 - Álbum de música");
        System.out.print("Escolha: ");
        int tipo = lerOpcao();

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido!");
            return;
        }

        String titulo = ler("Título: ");
        String autor;
        String extra;
        if (tipo == 1) {
            autor = ler("Autor: ");
            extra = ler("Número de páginas: ");
        } else if (tipo == 2) {
            autor = ler("Editora: ");
            extra = ler("Edição: ");
        } else {
            autor = ler("Artista: ");
            extra = ler("Número de faixas: ");
        }
        String categoria = ler("Categoria (ex.: suspense, romance, rock): ");

        String erro;
        if (tipo == 1) {
            erro = servico.adicionarLivro(titulo, autor, categoria, extra);
        } else if (tipo == 2) {
            erro = servico.adicionarRevista(titulo, autor, categoria, extra);
        } else {
            erro = servico.adicionarAlbum(titulo, autor, categoria, extra);
        }

        if (erro == null) {
            System.out.println("Item adicionado com sucesso!");
        } else {
            System.out.println("Erro: " + erro);
        }
    }

    private static void listarTodos() {
        mostrarLista(servico.listarTodos());
    }

    private static void listarPorCategoria() {
        List<String> categorias = servico.listarCategorias();
        if (categorias.isEmpty()) {
            System.out.println("Nenhum item cadastrado ainda.");
            return;
        }
        System.out.println("Categorias existentes: " + categorias);
        String categoria = ler("Digite a categoria: ");
        if (!Validador.textoValido(categoria)) {
            System.out.println("Erro: a categoria é obrigatória.");
            return;
        }
        mostrarLista(servico.listarPorCategoria(categoria));
    }

    private static void removerItem() {
        if (servico.listarTodos().isEmpty()) {
            System.out.println("Nenhum item para remover.");
            return;
        }
        mostrarLista(servico.listarTodos());
        String id = ler("Digite o ID do item a remover: ");
        String erro = servico.remover(id);
        if (erro == null) {
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Erro: " + erro);
        }
    }

    private static void mostrarLista(List<Item> itens) {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item encontrado.");
            return;
        }
        System.out.println("--- Itens ---");
        for (Item item : itens) {
            System.out.println(item);
        }
    }
}
