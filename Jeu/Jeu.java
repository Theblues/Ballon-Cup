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
import Projet.Cube.*;

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
	
	public Plateau getPlateau()	{	return plateau;	}
					  
    public void initialiserJeu()
    {
        // initialisation de la pioche
        pioche.initialiserPioche(tabBallon);
        pioche.melangerPioche();
		
        // initialisation du sac
        sac.initialiserSac(tabBallon);
        sac.melangerSac();
        
        // initialisation de la liste des trophées
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
			
			System.out.println("Choissisez le coté du plateau sur laquel la carte sera pose (G-D) :");
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
    
	public void donnerCubeAuJoueur(Joueur joueur, int choixTuile)
	{
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); ++i)
		{
			joueur.ajouterCube(plateau.getTuile(choixTuile).getPaysage().getDernierElement());
			plateau.getTuile(choixTuile).getPaysage().supprimerDernierElement();
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
		//System.out.println ( " 2. Poser Une Carte "                   );
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
	
	public int choisirTuile()
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
	
	public int choisirBallon(Joueur joueur, int choixTuile)
	{
		Cube cube = null;
		Ballon ballon;
		boolean stop = false;
		int choixBallon = 0;
		
		do
		{
			System.out.println("Choissisez une carte a placer sur la tuile : ");
			choixBallon = Clavier.lire_int();
			--choixBallon;
			
			if (choixBallon < 0 || choixBallon >= 8)
			{
				System.out.println("Choississez une carte entre 1 et 8");
				continue;
			}
				
			for(int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); ++i)
			{
				cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
				for (int j = 0; j < NB_CARTE_PAR_JOUEUR; j++)
				{
					ballon = joueur.getBallon(j);
					
					if (cube.getCouleur().equals(ballon.getCouleur()))
					{
						if (cube.getCouleur().equals(joueur.getBallon(choixBallon).getCouleur()))
							return choixBallon;
						stop = true;
					}
				}
			}
			if (!stop)
				return -1;
				
		} while (true);
	}
	
	public char choixCote(int choixTuile)
	{
		char choixCote = ' ';
		do
		{
			System.out.println("Choissisez un cote pour mettre votre carte : ");
			choixCote = Clavier.lire_char();
		} while (plateau.getTuile(choixTuile).estPleine(choixCote));
		
		return choixCote;
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
		Jeu jeu = new Jeu(nom1 , nom2);
		jeu.initialiserJeu();
		
		//affichage du Jeu ( plateau )
		System.out.println(jeu);
		
		char choixCoteJ1, choixCoteJ2;
		do
		{
			System.out.println("Choisissez votre cote (G/D): ");
			choixCoteJ1 = Clavier.lire_char();
		} while (choixCoteJ1 != 'G' && choixCoteJ1 != 'D');
		
		if ( choixCoteJ1 == 'G')		choixCoteJ2 = 'D';
		else							choixCoteJ2 = 'G';
		
		char[][] coteJoueur = { {     '1'    ,     '2'     },
		                        { choixCoteJ2, choixCoteJ2 },
							  };
			
		Joueur[] tabJoueur = { jeu.getJoueur1(), jeu.getJoueur2() };
		int choixTuile = 0;
		int choixBallon = 0;
		char choixCote = ' ';
		
		//Boucle de Jeu
		while (jeu.getJoueur1().getTrophee() != 3 || jeu.getJoueur2().getTrophee() != 3)
		{
			choixTuile = jeu.choisirTuile();
			for (int i = 0; i < jeu.getPlateau().getTuile(choixTuile).getAttribut(); i++)
			{
				for (int j = 0; j < tabJoueur.length; j++)
				{
					tabJoueur[j].afficherMain();
					choixBallon = jeu.choisirBallon(tabJoueur[j], choixTuile);
					if (choixBallon == -1)
					{
						System.out.println("Tu n'as aucune carte de la couleur d'un cube de la tuile");
						continue;
					}
					
					choixCote = jeu.choixCote(choixTuile);
					
					jeu.carteVersTuile(tabJoueur[i], choixBallon, choixCote, choixTuile);
				}
			}
			break;
		}
		
	}
}       