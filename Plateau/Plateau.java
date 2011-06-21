package Projet.Plateau;

import java.util.ArrayList;

public class Plateau
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
	
    public void ajouterTuile(Tuile tuile)	{	plateau.add (tuile);	}
	
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