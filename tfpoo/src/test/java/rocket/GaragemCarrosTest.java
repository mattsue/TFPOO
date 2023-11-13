package rocket;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import rocket.tfpoo.Carro;
import rocket.tfpoo.GaragemCarros;
import rocket.tfpoo.Locomotiva;
import rocket.tfpoo.Vagao;

public class GaragemCarrosTest {

    private GaragemCarros garagemCarros;

    @Before
    public void setUp() {
        garagemCarros = new GaragemCarros();
    }

    @Test
    public void testVerificaIdCarroExistente() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        garagemCarros.addCarro(locomotiva);
        boolean existe = garagemCarros.verificaIdCarro(1);
        assertTrue(existe);
    }

    @Test
    public void testVerificaIdCarroInexistente() {
        boolean existe = garagemCarros.verificaIdCarro(7);
        assertFalse(existe);
    }

    @Test
    public void testGetCarroExistente() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        garagemCarros.addCarro(locomotiva);
        Carro carro = garagemCarros.getCarro(1);
        assertNotNull(carro);
    }

    @Test
    public void testRemoveCarro() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        garagemCarros.addCarro(locomotiva);
        Carro carro = garagemCarros.getCarro(1);
        garagemCarros.removeCarro(carro);
        boolean existsInGaragem = garagemCarros.hasCarro(carro);
        assertFalse(existsInGaragem);
    }

    @Test
    public void testToStringLocomotivas() {
        Locomotiva locomotiva = new Locomotiva(1, 2000, 10);
        garagemCarros.addCarro(locomotiva);
        String result = garagemCarros.toString(1);
        String expectedLocomotivas = "Carro [1, Tipo: Locomotiva, Capacidade = 2000.0, Máximo de Vagões = 10]\n" ;
        assertEquals(expectedLocomotivas, result);
    }
    
    @Test
    public void testToStringVagoes() {
        Vagao vagao = new Vagao(1, 2000);
        garagemCarros.addCarro(vagao);
        String result = garagemCarros.toString(2);
        String expectedVagoes = "Carro [1, Tipo: Vagão, Capacidade = 2000.0]\n";
        assertEquals(expectedVagoes, result);
    }
}