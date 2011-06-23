package Projet.Joueur;

import java.util.*;
import java.util.Collections;

import Projet.Carte.*;
import Projet.Cube.*;
import Projet.Couleur.*;

public class Joueur
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Liste de Troph�e
	*/
	private ArrayList<Trophee> listeTrophee 	= new ArrayList<Trophee> ();
	
	/**
	* Liste de Ballon 
	*/
	private ArrayList<Ballon> main 				= new ArrayList<Ballon>();
	
	/**
	* Liste de Cube 
	*/
	private ArrayList<Cube> listeCube 			= new ArrayList<Cube>();
	
	/**
	* Numero du Joueur 
	*/
	private int numJoueur;
	
	/**
	* Nombre de Joueur
	*/
	private static int nbJoueur = 0;
	
	/**
	* Nom du joueur
	*/
	private String nomJoueur;
	
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	* Constructeur Joueur
	* @param nomJoueur
	*/
	public Joueur( String nomJoueur ) 
	{   
		numJoueur = ++nbJoueur; 
		this.nomJoueur=nomJoueur;
	}
	
	/**
	* Retourne la liste de Cube du Joueur
	* @return la liste de Cube 
	*/
	public ArrayList<Cube> getListeCube() { return listeCube ; }
	
	/**
	* Distribue une Carte au joueur
	*/
	public void distribuerCarte(Ballon ballon)  {   main.add (ballon);    }
	
	/**
	* Affiche les Cartes distribu�es
	* @return  les Cartes distribu�es
	*/
	public String afficherMain()                  
	{ 
		String s="";
		int i=1;
		for (Ballon b: main)    
		{
			s += i + "= " + b.toString() + " | " ;
			i++;
		}
	return s;
	}
	
	/**
	* Affiche les Troph�e gagn�s par un joueur
	* @return les Troph�e Gagn�s par un joueur
	*/
	public String afficherTrophee()			  
	{	
		String s ="";
		
		if ( listeTrophee.isEmpty() )
			s += "\nAucun Trophees\n";
		else
		{
			for (Trophee t : listeTrophee )  
				s += t.toString(); 
		}
	return s;
	}
	
	/**
	* Ajoute un Cube a la liste de cube
	* @param cube
	*/
	public void ajouterCube(Cube cube)          {   listeCube.add (cube);   }
	
	/**
	* Ajoute un Troph�e a la liste de Trophhe du joueur
	* @param trophee
	*/
	public void ajouterTrophee(Trophee trophee) {   listeTrophee.add (trophee);  }
	
	/**
	* Retourne le nom du joueur
	* @return lme nom du joueur
	*/
	public String getNomJoueur()			{   return nomJoueur ;  }
	
	/**
	* Retourne la liste de Cube d'un joueur
	* @return la liste de Cube d'un joueur
	*/
	public String afficherCube()			
	{
		String s ="";
		
		for (Cube c: listeCube)	
			s += c + " | ";	
			
	return s;
	}
	
	/**
	* Retourne le Ballon de l'indice choisi
	* @param choix
	* @return le Ballon de l'indice choisi
	*/
	public Ballon getBallon(int choix)    {  return main.get(choix);    }
	
	/**
	* Supprime le Ballon qui se trouve a l'indice choisi
	* @param choix
	*/
	public void supprimerBallon(int choix)  {  main.remove(choix);      }
	
	/**
	* Retourne le nombre Troph�e du Joueur
	* @return le nombre de Troph�e du Joueur
	*/
	public int getTrophee()          {  return listeTrophee.size();  }
    
	/**
	* Retourne le nombre de Cube que poss�de le Joueur
	* @param couleur
	* @return le nombre de Cube que poss�de le Joueur
	*/
	public int getNbCube(Couleur couleur)
	{
        int nbCube = 0;
        for (Cube c: listeCube)
            if (couleur.getLibelle().equals(c.getCouleur()))
                ++nbCube;

        return nbCube;            
	}
	
	/**
	* Supprime les Cube utilis�s pour achet� un troph�e
	* @param Couleur
	*/
	public void supprimerCube(String couleur)
	{
		int cpt = 0;

		//Permet de selectionner le nbs de cubes a retirer 
		if ( couleur.equals("Gris"))  { cpt = 3 ; }
		if ( couleur.equals("Bleu"))  { cpt = 4 ; }
		if ( couleur.equals("Vert"))  { cpt = 5 ; }
		if ( couleur.equals("Jaune")) { cpt = 6 ; }
		if ( couleur.equals("Rouge")) { cpt = 7 ; }
		
		int ok = 0;
		int i  = 0;
		
		//Parcour la liste de cube
		while ( i < listeCube.size() && ok < cpt )
		{
			//Si couleur Ok alors suppr�ssion du cube 
			if (couleur.equals(listeCube.get(i).getCouleur()))
			{
				listeCube.remove(i);
				ok++;
				i--;
			}
			System.out.println(i + "\t" + cpt);

		i++;
		}
	}
	
	/** 
	* Retourne La main, les Cube et Les Troph�e du Joueur
	* @return La main, les Cube et Les Troph�e du Joueur
	*/
	public String toString()
	{
		String s ="";
		
		s += "Votre Main : \n\n";
		s += afficherMain();
		
		s += "\n\nVos Cubes : ";
		if ( listeCube.isEmpty() )
			s += "0";
		else
			s += afficherCube();

			
		s += "\n\nVos Trophees : ";
		s += afficherTrophee();
		s += "\n";
	
	return s;
	
	}
}