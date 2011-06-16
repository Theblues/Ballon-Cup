package Projet.Jeu;

import java.util.ArrayList;
import java.util.Collections;
import org.fusesource.jansi.*;
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
    
    // Les cartes Troph�es
    private ArrayList<Trophee> listeTrophee = new ArrayList<Trophee>();

    private Pioche pioche       = new Pioche();
    private Defausse defausse   = new Defausse();
    private Sac sac             = new Sac();
    private Plateau plateau     = new Plateau();
	private Joueur joueur1;
    private Joueur joueur2;
    
    private final int NB_TUILE = 4;
    private final int NB_CARTE_PAR_JOUEUR = 8;
    
    private int[][] tabBallon = {   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },         // rouge
                                    { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },         // jaune
                                    { 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1 },         // vert
                                    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },         // bleu
                                    { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },         // gris
                                };

	public Jeu( String nom1, String nom2 )	
	{
		joueur1= new Joueur(nom1);
		joueur2= new Joueur(nom2);
	}
	
	public Joueur getJoueur1() { return joueur1; }
	public Joueur getJoueur2() { return joueur2; }
					  
    public void initialiserJeu()
    {
        // initialisation de la pioche
        pioche.initialiserPioche(tabBallon);
        pioche.melangerPioche();
		
        // initialisation du sac
        sac.initialiserSac(tabBallon);
        sac.melangerSac();
        
        // initialisation de la liste des troph�es
        initialiserListeTrophee();
        
        // donne des cartes au joueurs
        donnerCarteAuxJoueurs();	  
	       
        // on initialise le plateau
        initialiserPlateau();
        initialiserCubeSurTuile();
	  
	/*	
		for ( int i=0; i < 8 ; i++ )
		{
			
			System.out.println(plateau);
			System.out.print ("Choix de la tuile : ");
			int choixTuile = Clavier.lire_int();
			
			System.out.println("Choissisez une carte a placer sur la plateau :");
			System.out.println("Voici votre main :");
			joueur1.afficherMain();
			int ch = Clavier.lire_int();
			
			System.out.println("Choissisez le cot� du plateau sur laquel la carte sera pose (G-D) :");
			cot = Clavier.lire_char();
			
			
			
			carteVersTuile(joueur1, ch, cot, choixTuile);
		}*/
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
			for (int j = 0; j < plateau.getTuile(i).getAttribut(); ++j)
			{
				plateau.getTuile(i).ajouterCubeSurPaysage(sac.getDernierElement());
				sac.supprimerDernierElement();
			}
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
    
	public boolean PeutMettreUneCarte(char cote, int choixTuile)
	{
		if (plateau.getTuile(choixTuile).getElementJeu(cote) == plateau.getTuile(choixTuile).getAttribut())
			return false;
		
		return true;
	}
    
	public void carteVersTuile(Joueur joueur, int choixBallon, char cote, int choixTuile)
	{
		if (PeutMettreUneCarte(cote, choixTuile))
		{
			plateau.getTuile(choixTuile).ajouterBallon(joueur.getBallon(choixBallon), cote);
			joueur.supprimerBallon(choixBallon);
		}
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
		for (int i = 0; i < plateau.getTuile(i).getAttribut(); ++i)
		{
			joueur.ajouterCube(plateau.getTuile(i).getPaysage().getDernierElement());
			plateau.getTuile(i).getPaysage().supprimerDernierElement();
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
	
	public void afficherMenu( Joueur j)
	{
		
		Joueur joueur;
		
		if ( j.getNomJoueur().equals(joueur1.getNomJoueur() ))
			joueur=joueur1;
		else
			joueur=joueur2;
			
		System.out.println ( "\n\n\n" );
		System.out.println ( "*******************************" );
		System.out.println ( "**           MENU            **" );
		System.out.println ( "*******************************" );
		
		System.out.println ();
	  
		System.out.println ( " 1. Regarder Vos Cartes "         );
		//System.out.println ( " 2. D�fausser "                   );
		System.out.println ( " 3. Regarder ses trophees "                   );
		System.out.println ( " 0. Quitter"                      );
	  
		System.out.println ();
		System.out.print   ( "      votre choix : "            );
		
		int choix = Clavier.lire_int();
		
		switch(choix)
		{
			case 1 : joueur.afficherMain(); break;
			//case 2 : j.(); break;
			case 3 : joueur.afficherTrophee(); break;
			default : break;
		}

	}
	
	public int choisirtuile()
	{
		int choix = 0;
		do
		{
			System.out.println("Choissisez une tuile : ");
			choix = Clavier.lire_int();
			choix--;
		} while ( choix > 4 || choix < 0 || !tuilePrise(choix));
		
		return choix;
	}
	
	public boolean tuilePrise(int numTuile)
	{
		if (plateau.getTuile(numTuile).aUneCarte())
			return false;
			
		return true;
	}
    
    public String toString()
    {
		String s;
	
		s = "Joueur 1 : " + joueur1.getNomJoueur() +"\t" + "Joueur 2 : " + joueur2.getNomJoueur() +"\n\n";   
		s += plateau.toString();
		s += "\n";
	
		return s;
    }
    
	/************/
	/*** MAIN ***/
	/************/
    
	public static void main (String[] args)
	{
		String nom1, nom2;
		//Initialisation des Joueurs
		System.out.print("Veuillez entrer le nom du joueur 1 : ");
		nom1 = Clavier.lireString();
		System.out.print("Veuillez entrer le nom du joueur 2 : ");
		nom2 = Clavier.lireString();
		
		System.out.println();
		
		AnsiConsole.systemInstall();
	  
		//Nouveau Jeu
		Jeu j = new Jeu(nom1 , nom2);
		j.initialiserJeu();
		
		//affichage du Jeu ( plateau )
		System.out.println(j);
		System.out.println("test");
		//Boucle de Jeu
		while (j.getJoueur1().getTrophee() != 3 || j.getJoueur2().getTrophee() != 3)
		{
			int choixTuile = j.choisirtuile();
			
			Joueur[] tabJoueur = { j.getJoueur1(), j.getJoueur2() };
			
			for (int i = 0; i < tabJoueur.length; i++)
			{
				j.afficherMenu( tabJoueur[i]);
				break;
			}
			break;
		}
		
	}
}       