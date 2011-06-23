package Projet.Plateau;

import java.io.*;
import java.util.ArrayList;

/**
* @author Erwan Lebrun
* @author Jeremy Lebair
* @author Sarah Vernichon
* @version 1.0
*/
public class Plateau implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
    private ArrayList<Tuile> plateau 		= new ArrayList<Tuile>();
    private int choix;
    
    /****************/
	/** ACCESSEURS **/
	/****************/
    
	public void choixTuile(int choix)   	{	this.choix = choix;         }
	public Tuile getTuile(int choix)     	{   return plateau.get(choix);  }
	public ArrayList<Tuile> getPlateau()	{	return plateau; 			}
	
	/********************/
	/** AJOUT DE TUILE **/
	/********************/
	
	/**
	* Ajoute une Tuile au Plateau
	*/ 
    public void ajouterTuile(Tuile tuile)	{	plateau.add (tuile);	}

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