package Projet.Joueur;

import java.util.*;
import java.util.Collections;

import Projet.Carte.*;
import Projet.Cube.*;
import Projet.Couleur.*;

public class Joueur
{
    private ArrayList<Trophee> listeTrophee = new ArrayList<Trophee> ();
	private ArrayList<Ballon> main = new ArrayList<Ballon>();
	private ArrayList<Cube> listeCube = new ArrayList<Cube>();
	private int numJoueur;
    private static int nbJoueur = 0;

    public Joueur() {   numJoueur = ++nbJoueur; }
    public void distribuerCarte(Ballon ballon)  {   main.add (ballon);    }
    public void afficherMain()                  {   for (Ballon b: main)    System.out.println(b);  }
    public void ajouterCube(Cube cube)          {   listeCube.add (cube);   }
    public void ajouterTrophee(Trophee trophee) {   listeTrophee.add (trophee);  }
    
    public int getNbCube(Couleur couleur)
    {
        int nbCube = 0;
        for (Cube c: listeCube)
            if (couleur.getLibelle().equals(c.getCouleur()))
                ++nbCube;

        return nbCube;            
    }
}