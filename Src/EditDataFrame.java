import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditDataFrame extends JFrame {
  // Használt elemek felsorolása & init.
  private JLabel jlkiadas = new JLabel("Kiadás neve:");
  private JLabel jlosszeg = new JLabel("Kiadás összege (Ft): XXXXXXX");
  private JLabel jlkmallas = new JLabel("Jelenlegi km.óra állás: XXXXXXX");
  private JLabel jlcomment = new JLabel("Megjegyzés:");

  private JTextField jtfkiadas = new JTextField(20);
  private JTextField jtfosszeg = new JTextField(20);
  private JTextField jtfkmallas = new JTextField(20);
  private JTextField jtfcomment = new JTextField(20);
  private JButton jbedit = new JButton("Szerkeszt");
  private JButton jbvissza = new JButton("Vissza");
  private JPanel p1 = new JPanel(new GridLayout(0, 1));
  private JPanel p2 = new JPanel();

  /**
   * @param jarmu A kiválasztott jármű amin dolgozunk
   * @param idx A járműhoz tartozó rekordok közül, a kiválasztott indexe Felépítése hasonló az
   *     AddDataFrame-hez, JLabel-TextField párosítások,Textfield-be betöltve a már rögzített rekord
   *     értékei Azokat módosítva, majd szerkeszt gomb lenyomásával lehet végrehajtani a műveletet.
   */
  public EditDataFrame(Jarmu jarmu, int idx) {
    // Ablakbeállítások
    super("CarNote");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(true);
    setSize(400, 400);

    // Label-TextField felsor.
    p1.add(jlcomment, FlowLayout.LEFT);
    jtfcomment.setText(jarmu.getAdatok().get(idx).megjegyzes);
    p1.add(jtfcomment, FlowLayout.CENTER);
    p1.add(jlkmallas, FlowLayout.LEFT);
    jtfkmallas.setText(Integer.toString(jarmu.getAdatok().get(idx).kmallas));
    p1.add(jtfkmallas, FlowLayout.CENTER);
    p1.add(jlosszeg, FlowLayout.LEFT);
    jtfosszeg.setText(Integer.toString(jarmu.getAdatok().get(idx).osszeg));
    p1.add(jtfosszeg, FlowLayout.CENTER);
    p1.add(jlkiadas, FlowLayout.LEFT);
    jtfkiadas.setText(jarmu.getAdatok().get(idx).kiadas);
    p1.add(jtfkiadas, FlowLayout.CENTER);

    // Szerkeszt gombra action rendelés, gombnyomásra frissíti a rekord adatait, ha nem megfelelő a
    // bemeneti adatok formátuma hibaüzenetet küld a felh.-nak
    jbedit.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              jarmu.getAdatok().get(idx).megjegyzes = jtfcomment.getText();
              jarmu.getAdatok().get(idx).kmallas = Integer.parseInt(jtfkmallas.getText());
              jarmu.getAdatok().get(idx).osszeg = Integer.parseInt(jtfosszeg.getText());
              jarmu.getAdatok().get(idx).kiadas = jtfkiadas.getText();
              setVisible(false);
              ShowFrame sh = new ShowFrame(jarmu);
              sh.setVisible(true);
            } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(
                  null,
                  "Nem megfelelő bemeneti formátum!",
                  "Hiba",
                  JOptionPane.INFORMATION_MESSAGE);
            }
          }
        });
    // Vissza gombra action rendelés, gombnyomásra az aktuális ablak eltűnik, visszakerülünk a
    // "ShowFrame"-re
    jbvissza.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            EditDataFrame.super.setVisible(false);
            ShowFrame sh = new ShowFrame(jarmu);
            sh.setVisible(true);
          }
        });

    // Panel-Frame elemek hozzáadása
    p2.add(jbedit, FlowLayout.LEFT);
    p2.add(jbvissza, FlowLayout.LEFT);

    Image frameImage = new ImageIcon("icon.png").getImage();
    this.setIconImage(frameImage);

    this.add(p1, BorderLayout.NORTH);
    this.add(p2, BorderLayout.SOUTH);
  }
}
