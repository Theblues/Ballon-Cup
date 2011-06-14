package Projet.Plateau;

import java.util.ArrayList;

public class Plateau
{
    private ArrayList<Tuile> plateau = new ArrayList<Tuile>();
    
    public void ajouterTuile(Tuile tuile)
    {
        plateau.add (tuile);
    }
}