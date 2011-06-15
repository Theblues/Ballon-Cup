package Projet.Jeu;

import java.util.ArrayList;
import java.util.Collections;
import iut.algo.*;

import Projet.Liste.*;
import Projet.Carte.*;
import Projet.Joueur.*;
import Projet.Couleur.*;
import Projet.Plateau.*;

public class Jeu
{
    /*********************/
    /***** ATTRIBUTS *****/
    /*********************/
    
    // Les cartes Trophées
    private ArrayList<Trophee> listeTrophee = new ArrayList<Trophee>();

    private Pioche pioche       = new Pioche();
    private Defausse defausse   = new Defausse();
    private Sac sac             = new Sac();
    private Joueur joueur1;
    private Joueur joueur2;
    private Plateau plateau     = new Plateau();
    
    private final int NB_TUILE = 4;
    private final int NB_CARTE_PAR_JOUEUR = 8;
    
    private int[][] tabBallon = {   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },         // rouge
                                    { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },         // jaune
                                    { 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1 },         // vert
                                    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },         // bleu
                                    { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },         // gris
                                };

	public Jeu( String j1, String j2 )	
	{
		joueur1= new Joueur(j1);
		joueur2= new Joueur(j2);
	}
	
	public Joueur getJoueur1() { return joueur1; }
	public Joueur getJoueur2() { return joueur2; }
					  
    public void initialiserJeu()
    {
        // initialisation de la pioche
        pioche.initialiserPioche(tabBallon);
        pioche.melangerPioche();
        pioche.afficherPioche();
        System.out.println("\n");
        
        // initialisation du sac
        sac.initialiserSac(tabBallon);
        sac.melangerSac();
        
        // initialisation de la liste des trophées
        initialiserListeTrophee();
        
        // donne des cartes au joueurs
        donnerCarteAuxJoueurs();
        joueur1.afficherMain();
        System.out.println("\n");
        joueur2.afficherMain();
	  
	  
	  //affiche pioche apres main
	  System.out.println("\n");
	  pioche.afficherPioche();
        
        // on initialise le plateau
        initialiserPlateau();
        initialiserCubeSurTuile();
	  
		char cot=' ';
		for ( int i=0; i < 8 ; i++ )
		{
		
			System.out.println("Choissisez une carte a placer sur la plateau :");
			System.out.println("Voici votre main :");
			joueur1.afficherMain();
			int ch = Clavier.lire_int();
			System.out.println("Choissisez le coté du plateau sur laquel la carte sera pose (G-D) :");
			cot = Clavier.lire_char();
			
				
			carteVersTuile(joueur1, ch, cot);
		}
    }
    
    /*********************************/
    /*** METHODES POUR INITIALISER ***/
    /*********************************/
    
    public void donnerCarteAuxJoueurs()
    {
        for (int i = 0; i < NB_CARTE_PAR_JOUEUR; ++i)
        {
            pioche.distribuerCarte(joueur1);
            pioche.distribuerCarte(joueur2);
        }
    }
    
    public void initialiserPlateau()
    {
        for (int i = 0; i < NB_TUILE; ++i)
        {
            if ( i % 2 == 0)
                plateau.ajouterTuile(new Tuile(new Paysage("Plaine", "Montagne")));
            else
                plateau.ajouterTuile(new Tuile(new Paysage("Montagne", "Plaine")));
        }
    }
    
    public void initialiserCubeSurTuile()
    {
        for (int i = 0; i < NB_TUILE; ++i)
        {
            plateau.getTuile().ajouterCubeSurPaysage(sac.getDernierElement());
            sac.supprimerDernierElement();
        }
    }
    
    public void afficherVosCartes(Joueur joueur)
    {
        joueur.afficherMain();
    }
    
    public boolean DefausseVersPioche()
    {
        if (!pioche.estVide())
            return false;
            
        ArrayList<Ballon> inter = new ArrayList<Ballon> (defausse.getDefausse());
        for (Ballon b: inter)
            pioche.ajouterElement(b);
            
        return true;
    }
    
    
	public void carteVersTuile(Joueur joueur, int choixBallon, char coter)
	{

		plateau.getTuile().ajouterBallon(joueur.getBallon(choixBallon), coter);

		joueur.supprimerBallon(choixBallon);

	}
	
	
	public int getAttribut(Couleur couleur)
	{

		for (Trophee t: listeTrophee)
			if (t.getCouleur().equals(couleur.getLibelle()))
				return t.getNumero();

	return 0;

	} 

	public Trophee getTrophee(Couleur couleur)
	{

		for (Trophee t: listeTrophee)
			if (t.getCouleur().equals(couleur.getLibelle()))
				return t;

	return null;

	}
    
    
	/*********************************/
	/*** INITIALISATION DES LISTES ***/
	/*********************************/
    
	public void initialiserListeTrophee()
	{
		listeTrophee.add( new Trophee(3, Couleur.GRIS.getLibelle()));
		listeTrophee.add( new Trophee(4, Couleur.BLEU.getLibelle()));
		listeTrophee.add( new Trophee(5, Couleur.VERT.getLibelle()));
		listeTrophee.add( new Trophee(6, Couleur.JAUNE.getLibelle()));
		listeTrophee.add( new Trophee(7, Couleur.ROUGE.getLibelle()));
	}
    
	/***********/
	/** AUTRE **/
	/***********/
    
	public void donnerCubeAuJoueur(Joueur joueur)
	{
		for (int i = 0; i < plateau.getTuile().getAttribut(); ++i)
		{
			joueur.ajouterCube(plateau.getTuile().getPaysage().getElement());
			plateau.getTuile().getPaysage().supprimerElement();
		}
	}
    
	public boolean PeutAvoirUnTrophee(Joueur joueur,Couleur couleur)
	{
		for (Trophee t: listeTrophee)
			if (t.getCouleur().equals(couleur.getLibelle()))
				if (t.getNumero() == joueur.getNbCube(couleur))
					return true;

		return false;
	}       
    
	public void CubeEnTrophee(Joueur joueur, Couleur couleur)
	{

		joueur.ajouterTrophee(getTrophee(couleur));

		joueur.supprimerCube(couleur);

		sac.ajouterElement(getAttribut(couleur), couleur);

	}
    
    
	/**********/
	/** Menu **/
	/**********/
	public void afficherMenu( String j)
	{
		
		Joueur joueur;
		
		if ( j == joueur1.getNomJoueur() )
			joueur=joueur1;
		else
			joueur=joueur2;
		
		int quit=0;
		
		while(quit==0)
		{
		
			System.out.println ( "\n\n\n" );
			System.out.println ( "*******************************" );
			System.out.println ( "**           MENU            **" );
			System.out.println ( "*******************************" );
			
			System.out.println ();
		  
			System.out.println ( " 1. Regarder Vos Cartes "         );
			//System.out.println ( " 2. Défausser "                   );
			System.out.println ( " 3. Regarder ses trophees "                   );
			System.out.println ( " 0. Quitter"                      );
		  
			System.out.println ();
			System.out.print   ( "      votre choix : "            );
			
			int choix=0;
			
			choix = Clavier.lire_int();
			
			switch(choix)
			{
				case 1 : joueur.afficherMain(); break;
				//case 2 : j.defausser(); break;
				case 3 : joueur.afficherTropee(); break;
				case 0 : quit=1; break;
				default : quit=1;
			}
		}
	}
    
    
    public String toString()
    {
	String s="";
	
	s += "Joueur 1 : " + joueur1.getNomJoueur() +"\t" + "Joueur 2 : " + joueur2.getNomJoueur() +"\n\n";   
	
		s += plateau.toString();
		s += "\n";
	
	
	return s;
    
    }
    
	/************/
	/*** MAIN ***/
	/************/
    
	public static void main (String[] args)
	{
		String j1, j2;
	  
		//Initialisation des Joueurs
		System.out.println("Veuillez entrer le nom du joueur 1 :");
		j1 = Clavier.lireString();
		System.out.println("Veuillez entrer le nom du joueur 2 :");
		j2 = Clavier.lireString();
	  
	  
		//Nouveau Jeu
		Jeu j = new Jeu(j1 , j2);
		j.initialiserJeu();
		
		//affichage du Jeu ( plateau )
		System.out.println(j);
	  
	  
		//Menu joueur1
		//j.afficherMenu(j1);
	  
		//Menu joueur2
		//j.afficherMenu(j2);
		

		
		/*Boucle de Jeu
		while (joueur1.getTrophee() != 3 || joueur2.getTrophee() != 3)
		{}*/
		
	}
}       