package Projet.Carte;
import java.io.*;

public class Trophee extends Carte implements Serializable
{
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	* Constructeur de Trophee 
	* @param num
	* @param couleur
	* @see Carte
	*/
    public Trophee (int num, String couleur)
    {
        super(num, couleur);
    }
    
	/**
	* Donne la couleur du trophee
	* @return la couleur du trophee, sous forme de chaine de caracteres
	*/
	public String getCouleur()	{	return super.getCouleur();	}
	
	/**
	* Donne la valeur du Trophee
	* @return la valeur de la carte trophee, sous forme d'entier
	* @see Carte
	*/
	public int getNumero()			{	return super.getNumero();		}
	
    /**
	* Affiche la carte avec sa couleur et sa valeur
	* @return la couleur et la valeur de la carte, sous forme de chaine de caracteres
	* @see Carte
	*/
    public String toString()
    {
        return "T" + super.toString() + " | ";
    }
}

