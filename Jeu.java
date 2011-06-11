import java.util.ArrayList;
import java.util.Collections;

public class Jeu
{
    private static ArrayList<Cube> sac = new ArrayList<Cube> ();
    private static ArrayList<Ballon> pioche = new ArrayList<Ballon>();
    private static ArrayList<Ballon> defausse = new ArrayList<Ballon>();
    private static ArrayList<Ballon> main = new ArrayList<Ballon>();
    
    private static final int NBCARTE = 45;
    private static final int NBCUBE = 45;
    
    private static int[][] tabBallon = {   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },         // rouge
                                           { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },         // jaune
                                           { 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1 },         // vert
                                           { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },         // bleu
                                           { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },         // gris
                                       };
                               
                               
    // on initialise la pioche dans l'ordre
    public static void initialiserPioche()
    {
        for (int i = 1; i <= tabBallon.length;  i++)
            if ( tabBallon[i-1][0] == 1)
                pioche.add (new Ballon (i, "Rouge"));
        
        for (int i = 1; i <= tabBallon.length;  i++)
            if ( tabBallon[i-1][1] == 1)
                pioche.add (new Ballon (i, "Jaune"));
                
        for (int i = 1; i <= tabBallon.length;  i++)
            if ( tabBallon[i-1][2] == 1)
                pioche.add (new Ballon (i, "Vert"));

        for (int i = 1; i <= tabBallon.length;  i++)
            if ( tabBallon[i-1][3] == 1)
                pioche.add (new Ballon (i, "Bleu"));

        for (int i = 1; i <= tabBallon.length;  i++)
            if ( tabBallon[i-1][4] == 1)
                pioche.add (new Ballon (i, "Gris"));                
    }
    
    public static void melangerPioche()
    {
        Collections.shuffle(pioche);
    }
    
    public static void donnerCarte()
    {
        /*
        for (int i = 0, int j = pioche.size()-1 ; i < 8; ++i, --j)
        {
            main.add (pioche
            */
    }
    
    public static void initialiserSac()
    {
        for (int i = 0; i < 13; ++i)
            sac.add (new Cube ("Rouge"));
        
        for (int i = 0; i < 11; ++i)
            sac.add (new Cube ("Jaune"));
            
        for (int i = 0; i < 9; ++i)
            sac.add (new Cube ("Vert"));
            
        for (int i = 0; i < 7; ++i)
            sac.add (new Cube ("Bleu"));
            
        for (int i = 0; i < 7; ++i)
            sac.add (new Cube ("Gris"));
            
    }
    
    public static void melangerSac()
    {
        Collections.shuffle(sac);
    }
    
    public static void defausseVersPioche()
    {
        //pioche = defausse.clone(); bug
        defausse.clear();
    }
    
    public static void main (String[] args)
    {
        initialiserPioche();
        for ( Ballon b: pioche ) System.out.println (b); // manque le toString xD
    }
}       