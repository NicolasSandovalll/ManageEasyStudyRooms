package dominio;

public class Logia {
    private int idLogia;
    private int capacidad;
    private String ubicacion;
    private String estado;

    public Logia(int idLogia, int capacidad, String ubicacion, String estado) {
        this.idLogia = idLogia;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Getters y setters para cada campo
    public int getIdLogia() {
        return idLogia;
    }

    public void setIdLogia(int idLogia) {
        this.idLogia = idLogia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
