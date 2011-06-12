import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Jeu
{

    /*********************/
    /***** ATTRIBUTS *****/
    /*********************/
    
    // le sac contient des cubes
    private ArrayList<Cube> sac = new ArrayList<Cube> ();
    // La pioche contient des cartes de Type Ballon
    private ArrayList<Ballon> pioche = new ArrayList<Ballon>();
    // La défausse contient des cartes de Type ballon
    private ArrayList<Ballon> defausse = new ArrayList<Ballon>();
    // Les cartes Trophées
    private ArrayList<Trophee> listeTrophee = new ArrayList<Trophee>();
    private ArrayList<Ballon> main = new ArrayList<Ballon>();
    
    private final int NBCARTE = 45;
    private final int NBCUBE = 45;
    private final int TAILLE_BALLON = 13;
    private final int NB_CARTE_PAR_JOUEUR = 8;
    
    private int[][] tabBallon = {   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },         // rouge
                                    { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },         // jaune
                                    { 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1 },         // vert
                                    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },         // bleu
                                    { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },         // gris
                                };

    /*********************************/
    /*** INITIALISATION DES LISTES ***/
    /*********************************/
    
    // pioche
    public void initialiserPioche()
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
    
    // sac
    public void initialiserSac()
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
    
    // les trophees
    public void initialiserListeTrophee()
    {
        listeTrophee.add( new Trophee(3, Couleur.GRIS.getLibelle()));
        listeTrophee.add( new Trophee(4, Couleur.BLEU.getLibelle()));
        listeTrophee.add( new Trophee(5, Couleur.VERT.getLibelle()));
        listeTrophee.add( new Trophee(6, Couleur.JAUNE.getLibelle()));
        listeTrophee.add( new Trophee(7, Couleur.ROUGE.getLibelle()));
    }
    
    public void initialiserMain()
    {
        for (int i = 1; i <= NB_CARTE_PAR_JOUEUR; ++i)
        {
            main.add (pioche.get(pioche.size() - i));
            pioche.remove(pioche.size() - i);
        }
    }
    
    /***************************/
    /*** MELANGES DES LISTES ***/
    /***************************/
    
    public void melangerPioche()
    {
        Collections.shuffle(pioche);
    }
    
    public void melangerSac()
    {
        Collections.shuffle(sac);
    }
    
    /***********/
    /** AUTRE **/
    /***********/
    
    public boolean defausseVersPioche()
    {
        if (!pioche.isEmpty())
            return false;
            
		pioche.addAll(defausse);
		defausse.clear();
		melangerPioche();
        
        return true;
    }
    
    public void afficherMenu()
	{
		System.out.println ( "\n\n\n" );
		System.out.println ( "*******************************" );
		System.out.println ( "**           MENU            **" );
		System.out.println ( "*******************************" );
		
		System.out.println ();
        
        System.out.println ( " 1. Regarder Vos Cartes "         );
        System.out.println ( " 2. Défausser "                   );
        System.out.println ( " 0. Quitter"                      );
        
        System.out.println ();
		System.out.print   ( "      votre choix : "            );
    }
    
   
    
    // pour test
    public void afficherPioche()    {   for (Ballon b: pioche)        System.out.println (b);    }
    public void afficherSac()       {   for (Cube c: sac)             System.out.println (c);    }
    public void listerTrophee()     {   for (Trophee t: listeTrophee)   System.out.println (t);  }
    public void afficherMain()      {   for (Ballon b: main)  System.out.println(b);             }
    
    
    /************/
    /*** MAIN ***/
    /************/
    
    public static void main (String[] args)
    {
        Jeu j = new Jeu();
		
        long before = System.currentTimeMillis();
		j.initialiserPioche();
        
        j.melangerPioche();
        
        
        j.initialiserSac();
        j.initialiserListeTrophee();
        j.initialiserMain();
        j.afficherMain();
        //j.listerTrophee();
        System.out.println( "\n Le temps d'éxecution (ms.) "+(System.currentTimeMillis() - before));
		
    }
}       