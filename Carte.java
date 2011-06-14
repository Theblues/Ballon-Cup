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
        return " (" + numero + ") " + couleur;
    }
}
    
    