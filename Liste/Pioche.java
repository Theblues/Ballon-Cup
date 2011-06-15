package Projet.Liste;

import java.util.Collections;
import java.util.ArrayList;

import Projet.Couleur.*;
import Projet.Joueur.*;
import Projet.Carte.*;

public class Pioche
{
    private ArrayList<Ballon> pioche = new ArrayList<Ballon>();
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
    
    /**
    * Methode qui distribue les cartes au joueur et supprime cette carte de la pioche.
    * @param : joueur
    **/
    public void distribuerCarte(Joueur joueur)
    {
        joueur.distribuerCarte(pioche.get(pioche.size() - 1));
        pioche.remove(pioche.size() - 1);
    }
    
	public void melangerPioche()    {   Collections.shuffle(pioche);    }
	public void afficherPioche()    
	{   
		int cpt=0;
		for (Ballon b: pioche) 
		{
			System.out.println (b); 
			cpt++;
		}
		System.out.println("\nNombre de cartes : " + cpt );
	}
    public boolean estVide()        {   if(pioche.isEmpty())  return true;     else     return false; }
    public void ajouterElement(Ballon ballon)   {   pioche.add(ballon);    }
}