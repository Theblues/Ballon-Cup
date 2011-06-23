package Projet.Liste;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import Projet.Carte.*;

public class Defausse implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* La Liste de Ballon 
	*/
    private ArrayList<Ballon> defausse = new ArrayList<Ballon>();
    
	/**
	* Retourne la liste de Ballon posés sur la Tuile
	* @return la liste de Ballon posés sur la Tuile 
	*/
    public ArrayList<Ballon> getDefausse()      {   return defausse;	    }
	
	/**
	* Ajoute un ballon a la liste 
	*/
    public void ajouterElement(Ballon ballon)   {   defausse.add(ballon);   }
	
	/**
	* Supprime tous les éléments de la liste
	*/
    public void supprimerTout()          		{  defausse.clear();    	}
}