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
	
	private ArrayList<Trophee> listeTrophee 	= new ArrayList<Trophee> ();
	private ArrayList<Ballon> main 				= new ArrayList<Ballon>();
	private ArrayList<Cube> listeCube 			= new ArrayList<Cube>();
	
	private int numJoueur;
	private static int nbJoueur = 0;
	private String nomJoueur;
	
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	public Joueur( String nomJoueur ) 
	{   
		numJoueur = ++nbJoueur; 
		this.nomJoueur=nomJoueur;
	}
	
	public ArrayList<Cube> getListeCube() { return listeCube ; }
	
	public void distribuerCarte(Ballon ballon)  {   main.add (ballon);    }
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
	
	public void afficherTrophee()			  
	{	
		if ( listeTrophee.isEmpty() )
			System.out.println("\nAucun Trophees\n");
		else
		{
			for (Trophee t : listeTrophee )  
				System.out.println(t); 
		}
	}
	
	public void ajouterCube(Cube cube)          {   listeCube.add (cube);   }
	public void ajouterTrophee(Trophee trophee) {   listeTrophee.add (trophee);  }
	public String getNomJoueur()			{   return nomJoueur ;  }
	public String afficherCube()			
	{
		String s ="";
		
		for (Cube c: listeCube)	
			s += c;	
			
	return s;
	}
	
	public Ballon getBallon(int choix)    {  return main.get(choix);    }
	public void supprimerBallon(int choix)  {  main.remove(choix);      }
	public int getTrophee()          {  return listeTrophee.size();  }
    
	public int getNbCube(Couleur couleur)
	{
        int nbCube = 0;
        for (Cube c: listeCube)
            if (couleur.getLibelle().equals(c.getCouleur()))
                ++nbCube;

        return nbCube;            
	}
	
	public void supprimerCube(String couleur)
	{
		int cpt = 0;
		for (Cube c: listeCube)
		{
			if (couleur.equals(c.getCouleur()))
				listeCube.remove(cpt);

			++cpt;
		}
	}
	
	public String toString()
	{
		String s ="";
		
		s += "Votre Main : \n\n";
		s += afficherMain();
		
		s += "\n\nVos Cubes : ";
		if ( listeCube.isEmpty() )
			s += "0";
		else
		{
			s += afficherCube();
		}
			
		s += "\n\nVos Trophees : ";
		s += getTrophee();
		s += "\n";
	
	return s;
	
	}
}