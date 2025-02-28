import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class HozzaadFrame extends JFrame {
  // Használt elemek felsorolása & init.
  private JLabel jlname = new JLabel("Jármű neve:");
  private JLabel jltulaj = new JLabel("Tulaj neve:");
  private JLabel jlyear = new JLabel("Gyártási éve:");
  private JLabel jlpassenger = new JLabel("Szállítható utasok száma:");
  private JLabel jltype = new JLabel("Autó típusa:");
  private JLabel jlkobcenti = new JLabel("Köbcenti:");
  private JLabel jlsuly = new JLabel("Teherautó súlya:");
  private JComboBox jcb;
  private JTextField jtfname = new JTextField(20);
  private JTextField jtfyear = new JTextField(20);
  private JTextField jtftulaj = new JTextField(20);
  private JTextField jtfpassenger = new JTextField(20);
  private JTextField jtftype = new JTextField(20);
  private JTextField jtfkobcenti = new JTextField(20);
  private JTextField jtfsuly = new JTextField(20);
  private JButton jbadd = new JButton("Hozzáad");
  private JButton jbvissza = new JButton("Vissza");
  private JPanel p1 = new JPanel(new GridLayout(0, 1));
  private JPanel p2 = new JPanel();

  /**
   * @param jarmuvek Kezdőképernyőről ide érkezünk, a járművek listáját veszi át paraméterként.
   *     Jármű hozzáadása a listához művelet valósítja meg. Combobox-ban kiválasztható a felvenni
   *     kívánt jármű típusa, az ablak aszerint változik (mindegyikhez más-más adat). "Hozzáad"
   *     gombnyomásra, ablak bezárásakor a kezdőképernyőre kerülünk vissza.
   */
  public HozzaadFrame(ArrayList<Jarmu> jarmuvek) {
    super("CarNote");
    String array[] = {
      "Autó", "Motor", "Teherautó"
    }; // Tömb amivel a comboboxot populáljuk (kiválasztható típusok nevei)
    jcb = new JComboBox(array);
    DefaultComboBoxModel dml =
        new DefaultComboBoxModel(); // Cb model létrehozása, cb feltöltése, majd model cb-re
    // illesztése.
    for (int i = 0; i < 3; i++) {
      dml.addElement(array[i]);
    }
    jcb.setModel(dml);

    // Ablakbeállítások (bezáráskor mi történjen,újraméretezhető, méret)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setSize(400, 400);

    // Cb.-hez action rendelése, az alapján, hogy mit választunk fog módosulni az ablak tartalma,
    // minden választásnál(letisztítjuk az előzőt és kiírjuk az aktuálisat)
    // Az if-eken belül pedig a Hozzáadás gombhoz rendelünk actiont(tehát ha megnyomtuk), létrehozza
    // az új járművet(adott típus), majd hozzáadja a listához
    // Ha rossz formátumú adatokat adnánk meg, hibaüzenet formájában tájékoztatjuk a felh.-t

    jcb.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String selectedOption = (String) combo.getSelectedItem();
            p1.removeAll();
            if (selectedOption.equals("Autó")) {
              p1.add(jlpassenger, FlowLayout.LEFT);
              p1.add(jtfpassenger, FlowLayout.CENTER);
              p1.add(jltype, FlowLayout.LEFT);
              p1.add(jtftype, FlowLayout.CENTER);
              p1.add(jltulaj, FlowLayout.LEFT);
              p1.add(jtftulaj, FlowLayout.CENTER);
              p1.add(jlyear, FlowLayout.LEFT);
              p1.add(jtfyear, FlowLayout.CENTER);
              p1.add(jlname, FlowLayout.LEFT);
              p1.add(jtfname, FlowLayout.CENTER);
              p1.add(jcb, FlowLayout.LEFT);
              p2.add(jbvissza, FlowLayout.LEFT);
              jbvissza.addActionListener(
                  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      HozzaadFrame.super.setVisible(false);
                      KezdoFrame kf = new KezdoFrame(jarmuvek);
                      kf.setVisible(true);
                    }
                  });
              p2.add(jbadd, FlowLayout.CENTER);
              jbadd.addActionListener(
                  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      try {
                        Jarmu ujjarmu =
                            new Auto(
                                jtftulaj.getText(),
                                Integer.parseInt(jtfyear.getText()),
                                jtfname.getText(),
                                Integer.parseInt(jtfpassenger.getText()),
                                jtftype.getText());
                        jarmuvek.add(ujjarmu);
                        setVisible(false);
                        KezdoFrame kf = new KezdoFrame(jarmuvek);
                        kf.setVisible(true);
                      } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Nem megfelelő bemeneti formátum!",
                            "Hiba",
                            JOptionPane.INFORMATION_MESSAGE);
                      }
                    }
                  });
            } else if (selectedOption.equals("Motor")) {
              p1.add(jlkobcenti, FlowLayout.LEFT);
              p1.add(jtfkobcenti, FlowLayout.CENTER);
              p1.add(jltulaj, FlowLayout.LEFT);
              p1.add(jtftulaj, FlowLayout.CENTER);
              p1.add(jlyear, FlowLayout.LEFT);
              p1.add(jtfyear, FlowLayout.CENTER);
              p1.add(jlname, FlowLayout.LEFT);
              p1.add(jtfname, FlowLayout.CENTER);
              p1.add(jcb, FlowLayout.LEFT);
              p2.add(jbvissza, FlowLayout.LEFT);
              jbvissza.addActionListener(
                  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      HozzaadFrame.super.setVisible(false);
                      KezdoFrame kf = new KezdoFrame(jarmuvek);
                      kf.setVisible(true);
                    }
                  });
              p2.add(jbadd, FlowLayout.CENTER);
              jbadd.addActionListener(
                  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      try {
                        Jarmu ujjarmu =
                            new Motor(
                                jtftulaj.getText(),
                                Integer.parseInt(jtfyear.getText()),
                                jtfname.getText(),
                                Integer.parseInt(jtfkobcenti.getText()));
                        jarmuvek.add(ujjarmu);
                        setVisible(false);
                        KezdoFrame kf = new KezdoFrame(jarmuvek);
                        kf.setVisible(true);
                      } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Nem megfelelő bemeneti formátum!",
                            "Hiba",
                            JOptionPane.INFORMATION_MESSAGE);
                      }
                    }
                  });
            } else if (selectedOption.equals("Teherautó")) {
              p1.add(jlsuly, FlowLayout.LEFT);
              p1.add(jtfsuly, FlowLayout.CENTER);
              p1.add(jltulaj, FlowLayout.LEFT);
              p1.add(jtftulaj, FlowLayout.CENTER);
              p1.add(jlyear, FlowLayout.LEFT);
              p1.add(jtfyear, FlowLayout.CENTER);
              p1.add(jlname, FlowLayout.LEFT);
              p1.add(jtfname, FlowLayout.CENTER);
              p1.add(jcb, FlowLayout.LEFT);
              p2.add(jbvissza, FlowLayout.LEFT);
              jbvissza.addActionListener(
                  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      HozzaadFrame.super.setVisible(false);
                      KezdoFrame kf = new KezdoFrame(jarmuvek);
                      kf.setVisible(true);
                    }
                  });
              p2.add(jbadd, FlowLayout.CENTER);
              jbadd.addActionListener(
                  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      try {
                        Jarmu ujjarmu =
                            new Teherauto(
                                jtftulaj.getText(),
                                Integer.parseInt(jtfyear.getText()),
                                jtfname.getText(),
                                Integer.parseInt(jtfsuly.getText()));
                        jarmuvek.add(ujjarmu);
                        setVisible(false);
                        KezdoFrame kf = new KezdoFrame(jarmuvek);
                        kf.setVisible(true);
                      } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Nem megfelelő bemeneti formátum!",
                            "Hiba",
                            JOptionPane.INFORMATION_MESSAGE);
                      }
                    }
                  });
            }
            p1.revalidate();
            p1.repaint();
          }
        });

    // CREATE UI
    p1.add(jcb, FlowLayout.LEFT);
    p2.add(jbadd, FlowLayout.LEFT);

    Image frameImage = new ImageIcon("icon.png").getImage();
    this.setIconImage(frameImage);

    this.add(p1, BorderLayout.NORTH);
    this.add(p2, BorderLayout.SOUTH);
  }
}
