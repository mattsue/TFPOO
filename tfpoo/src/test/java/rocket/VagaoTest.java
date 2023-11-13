package rocket;
import static org.junit.Assert.*;
import org.junit.Test;
import rocket.tfpoo.Vagao;

public class VagaoTest {

    @Test
    public void testGetId() {
        Vagao vagao = new Vagao(1, 200);
        int id = vagao.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetCapacidade() {
        Vagao vagao = new Vagao(1, 200);
        double capacidade = vagao.getCapacidade();
        assertEquals(200, capacidade, 0.001);
    }

    @Test
    public void testGetIdTrem() {
        Vagao vagao = new Vagao(1, 200);
        int idTrem = vagao.getIdTrem();
        assertEquals(0, idTrem);
    }

    @Test
    public void testSetIdTrem() {
        Vagao vagao = new Vagao(1, 200);
        vagao.setIdTrem(2);
        int idTrem = vagao.getIdTrem();
        assertEquals(2, idTrem);
    }

    @Test
    public void testToString() {
        Vagao vagao = new Vagao(1, 200);
        String str = vagao.toString();
        assertTrue(str.contains("Vagão"));
        assertTrue(str.contains("Capacidade = 200.0]"));
    }
}