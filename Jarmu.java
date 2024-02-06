import java.util.ArrayList;

public class Jarmu {
    private ArrayList<Adat> adatok= new ArrayList<>();
    protected String tulaj;
    protected int gyartas_ev;
    protected String nev;
    protected int maxkm;
    protected  int osszkiadas= 0;


public Jarmu(String t,int g,String n){tulaj=t;gyartas_ev=g;nev=n;}
    public String getNev(){return nev;}
    public int getMaxkm(){return maxkm;}
    public int getOsszkiadas(){return osszkiadas;}
    public ArrayList<Adat> getAdatok(){return adatok;}
    public String getJarmuTipus(){return "Jarmu";};
public void addAdat(String kiadas,int osszeg,int kmallas,String megjegyzes){
    Adat a= new Adat(kiadas,osszeg,kmallas,megjegyzes);
    adatok.add(a);
}
    public void removeAdat(int idx){adatok.remove(idx);}

    /**
     * Hogy elkerüljük a rekordok értékeinek duplán számolását, ezzel számítjuk ki
     */
    public void setOsszkiadas(){
    int osszeg=0;
    for(int i=0; i<adatok.size(); i++){
        osszeg+=adatok.get(i).osszeg;
    }
    osszkiadas=osszeg;
}

}
