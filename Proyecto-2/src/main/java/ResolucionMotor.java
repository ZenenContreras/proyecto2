import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResolucionMotor {

    private int clausulaIndex = 9;  // Iniciar el índice de nuevas cláusulas

    // Método para resolver dos cláusulas eliminando literales complementarios.
    public Clausula resolver(Clausula c1, Clausula c2) {
        Set<Literal> nuevaClausula = new HashSet<>(c1.getLiterales());
        boolean complementFound = false;  // Indicador de si se encuentra un complemento.

        System.out.println("Resolviendo: " + c1 + " y " + c2);

        // Comparar los literales de c2 con los de c1 y eliminar complementos.
        for (Literal literal : c2.getLiterales()) {
            if (nuevaClausula.contains(literal.getComplemento())) {
                nuevaClausula.remove(literal.getComplemento());
                complementFound = true;  // Complemento encontrado.
            } else {
                nuevaClausula.add(literal);
            }
        }

        // Si no se encontró un complemento, no se puede resolver.
        if (!complementFound) {
            System.out.println("No se encontraron complementos. No se puede resolver.");
            return null;
        }

        // Devolver la nueva cláusula resultante con un nuevo subíndice
        Clausula resolucion = new Clausula(new ArrayList<>(nuevaClausula), clausulaIndex++);
        System.out.println("Resultado de la resolución: " + resolucion);
        return resolucion;
    }

    // Método para verificar si una cláusula está vacía (lo que indica una contradicción).
    public boolean esClausulaVacia(Clausula clausula) {
        return clausula.getLiterales().isEmpty();
    }

    // Método que aplica el algoritmo de resolución sobre la base de conocimiento.
    public boolean aplicarResolucion(List<Clausula> baseDeConocimiento) {
        boolean nuevaClausulaAñadida = true;

        // Continuar resolviendo mientras se añadan nuevas cláusulas.
        while (nuevaClausulaAñadida) {
            nuevaClausulaAñadida = false;

            // Iterar sobre todas las combinaciones posibles de cláusulas.
            for (int i = 0; i < baseDeConocimiento.size(); i++) {
                for (int j = i + 1; j < baseDeConocimiento.size(); j++) {
                    // Intentar resolver las cláusulas.
                    Clausula resolucion = resolver(baseDeConocimiento.get(i), baseDeConocimiento.get(j));

                    // Si no se puede resolver, pasar a la siguiente combinación.
                    if (resolucion == null) {
                        continue;
                    }

                    // Si se encuentra una cláusula vacía, significa que hay una contradicción.
                    if (esClausulaVacia(resolucion)) {
                        System.out.println("Contradicción encontrada (cláusula vacía).");
                        return true;
                    }

                    // Si se genera una nueva cláusula, agregarla a la base de conocimiento.
                    if (!baseDeConocimiento.contains(resolucion)) {
                        baseDeConocimiento.add(resolucion);
                        nuevaClausulaAñadida = true;
                    }
                }
            }
        }

        // Si no se encuentra contradicción, la proposición no puede ser probada.
        return false;
    }
}
