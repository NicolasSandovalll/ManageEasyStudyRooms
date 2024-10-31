

package dominio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Estudiante extends Usuario {
    private final LogiaHandler logiaHandler;

    public Estudiante(String matricula, String contrasena, LogiaHandler logiaHandler) {
        super(matricula, contrasena);
        this.logiaHandler = logiaHandler;
    }

    public void mostrarMenuEstudiante() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1; // Inicialización para evitar error de variable no inicializada
        do {
            try {
                System.out.println("\nMenú del Estudiante:");
                System.out.println("1. Mostrar disponibilidad de logias");
                System.out.println("2. Reservar Logia (En construcción)");
                System.out.println("3. Mi Reserva (En construcción)");
                System.out.println("4. Salir");
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> logiaHandler.mostrarLogiasDisponibles();
                    case 2 -> System.out.println("Reservar Logia (En construcción)");
                    case 3 -> System.out.println("Mi Reserva (En construcción)");
                    case 4 -> System.out.println("Saliendo del menú del estudiante...");
                    default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar la entrada inválida
            }
        } while (opcion != 4);
    }
}