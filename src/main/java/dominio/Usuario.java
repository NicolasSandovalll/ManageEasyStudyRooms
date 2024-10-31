

package dominio;

public class Usuario {
    protected String matricula;
    protected String contrasena;

    public Usuario(String matricula, String contrasena) {
        this.matricula = matricula;
        this.contrasena = contrasena;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean iniciarSesion(String matriculaIngresada, String contrasenaIngresada) {
        return this.matricula.equals(matriculaIngresada) && this.contrasena.equals(contrasenaIngresada);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "matricula='" + matricula + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}