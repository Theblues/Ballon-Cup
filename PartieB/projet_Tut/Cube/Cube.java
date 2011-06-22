package Projet.Cube;
import java.io.*;

public class Cube implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
    private String couleur;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
    public Cube(String couleur)
    {
        this.couleur = couleur;
    }
    
    public String getCouleur()  { return couleur;   }
    public void setCouleur(String couleur)  {   this.couleur = couleur; }
    
    // modifiable comme vous le souhaitez
    public String toString()
    {
        String s="";
		if(couleur == "Rouge")
			return "\u001b[41m " + "\u001b[0m";
		else if (couleur == "Jaune")
			return "\u001b[43m " + "\u001b[0m";
		else if (couleur == "Vert")
			return "\u001b[42m " + "\u001b[0m";
		else if (couleur == "Bleu")
			return "\u001b[46m " + "\u001b[0m";
		else if (couleur == "Gris")
			return "\u001b[47m " + "\u001b[0m";

		return "";
    }
}