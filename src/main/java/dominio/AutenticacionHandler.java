

package dominio;

import datos.ArchivoJSONHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AutenticacionHandler {
    private final ArchivoJSONHandler archivoJSONHandler;
    private final String rutaArchivoUsuarios;
    private final LogiaHandler logiaHandler;

    public AutenticacionHandler(String rutaArchivoUsuarios, String rutaArchivoLogias) {
        this.archivoJSONHandler = new ArchivoJSONHandler();
        this.rutaArchivoUsuarios = rutaArchivoUsuarios;
        this.logiaHandler = new LogiaHandler(rutaArchivoLogias);
        if (archivoJSONHandler.verificarOCrearArchivo(rutaArchivoUsuarios)) {
            System.out.println("Archivo JSON de usuarios verificado o creado exitosamente.");
        }
    }

    public Usuario iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Iniciar Sesión:");
            System.out.print("Ingrese su matrícula: ");
            String matricula = scanner.next();
            System.out.print("Ingrese su contraseña: ");
            String contrasena = scanner.next();

            JSONArray usuariosArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivoUsuarios, "usuarios");
            for (int i = 0; i < usuariosArray.length(); i++) {
                JSONObject usuarioJson = usuariosArray.getJSONObject(i);
                if (usuarioJson.getString("matricula").equals(matricula) &&
                        usuarioJson.getString("contrasena").equals(contrasena)) {
                    String tipoUsuario = usuarioJson.getString("tipo");
                    Usuario usuario;
                    if (tipoUsuario.equalsIgnoreCase("administrador")) {
                        usuario = new Administrador(matricula, contrasena, logiaHandler);
                    } else {
                        usuario = new Estudiante(matricula, contrasena, logiaHandler);
                    }
                    System.out.println("Inicio de sesión exitoso. Bienvenido " + tipoUsuario + "!");
                    return usuario;
                }
            }
            System.out.println("Matrícula o contraseña incorrecta. Por favor, intente de nuevo.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese los datos correctamente.");
            scanner.next(); // Limpiar la entrada inválida
        }
        return null;
    }

    public void registrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Registro de Usuario:");
            System.out.print("Ingrese su matrícula: ");
            String matricula = scanner.next();
            System.out.print("Ingrese su contraseña: ");
            String contrasena = scanner.next();
            System.out.print("Ingrese el tipo de usuario (estudiante/administrador): ");
            String tipo = scanner.next();

            JSONArray usuariosArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivoUsuarios, "usuarios");
            for (int i = 0; i < usuariosArray.length(); i++) {
                JSONObject usuarioJson = usuariosArray.getJSONObject(i);
                if (usuarioJson.getString("matricula").equals(matricula)) {
                    System.out.println("La matrícula ya está registrada.");
                    return;
                }
            }

            JSONObject nuevoUsuario = new JSONObject();
            nuevoUsuario.put("matricula", matricula);
            nuevoUsuario.put("contrasena", contrasena);
            nuevoUsuario.put("tipo", tipo);
            usuariosArray.put(nuevoUsuario);

            if (archivoJSONHandler.escribirEnArchivo(rutaArchivoUsuarios, "usuarios", usuariosArray)) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el usuario.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese los datos correctamente.");
            scanner.next(); // Limpiar la entrada inválida
        }
    }
}
