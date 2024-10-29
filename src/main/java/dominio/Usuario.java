package dominio;

public class Usuario {
    private String matricula;
    private String contrasena;

    public Usuario(String matricula, String contrasena) {
        this.matricula = matricula;
        this.contrasena = contrasena;
    }

    public boolean iniciarSesion(String matriculaIngresada, String contrasenaIngresada) {
        return this.matricula.equals(matriculaIngresada) && this.contrasena.equals(contrasenaIngresada);
    }

    public String getMatricula() {
        return matricula;
    }

    public String getContrasena() {
        return contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "matricula='" + matricula + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
