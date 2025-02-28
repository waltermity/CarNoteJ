import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class KezdoFrame extends JFrame {

  // Használt elemek felsorolása & init.
  private JComboBox jcb;
  private JButton jbchoose = new JButton("Kiválaszt");
  private JButton jbadd = new JButton("Hozzáad");
  private JButton jbdel = new JButton("Töröl");
  private JPanel p1 = new JPanel();
  private JPanel p2 = new JPanel();

  /**
   * @param jarmuvek Paraméterként megkapja a listát amelyben a járműveket tároljuk Megjeleníti a
   *     kezdőképernyőt, melyen járművet választhatunk ki (Comboboxban legördülő listából),
   *     törölhetünk/hozzáadhatunk
   */
  public KezdoFrame(ArrayList<Jarmu> jarmuvek) {
    // SET
    super("CarNote (Válassz járművet a listából!)");
    jcb = new JComboBox(jarmuvek.toArray()); // populate combobox
    DefaultComboBoxModel dml =
        new DefaultComboBoxModel(); // combobox model létrehozása,majd a listába, hogy a nevek
    // jelenjenek meg
    for (int i = 0; i < jarmuvek.size(); i++) {
      dml.addElement(jarmuvek.get(i).getNev());
    }
    jcb.setModel(dml); // model ráhúzása a comboboxra

    // ablakbeállítások
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setSize(400, 110);
    jcb.setEditable(false); // combobox ne legyen editelheto

    jbchoose.addActionListener(
        new ActionListener() { // action hozzárendelése a kiválaszt gombhoz
          @Override
          public void actionPerformed(
              ActionEvent
                  e) { // Ha nincs kiválasztott elem/üres a lista hibaüzenetet dob a felh. számára
            if (jcb.getSelectedIndex() == -1) {
              JOptionPane.showMessageDialog(
                  null, "Nincs kiválasztott elem", "Hiba", JOptionPane.INFORMATION_MESSAGE);
            }

            ArrayList<Adat> adatok = new ArrayList<>();
            Jarmu jarmu = null;

            for (int i = 0;
                i < jarmuvek.size();
                i++) { // A comboboxból behúzott kiválasztott elem indexe alapján kikeressük a
              // járművek listában, jarmu-ben tároljuk.
              if (jarmuvek.get(i).getNev().equals(jcb.getSelectedItem())) {
                jarmu = jarmuvek.get(i);
              }
            }

            ShowFrame shf =
                new ShowFrame(jarmu); // A jármű adat megjelenítő ablaknak paraméterként odaadjuk a
            // kiválasztott járművet.
            shf.setVisible(true);
          }
        });

    jbadd.addActionListener(
        new ActionListener() { // Hozzáadás gombhoz rendelünk action-t.
          @Override
          public void actionPerformed(ActionEvent e) {
            HozzaadFrame hf =
                new HozzaadFrame(
                    jarmuvek); // Jármű hozzáadása ablak megjelenítése, annak paraméterként a
            // járművek lista átadása.
            hf.setVisible(true);
            KezdoFrame.super.setVisible(false);
          }
        });

    jbdel.addActionListener(
        new ActionListener() { // Törlés gombhoz rendelünk action-t.
          @Override
          public void actionPerformed(
              ActionEvent e) { // Ha nincs kiválaszott elem hibaüzenetet kap a felh., egyébként a
            // kiválasztott elem indexe alapján töröljuk a járművek listából az elemet.
            if (jcb.getSelectedIndex() == -1) {
              JOptionPane.showMessageDialog(
                  null, "Nincs kiválasztott elem", "Hiba", JOptionPane.INFORMATION_MESSAGE);
            }
            jarmuvek.remove(jcb.getSelectedIndex());
            setVisible(false); // mostani ablakot elrejtjük(bezárjuk).
            KezdoFrame kf = new KezdoFrame(jarmuvek); // Visszanyitjuk(visszatérünk) a kezdőoldalra.
            kf.setVisible(true);
          }
        });

    // CREATE UI (Panelhez,framehez elemek addolása)
    p1.add(jcb, FlowLayout.LEFT);
    p1.add(jbchoose, FlowLayout.CENTER);
    p2.add(jbadd, FlowLayout.LEFT);
    p2.add(jbdel, FlowLayout.CENTER);

    Image frameImage = new ImageIcon("icon.png").getImage();
    this.setIconImage(frameImage);

    this.add(p1, BorderLayout.NORTH);
    this.add(p2, BorderLayout.SOUTH);
  }
}
