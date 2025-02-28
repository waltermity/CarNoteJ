import java.util.ArrayList;

public class Auto extends Jarmu {
  private int utasszam;
  private String tipus;
  private ArrayList<Adat> adatok;

  public Auto(String tu, int g, String n, int u, String ti) {
    super(tu, g, n);
    utasszam = u;
    tipus = ti;
  }

  public String getJarmuTipus() {
    return "Auto";
  }
  ;

  public int getUtasszam() {
    return utasszam;
  }

  public String getTipus() {
    return tipus;
  }
}
