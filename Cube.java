
public class Cube
{
    private String couleur;
    
    public Cube(String couleur)
    {
        this.couleur = couleur;
    }
    
    public String getCouleur()  { return couleur;   }
    public void setCouleur(String couleur)  {   this.couleur = couleur; }
    
    // modifiable comme vous le souhaitez
    public String toString()
    {
        return "Cube de couleur " + couleur;
    }
}