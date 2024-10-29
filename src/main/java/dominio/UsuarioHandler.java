// Archivo: dominio/UsuarioHandler.java
package dominio;

import datos.ArchivoJSONHandler;
import org.json.JSONArray;
import org.json.JSONObject;

public class UsuarioHandler {
    private final ArchivoJSONHandler archivoJSONHandler;
    private final String rutaArchivo;

    public UsuarioHandler(String rutaArchivo) {
        this.archivoJSONHandler = new ArchivoJSONHandler();
        this.rutaArchivo = rutaArchivo;
        if (archivoJSONHandler.verificarOCrearArchivo(rutaArchivo)) {
            System.out.println("Archivo JSON verificado o creado exitosamente.");
        }
    }

    public boolean registrarEstudiante(String matricula, String contrasena) {
        JSONArray usuariosArray = archivoJSONHandler.leerUsuariosDelArchivo(rutaArchivo);

        if (matriculaYaRegistrada(usuariosArray, matricula)) {
            System.out.println("La matrícula ya está registrada.");
            return false;
        }

        agregarUsuario(usuariosArray, matricula, contrasena);
        return archivoJSONHandler.escribirEnArchivo(rutaArchivo, usuariosArray);
    }

    private boolean matriculaYaRegistrada(JSONArray usuariosArray, String matricula) {
        for (int i = 0; i < usuariosArray.length(); i++) {
            JSONObject usuario = usuariosArray.getJSONObject(i);
            if (usuario.getString("matricula").equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    private void agregarUsuario(JSONArray usuariosArray, String matricula, String contrasena) {
        JSONObject nuevoUsuario = new JSONObject();
        nuevoUsuario.put("matricula", matricula);
        nuevoUsuario.put("contrasena", contrasena);
        usuariosArray.put(nuevoUsuario);
    }
}
