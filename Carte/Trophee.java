package Projet.Carte;

public class Trophee extends Carte
{
    public Trophee (int num, String couleur)
    {
        super(num, couleur);
    }
    
    // modifiable comme vous le souhaitez
    public String toString()
    {
        return "Carte de type Trophée" + super.toString();
    }
}

