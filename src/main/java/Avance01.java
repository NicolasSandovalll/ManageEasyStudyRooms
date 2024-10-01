import java.util.*;

public class Avance01 {

    private static final Scanner scanner = new Scanner(System.in);
    static final List<String> usuarios = new ArrayList<>();
    static String matriculaActual = null;

    public static void main(String[] args) {
        EjecutarSistema();
    }

    public static void EjecutarSistema() {
        cargarUsuarios();

        int opcion;
        do {
            mostrarMenuInicial();
            opcion = solicitarOpcion("Seleccione una opción: ", 1, 3);
            procesarOpcionInicial(opcion);
        } while (opcion != 3);
    }

    // Modularización de usuarios
    public static void cargarUsuarios() {
        usuarios.add("21212121k21");
        usuarios.add("21212121211");
        usuarios.add("21212121212");
        usuarios.add("21212121213");
        usuarios.add("21212121214");
        usuarios.add("21212121215");
        usuarios.add("21212121217");
        // Agrega más usuarios si es necesario...
    }

    public static void mostrarMenuInicial() {
        System.out.println("\nMenú Inicial\n");
        System.out.println("1.- Iniciar Sesión");
        System.out.println("2.- Registrarse");
        System.out.println("3.- Salir");
    }

    public static boolean iniciarSesion() {
        System.out.println("\nIniciar Sesión");
        while (true) {
            System.out.print("Ingrese su matrícula (o ingrese 0 para volver al menú anterior): ");
            matriculaActual = limpiarMatricula(scanner.nextLine());

            if (matriculaActual.equals("0")) {
                return false;
            }

            if (usuarios.contains(matriculaActual)) {
                System.out.println("Bienvenido!");
                return true;
            } else {
                System.out.println("Usuario no encontrado. Por favor, intente nuevamente.");
            }
        }
    }

    public static boolean registrarUsuario() {
        System.out.println("\nRegistro de Usuario");
        String matricula;

        while (true) {
            System.out.print("Ingrese su matrícula (o ingrese 0 para volver al menú anterior): ");
            matricula = limpiarMatricula(scanner.nextLine());

            if (matricula.equals("0")) {
                System.out.println("Volviendo al menú principal...");
                return false;
            } else if (usuarios.contains(matricula)) {
                System.out.println("El usuario ya está registrado. Por favor, inicie sesión.");
                return false;
            } else if (!esMatriculaValida(matricula)) {
                System.out.println("Matrícula inválida. Intente nuevamente.");
            } else {
                break;
            }
        }

        usuarios.add(matricula);
        matriculaActual = matricula;
        System.out.println("Usuario registrado exitosamente.");
        return true;
    }


    public static void procesarOpcionInicial(int opcion) {
        switch (opcion) {
            case 1:
                if (iniciarSesion()) {
                    System.out.println("Aquí se llama al menú principal");
                }
                break;
            case 2:
                if (registrarUsuario()) {
                    System.out.println("Usuario registrado exitosamente, inicie sesión.");
                }
                break;
            case 3:
                System.out.println("Saliendo del programa...");
                break;
            default:
                mostrarMensajeOpcionInvalida();
        }
    }

    public static int solicitarOpcion(String mensaje, int min, int max) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = scanner.nextInt();
                scanner.nextLine();
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.nextLine();
            }
        }
    }

    public static String limpiarMatricula(String matricula) {
        return matricula.replaceAll("[^\\dk]", "");
    }

    public static boolean esMatriculaValida(String matricula) {
        return matricula.matches("\\d{8}[\\dk]\\d{2}");
    }

    private static void mostrarMensajeOpcionInvalida() {
        System.out.println("Opción inválida. Por favor, inténtelo de nuevo.");
    }
}