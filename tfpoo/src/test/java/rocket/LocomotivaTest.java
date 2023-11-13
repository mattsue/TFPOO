package rocket;
import static org.junit.Assert.*;
import org.junit.Test;
import rocket.tfpoo.Locomotiva;

public class LocomotivaTest {

    @Test
    public void testGetId() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        assertEquals(1, locomotiva.getId());
    }

    @Test
    public void testGetMaxPeso() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        assertEquals(2000, locomotiva.getMaxPeso(), 0.01); // Use delta para comparar valores double
    }

    @Test
    public void testGetMaxVagoes() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        assertEquals(10, locomotiva.getMaxVagoes());
    }

    @Test
    public void testGetIdTrem() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        assertEquals(0, locomotiva.getIdTrem());
    }

    @Test
    public void testSetIdTrem() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        locomotiva.setIdTrem(5);
        assertEquals(5, locomotiva.getIdTrem());
    }

    @Test
    public void testSetId() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        locomotiva.setId("2");
        assertEquals(2, locomotiva.getId());
    }

    @Test
    public void testSetMaxPeso() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        locomotiva.setMaxPeso("2500.5");
        assertEquals(2500.5, locomotiva.getMaxPeso(), 0.01);
    }

    @Test
    public void testSetMaxVagoes() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        locomotiva.setMaxVagoes("12");
        assertEquals(12, locomotiva.getMaxVagoes());
    }

    @Test
    public void testToString() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        String expected = "Carro [1, Tipo: Locomotiva, Capacidade = 2000.0, Máximo de Vagões = 10]";
        assertEquals(expected, locomotiva.toString());
    }
}