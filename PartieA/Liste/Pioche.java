package Projet.Liste;

import java.util.Collections;
import java.util.ArrayList;

import Projet.Couleur.*;
import Projet.Joueur.*;
import Projet.Carte.*;

public class Pioche
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Liste de Ballon
	*/
    private ArrayList<Ballon> pioche = new ArrayList<Ballon>();
	
	/**
	* Taille maximale du tableau
	*/
    private final int TAILLE_BALLON = 13;
    
	/**
	* Initialise les cartes de la Pioche
	* @param tabBallon
	*/
    public void initialiserPioche(int[][] tabBallon)
    {
        int j = 0;
        for (int i = 1; i <= TAILLE_BALLON; i++)
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
    * @param  joueur
    **/
    public void distribuerCarte(Joueur joueur)
    {
        joueur.distribuerCarte(pioche.get(pioche.size() - 1));
        pioche.remove(pioche.size() - 1);
    }
    
	/**
	* Melange les cartes contenue dans la pioche 
	*/
	public void melangerPioche()    {   Collections.shuffle(pioche);    }
	
	
	/**
	* Determine si la Pioche est vide 
	* @return true si la Pioche est vide 
	*/
    public boolean estVide()        
	{
		if(pioche.isEmpty())  	return true;     
		else     				return false; 
	}
	
	/**
	* Ajoute un Ballon a la Pioche
	* @param ballon
	*/
    public void ajouterElement(Ballon ballon)   {   pioche.add(ballon);    }
	
	public void supprimerCarte(int numero, String couleur)
	{
		for (int i = 0; i < pioche.size(); i++)
		{
			if (pioche.get(i).getCouleur().equals(couleur) && pioche.get(i).getNumero() == numero)
			{
				pioche.remove(i);
				break;
			}
		}
	}
			
}