package dominio;

import datos.ArchivoJSONHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Scanner;

public class ReservaHandler {
    private final ArchivoJSONHandler archivoJSONHandler;
    private final String rutaArchivo;
    private final LogiaHandler logiaHandler;
    private final Scanner scanner = new Scanner(System.in);

    public ReservaHandler(String rutaArchivo, LogiaHandler logiaHandler) {
        this.archivoJSONHandler = new ArchivoJSONHandler();
        this.rutaArchivo = rutaArchivo;
        this.logiaHandler = logiaHandler;
        archivoJSONHandler.verificarOCrearArchivo(rutaArchivo);
    }

    public boolean realizarReserva(String idReserva, String idLogia, String matricula, String horaInicio, String horaFin, List<String> companeros) {
        JSONArray reservasArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivo, "reservas");

        if (reservaYaExiste(reservasArray, idReserva)) {
            System.out.println("La reserva ya existe.");
            return false;
        }

        Logia logiaSeleccionada = logiaHandler.buscarLogiaPorId(Integer.parseInt(idLogia));
        if (logiaSeleccionada == null || !logiaSeleccionada.getEstado().equals("disponible")) {
            System.out.println("La logia no está disponible.");
            return false;
        }

        Reserva nuevaReserva = new Reserva(idReserva, idLogia, matricula, horaInicio, horaFin);
        nuevaReserva.setEstado("pendiente");
        if (companeros != null) {
            for (String companero : companeros) {
                nuevaReserva.agregarCompanero(companero);
            }
        }

        reservasArray.put(new JSONObject(nuevaReserva));
        return archivoJSONHandler.escribirEnArchivo(rutaArchivo, "reservas", reservasArray);
    }

    public void verMiReserva(String matriculaEstudiante) {
        JSONArray reservasArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivo, "reservas");

        for (int i = 0; i < reservasArray.length(); i++) {
            JSONObject reservaJson = reservasArray.getJSONObject(i);
            if (reservaJson.getString("matriculaEstudiante").equals(matriculaEstudiante)) {
                System.out.println("Reserva actual: " + reservaJson.toString(4));
                return;
            }
        }
        System.out.println("No se encontró ninguna reserva para la matrícula: " + matriculaEstudiante);
    }

    public void confirmarReservaDesdeAdministrador() {
        System.out.println("Ingrese el ID de la reserva que desea confirmar: ");
        String idReserva = scanner.next();
        boolean confirmada = confirmarReserva(idReserva);
        if (confirmada) {
            System.out.println("Reserva confirmada exitosamente.");
        } else {
            System.out.println("No se pudo confirmar la reserva. Verifique el ID.");
        }
    }

    private boolean confirmarReserva(String idReserva) {
        JSONArray reservasArray = archivoJSONHandler.leerJSONArrayDelArchivo(rutaArchivo, "reservas");
        for (int i = 0; i < reservasArray.length(); i++) {
            JSONObject reservaJson = reservasArray.getJSONObject(i);
            if (reservaJson.getString("idReserva").equals(idReserva)) {
                reservaJson.put("estado", "confirmada");
                return archivoJSONHandler.escribirEnArchivo(rutaArchivo, "reservas", reservasArray);
            }
        }
        return false;
    }

    private boolean reservaYaExiste(JSONArray reservasArray, String idReserva) {
        for (int i = 0; i < reservasArray.length(); i++) {
            JSONObject reserva = reservasArray.getJSONObject(i);
            if (reserva.getString("idReserva").equals(idReserva)) {
                return true;
            }
        }
        return false;
    }
}