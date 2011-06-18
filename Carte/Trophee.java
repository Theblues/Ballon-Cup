package Projet.Carte;

public class Trophee extends Carte
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
        return "T" + super.toString();
    }
}

