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
	
	/**
	* Liste de Cube
	*/
    private ArrayList<Cube> sac = new ArrayList<Cube> ();
	
	/**
	* Taille maximale du tableau
	*/
    private final int TAILLE_BALLON = 13;
    
	/**
	* Initiale les Cube dans le Sac
	* @param tabBallon
	*/
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
    
	/**
	* Mélange les Cube contenus dans le Sac
	*/
	public void melangerSac()    			{  Collections.shuffle(sac);    		}
	
	/**
	* Retourne le dernier éléments du sac
	* @return de dernier Cube du Sac
	*/
	public Cube getDernierElement()    		{    return sac.get(sac.size() -1);    	}
	
	/**
	* Supprime le dernier Cube  de la liste de Sac
	*/
	public void supprimerDernierElement()   {   sac.remove(sac.size()-1);   		}
	
	
	/**
	* Ajoute un Cube a la Liste Sac
	* @param nbCube
	* @param couleur
	*/
	public void ajouterElement(int nbCube, Couleur couleur)
	{
		for ( int i = 0; i < nbCube; i++)
			sac.add (new Cube(couleur.getLibelle()));
	}
}