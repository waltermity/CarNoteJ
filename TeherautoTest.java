import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeherautoTest {

    Teherauto teherauto = new Teherauto("Jozsef",1995,"IFA",2100);

    @Test
    @DisplayName("Teherauto getJarmuTipus teszt")
    void getJarmuTipus() {
        assertEquals("Teherauto",teherauto.getJarmuTipus());
    }

    @Test
    @DisplayName("Teherauto getSuly teszt")
    void getSuly() {
        assertEquals(2100,teherauto.getSuly());
    }
}