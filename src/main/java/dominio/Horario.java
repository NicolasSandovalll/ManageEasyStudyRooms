package dominio;

public enum Horario {
    PRIMER_HORARIO("08:30", "10:30"),
    SEGUNDO_HORARIO("10:30", "12:30"),
    TERCER_HORARIO("12:30", "14:30"),
    CUARTO_HORARIO("14:30", "16:30");

    private String inicio;
    private String fin;

    Horario(String inicio, String fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }
}