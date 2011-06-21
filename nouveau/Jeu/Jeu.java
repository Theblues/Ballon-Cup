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

import java.io.*;

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
    
    public static final int NB_TUILE = 4;
    public final int NB_CARTE_PAR_JOUEUR = 8;
    
    private int[][] tabBallon = {   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },         // rouge
                                    { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },         // jaune
                                    { 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1 },         // vert
                                    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },         // bleu
                                    { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },         // gris
                                };

	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	public Jeu( String nom1, String nom2 )	
	{
		joueur1= new Joueur(nom1);
		joueur2= new Joueur(nom2);
	}
	
	/*****************/
	/*** ACCESSEUR ***/
	/*****************/
	
	public Joueur getJoueur1() 		{	return joueur1; 	}
	public Joueur getJoueur2() 		{	return joueur2; 	}
	public Plateau getPlateau()		{	return plateau;		}
	public Pioche getPioche()		{	return pioche;		}
	public Defausse getDefausse()	{	return defausse;	}
	public Sac getSac()				{	return sac;			}
	public ArrayList<Trophee> getListeTrophee()    {    return listeTrophee;    }
			  
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
    }
    
    /*********************************/
    /*** METHODES POUR INITIALISER ***/
    /*********************************/
    
    public void donnerCarteAuxJoueurs()
    {
        for (int i = 0; i < NB_CARTE_PAR_JOUEUR; i++)
        {
            pioche.distribuerCarte(joueur1);
            pioche.distribuerCarte(joueur2);
        }
    }
    
    public void initialiserPlateau()
    {
        for (int i = 0; i < NB_TUILE; i++)
        {
            if ( i % 2 == 0)
                plateau.ajouterTuile(new Tuile(new Paysage("Plaine", "Montagne")));
            else
                plateau.ajouterTuile(new Tuile(new Paysage("Montagne", "Plaine")));
        }
    }
    
    public void initialiserCubeSurTuile()
    {
        for (int i = 0; i < NB_TUILE; i++)
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
    
    public void DefausseVersPioche()
    {       
        ArrayList<Ballon> inter = new ArrayList<Ballon> (defausse.getDefausse());
        for (Ballon b: inter)
            pioche.ajouterElement(b);
			
		pioche.melangerPioche();    
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
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
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
		joueur.supprimerCube(couleur.getLibelle());
		sac.ajouterElement(getAttribut(couleur), couleur);
	}
	
	public int choisirBallon()
	{
		int choixBallon = 0;
		do
		{
			System.out.println("Choissisez une carte a placer sur la tuile : ");
			choixBallon = Clavier.lire_int();
			--choixBallon;
		} while (choixBallon < 0 || choixBallon >= 8);
		
		return choixBallon;
	}
	
	public char choisirCote()
	{
		char choixCote = ' ';
		do
		{
			System.out.println("Choissisez un cote pour mettre votre carte : ");
			choixCote = Clavier.lire_char();
		} while (choixCote != 'g' && choixCote != 'd' &&choixCote != 'D' && choixCote != 'G');
		
		return choixCote;
	}
	
	public int choisirTuile()
	{
		int choix = 0;
		do
		{
			System.out.println("Choissisez une tuile : ");
			choix = Clavier.lire_int();
			choix--;
		} while ( choix > 4 || choix < 0);
		
		return choix;
	}
	
	public boolean cotePlein(int choixTuile, char choixCote)
	{
		if (plateau.getTuile(choixTuile).estPleine(choixCote))
			return true;
		
		return false;
	}
	
	public boolean cubeUtilise(int choixTuile, char choixCote, String couleur)
	{
		Cube cube = null;
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			// on recupere le cube
			cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			// si la couleur est egale a la couleur du cube
			if (couleur.equals(cube.getCouleur()))
			{
				// si le cube est "deja" utilise
				if (plateau.getTuile(choixTuile).getPaysage().estUtilise(cube, choixCote))
				{
					System.out.println(choixCote + "\t test");
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean verifcouleur(int choixTuile, char choixCote, String couleur)
	{
		Cube cube = null;
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			// on recupere le cube
			cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			if (couleur.equals(cube.getCouleur()))
				return true;
		}
		return false;
	}
	
	public boolean impossibleDeJoueur(Joueur joueur)
	{
		Cube cube = null;
		String couleur = "";
		for (int i = 0; i < NB_TUILE; i++)
		{
			for (int j = 0; j < plateau.getTuile(i).getAttribut(); j++)
			{
				cube = plateau.getTuile(i).getPaysage().getElement(j);
				for(int k = 0; k < NB_CARTE_PAR_JOUEUR; k++)
				{
					couleur = joueur.getBallon(k).getCouleur();
					if (couleur.equals(cube.getCouleur()))
						return false;
				}
			}
		}
		return true;
	}
	
	public void ajouterCubeUtilise(int choixTuile, char choixCote, String couleur)
	{
		Cube cube = null;
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			if (couleur.equals(cube.getCouleur()))
			{
				plateau.getTuile(choixTuile).getPaysage().ajouterCubeUtilise(cube, choixCote);
				break;
			}
		}
	}
	
	public void piocher(Joueur joueur)
	{
		pioche.distribuerCarte(joueur);
	}
	
	public Joueur quiAGagne(Joueur dernierJoueur, char[][] coteJoueur, int choixTuile)
	{
		// on compte les points des points
		int compteDroit = plateau.getTuile(choixTuile).getResultat('D');
		int compteGauche = plateau.getTuile(choixTuile).getResultat('G');
		
		char cote;
		// On regarde quel cote a gagne
		if (compteDroit > compteGauche)
		{	
			if (plateau.getTuile(choixTuile).getPaysage().getVerso() == "plaine")
				cote = 'G';
			else
				cote = 'D';
		}
		else if (compteDroit < compteGauche)
		{
			if (plateau.getTuile(choixTuile).getPaysage().getVerso() == "plaine")
				cote = 'D';
			else
				cote = 'G';
		}
		else
			return dernierJoueur;
		
		for (int i = 0; i < coteJoueur.length; i++)
		{
			// on regarde le cote assiocier au joueur
			if (coteJoueur[1][i] == cote)
			{
				if (coteJoueur[0][i] == '1')
					return joueur1;
				else
					return joueur2;
			}
		}
		System.out.println("error : return null");
		// normalement impossible
		return null;
	}
	
	public void distribuerCube(Joueur joueur, int choixTuile)
	{
		for(int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			Cube cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			joueur.ajouterCube(cube);
			
		}
		plateau.getTuile(choixTuile).getPaysage().supprimerTousLesElements();
    }
	
	public void tuileVersDefausse(int choixTuile)
	{
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			// partie gauche
			defausse.ajouterElement(plateau.getTuile(choixTuile).getBallon(i, 'G'));
			// partie droite
			defausse.ajouterElement(plateau.getTuile(choixTuile).getBallon(i, 'D'));
		}
		plateau.getTuile(choixTuile).supprimerToutesLesCartes();
	}
	
	public void inverserTuile()
	{
		for (int i = 0; i < NB_TUILE; i++)
			plateau.getTuile(i).getPaysage().inverserPaysage();
	}
	
	public void supprimerCubeDejaUtilise(int choixTuile)
	{
		plateau.getTuile(choixTuile).getPaysage().supprimerCubeDejaUtilise();
	}
	
	
    public String toString()
    {
		String s;
	
		//affichage plateau entier
		s = "Joueur 1 : " + joueur1.getNomJoueur() +"\t" + "Joueur 2 : " + joueur2.getNomJoueur() +"\n\n";   
		s += plateau.toString();
		s += "\n";
	
		return s;
    }
    
    
	public static boolean peutAcheterTrophee(Joueur j, Trophee t)
	{
		int cpt=0;
		
		for ( Cube b : j.getListeCube() )
			if ( t.getCouleur() == b.getCouleur() )
				cpt++;
		
		if ( cpt == t.getNumero() )
			return true;
		else
			return false;
									
	}
	
	public static boolean acheterTrophee( Joueur j, Trophee t  )
	{
		if ( peutAcheterTrophee( j, t ) )
		{
			j.supprimerCube(t.getCouleur());
			j.ajouterTrophee(t);
			return true;
		
		}	
		else
			return false;
	}
	
	public boolean AUneTuilePleine()
	{
		for (int i = 0; i < NB_TUILE; i++)
			if (plateau.getTuile(i).estEntierementPleine())
				return true;
				
		return false;
	}
	public int quelTuilePleine()
	{
		for (int i = 0; i < NB_TUILE; i++)
			if (plateau.getTuile(i).estEntierementPleine())
				return i;
		
		return -1;
	}
	
	public void inverserLaTuile(int choixTuile)
	{
		plateau.getTuile(choixTuile).getPaysage().inverserPaysage();
	}
	
	public void ajouterCube(int choixTuile)
	{
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			plateau.getTuile(choixTuile).ajouterCubeSurPaysage(sac.getDernierElement());
			sac.supprimerDernierElement();
		}
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
		// initialisation des couleurs
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
		
		// permet de savoir quel joueur gagne 
		char[][] coteJoueur = { {     '1'    ,     '2'     },
		                        { choixCoteJ1, choixCoteJ2 },
							  };
			
		Joueur[] tabJoueur = { jeu.getJoueur1(), jeu.getJoueur2() };
			
		int choixTuile = 0;
		int choixBallon = 0;
		int dernierJoueur = 0;
		char choixCote = ' ';
		
		
		//Boucle de Jeu
		while (jeu.getJoueur1().getTrophee() != 3 || jeu.getJoueur2().getTrophee() != 3)
		{
			while (!jeu.AUneTuilePleine())
			{
				for (int j = 0; j < tabJoueur.length; j++)
				{
					System.out.println();
					System.out.println(jeu.getPlateau().toString());
					System.out.println();
					System.out.print("Joueur : ");
					System.out.println(tabJoueur[j].getNomJoueur());
					System.out.println();
					System.out.println(tabJoueur[j]);
					System.out.println();
					
					boolean pass = true;
					boolean impossibleDeJouer = false;
					do
					{
						pass = true;
						if (jeu.impossibleDeJoueur(tabJoueur[j]))
						{
							System.out.println("Vous ne pouvez pas jouer :( ");
							impossibleDeJouer = true;
							break;
						}
						choixBallon = jeu.choisirBallon();
						choixTuile = jeu.choisirTuile();
						choixCote = jeu.choisirCote();
					
						if (jeu.cotePlein(choixTuile, choixCote))
							pass = false;
						if (jeu.cubeUtilise(choixTuile, choixCote, tabJoueur[j].getBallon(choixBallon).getCouleur()))
							pass = false;
						if (!jeu.verifcouleur(choixTuile, choixCote, tabJoueur[j].getBallon(choixBallon).getCouleur()))
							pass = false;		
					} while (!pass);
					
					if (impossibleDeJouer)
						continue;
						
					// permet de ne plus "utilise" le cube de la couleur de la carte
					jeu.ajouterCubeUtilise(choixTuile, choixCote, tabJoueur[j].getBallon(choixBallon).getCouleur());
					// on place la carte choisi sur le jeu
					jeu.carteVersTuile(tabJoueur[j], choixBallon, choixCote, choixTuile);
					// si la pioche est vide alors on remet des cartes
					if (jeu.getPioche().estVide())
					jeu.DefausseVersPioche();
					// lorsque le joueur joue il pioche une carte
					jeu.piocher(tabJoueur[j]);
					dernierJoueur = j;
				}
			}
			int tuilePleine = jeu.quelTuilePleine();
			// on regarde quel joueur a gagne
			Joueur joueur = jeu.quiAGagne(tabJoueur[dernierJoueur], coteJoueur, tuilePleine);
			// on distribu les cubes au gagnant et on supprime les cubes de la tuile
			jeu.distribuerCube(joueur, tuilePleine);

			// on met les cartes de la tuile fini dans la defausse
			jeu.tuileVersDefausse(tuilePleine);
			// on supprime les cube deja utilise
			jeu.supprimerCubeDejaUtilise(tuilePleine);
			jeu.inverserLaTuile(tuilePleine);
			jeu.ajouterCube(tuilePleine);
		}
	}
}       