import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDataFrame extends JFrame {
    //Használt elemek felsorolása & init.
    private JLabel jlkiadas=new JLabel("Kiadás neve:");
    private JLabel jlosszeg=new JLabel("Kiadás összege (Ft): XXXXXXX");
    private JLabel jlkmallas=new JLabel("Jelenlegi km.óra állás: XXXXXXX");
    private JLabel jlcomment=new JLabel("Megjegyzés:");

    private JTextField jtfkiadas = new JTextField(20);
    private JTextField jtfosszeg = new JTextField(20);
    private JTextField jtfkmallas = new JTextField(20);
    private JTextField jtfcomment = new JTextField(20);
    private JButton jbadd = new JButton("Hozzáad");
    private JButton jbvissza=new JButton("Vissza");
    private JPanel p1 = new JPanel(new GridLayout(0,1));
    private JPanel p2 = new JPanel();

    /**
     * @param jarmu
     * ShowFrame-ből érkezünk ide, paraméterként a kiválasztott járművet kapja meg
     * TextFieldek kitöltésével vihetőek be az adatok, hozzáad gombbal hozzáadható a rekord
     * Vissza gomb lenyomásával visszakerülünk a ShowFrame-be (Jármű adatait megjelenítő ablak)
     */
    public AddDataFrame(Jarmu jarmu){
        //Ablak beállítások
        super("CarNote");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(400,400);

        //Szöveg-TextField párosítás,felsorolás Panel p1-en tárolva.
        p1.add(jlcomment,FlowLayout.LEFT);
        p1.add(jtfcomment,FlowLayout.CENTER);
        p1.add(jlkmallas,FlowLayout.LEFT);
        p1.add(jtfkmallas,FlowLayout.CENTER);
        p1.add(jlosszeg,FlowLayout.LEFT);
        p1.add(jtfosszeg,FlowLayout.CENTER);
        p1.add(jlkiadas,FlowLayout.LEFT);
        p1.add(jtfkiadas,FlowLayout.CENTER);

        //A gombok Panel p2-n helyezkednek el.
        p2.add(jbadd,FlowLayout.LEFT);
        p2.add(jbvissza,FlowLayout.LEFT);

        jbvissza.addActionListener(new ActionListener() {   //Vissza gombra action rendelés, bezárjuk (eltűntetjük), visszahíhjuk a ShowFrame-et
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDataFrame.super.setVisible(false);
                ShowFrame sh =new ShowFrame(jarmu);
                sh.setVisible(true);
            }
        });

        //Hozzáadás gombra action rendelés, addAdat fgv, paramétereként a Textfield-ek értékeit adjuk, ahol kell típus illesztés/konvertálással
        //Ha bemeneti adatok formátumával valami baj van, hibaüzenetként jelezzük a felh. fele
        jbadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jarmu.addAdat(jtfkiadas.getText(),Integer.parseInt(jtfosszeg.getText()),Integer.parseInt(jtfkmallas.getText()),jtfcomment.getText());
                    setVisible(false);
                    ShowFrame sh = new ShowFrame(jarmu);
                    sh.setVisible(true);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Nem megfelelő bemeneti formátum!","Hiba",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        //Panel-Frame elemek hozzáadása
        Image frameImage=new ImageIcon("icon.png").getImage();
        this.setIconImage(frameImage);
        this.add(p1,BorderLayout.NORTH);
        this.add(p2,BorderLayout.SOUTH);
    }
}
