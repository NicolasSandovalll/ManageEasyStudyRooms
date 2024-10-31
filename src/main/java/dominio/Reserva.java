package dominio;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String idReserva;
    private String idLogia;
    private String matriculaEstudiante;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private List<String> companeros;

    public Reserva(String idReserva, String idLogia, String matriculaEstudiante, String horaInicio, String horaFin) {
        this.idReserva = idReserva;
        this.idLogia = idLogia;
        this.matriculaEstudiante = matriculaEstudiante;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = "pendiente";
        this.companeros = new ArrayList<>();
    }

    // Getter y Setter para estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public String getIdLogia() {
        return idLogia;
    }

    public String getMatriculaEstudiante() {
        return matriculaEstudiante;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public List<String> getCompaneros() {
        return companeros;
    }

    public void agregarCompanero(String companero) {
        this.companeros.add(companero);
    }

    public void cancelarReserva() {
        this.estado = "cancelada";
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva='" + idReserva + '\'' +
                ", idLogia='" + idLogia + '\'' +
                ", matriculaEstudiante='" + matriculaEstudiante + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", estado='" + estado + '\'' +
                ", companeros=" + companeros +
                '}';
    }
}