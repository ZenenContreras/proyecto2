import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingresar las cláusulas de la base de conocimiento
        System.out.println("Ingresa las cláusulas de la base de conocimiento. Escribe 'fin' para terminar.");
        List<Clausula> baseDeConocimiento = new ArrayList<>();
        int clausulaIndex = 1;
        while (true) {
            System.out.print("Cláusula " + clausulaIndex + ": ");
            String inputClausula = scanner.nextLine();
            if (inputClausula.equalsIgnoreCase("fin")) {
                break;
            }
            Clausula clausula = ClausulaTokenizer.tokenizar(inputClausula, clausulaIndex);
            baseDeConocimiento.add(clausula);
            clausulaIndex++;
        }

        // Ingresar la cláusula a refutar
        System.out.println("\nIngresa la cláusula a refutar: ");
        String clausulaARefutar = scanner.nextLine();
        Clausula clausulaNegada = ClausulaTokenizer.tokenizar("¬" + clausulaARefutar, clausulaIndex);
        baseDeConocimiento.add(clausulaNegada);  // Agregar la cláusula negada a la base de conocimiento
        System.out.println("\nCláusula a refutar (negada): " + clausulaNegada + "\n");

        // Mostrar las cláusulas ingresadas
        System.out.println("Base de conocimiento con la cláusula negada:\n");
        for (Clausula clausula : baseDeConocimiento) {
            System.out.println(clausula + "\n");
        }

        // Aplicar resolución paso a paso
        System.out.println("\nIniciando proceso de resolución paso a paso...\n");
        ResolucionMotor motor = new ResolucionMotor();
        boolean resultado = motor.aplicarResolucion(baseDeConocimiento);

        // Mostrar resultado final
        System.out.println("\nResultado final:\n");
        if (resultado) {
            System.out.println("Contradicción encontrada. La proposición original es verdadera.\n");
        } else {
            System.out.println("No se encontró contradicción. La proposición no pudo ser probada.\n");
        }
    }
}
