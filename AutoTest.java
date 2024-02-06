import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {

    Auto auto = new Auto("Tomi",2002,"Renault",7,"egylegteru");


    @Test
    @DisplayName("Auto getJarmuTipus teszt")
    void getJarmuTipus() {
        assertEquals("Auto",auto.getJarmuTipus());
    }

    @Test
    @DisplayName("Auto getUtasszam teszt")
    void getUtasszam() {
        assertEquals(7,auto.getUtasszam());
    }

    @Test
    @DisplayName("Auto getTipus teszt")
    void getTipus() {
        assertEquals("egylegteru",auto.getTipus());
    }
}