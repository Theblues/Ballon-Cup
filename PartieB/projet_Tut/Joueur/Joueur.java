package Projet.Joueur;

import java.util.*;
import java.util.Collections;
import java.io.*;
import Projet.Carte.*;
import Projet.Cube.*;
import Projet.Couleur.*;

public class Joueur implements Serializable
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
	
	public void ajouterCube(Cube cube)          {   listeCube.add (cube);   }
	public void ajouterTrophee(Trophee trophee) {   listeTrophee.add (trophee);  }
	public String getNomJoueur()			{   return nomJoueur ;  }
	public void	  setNomJoueur(String s)	{	this.nomJoueur = s; }
	public String afficherCube()			
	{
		String s ="";
		
		for (Cube c: listeCube)	
			s += c + " | ";	
			
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
			//Si couleur Ok alors suppréssion du cube 
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