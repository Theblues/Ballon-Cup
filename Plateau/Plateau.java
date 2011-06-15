package Projet.Plateau;

import java.util.ArrayList;

public class Plateau
{
    private ArrayList<Tuile> plateau = new ArrayList<Tuile>();
    private int tuile;
    
    public void ajouterTuile(Tuile tuile)
    {
        plateau.add (tuile);
    }
    
    public void choixTuile(int choix)   {        tuile = choix;                 }
    public Tuile getTuile()             {        return plateau.get(tuile);     }
}