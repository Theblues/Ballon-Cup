package Projet.Carte;

public class Ballon extends Carte
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