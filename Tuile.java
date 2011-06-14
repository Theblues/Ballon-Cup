import java.util.ArrayList;

public class Tuile
{
    private Paysage paysage;
    private ArrayList<Ballon> JeuJoueur1 = new ArrayList<Ballon>();
    private ArrayList<Ballon> JeuJoueur2 = new ArrayList<Ballon>();
    private int attribut;
    private static int nbTuile = 0;
    
    public Tuile(Paysage paysage)
    {
        this.paysage = paysage;
        attribut = ++nbTuile;
        if (attribut % 2 == 0)
            paysage.inversePaysage();
    }
}