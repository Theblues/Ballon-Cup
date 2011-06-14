import java.util.Collections;
import java.util.ArrayList;

public class Pioche
{
    private ArrayList<Ballon> pioche = new ArrayList<Ballon>();
    private final int NB_CARTE_PAR_JOUEUR = 8;
    private final int TAILLE_BALLON = 13;
    
    public void initialiserPioche(int[][] tabBallon)
    {
        int j = 0;
        for (int i = 1; i <= TAILLE_BALLON; ++i)
        {
            for(Couleur coul : Couleur.values())
            {
                if(tabBallon[j][i-1] == 1)
                   pioche.add (new Ballon (i, coul.getLibelle()));
                j++;
            }
            j = 0;
        }
    }
    
    public void distribuerCarte(Joueur joueur)
    {
        joueur.distribuerCarte(pioche.get(pioche.size() - 1));
        pioche.remove(pioche.size() - 1);
    }
    
    public void melangerPioche()    {   Collections.shuffle(pioche);    }    
    public void afficherPioche()    {   for (Ballon b: pioche)        System.out.println (b);    }
}