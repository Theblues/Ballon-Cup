package Projet.Carte;
import java.io.*;

public class Trophee extends Carte implements Serializable
{
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
    public Trophee (int num, String couleur)
    {
        super(num, couleur);
    }
    
	public String getCouleur()	{	return super.getCouleur();	}
	public int getNumero()			{	return super.getNumero();		}
	
    // modifiable comme vous le souhaitez
    public String toString()
    {
        return "T" + super.toString() + " | ";
    }
}

