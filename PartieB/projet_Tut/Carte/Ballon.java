package Projet.Carte;

import java.io.*;

public class Ballon extends Carte implements Serializable
{
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
    public Ballon (int num, String couleur)
    {
        super(num, couleur);
    }
    
    // modifiable comme vous le souhaitez
    public String toString()
    {
        return super.toString();
    }
    
	public String getCouleur()  {  return super.getCouleur();  }

	public int getNumero()      {  return super.getNumero();    }
}