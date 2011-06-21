package Projet.Couleur;

public enum Couleur
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