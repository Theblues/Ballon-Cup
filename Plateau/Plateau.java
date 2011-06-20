package Projet.Plateau;

import java.util.ArrayList;

public class Plateau
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
    private ArrayList<Tuile> plateau 		= new ArrayList<Tuile>();
	private ArrayList<Tuile> tuileUtilisee 	= new ArrayList<Tuile>();
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
	public void ajouterTuileUtilise(Tuile tuile)	{	tuileUtilisee.add(tuile);	}

	public boolean estTuileUtilise(Tuile tuile)
	{
		for (int i = 0; i < tuileUtilisee.size(); i++)
		{
			Tuile tuileDejaUtilise = tuileUtilisee.get(i);
			if (tuileDejaUtilise.equals(tuile))
				return true;
		}
		return false;
	}
	
	public String toString()
	{
		String  s ="";
		
		//affichage plateaus
		s += "Plateau de Jeu : \n\n";
		
		for (Tuile t: plateau)
		{
			s += t.toString();
			s += "\n\n";
			s += "            Cubes : ";
			
			//affichage cubes
			if (!estTuileUtilise(t))
			{
				for(int i = 0; i < t.getAttribut();	i++)
				{
					s += t.getPaysage().getElement(i);
					s += " ";
				}
			}
			s += "\n\n";
		}
			
		return s;
	}
}