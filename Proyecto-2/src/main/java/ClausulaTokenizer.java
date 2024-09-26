import java.util.ArrayList;
import java.util.List;

public class ClausulaTokenizer {

    // Método estático que convierte una cadena de texto en una cláusula con literales y un subíndice
    public static Clausula tokenizar(String clausula, int indice) {
        String[] literales = clausula.split(" ∨ ");  // Separar los literales por el operador OR
        List<Literal> listaLiterales = new ArrayList<>();

        // Procesar cada literal de la cláusula.
        for (String literal : literales) {
            literal = literal.trim();
            boolean negado = literal.startsWith("¬");  // Verificar si el literal está negado.
            if (negado) {
                literal = literal.substring(1).trim();  // Eliminar la negación para el análisis posterior.
            }
            // Obtener el predicado (parte antes del paréntesis) y el argumento (parte dentro del paréntesis).
            String predicado = literal.substring(0, literal.indexOf('('));
            String argumento = literal.substring(literal.indexOf('(') + 1, literal.indexOf(')'));

            // Agregar el literal a la lista de literales.
            listaLiterales.add(new Literal(negado, predicado, argumento));
        }

        // Devolver la cláusula tokenizada con subíndice
        return new Clausula(listaLiterales, indice);
    }
}
