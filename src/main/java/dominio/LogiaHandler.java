package dominio;

import datos.ArchivoJSONHandler;
import org.json.JSONArray;
import org.json.JSONObject;

public class LogiaHandler {
    private final ArchivoJSONHandler archivoJSONHandler;
    private final String rutaArchivoLogias;

    public LogiaHandler(String rutaArchivoLogias) {
        this.archivoJSONHandler = new ArchivoJSONHandler();
        this.rutaArchivoLogias = rutaArchivoLogias;
        if (archivoJSONHandler.verificarOCrearArchivo(rutaArchivoLogias)) {
            System.out.println("Archivo JSON de logias verificado o creado exitosamente.");
        }
    }

    public void mostrarLogiasDisponibles() {
        JSONArray logiasArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivoLogias, "logias");
        for (int i = 0; i < logiasArray.length(); i++) {
            JSONObject logia = logiasArray.getJSONObject(i);
            if (logia.getString("estado").equalsIgnoreCase("disponible")) {
                System.out.println("Logia ID: " + logia.getInt("idLogia") + ", Capacidad: " + logia.getInt("capacidad") + ", Ubicación: " + logia.getString("ubicacion"));
            }
        }
    }

    public Logia buscarLogiaPorId(int idLogia) {
        JSONArray logiasArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivoLogias, "logias");
        for (int i = 0; i < logiasArray.length(); i++) {
            JSONObject logiaJson = logiasArray.getJSONObject(i);
            if (logiaJson.getInt("idLogia") == idLogia) {
                String estado = logiaJson.getString("estado");
                int capacidad = logiaJson.getInt("capacidad");
                String ubicacion = logiaJson.getString("ubicacion");
                return new Logia(idLogia, capacidad, ubicacion, estado);
            }
        }
        return null;
    }
}
