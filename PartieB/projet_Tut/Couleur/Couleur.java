package Projet.Couleur;
import java.io.*;

public enum Couleur implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	ROUGE   ( "Rouge"),
    JAUNE   ( "Jaune"),
	VERT    ( "Vert"),
	BLEU    ( "Bleu"),
	GRIS    ( "Gris");

	private String libelle;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
    Couleur (String libelle)
    {
        this.libelle = libelle;
    }
    
    public String getLibelle()  {   return libelle; }
}