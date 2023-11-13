package rocket;
import static org.junit.Assert.*;
import org.junit.Test;
import rocket.tfpoo.GaragemCarros;
import rocket.tfpoo.Locomotiva;
import rocket.tfpoo.Trem;
import rocket.tfpoo.Vagao;

public class TremTest {

    @Test
    public void testGetId() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        assertEquals(1, trem.getId());
    }

    @Test
    public void testGetQuantLocomotiva() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        assertEquals(1, trem.getQuantLocomotivas());
    }

    @Test
    public void testGetQuantVagoes() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        assertEquals(0, trem.getQuantVagoes());
    }

    @Test
    public void testEngataLocomotiva() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        GaragemCarros gc = new GaragemCarros();
        Locomotiva locomotiva = new Locomotiva(2, 2000, 10);
        assertTrue(trem.engataLocomotiva(locomotiva, gc));
    }

    @Test
    public void testEngataVagao() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        GaragemCarros gc = new GaragemCarros();
        Vagao vagao = new Vagao(2, 200);
        assertTrue(trem.engataVagao(vagao, gc));
    }

    @Test
    public void testDesengataLocomotiva() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        GaragemCarros gc = new GaragemCarros();
        Locomotiva locomotiva = new Locomotiva(2, 2000, 10);
        trem.engataLocomotiva(locomotiva, gc);
        assertTrue(trem.desengataLocomotiva(gc));
    }

    @Test
    public void testDesengataVagao() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        GaragemCarros gc = new GaragemCarros();
        Vagao vagao = new Vagao(2, 200);
        trem.engataVagao(vagao, gc);
        assertTrue(trem.desengataVagao(gc));
    }

    @Test
    public void testDesengataTudo() {
        Trem trem = new Trem(1, new Locomotiva(1, 2000, 10), new GaragemCarros());
        GaragemCarros gc = new GaragemCarros();
        Vagao vagao = new Vagao(2, 200);
        trem.engataVagao(vagao, gc);
        trem.desengataTudo(gc);
        assertEquals(0, trem.getQuantLocomotivas());
        assertEquals(0, trem.getQuantVagoes());
    }
}