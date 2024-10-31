package dominio;

import datos.ArchivoJSONHandler;
import org.json.JSONArray;
import org.json.JSONObject;

public class UsuarioHandler {
    private final ArchivoJSONHandler archivoJSONHandler;
    private final String rutaArchivo;
    private final LogiaHandler logiaHandler;

    public UsuarioHandler(String rutaArchivo, String rutaArchivoLogias) {
        this.archivoJSONHandler = new ArchivoJSONHandler();
        this.rutaArchivo = rutaArchivo;
        this.logiaHandler = new LogiaHandler(rutaArchivoLogias);
    }

    public Usuario iniciarSesion(String matricula, String contrasena) {
        JSONArray usuariosArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivo, "usuarios");
        for (int i = 0; i < usuariosArray.length(); i++) {
            JSONObject usuarioJSON = usuariosArray.getJSONObject(i);
            if (usuarioJSON.getString("matricula").equals(matricula) &&
                    usuarioJSON.getString("contrasena").equals(contrasena)) {

                String tipo = usuarioJSON.optString("tipo", "estudiante");
                if (tipo.equals("administrador")) {
                    return new Administrador(matricula, contrasena, logiaHandler);
                } else {
                    return new Estudiante(matricula, contrasena, logiaHandler);
                }
            }
        }
        System.out.println("Credenciales incorrectas.");
        return null;
    }
}
