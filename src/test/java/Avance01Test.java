import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Avance01Test {

    private Avance01 avance01;

    @BeforeEach
    public void setUp() {
        avance01 = new Avance01();
        // Puedes inicializar cualquier otro estado que necesites
    }

    @Test
    public void testLimpiarMatricula() {
        String input = "12345678k21";
        String expected = "12345678k21";
        assertEquals(expected, avance01.limpiarMatricula(input));

        input = "12a345678k21";
        expected = "12345678k21";  // Asumiendo que solo se quieren números y 'k'
        assertEquals(expected, avance01.limpiarMatricula(input));
    }

    @Test
    public void testEsMatriculaValida() {
        assertTrue(avance01.esMatriculaValida("12345678k21"));
        assertFalse(avance01.esMatriculaValida("1234567k21"));  // Menos de 8 dígitos
        assertFalse(avance01.esMatriculaValida("12345678k2a")); // Caracter inválido
    }

    @Test
    public void testProcesarOpcionInicial() {
        avance01.cargarUsuarios(); // Carga los usuarios para la prueba
        avance01.matriculaActual = "21212121k21"; // Simula un usuario existente

        // Procesar opción 1 (Iniciar sesión)
        avance01.procesarOpcionInicial(1);
        assertEquals("21212121k21", avance01.matriculaActual, "La matrícula debería ser la del usuario que inició sesión.");

        // Procesar opción 2 (Registrar usuario)
        avance01.procesarOpcionInicial(2);
        assertNotNull(avance01.usuarios, "La lista de usuarios no debería ser nula después de intentar registrar un usuario.");

        // Procesar opción 3 (Salir)
        avance01.procesarOpcionInicial(3);
        assertNull(avance01.matriculaActual, "La matrícula debería ser nula después de salir del sistema.");
    }
}
