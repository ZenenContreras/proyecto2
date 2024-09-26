import java.util.ArrayList;
import java.util.List;

public class CNFConverter {

    // Método para interpretar el enunciado en lenguaje natural y convertirlo a lógica proposicional
    public static String interpretarFrase(String frase) {
        // Convertir el enunciado en lógica de predicados
        frase = frase.toLowerCase();
        frase = frase.replace("es un crimen que", "Criminal(x) ∧");
        frase = frase.replace("un americano", "Americano(x)");
        frase = frase.replace("venda armas", "Vende(x, y, z)");
        frase = frase.replace("a países hostiles", "Hostil(z)");
        frase = frase.replace("todos los misiles de nono fueron vendidos por el coronel west", "Misil(y) ∧ Tiene(Nono, y) ⇒ Vende(West, y, Nono)");
        frase = frase.replace("nono es un país hostil", "Hostil(Nono)");
        frase = frase.replace("west es americano", "Americano(West)");
        frase = frase.replace("nono es enemigo de américa", "Enemigo(Nono, América) ⇒ Hostil(Nono)");

        // Volver a separar las partes de la proposición por conjunciones
        frase = frase.replace(".", " ∧ ");

        return frase;
    }

    // Método para eliminar implicaciones de una proposición
    public static String eliminarImplicaciones(String proposicion) {
        return proposicion.replace("⇒", "∨ ¬").trim();
    }

    // Método para aplicar las leyes de De Morgan
    public static String aplicarDeMorgan(String proposicion) {
        String resultado = proposicion.replaceAll("¬\\((\\w+) ∧ (\\w+)\\)", "¬$1 ∨ ¬$2");
        resultado = resultado.replaceAll("¬\\((\\w+) ∨ (\\w+)\\)", "¬$1 ∧ ¬$2");
        return resultado;
    }

    // Método que distribuye disyunciones sobre conjunciones
    public static List<String> distribuirDisyunciones(String proposicion) {
        return List.of(proposicion.split(" ∧ "));
    }

    // Método principal para convertir una proposición en CNF
    public static List<Clausula> convertirACNF(String proposicion) {
        // Paso 1: Interpretar la frase en lógica proposicional
        System.out.println("Paso 1: Interpretando frase en lógica proposicional...");
        String logicaProposicional = interpretarFrase(proposicion);
        System.out.println("Lógica proposicional: " + logicaProposicional);

        // Paso 2: Eliminar implicaciones
        System.out.println("Paso 2: Eliminando implicaciones...");
        String sinImplicaciones = eliminarImplicaciones(logicaProposicional);
        System.out.println("Proposición sin implicaciones: " + sinImplicaciones);

        // Paso 3: Aplicar leyes de De Morgan
        System.out.println("Paso 3: Aplicando leyes de De Morgan...");
        String sinNegaciones = aplicarDeMorgan(sinImplicaciones);
        System.out.println("Proposición sin negaciones: " + sinNegaciones);

        // Paso 4: Distribuir disyunciones sobre conjunciones
        System.out.println("Paso 4: Distribuyendo disyunciones...");
        List<String> clausulasString = distribuirDisyunciones(sinNegaciones);
        System.out.println("Cláusulas separadas: " + clausulasString);

        // Convertir cada cláusula de texto en objetos de tipo Clausula con numeración
        List<Clausula> clausulas = new ArrayList<>();
        int indice = 1;
        for (String clausulaString : clausulasString) {
            Clausula clausula = ClausulaTokenizer.tokenizar(clausulaString, indice);  // Numerar las cláusulas
            clausulas.add(clausula);
            indice++;
        }

        return clausulas;
    }
}
