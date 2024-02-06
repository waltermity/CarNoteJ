import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

import java.io.*;
import java.util.ArrayList;


public class Main {

    /**
     * @param list fő lista amiben minden tárolva van
     * @throws IOException
     * A paraméterként megkapott lista tartalmát írja ki .xml fájlba egy külső könyvtár segítségével.
     * A használt könyvtár "XStream", az írás sikerességéről az elkapott IOExcep. alapján tudunk vélekedni.
     * FileWriterrel kiírjuk az XStream által generált objektumot.
     */
    static public void printData(ArrayList<Jarmu> list) throws IOException {
        XStream xstream = new XStream();
        String objectToWrite= xstream.toXML(list);
        try {
            FileWriter writer= new FileWriter("data.xml");
            writer.write(objectToWrite);
            writer.close();
            System.out.println("Sikeres fájlba mentés");
        }catch (IOException e){
            System.out.println("Sikertelen fájlba mentés");
        }
    }

    /**
     * @return Lista, a beolvasott fájl tartalmával
     * Xml fájlból, külső (Xstream) könyvtár segítségével beolvassuk az adatokat
     */
    public static ArrayList<Jarmu> readData() {
        try {
            // XStream objektum létrehozása
            XStream xstream = new XStream(new DomDriver());

            //Egyedi biztonsági konfig. beállítása a könyvtárnak, hogy olvashasson
            TypePermission typePermission = new AnyTypePermission();
            TypePermission wildcardTypePermission = new WildcardTypePermission(new String[] {"C:\\Users\\waltierne.szabo.szil\\Desktop\\prog3\\NHF\\CarNoteJ\\CarNoteJ\\src"}); // Replace with the package name of your Jarmu class
            xstream.addPermission(typePermission);
            xstream.addPermission(wildcardTypePermission);

            // InputStream-el beolvassuk az xml fájl tartalmát
            FileInputStream fileInputStream = new FileInputStream(new File("data.xml"));

            // Külső könyvtár segítségével deszerializáljuk a fájl tartalmát és listába tesszük
            ArrayList<Jarmu> jarmuList = (ArrayList<Jarmu>) xstream.fromXML(fileInputStream);

            // I.S bezárása
            fileInputStream.close();

            return jarmuList;
        }
        catch (FileNotFoundException es){   //Ha nem létezik a data.xml exception lekezelése
            System.out.println("A fájl még nem létezik, bezárás után a felvitt adatokkal létrejön.");
            return new ArrayList<>();
        }
        catch (Exception e) {
            e.printStackTrace();
                return new ArrayList<>(); // Hiba esetén
        }
    }

    public static void main(String[] args) {
        ArrayList<Jarmu> jarmuvek= new ArrayList<>(); //Lista init.
        jarmuvek=readData(); //listaba beolvassuk az adatokat

    KezdoFrame kf = new KezdoFrame(jarmuvek); //Kezdőképernyő init.
    kf.setVisible(true); //láthatóvá tétel

    //A program bezáródása előtt kiírja fájlba az adatokat, a lambdás kifejezés miatt kell egy "final" változó(amit nem változtatunk már)
        ArrayList<Jarmu> finalJarmuvek = jarmuvek;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                printData(finalJarmuvek);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
