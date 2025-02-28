import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

class JarmuTest {
  Jarmu jarmu = new Jarmu("Tomi", 2002, "Fiat");

  @org.junit.jupiter.api.Test
  @DisplayName("Jarmu getNev teszt")
  void getNev() {
    assertEquals("Fiat", jarmu.getNev());
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Jarmu Constructor teszt")
  public void Jarmu() {
    assertEquals("Tomi", jarmu.tulaj);
    assertEquals(2002, jarmu.gyartas_ev);
    assertEquals("Fiat", jarmu.nev);
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Jarmu Maxkm getter teszt")
  void getMaxkm() {
    jarmu.maxkm = 45000;
    assertEquals(45000, jarmu.getMaxkm());
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Jarmu Osszkiadas getter teszt")
  void getOsszkiadas() {
    jarmu.osszkiadas = 120000;
    assertEquals(120000, jarmu.getOsszkiadas());
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Jarmu JarmuTipus teszt")
  void getJarmuTipus() {
    assertEquals("Jarmu", jarmu.getJarmuTipus());
  }
}
