package datos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchivoJSONHandler {

    public boolean verificarOCrearArchivo(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            if (archivo.exists()) {
                return true; // Ya existe
            }
            if (!archivo.getParentFile().mkdirs() && !archivo.getParentFile().exists()) {
                System.out.println("No se pudo crear el directorio para el archivo.");
                return false;
            }
            return archivo.createNewFile();
        } catch (IOException e) {
            System.out.println("Error al verificar o crear el archivo: " + e.getMessage());
            return false;
        }
    }

    public JSONArray leerJSONArrayDelArchivo(String rutaArchivo, String nombreArray) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            JSONObject jsonRoot = new JSONObject(contenido);
            return jsonRoot.getJSONArray(nombreArray);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
            return new JSONArray();
        }
    }

    public boolean escribirEnArchivo(String rutaArchivo, String nombreArray, JSONArray jsonArray) {
        try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put(nombreArray, jsonArray);
            fileWriter.write(jsonRoot.toString(4));
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo JSON: " + e.getMessage());
            return false;
        }
    }
}