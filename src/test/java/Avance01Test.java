import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Avance01Test {

    private Avance01 avance01;

    @BeforeEach
    public void setUp() {
        avance01 = new Avance01();
        
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


}
