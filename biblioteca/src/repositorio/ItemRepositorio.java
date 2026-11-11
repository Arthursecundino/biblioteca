package repositorio;

import modelo.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda os itens em memória (uma lista simula o banco de dados).
 */
public class ItemRepositorio {

    private final List<Item> itens = new ArrayList<>();
    private int proximoId = 1;

    public void adicionar(Item item) {
        item.definirId(proximoId);
        proximoId++;
        itens.add(item);
    }

    // Devolve uma cópia da lista para ninguém alterar a original por fora
    public List<Item> listarTodos() {
        return new ArrayList<>(itens);
    }

    public List<Item> listarPorCategoria(String nomeCategoria) {
        List<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getCategoria().temMesmoNome(nomeCategoria)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    // Retorna true se encontrou e removeu, false se o id não existe
    public boolean remover(int id) {
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getId() == id) {
                itens.remove(i);
                return true;
            }
        }
        return false;
    }
}
