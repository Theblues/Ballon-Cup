package Projet.Couleur;
import java.io.*;

public enum Couleur implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	/**
	*L'énumération des couleurs
	*/
	ROUGE   ( "Rouge"),
    JAUNE   ( "Jaune"),
	VERT    ( "Vert"),
	BLEU    ( "Bleu"),
	GRIS    ( "Gris");

	/**
	* Nom de la couleur
	*/
	private String libelle;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	* Constructeur de Couleur
	* @param libelle
	*/
    Couleur (String libelle)
    {
        this.libelle = libelle;
    }
    
	/**
	* Retourne la couleur du libelle 
	* @return la couleur sous forme de chaine de caractere
	*/
    public String getLibelle()  {   return libelle; }
}