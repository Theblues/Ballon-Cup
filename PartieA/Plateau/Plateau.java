package Projet.Plateau;

import java.util.ArrayList;

/**
* @author Erwan Lebrun
* @author Jeremy Lebair
* @author Sarah Vernichon
* @version 1.0
*/
public class Plateau
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Liste de Tuile 
	*/
    private ArrayList<Tuile> plateau 		= new ArrayList<Tuile>();
	
	/**
	* Liste des tuiles supprimées
	*/
	private ArrayList<Tuile> tuileSupprimee = new ArrayList<Tuile>();
	
	/**
	* Position de la Tuile
	*/
    private int choix;
    
    /****************/
	/** ACCESSEURS **/
	/****************/
    
	/**
	* Permet de choisir une Tuile
	* @param choix
	*/
	public void choixTuile(int choix)   	{	this.choix = choix;         }
	
	/**
	* Retourne une Tuile a l'indice choisi
	* @param choix
	* @return une Tuile a l'indice choisi
	*/
	public Tuile getTuile(int choix)     	{   return plateau.get(choix);  }
	
	/**
	* Retourne la liste de Tuile sur le Plateau
	* @return la liste de Tuile sur le Plateau
	*/
	public ArrayList<Tuile> getPlateau()	{	return plateau; 			}
	
	/********************/
	/** AJOUT DE TUILE **/
	/********************/
	
	/**
	* Ajoute une Tuile au Plateau
	* @param tuile
	*/ 
    public void ajouterTuile(Tuile tuile)	{	plateau.add (tuile);	}
	
	/**
	* Supprime une tuile du plateau
	* @param numTuile
	*/
	public void supprimerTuile(int numTuile)
	{
		System.out.println(plateau.get(numTuile));
		tuileSupprimee.add (plateau.get(numTuile));
		plateau.remove(numTuile);
	}
	
	/**
	* Retourne la taille du plateau
	* @return la taille du plateau
	*/
	public int getTaille()
	{
		return plateau.size();
	}
	
	/**
	* Ajoute une tuile a la liste des tuiles manquantes
	* @param tuile
	* @param num
	*/	
	public void ajouterTuileManquanteSurPlateau(Tuile tuile, int num)
	{
		plateau.add(num, tuile);
	}
	
	/**
	* Retourne la premiereTuileManquante
	* @return la premiereTuileManquante
	*/
	public Tuile getPremiereTuileManquante()
	{
		return tuileSupprimee.get(0);
	}
	
	/**
	* Supprime la premiere Tuile de la liste des tuiles manquantes
	*/
	public void supprimerPremiereTuileManquante()
	{
		tuileSupprimee.remove(0);
	}

	/**
	* Affiche le Plateau et les Cube sur le plateau 
	* @return le Plateau et les Cube sur le plateau 
	*/
	public String toString()
	{
		String  s ="";
		
		//affichage plateaus
		s += "Plateau de Jeu : \n\n";
		
		for (Tuile t: plateau)
		{
			s += t.toString();
			s += "\n\n";
			s += "                    ";
			
			//affichage cubes
			if ( t.getAttribut() == 1 )
					s += "   ";
			if ( t.getAttribut() == 2 )
					s += "  ";
			if ( t.getAttribut() == 3 )
					s += " ";
				
			for(int i = 0; i < t.getAttribut();	i++)
			{	
				s += t.getPaysage().getElement(i);
				s += " ";
			}
			s += "\n\n";
		}
			
		return s;
	}
}