package Projet.Plateau;

public class Paysage
{
    public String recto;
    public String verso;
    
    public Paysage(String recto, String verso)
    {
        this.recto = recto;
        this.verso = verso;
    }
    
    public String getRecto()    {   return recto;   }
    public String getVerso()    {   return verso;   }
}