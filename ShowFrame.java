import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowFrame extends JFrame {

    //Használt elemek felsorolása & init.
    private JList<String> jlistadat= new JList<>();
    private JLabel jlnev = new JLabel("Név:");
    private JLabel jlyear= new JLabel("Gyártási év:");
    private JLabel jltulaj= new JLabel("Tulajdonos:");
    private JLabel jlpassenger= new JLabel("Szállítható utasok száma:");
    private JLabel jltype= new JLabel("Autó típusa:");
    private JLabel jlkobcenti= new JLabel("Motor lökettérfogata (ccm):");
    private JLabel jlsuly= new JLabel("Teherautó súlya (kg):");
    private JLabel jlmaxkm = new JLabel("Utoljára rögzített km.óra állás:");
    private JLabel jlsumkiadas= new JLabel("Összes ráfordított összeg (Ft):");
    private JButton jbvissza = new JButton("Vissza");
    private JButton jbadd = new JButton("Hozzáad");
    private JButton jbdel=new JButton("Törlés");
    private JButton jbedit = new JButton("Szerkeszt");
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();

//----------------------------------------------------------------------------------------------------------------------

    /**
     * @param jarmu
     * Kezdőképernyőről ide érkezünk, az ott kiválasztott járművet veszi át paraméterként.
     * A járműről tárolt információk, adatok, felvitt rekordokat jeleníti meg.
     * Új rekordok felvételére, törlésére, módosítására ad lehetőséget.
     * Annak alapján, hogy milyen típusú a tárolt jármű más-más adatokat kell megjeleníteni (eset szétválasztás A-M-T).
     * Jobb oldalt görgethető JList-ben találhatóak a felvitt rekordok, azokra kattintva kiválaszthatóak.
     */
public ShowFrame(Jarmu jarmu){
    //Frame beallítás paraméterek
    super("CarNote");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(true);
    setSize(600,300);
    //kiválasztott járműhöz tartozó adatok listába kiszedése
    ArrayList<Adat> jarmuadatok =jarmu.getAdatok();
    //Listamodell létrehozása
    DefaultListModel<String> dlm= new DefaultListModel<>();

    //Lista populálása
    for (int i = 0; i < jarmuadatok.size(); i++) {
        dlm.addElement(jarmuadatok.get(i).kiadas+" "+jarmuadatok.get(i).osszeg+" "+jarmuadatok.get(i).kmallas+" "+jarmuadatok.get(i).megjegyzes);

        if(jarmuadatok.get(i).kmallas>jarmu.maxkm){jarmu.maxkm=jarmuadatok.get(i).kmallas;}
    }

    jlistadat= new JList<>(dlm); //modell listára illesztése


    //Attól függően hogy a válaszott jármű milyen típusú, a megjelenített ablak ahhoz fog igazodni
    if(jarmu.getJarmuTipus().equals("Auto")){
        Auto auto= (Auto)jarmu;    //Típus illesztése (kasztolás) járműhöz megfelelően

        //A következőkben Flowlayout és BoxLayout kombinálása fog történni,így szépen lehet illeszteni az adatok felsorolását, és mellé a Jlistet a rekordokkal, alattuk az action gombokkal
        //Left alignment-->Bal oldalra orientált szöveg, Center alignment-->Középre(igazából a szöveg mellé jobbra) orientált szövegek.
        JPanel mainPanel=new JPanel(new FlowLayout());

        // Panel with LEFT alignment
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel leftLabel = new JLabel("Left Aligned");
        leftLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //LEFT ORIENTED (Balra orientált szövegek felsorol.)
        JLabel nev = new JLabel(auto.nev);
        leftPanel.add(nev);
        leftPanel.add(jlyear);
        leftPanel.add(jltulaj);
        leftPanel.add(jltype);
        leftPanel.add(jlpassenger);
        leftPanel.add(new JLabel(" "));
        leftPanel.add(jlmaxkm);
        leftPanel.add(jlsumkiadas);

        // Panel with CENTER alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel centerLabel = new JLabel("Center Aligned");
        centerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // CENTER ORIENTED TEXTS

        centerPanel.add(new JLabel(" "));
        JLabel gy_ev = new JLabel(Integer.toString(auto.gyartas_ev));
        centerPanel.add(gy_ev);
        JLabel tulaj = new JLabel(auto.tulaj);
        centerPanel.add(tulaj);
        JLabel tipus = new JLabel(auto.getTipus());
        centerPanel.add(tipus);
        JLabel utasszam = new JLabel(Integer.toString(auto.getUtasszam()));
        centerPanel.add(utasszam);
        centerPanel.add(new JLabel(" "));
        JLabel maxkm= new JLabel(Integer.toString(jarmu.getMaxkm()));
        centerPanel.add(maxkm);
        jarmu.setOsszkiadas();
        JLabel osszkiad= new JLabel(Integer.toString(jarmu.getOsszkiadas()));
        centerPanel.add(osszkiad);

        // Combine left and center panels in a horizontal manner
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        horizontalPanel.add(leftPanel);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between left and center panels.
        horizontalPanel.add(centerPanel);
        JScrollPane listScrollPane = new JScrollPane(jlistadat); //Görgethetővé tesszük a listát.
        mainPanel.add(horizontalPanel);
        mainPanel.add(listScrollPane);

        jbvissza.addActionListener(new ActionListener() {   //Vissza gombra action
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  //ablak eltűnik, visszakerülünk a kezdőoldalra
            }
        });

        jbadd.addActionListener(new ActionListener() {  //Hozzáad gombra action rendelés
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDataFrame adf = new AddDataFrame(jarmu); //Meghívjuk azt az ablakot ahol adatot tudunk hozzáadni, paraméterként a kiválasztott járművet adjuk tovább
                adf.setVisible(true);
                ShowFrame.super.setVisible(false);
            }
        });

        jbedit.addActionListener(new ActionListener() { //Szerkeszt gombra action rendelése
            @Override
            public void actionPerformed(ActionEvent e) {    //Ha nincs kiválasztott elem, hibaüzenetet dob, egyébként a jlistből behúzott kiválasztott rekord indexe alapján nyit egy ablakot amiben szerkeszthetjük a rekordban tárolt adatokat.
            if(jlistadat.getSelectedIndex()==-1){JOptionPane.showMessageDialog(null,"Nincs kiválasztott elem","Hiba",JOptionPane.INFORMATION_MESSAGE);}
            else{
                int idx= jlistadat.getSelectedIndex();
                EditDataFrame edf=new EditDataFrame(jarmu,idx);
                edf.setVisible(true);
                ShowFrame.super.setVisible(false);
            }
            }
        });

        jbdel.addActionListener(new ActionListener() {  //Törlés gombra action rendelése
            @Override
            public void actionPerformed(ActionEvent e) {    //Ha nincs kiválasztott elem, hibaüzenetet dob, egyébként- a jlistből behúzott kiválasztott rekord indexe alapján törli a rekordot a listából,újrahívja az ablakot, hogy frissüljenek az adatok.
                if(jlistadat.getSelectedIndex()==-1){JOptionPane.showMessageDialog(null,"Nincs kiválasztott elem","Hiba",JOptionPane.INFORMATION_MESSAGE);}
                else{
                    int idx=jlistadat.getSelectedIndex();
                    jarmu.removeAdat(idx);
                    setVisible(false);
                    ShowFrame sh= new ShowFrame(jarmu);
                    sh.setVisible(true);
                }
            }
        });
        //Panel p1-en a felsorolás & lista, p2-n a gombok találhatóak
        p1.add(mainPanel);
        p2.add(jbvissza);
        p2.add(jbadd);
        p2.add(jbdel);
        p2.add(jbedit);

    }
    //.A továbbiakban ugyan ezeket a műveleteket végzem, mindegyik járműre a rá vonatkozó adatokkal.....................
    else if(jarmu.getJarmuTipus().equals("Motor")){
        Motor motor= (Motor)jarmu;  //Típus illesztése (kasztolás) járműhöz megfelelően

        JPanel mainPanel=new JPanel(new FlowLayout());
        // Panel with LEFT alignment
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel leftLabel = new JLabel("Left Aligned");
        leftLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // LEFT ORIENTED
        JLabel nev = new JLabel(motor.nev);
        leftPanel.add(nev);
        leftPanel.add(jlyear);
        leftPanel.add(jltulaj);
        leftPanel.add(jlkobcenti);
        leftPanel.add(new JLabel(" "));
        leftPanel.add(jlmaxkm);
        leftPanel.add(jlsumkiadas);

        // Panel with CENTER alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel centerLabel = new JLabel("Center Aligned");
        centerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // CENTER ORIENTED TEXTS
        centerPanel.add(new JLabel(" "));
        JLabel gy_ev = new JLabel(Integer.toString(motor.gyartas_ev));
        centerPanel.add(gy_ev);
        JLabel tulaj = new JLabel(motor.tulaj);
        centerPanel.add(tulaj);
        JLabel kobcenti = new JLabel(Integer.toString(motor.getKobcenti()));
        centerPanel.add(kobcenti);
        centerPanel.add(new JLabel(" "));
        JLabel maxkm= new JLabel(Integer.toString(jarmu.getMaxkm()));
        centerPanel.add(maxkm);
        jarmu.setOsszkiadas();
        JLabel osszkiad= new JLabel(Integer.toString(jarmu.getOsszkiadas()));
        centerPanel.add(osszkiad);

        // Combine left and center panels in a horizontal manner
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        horizontalPanel.add(leftPanel);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between left and center panels
        horizontalPanel.add(centerPanel);
        JScrollPane listScrollPane = new JScrollPane(jlistadat);
        mainPanel.add(horizontalPanel);
        mainPanel.add(listScrollPane);

        jbvissza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        jbadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDataFrame adf = new AddDataFrame(jarmu);
                adf.setVisible(true);
                ShowFrame.super.setVisible(false);
            }
        });

        jbedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jlistadat.getSelectedIndex()==-1){JOptionPane.showMessageDialog(null,"Nincs kiválasztott elem","Hiba",JOptionPane.INFORMATION_MESSAGE);}
                else{
                    int idx= jlistadat.getSelectedIndex();
                    EditDataFrame edf=new EditDataFrame(jarmu,idx);
                    edf.setVisible(true);
                    ShowFrame.super.setVisible(false);
                }
            }
        });

        jbdel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jlistadat.getSelectedIndex()==-1){JOptionPane.showMessageDialog(null,"Nincs kiválasztott elem","Hiba",JOptionPane.INFORMATION_MESSAGE);}
                else{
                    int idx=jlistadat.getSelectedIndex();
                    jarmu.removeAdat(idx);
                    setVisible(false);
                    ShowFrame sh= new ShowFrame(jarmu);
                    sh.setVisible(true);
                }
            }
        });

        p1.add(mainPanel);
        p2.add(jbvissza);
        p2.add(jbadd);
        p2.add(jbdel);
        p2.add(jbedit);
    }
    //..................................................................................................................
    else if(jarmu.getJarmuTipus().equals("Teherauto")){
        Teherauto teherauto= (Teherauto) jarmu;

        JPanel mainPanel=new JPanel(new FlowLayout());
        // Panel with LEFT alignment
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel leftLabel = new JLabel("Left Aligned");
        leftLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // LEFT ORIENTED
        JLabel nev = new JLabel(teherauto.nev);
        leftPanel.add(nev);
        leftPanel.add(jlyear);
        leftPanel.add(jltulaj);
        leftPanel.add(jlsuly);
        leftPanel.add(new JLabel(" "));
        leftPanel.add(jlmaxkm);
        leftPanel.add(jlsumkiadas);

        // Panel with CENTER alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel centerLabel = new JLabel("Center Aligned");
        centerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // CENTER ORIENTED TEXTS
        centerPanel.add(new JLabel(" "));
        JLabel gy_ev = new JLabel(Integer.toString(teherauto.gyartas_ev));
        centerPanel.add(gy_ev);
        JLabel tulaj = new JLabel(teherauto.tulaj);
        centerPanel.add(tulaj);
        JLabel suly = new JLabel(Integer.toString(teherauto.getSuly()));
        centerPanel.add(suly);
        centerPanel.add(new JLabel(" "));
        JLabel maxkm= new JLabel(Integer.toString(jarmu.getMaxkm()));
        centerPanel.add(maxkm);
        jarmu.setOsszkiadas();
        JLabel osszkiad= new JLabel(Integer.toString(jarmu.getOsszkiadas()));
        centerPanel.add(osszkiad);

        // Combine left and center panels in a horizontal manner
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        horizontalPanel.add(leftPanel);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between left and center panels
        horizontalPanel.add(centerPanel);
        JScrollPane listScrollPane = new JScrollPane(jlistadat);
        mainPanel.add(horizontalPanel);
        mainPanel.add(listScrollPane);

        jbvissza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        jbadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDataFrame adf = new AddDataFrame(jarmu);
                adf.setVisible(true);
                ShowFrame.super.setVisible(false);
            }
        });

        jbedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jlistadat.getSelectedIndex()==-1){JOptionPane.showMessageDialog(null,"Nincs kiválasztott elem","Hiba",JOptionPane.INFORMATION_MESSAGE);}
                else{
                    int idx= jlistadat.getSelectedIndex();
                    EditDataFrame edf=new EditDataFrame(jarmu,idx);
                    edf.setVisible(true);
                    ShowFrame.super.setVisible(false);
                }
            }
        });

        jbdel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jlistadat.getSelectedIndex()==-1){JOptionPane.showMessageDialog(null,"Nincs kiválasztott elem","Hiba",JOptionPane.INFORMATION_MESSAGE);}
                else{
                    int idx=jlistadat.getSelectedIndex();
                    jarmu.removeAdat(idx);
                    setVisible(false);
                    ShowFrame sh= new ShowFrame(jarmu);
                    sh.setVisible(true);
                }
            }
        });

        p1.add(mainPanel);
        p2.add(jbvissza);
        p2.add(jbadd);
        p2.add(jbdel);
        p2.add(jbedit);
    }

    Image frameImage=new ImageIcon("icon.png").getImage();
    this.setIconImage(frameImage);

    this.add(p1,BorderLayout.NORTH);
    this.add(p2,BorderLayout.SOUTH);

}
}
