import java.util.Collections;
import java.util.ArrayList;

public class Sac
{
    private ArrayList<Cube> sac = new ArrayList<Cube> ();
    private final int TAILLE_BALLON = 13;
    
    public void initialiserSac(int[][] tabBallon)
    {
        int j = 0;
        for (int i = 1; i <= TAILLE_BALLON; ++i)
        {
            for(Couleur coul : Couleur.values())
            {
                if(tabBallon[j][i-1] == 1)
                   sac.add (new Cube (coul.getLibelle()));
                j++;
            }
            j = 0;
        }
    }
    
    public void melangerSac()    {  Collections.shuffle(sac);    }
}