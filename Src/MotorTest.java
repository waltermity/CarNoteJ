import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotorTest {

    Motor motor=new Motor("Karoly",2011,"Kawasaki",400);

    @Test
    @DisplayName("Motor getJarmuTipus teszt")
    void getJarmuTipus() {
        assertEquals("Motor",motor.getJarmuTipus());
    }

    @Test
    @DisplayName("Motor getKobcenti teszt")
    void getKobcenti() {
        assertEquals(400,motor.getKobcenti());
    }
}