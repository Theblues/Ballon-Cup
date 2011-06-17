package Projet.Joueur;

import java.util.*;
import java.util.Collections;

import Projet.Carte.*;
import Projet.Cube.*;
import Projet.Couleur.*;

public class Joueur
{
	private ArrayList<Trophee> listeTrophee = new ArrayList<Trophee> ();
	private ArrayList<Ballon> main = new ArrayList<Ballon>();
	private ArrayList<Cube> listeCube = new ArrayList<Cube>();
	private int numJoueur;
	private static int nbJoueur = 0;
	private String nomJoueur;

	public Joueur( String nomJoueur ) 
	{   
		numJoueur = ++nbJoueur; 
		this.nomJoueur=nomJoueur;
	}
	
	public void distribuerCarte(Ballon ballon)  {   main.add (ballon);    }
	public void afficherMain()                  {   for (Ballon b: main)    System.out.println(b);  }
	
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
	public void afficherCube()			{	for (Cube c: listeCube)	System.out.println(c);	}
	
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
	
	public void supprimerCube(Couleur couleur)
	{
		int cpt = 0;
		for (Cube c: listeCube)
		{
			if (couleur.getLibelle().equals(c.getCouleur()))
				listeCube.remove(cpt);

			++cpt;
		}
	}
}