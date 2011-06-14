package Projet.Joueur;

import java.util.*;
import java.util.Collections;

import Projet.Carte.*;
import Projet.Cube.*;

public class Joueur
{
		private ArrayList<Trophee> trophee = new ArrayList<Trophee> ();
		private ArrayList<Ballon> main = new ArrayList<Ballon>();
		private ArrayList<Cube> cube = new ArrayList<Cube>();
		private int numJoueur;
        private static int nbJoueur = 0;

		public Joueur() {   numJoueur = ++nbJoueur; }
        public void distribuerCarte(Ballon ballon)  {   main.add (ballon);    }
        public void afficherMain()                  {   for (Ballon b: main)    System.out.println(b);  }
}