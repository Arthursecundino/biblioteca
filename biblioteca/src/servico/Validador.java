package servico;

/**
 * Validações simples, sem usar exceções.
 */
public class Validador {

    public static boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    // Aceita só dígitos (1 a 6), maior que zero. Assim, converter para int é sempre seguro.
    public static boolean inteiroPositivo(String texto) {
        if (texto == null) {
            return false;
        }
        String t = texto.trim();
        return t.matches("\\d{1,6}") && !t.matches("0+");
    }
}
