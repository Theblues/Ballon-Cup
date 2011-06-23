package Projet.Plateau;

import Projet.Cube.*;
import java.util.ArrayList;

/**
* @author Erwan Lebrun
* @author Jeremy Lebair
* @author Sarah Vernichon
* @version 1.0
*/
public class Paysage
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Le nom du Paysage au recto
	*/
	
    private String recto;
	/**
	* Le nom du Paysage au verso
	*/
    private String verso;
	
	/**
	* Liste de Cube sur le Paysage
	*/
    private ArrayList<Cube> listeCube = new ArrayList<Cube> ();
	
	/**
	* Liste de Cube qui ont �t� jou� par une carte pos�e a Gauche
	*/
	private ArrayList<Cube> listeCubeUtiliseAGauche = new ArrayList<Cube>();
	
	/**
	* Liste de Cube qui ont �t� jou� par une carte pos�e a Droite
	*/
	private ArrayList<Cube> listeCubeUtiliseADroite = new ArrayList<Cube>();
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	*Constructeur Paysage
	* @param recto
	* @param verso
	*/
    public Paysage(String recto, String verso)
    {
        this.recto = recto;
        this.verso = verso;
    }
	
	/*****************/
	/*** ACCESSEUR ***/
	/*****************/
	
	/**
	* Retourne le nom du Paysage au recto
	* @return le nom du Paysage au recto
	*/
	public String getRecto()    			{   return recto;   }
	
	/**
	* Retourne le nom du Paysage au verso
	* @return le nom du Paysage au verso
	*/
    public String getVerso()    			{   return verso;   }
	
	/**
	* Retourne le dernier Cube de la Liste
	* @return le dernier Cube de la Liste
	*/
	public Cube getDernierElement()        	{   return listeCube.get(listeCube.size()-1);   }
	
	/**
	* Retourne un Cube a un indice choisi
	* @param nb 
	* @return le Cube a l'indice choisi
	*/
	public Cube getElement(int nb)			{	return listeCube.get(nb);	}
	
	/**
	* Ajoute un Cube a une des listes de listeCubeUtilise en fonction du cot� choisi
	* @param cube
	* @param choixCote 
	*/
	public void ajouterCubeUtilise(Cube cube, char choixCote)	
	{
		if (choixCote == 'G')
			listeCubeUtiliseAGauche.add(cube);
		
		if (choixCote == 'D')
			listeCubeUtiliseADroite.add(cube);
	}
	
	/**
	* D�termine si un cube choisi est utilis�
	* @param cote
	* @param cube
	* @return true si le cube choisi est utilis�
	*/
	public boolean estUtilise(Cube cube, char cote)
	{
		if (cote == 'G')
			// on parcours la liste des cubes utilises
			for (int i = 0; i < listeCubeUtiliseAGauche.size(); i++)
				// si le cube est egaux a celui dans la liste des cubes utilises
				if (cube.equals(listeCubeUtiliseAGauche.get(i)))
					// si le nombre de cube par couleur est atteint
					if (nbCubeDansListeCube(cube.getCouleur()) == nbCubeDansListeCubeUtilise(cube.getCouleur(), cote))
						return true;

		if (cote == 'D')
			for (int i = 0; i < listeCubeUtiliseADroite.size(); i++)
				if (cube.equals(listeCubeUtiliseADroite.get(i)))
					if (nbCubeDansListeCube(cube.getCouleur()) == nbCubeDansListeCubeUtilise(cube.getCouleur(), cote))
						return true;
					
		return false;
	}

	/**
	* Retourne le nombre d'�l�ments qu'il y a dans la liste 
	* @param couleur
	* @return le nombre d'�l�ments qu'il y a dans la liste
	*/
	public int nbCubeDansListeCube(String couleur)
	{
		int cpt = 0;
		for (int i = 0; i < listeCube.size(); i++)
			if (couleur.equals(getElement(i).getCouleur()))
				cpt++;
				
		return cpt;
	}
	
	/**
	* Retourne le nombre de Cube contenus dans la liste de Cube utilis� 
	* @param cote
	* @param couleur 
	* @return Le nombre d'�l�ments contenus dans la liste de Cube utilis�
	*/
	public int nbCubeDansListeCubeUtilise(String couleur, char cote)
	{
		int cpt = 0;
		if (cote == 'G')
			for (int i = 0;	i < listeCubeUtiliseAGauche.size(); i++)
				if (couleur.equals(listeCubeUtiliseAGauche.get(i).getCouleur()))
					cpt++;
		
		if (cote == 'D')
			for (int i = 0;	i < listeCubeUtiliseADroite.size(); i++)
				if (couleur.equals(listeCubeUtiliseADroite.get(i).getCouleur()))
					cpt++;
					
		return cpt;
	}
	
    /**
	* Ajoute un Cube a la liste de Cube
	* @param Cube
	*/
    public void ajouterCube(Cube cube)   	{   listeCube.add(cube);   }
    
	/**
	* Supprime le dernier �l�ment donn� de la liste de Cube 
	*/
    public void supprimerDernierElement()  	{	listeCube.remove(listeCube.size()-1);   }
	
	/**
	* Supprime un �l�ment choisi
	* @param i 
	*/
	public void supprimerElement(int i)		{	listeCube.remove(i);	}
	
	/**
	* Supprime tous les �lements de la liste de Cube
	*/
	public void supprimerTousLesElements()	{	listeCube.clear();	}
	
	/**
	* Supprime les cartes plac�es a cot� des Tuile
	*/
	public void supprimerCubeDejaUtilise()  {	listeCubeUtiliseAGauche.clear();	listeCubeUtiliseADroite.clear();	}
	
	
	/**
	* Inverse les paysages d'une Tuile
	*/
	public void inverserPaysage()
	{
		String s = recto;
		recto = verso;
		verso = s;
	}
    
}