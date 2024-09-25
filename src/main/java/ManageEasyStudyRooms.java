import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageEasyStudyRooms {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iniciarPrograma();
        scanner.close(); // buena práctica cerrar el escaner al final del programa para liberar recursos.
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("\n Menú Principal \n");
        System.out.println("1.- Reservar logia ");
        System.out.println("2.- Consultar disponibilidad de las logias");
        System.out.println("3.- Cancelar Reserva de logias");
        System.out.println("4.- Salir\n");
    }

    public static void iniciarPrograma() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = solicitarOpcion("Ingrese su opción: ", 1 , 4);
            procesarOpcion(opcion);
        } while ( opcion != 4 );
    }

    public static int solicitarOpcion(String mensaje, int minimaOpcion, int maximaOpcion) {
        while(true) {
            try {
                System.out.print(mensaje);
                int opcion = scanner.nextInt();
                if ( opcion >= minimaOpcion && opcion <= maximaOpcion) {
                    return opcion;
                } else {
                    System.out.println("Números inválidos, por favor seleccione una opcion entre " + minimaOpcion + " y " + maximaOpcion + ".");
                }
            } catch ( InputMismatchException e ) {
                System.out.println(" Caracteres inválidos, por favor seleccione una opcion entre " + minimaOpcion + " y " + maximaOpcion + ".");
            }
        }
    }

    public static void procesarOpcion(int opcion) {
        switch(opcion) {
            case 1:
                reservarLogia();
                break;
            case 2:
                mostrarDisponibilidadLogias();
                break;
            case 3:
                cancelarReservaLogia();
                break;
            case 4:
                System.out.println("Saliendo del programa....");
                break;
            default:
                System.out.println("inválida opción"); // esto jamás se ejecutará ya que el metodo solicitarOpcion() asegura que se entre 1,2,3 o 4.
        }
    }

    public static void reservarLogia() {
        System.out.println("Aquí iría el desarrollo del método.");
    }

    public static void mostrarDisponibilidadLogias() {
        System.out.println("Aquí iría el desarrollo del método.");
    }

    public static void cancelarReservaLogia() {
        System.out.println("Aquí iría el desarrollo del método.");
    }
}
