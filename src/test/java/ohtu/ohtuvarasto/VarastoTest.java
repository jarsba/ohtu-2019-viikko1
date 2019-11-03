package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiNegatiivinenTilavuus() {
        varasto = new Varasto(-10);
        assertEquals(varasto.getTilavuus(), 0, vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiSaldoIsompi() {
        varasto = new Varasto(5, 10);
        assertEquals(varasto.getTilavuus(), 5, vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), 5, vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiOikeatParametrit() {
        varasto = new Varasto(10, 2);
        assertEquals(varasto.getTilavuus(), 10, vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), 2, vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiVaaratParametrit() {
        varasto = new Varasto(-1, -1);
        assertEquals(varasto.getTilavuus(), 0, vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
    }

    @Test
    public void lisaaNegatiivinenMaara() {
        double vanhaSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaLiikaa() {
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu() + 1);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenMaara() {
        double vanhaSaldo = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaLiikaa() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(saldo + 1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    // comment

    @Test
    public void vertaaToString() {
        double saldo = varasto.getSaldo();
        double mahtuu = varasto.paljonkoMahtuu();
        assertEquals("saldo = " + saldo + ", vielä tilaa " + mahtuu, varasto.toString());
    }
}