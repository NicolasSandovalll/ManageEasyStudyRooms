
package dominio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrador extends Usuario {
    private final LogiaHandler logiaHandler;

    public Administrador(String matricula, String contrasena, LogiaHandler logiaHandler) {
        super(matricula, contrasena);
        this.logiaHandler = logiaHandler;
    }

    public void mostrarMenuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1; // Inicialización para evitar error de variable no inicializada
        do {
            try {
                System.out.println("\nMenú del Administrador:");
                System.out.println("1. Mostrar disponibilidad de logias");
                System.out.println("2. Confirmar Reserva (En construcción)");
                System.out.println("3. Salir");
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> logiaHandler.mostrarLogiasDisponibles();
                    case 2 -> System.out.println("Confirmar Reserva (En construcción)");
                    case 3 -> System.out.println("Saliendo del menú del administrador...");
                    default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar la entrada inválida
            }
        } while (opcion != 3);
    }
}