// Clase que representa un literal, que podría estar negado o no, y tiene un predicado y un argumento.
public class Literal {
    private boolean negado;  // Indica si el literal está negado (¬).
    private String predicado;  // El predicado del literal (ej. Americano).
    private String argumento;  // El argumento del literal (ej. West).

    // Constructor que inicializa el literal con su negación, predicado y argumento.
    public Literal(boolean negado, String predicado, String argumento) {
        this.negado = negado;
        this.predicado = predicado;
        this.argumento = argumento;
    }

    // Método para obtener si el literal está negado o no.
    public boolean isNegado() {
        return negado;
    }

    // Método para obtener el predicado del literal.
    public String getPredicado() {
        return predicado;
    }

    // Método para obtener el argumento del literal.
    public String getArgumento() {
        return argumento;
    }

    // Método para devolver el literal en formato legible, incluyendo la negación si corresponde.
    @Override
    public String toString() {
        return (negado ? "¬" : "") + predicado + "(" + argumento + ")";
    }

    // Método que compara dos literales y verifica si son iguales.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literal literal = (Literal) o;
        return negado == literal.negado &&
                predicado.equals(literal.predicado) &&
                argumento.equals(literal.argumento);
    }

    // Método que genera el código hash del literal, utilizado para comparar literales en colecciones.
    @Override
    public int hashCode() {
        return (negado ? 1 : 0) + predicado.hashCode() + argumento.hashCode();
    }

    // Método que devuelve el complemento del literal (por ejemplo, ¬A -> A o A -> ¬A).
    public Literal getComplemento() {
        return new Literal(!negado, predicado, argumento);
    }
}
