package Projet.Plateau;
import java.io.*;

import java.util.ArrayList;
import Projet.Carte.*;
import Projet.Cube.*;

/**
* @author Erwan Lebrun
* @author Jeremy Lebair
* @author Sarah Vernichon
* @version 1.0
*/
public class Tuile implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Le Paysage de la Tuile 
	* @see Tuile#Tuile(Paysage)
	*/
	private Paysage paysage;
	
	/**
	* Liste des cartes jouées du coté gauche 
	*/
	private ArrayList<Ballon> JeuGauche = new ArrayList<Ballon>();
	
	/**
	* Liste des cartes jouées du coté droit
	*/
	private ArrayList<Ballon> JeuDroite = new ArrayList<Ballon>();
	
	/**
	* Nombre de Cube sur la Tuile
	* @see Tuile#getAttribut()
	*/
	private int attribut;
	
	/**
	* Nombre de Tuiles crées
	*/
	private static int nbTuile = 0;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	
	/**
	* Constructeur Tuile 
	* @param paysage 
	*/
	public Tuile(Paysage paysage)
	{
		this.paysage = paysage;
		attribut = ++nbTuile;
	}
    
	/**
	* Ajoute un Ballon a JeuGauche ou JeuDroite en fonction du coté choisi
	* @param ballon
	* @param cote
	*/
	public void ajouterBallon(Ballon ballon, char cote)
	{
		if (cote == 'G')	JeuGauche.add(ballon);
		else				JeuDroite.add(ballon);
	}
    
	/**
    * Ajoute un cube sur un paysage
	* @param cube
	*/
	public void ajouterCubeSurPaysage(Cube cube)	{	paysage.ajouterCube(cube);	}
	
	public boolean estEntierementPleine()
	{
		if ( JeuGauche.size() == attribut  && JeuDroite.size() == attribut)
			return true;

		return false;
	}

	/**
	* Retourne la taille de la liste en fonction du coté choisi
	* @param cote
	* @return La taille de la liste
	*/
	public int getElementJeu (char cote)
	{
		if (cote == 'G')	return JeuGauche.size();
		else				return JeuDroite.size();
	}
    
	/**
	* Retourne le paysage qu'il y a sur la carte 
	* @return Le paysage de la carte
	*/
	public Paysage getPaysage()     {   return paysage;     }
	
	/**
	* Retourne le nombre de cube de la tuile
	* @return  le nombre de cube sur la tuile 
	*/
	public int getAttribut()        {   return attribut;    }
	
	/**
	* Determine si JeuGauche ou Jeudroit est vide
	* @return true si la JeuGauche ou JeuDroit n'est pas vide 
	*/
	public boolean aUneCarte()
	{
		if (JeuGauche.size() == 0 || JeuDroite.size() == 0)
			return false;
		
		return true;
	}
	
	/**
	* Détermine s'il est encore possible de poser une carte a coté de la tuile  
	* @param cote
	* @return true 
	*/
	public boolean estPleine (char cote)
	{
		if (cote == 'G')
			if (JeuGauche.size() == attribut)
				return true;
		
		if (cote == 'D')
			if (JeuDroite.size() == attribut)
				return true;
		
		return false;
	}
	
	/**
	* Affiche le résultat des carte de chaque coté de la Tuile
	* @param cote 
	* @return le résultat des Carte de chaque coté de la Tuile
	*/
	public int getResultat(char cote)
	{
		int resultat = 0;
		if (cote == 'D')
			for (Ballon b: JeuDroite)
				resultat += b.getNumero();
		else if (cote == 'G')
			for (Ballon b: JeuGauche)
				resultat += b.getNumero();
				
		return resultat;
	}
	
	/**
	* Affiche chaque tuile
	* @return Le contenu des ArrayList JeuGauche, JeuDroit et le paysage
	*/
	public String toString()
	{

		String s = "" ;

			if ( attribut == 1 )
				s = "                ";
			if ( attribut == 2 )
				s = "            ";
			if ( attribut == 3 )
				s = "        ";
			if ( attribut == 4 )
				s = "    ";
				
			
			for (int i =0; i < attribut; i++ )
				s += afficherCote(JeuGauche, i);

			s +=  paysage.getRecto() + " ";
			
			for (int i =0; i < attribut ; i++ )
				s += afficherCote(JeuDroite, i);
		
		
	return s ;
	}
	
	/**
	* Affiche les Cartes a coté des Tuiles 
	* @param arr
	* 		ArrayList de Ballon
	* @param ind
	* @return 
	*/
	public String afficherCote( ArrayList<Ballon> arr, int ind )
	{
		String s="";
		
		if ( arr.isEmpty() || arr.size() <= ind  )
		
			s += "[?] ";

		else
		{
		
			s += " ";
			s += arr.get(ind).toString();
			s += " ";
		}
		
	return s;
	}
	
	/**
	* Retourne un ballon contenu dans une ArrayList a l'indice et au cote choisi
	* @param cote
	* @param num
	* @return le ballon contenu a l'indice et au cote choisi
	*/
	public Ballon getBallon (int num,char cote)
	{
		if (cote == 'D')
			return JeuDroite.get(num);
		else
			return JeuGauche.get(num);
	}
	
	/**
	* Supprime une carte de l'ArrayList du coté choisit 
	* @param num
	* @param cote
	*/
	public void supprimerCarte(int num, char cote)
	{
		if (cote == 'D')
			JeuDroite.remove(num);
		else
			JeuGauche.remove(num);
	}
	
	/**
	* Supprime toures les cartes des ArrayList JeuDroit et JeuGauche
	*/
	public void supprimerToutesLesCartes()
	{
		JeuDroite.clear();
		JeuGauche.clear();
	}
	
	public ArrayList<Ballon>  getJeuGauche() { return JeuGauche ; }
	public ArrayList<Ballon>  getJeuDroite() { return JeuDroite ; }
}