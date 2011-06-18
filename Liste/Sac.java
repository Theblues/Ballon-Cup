package Projet.Liste;

import java.util.Collections;
import java.util.ArrayList;

import Projet.Cube.*;
import Projet.Couleur.*;
import Projet.Joueur.*;

public class Sac
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
    private ArrayList<Cube> sac = new ArrayList<Cube> ();
    private final int TAILLE_BALLON = 13;
    
    public void initialiserSac(int[][] tabBallon)
    {
        int j = 0;
        for (int i = 1; i <= TAILLE_BALLON; i++)
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
    
	public void melangerSac()    			{  Collections.shuffle(sac);    		}
	public Cube getDernierElement()    		{    return sac.get(sac.size() -1);    	}
	public void supprimerDernierElement()   {   sac.remove(sac.size()-1);   		}
	
	public void ajouterElement(int nbCube, Couleur couleur)
	{
		for ( int i = 0; i < nbCube; i++)
			sac.add (new Cube(couleur.getLibelle()));
	}
}