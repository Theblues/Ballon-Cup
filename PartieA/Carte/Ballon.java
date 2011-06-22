package Projet.Carte;

public class Ballon extends Carte
{
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	/**
	* Constructeur de la classe Ballon
	* @param num
	* @param couleur
	* @see Carte
	*/
    public Ballon (int num, String couleur)
    {
        super(num, couleur);
    }
    
	/**
	* Affiche la carte avec sa couleur et sa valeur, il utilise le toString de la classe Carte
	* @return la couleur et la valeur de la carte
	* @see Carte
	*/
    public String toString()
    {
        return super.toString();
    }
    
	/**
	* Donne la couleur du ballon 
	* @return la couleur du ballon, sous forme de chaine de caractere
	* @see Carte
	*/
	public String getCouleur()  {  return super.getCouleur();  }

	/**
	* Donne la valeur du ballon 
	* @return la valeur de la carte ballon, sous forme d'entier
	* @see Carte
	*/
	public int getNumero()      {  return super.getNumero();    }
}