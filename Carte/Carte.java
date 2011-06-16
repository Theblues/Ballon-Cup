package Projet.Carte;

public abstract class Carte
{
    private int numero;
    private String couleur;
    
    protected Carte(int numero, String couleur)
    {
        this.numero = numero;
        this.couleur = couleur;
    }
    
    public int getNumero()                  {   return numero;  }
    public String getCouleur()              {   return couleur; }
    public void setNumero(int numero)       {   this.numero = numero;   }
    public void setCouleur(String couleur)  {   this.couleur = couleur; }
    
    // modifiable comme vous le souhaitez
    public String toString()
    {
		if(couleur == "Rouge")
			return "\u001b[31m[" + numero + "]\u001b[0m";
		else if (couleur == "Jaune")
			return "\u001b[33m[" + numero +  "]\u001b[0m";
		else if (couleur == "Vert")
			return "\u001b[32m[" + numero +  "]\u001b[0m";
		else if (couleur == "Bleu")
			return "\u001b[36m[" + numero +  "]\u001b[0m";
		else if (couleur == "Gris")
			return "\u001b[37m[" + numero +  "]\u001b[0m";
			
        return "";
    }
}
    
    