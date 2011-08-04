package Projet.Cube;

public class Cube
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	* Couleur du cube
	*/
    private Couleur couleur;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	* Constructeur Cube
	* @param couleur
	*/
    public Cube(Couleur couleur)
    {
        this.couleur = couleur;
    }
    
	/**
	* retourne la couleur du Cube
	* @return la couleur du Cube, sous forme de chaine de caractères
	*/
    public Couleur getCouleur()  { return couleur;   }
	
	/**
	* Permet de modifier la couleur d'un Cube
	* @param couleur
	*/
    public void setCouleur(Couleur couleur)  {   this.couleur = couleur; }
    
    /**
	* Retourne un cube coloré, en fonction de l'attribut couleur
	* @return un Cube coloré 
	*/
    public String toString()
    {
        String s="";
		if(couleur.equals("Rouge"))
			return "\u001b[41m " + "\u001b[0m";
		else if (couleur.equals("Jaune"))
			return "\u001b[43m " + "\u001b[0m";
		else if (couleur.equals("Vert"))
			return "\u001b[42m " + "\u001b[0m";
		else if (couleur.equals("Bleu"))
			return "\u001b[46m " + "\u001b[0m";
		else if (couleur.equals("Gris"))
			return "\u001b[47m " + "\u001b[0m";

		return "";
    }
}