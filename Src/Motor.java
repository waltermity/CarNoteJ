public class Motor extends Jarmu{
    private int kobcenti;
    public Motor(String tu,int g,String n,int k){super(tu,g,n); kobcenti=k;}
    public String getJarmuTipus(){return "Motor";};
    public int getKobcenti(){return kobcenti;}
}
