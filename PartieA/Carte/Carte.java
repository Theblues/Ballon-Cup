package Projet.Carte;

import Projet.Couleur.*;

public abstract class Carte
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Le numéro de la carte 
	* @see Carte#Carte(int, String)
	*/
    private int numero;
	
	/**
	* La couleur de la carte 
	* @see Carte#Carte ( int, String)
	*/
    private Couleur couleur;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	* Constructeur de la Classe Carte. Utilisé pour créer  des ballons dans la classe Ballon
	* @param numero
	*			numero de la carte
	* @param couleur
	*			couleur de la carte
	*/
    protected Carte(int numero, Couleur couleur)
    {
        this.numero = numero;
        this.couleur = couleur;
    }
    
	/**
	* Donne la valeur du ballon 
	* @return la valeur de la carte ballon
	*/
    public int getNumero()                  {   return numero;  }
	
	/**
	* Donne la couleur du ballon 
	* @return la couleur de la carte, sous forme de chaine caracteres
	*/
    public Couleur getCouleur()              {   return couleur; }
	
	/**
	* Permet de changer le numero de la carte
	* @param numero
	*/
    public void setNumero(int numero)       {   this.numero = numero;   }
	
	/**
	* Permet de changer la couleur de la carte
	* @param couleur
	*/
    public void setCouleur(Couleur couleur)  {   this.couleur = couleur; }
    
    /**
	* Affiche la carte avec sa couleur et sa valeur
	* @return la couleur et la valeur de la carte, sous forme de chaine de caracteres
	*/
    public String toString()
    {
		if(couleur.getLibelle().equals("Rouge"))
			return "\u001b[31m[" + numero + "]\u001b[0m";
		else if (couleur.getLibelle().equals("Jaune"))
			return "\u001b[33m[" + numero +  "]\u001b[0m";
		else if (couleur.getLibelle().equals("Vert"))
			return "\u001b[32m[" + numero +  "]\u001b[0m";
		else if (couleur.getLibelle().equals("Bleu"))
			return "\u001b[36m[" + numero +  "]\u001b[0m";
		else if (couleur.getLibelle().equals("Gris"))
			return "\u001b[37m[" + numero +  "]\u001b[0m";
			
        return "";
    }
}
    
    