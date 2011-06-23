package Projet.Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.fusesource.jansi.*;

import Projet.Liste.*;
import Projet.Carte.*;
import Projet.Joueur.*;
import Projet.Couleur.*;
import Projet.Plateau.*;
import Projet.Cube.*;

import java.io.*;

/**
* @author Erwan Lebrun
* @author Jeremy Lebair
* @author Sarah Vernichon
* @version 1.0
*/
public class Jeu
{
    /*********************/
    /***** ATTRIBUTS *****/
    /*********************/
    
	/**
	* Liste d Trophée 
	* @see Trophee
	*/
    private ArrayList<Trophee> listeTrophee = new ArrayList<Trophee>();

	/**
	* Initialisation de Pioche
	* @see Pioche
	*/
    private Pioche pioche       = new Pioche();
	
	/**
	* Initialisation de Defausse
	* @see defause
	*/
    private Defausse defausse   = new Defausse();
	
	/**
	* Initialisation de Sac
	* @see Defausse
	*/
    private Sac sac             = new Sac();
	
	/**
	* Initialisation de Plateau
	* @see Plateau
	*/
    private Plateau plateau     = new Plateau();
	
	/**
	* Initialisation d'un Joueur
	* @see Joueur
	*/
	private Joueur joueur1;
	
	/**
	* Initialisation d'un Joueur
	* @see Joueur
	*/
    private Joueur joueur2;
    
	/**
	* Nombre Total de Tuile
	*/
    public final int NB_TUILE = 4;
	
	/**
	* Nombre total de Carte par Joueur
	*/
    public final int NB_CARTE_PAR_JOUEUR = 8;
    
	/**
	* Tableau des valeaurs des cartes en fonctionde leur couleur
	*/
    private int[][] tabBallon = {   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },         // rouge
                                    { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 },         // jaune
                                    { 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1 },         // vert
                                    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },         // bleu
                                    { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },         // gris
                                };

	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	
	/**
	* Constructeur des Joueurs
	* @param nom1
	* @param nom2
	*/ 
	public Jeu( String nom1, String nom2 )	
	{
		joueur1= new Joueur(nom1);
		joueur2= new Joueur(nom2);
	}
	
	/*****************/
	/*** ACCESSEUR ***/
	/*****************/
	
	/**
	* Retourne les attributs du joueur
	* @return le joueur
	*/
	public Joueur getJoueur1() 		{	return joueur1; 	}
	
	/**
	* Retourne les attributs du joueur
	* @return le joueur
	*/
	public Joueur getJoueur2() 		{	return joueur2; 	}
	
	/**
	* Retourne le contenu du plateau
	* @return le contenu du plateau
	*/
	public Plateau getPlateau()		{	return plateau;		}
	
	/**
	* Retourne le contenu de la Pioche 
	* @return le contenu de la Pioche
	*/
	public Pioche getPioche()		{	return pioche;		}
	
	/**
	* Retourne le contenu de la Defausse
	* @return le contenu de la defausse
	*/
	public Defausse getDefausse()	{	return defausse;	}
	
	/**
	* Retourne le contenu du Sac
	* @return le contenu du Sac
	*/
	public Sac getSac()				{	return sac;			}
	
	/**
	* retourn la liste de trophée
	* @return la liste de trophée
	*/
	public ArrayList<Trophee> getListeTrophee()    {    return listeTrophee;    }
			  
	/**
	* Initialise le Jeu : Initialise la pioche et la mélange, Mélange les cubes du Sac, Initialise la liste des Trophée,
	* Distribue les carte aux Joueur, initialise le Plateau et les Cube
	*/
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
	/*** INITIALISATION DES LISTES ***/
	/*********************************/
    
	/**
	* Initialise la liste des trophées
	*/
	public void initialiserListeTrophee()
	{
		listeTrophee.add( new Trophee(3, Couleur.GRIS.getLibelle()));
		listeTrophee.add( new Trophee(4, Couleur.BLEU.getLibelle()));
		listeTrophee.add( new Trophee(5, Couleur.VERT.getLibelle()));
		listeTrophee.add( new Trophee(6, Couleur.JAUNE.getLibelle()));
		listeTrophee.add( new Trophee(7, Couleur.ROUGE.getLibelle()));
	}
    
    /*********************************/
    /*** METHODES POUR INITIALISER ***/
    /*********************************/
    
	/** 
	* Distribue aux Joueurs le nombre de carte par joueur déterminé
	*/
    public void donnerCarteAuxJoueurs()
    {
        for (int i = 0; i < NB_CARTE_PAR_JOUEUR; i++)
        {
            pioche.distribuerCarte(joueur1);
            pioche.distribuerCarte(joueur2);
        }
    }
    
	/**
	* initialise le Plateau en fonction du nombre de Tuile determiné et ajoute un paysage a la Tuile 
	*/
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
    
	/**
	* Initialise le nombre de Cube sur une Tuile fonction de sa	position sur le plateau
	*/
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
    
	/************************/
	/** METHODE AUXILIAIRE **/
	/************************/
	
	/**
	* Retourne la couleur que l'on souhaite
	* @param couleur
	* @return la couleur que l'on souhaite
	*/
	public String retournerCouleur(char couleur)
    {
		if (couleur == 'R')
			return "Rouge";
		else if (couleur == 'J')
			return "Jaune";
		else if (couleur == 'V')
			return "Vert";
		else if (couleur == 'B')
			return "Bleu";
		else if (couleur == 'G')
			return "Gris";
    
		return "Mauvaise couleur";
    }
    
	/**
	* Met les cartes utilisées dans la pioche puis la mélmange
	*/
    public void DefausseVersPioche()
    {       
        ArrayList<Ballon> inter = new ArrayList<Ballon> (defausse.getDefausse());
        for (Ballon b: inter)
            pioche.ajouterElement(b);
			
		pioche.melangerPioche();    
    }
    
	/**
	* Retourne vrai si on peut déposé une carte a coté de la Tuile
	* @param choixTuile
	* @param cote
	* @return true si on peut déposé une carte a coté de la Tuile
	*/
	public boolean PeutMettreUneCarte(char cote, int choixTuile)
	{
		if (plateau.getTuile(choixTuile).getElementJeu(cote) == plateau.getTuile(choixTuile).getAttribut())
			return false;
		
		return true;
	}
    
	/**
	* Si on peut mettre une carte sur une Tuile alors on la met a l'endroit choisi
	* @param choixTuile
	* @param cote
	* @param choixBallon
	* @param joueur
	*/
	public void carteVersTuile(Joueur joueur, int choixBallon, char cote, int choixTuile)
	{
		if (PeutMettreUneCarte(cote, choixTuile))
		{
			plateau.getTuile(choixTuile).ajouterBallon(joueur.getBallon(choixBallon), cote);
			joueur.supprimerBallon(choixBallon);
		}
	}
	
	
	/**
	* Retourne le numero du trophee en fonction de sa couleur
	* @param couleur
	* @return le numero du trophee en fonction de sa couleur
	*/
	public int getAttribut(String couleur)
	{
		for (Trophee t: listeTrophee)
			if (t.getCouleur().equals(couleur))
				return t.getNumero();

		return 0;
	} 

	/**
	* Retourne le Trophée choisi en Fonction de sa couleur
	* @param couleur
	* @return le Trophée choisi en Fonction de sa couleur
	*/
	public Trophee getTrophee(String couleur)
	{
		for (Trophee t: listeTrophee)
			if (t.getCouleur().equals(couleur))
				return t;

		return null;
	}
    
	/**
	* Donne un trophée au joueur, supprime les cubes qui ont servi a obtenir le trophée et les met dans le sac de cube
	* @param joueur
	* @param couleur
	*/
	public void CubeEnTrophee(Joueur joueur, String couleur)
	{
		joueur.ajouterTrophee(getTrophee(couleur));
		joueur.supprimerCube(couleur);
		sac.ajouterElement(getAttribut(couleur), couleur);
	}
	
	/**
	* Permet de choisir le Ballon que l'on veut poser sur la Tuile
	* @return la valeur du Ballon 
	*/
	public int choisirBallon()
	{
		int choixBallon = 0;
		do
		{
			System.out.println("Choissisez une carte a placer sur la tuile : ");
			Scanner sc = new Scanner(System.in);
			choixBallon = sc.nextInt();
			--choixBallon;
		} while (choixBallon < 0 || choixBallon >= 8);
		
		return choixBallon;
	}
	
	/** 
	* Permet au joueur de choisir de quel cote il veut poser se carte
	* @return le caractère qui correspond au coté choisi
	*/
	public char choisirCote()
	{
		char choixCote = ' ';
		do
		{
			System.out.println("Choissisez un cote pour mettre votre carte : ");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			choixCote = str.charAt(0);
		} while (choixCote != 'g' && choixCote != 'd' &&choixCote != 'D' && choixCote != 'G');
		
		return choixCote;
	}
	
	/**
	* Permet au joueur de choisir sur quelle Tuile il veut joueur
	* @return l'indice de la Tuile choisie
	*/
	public int choisirTuile()
	{
		int choixTuile = 0;
		do
		{
			System.out.println("Choissisez une tuile : ");
			Scanner sc = new Scanner(System.in);
			choixTuile = sc.nextInt();
			choixTuile--;
		} while ( choixTuile > plateau.getTaille() || choixTuile < 0);
		
		return choixTuile;
	}
	
	/**
	* Permet de déterminer si un si le cote choisi sur la Tuile choisi est plein
	* @param choixCote
	* @param choixTuile
	* @return true si je coté est plein
	*/
	public boolean cotePlein(int choixTuile, char choixCote)
	{
		if (plateau.getTuile(choixTuile).estPleine(choixCote))
			return true;
		
		return false;
	}
	
	/**
	* Retourne vrai si le cube est déja utilisé
	* @param couleur
	* @param choixCote
	* @param choixTuile
	* @return returne true si le cube est déja utilisé
	*/
	public boolean cubeUtilise(int choixTuile, char choixCote, String couleur)
	{
		Cube cube = null;
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			// on recupere le cube
			cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			// si la couleur est egale a la couleur du cube
			if (couleur.equals(cube.getCouleur()))
				// si le cube est "deja" utilise
				if (plateau.getTuile(choixTuile).getPaysage().estUtilise(cube, choixCote))
					return true;
		}
		return false;
	}
	
	/**
	* Verifie si la carte que l'on veut posée corespond a la couleur d'un cube sur une Tuile
	* @param choixTuile
	* @param choixCote
	* @param couleur 
	* @return true si la carte que l'on veut posée corespond a la couleur d'un cube sur une Tuile
	*/
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
	
	/**
	* Verifie s'il est possible de de jouer sur un Tuile
	* @param joueur
	* @return true s'il est impossible de jouer
	*/
	public boolean impossibleDeJoueur(Joueur joueur)
	{
		Cube cube = null;
		String couleur = "";
		for (int i = 0; i < plateau.getTaille(); i++)
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
	
	/** 
	* @param choixTuile
	* @param choixCote
	* @param couleur 
	*/
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
	
	/**
	* Permet d'ajouter une Carte dans la main d'un joueur
	* @param joueur
	*/
	public void piocher(Joueur joueur)
	{
		pioche.distribuerCarte(joueur);
	}
	
	/**
	* Retourne le joueur qui a gagné 
	* @param choixTuile
	* @param coteJoueur
	* @param dernierJoueur
	* @return le joueur qui a gagné
	*/
	public Joueur quiAGagne(Joueur dernierJoueur, char[][] coteJoueur, int choixTuile)
	{
		// on compte les points des points
		int compteDroit = plateau.getTuile(choixTuile).getResultat('D');
		int compteGauche = plateau.getTuile(choixTuile).getResultat('G');
		
		char cote;
		// On regarde quel cote a gagne
		if (compteDroit > compteGauche)
		{	
			if (plateau.getTuile(choixTuile).getPaysage().getRecto().equals("Plaine"))
				cote = 'G';
			else
				cote = 'D';
		}
		else if (compteDroit < compteGauche)
		{
			if (plateau.getTuile(choixTuile).getPaysage().getRecto().equals("Plaine"))
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
	
	/**
	* Distribue les cubes au joueur par rapport a la Tuile
	* @param choixTuile
	* @param joueur
	*/
	public void distribuerCube(Joueur joueur, int choixTuile)
	{
		for(int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			Cube cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			joueur.ajouterCube(cube);
			
		}
		plateau.getTuile(choixTuile).getPaysage().supprimerTousLesElements();
    }
	
	/**
	*
	* @param choixTuile
	*/
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
	
	/**
	* Supprime un Cube déja utilisé
	* @param choixTuile
	*/
	public void supprimerCubeDejaUtilise(int choixTuile)
	{
		plateau.getTuile(choixTuile).getPaysage().supprimerCubeDejaUtilise();
	}
	
	/**
	* Retourne vrai si une tuile du plateau est pleine
	* @return true  si une tuile du plateau est pleine
	*/
	public boolean AUneTuilePleine()
	{
		for (int i = 0; i < plateau.getTaille(); i++)
			if (plateau.getTuile(i).estEntierementPleine())
				return true;
				
		return false;
	}
	
	/**
	* Retourne le numéro de la Tuile pleine
	* @return le numéro de la Tuile pleine
	*/
	public int quelTuilePleine()
	{
		for (int i = 0; i < plateau.getTaille(); i++)
			if (plateau.getTuile(i).estEntierementPleine())
				return i;
		
		return -1;
	}
	
	/**
	* Change le paysage d ela Tuile
	* @param choixTuile
	*/
	public void inverserLaTuile(int choixTuile)
	{
		plateau.getTuile(choixTuile).getPaysage().inverserPaysage();
	}
	
	/**
	* Ajoute un Cube sur une Tuile 
	* @param choixTuile
	*/
	public void ajouterCube(int choixTuile)
	{
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			plateau.getTuile(choixTuile).ajouterCubeSurPaysage(sac.getDernierElement());
			sac.supprimerDernierElement();
		}
	}
	
	/**
	* Dertemine si un joeur peut acheter un trophée
	* @param joueur
	*/
	public boolean peutAcheterTrophee(Joueur joueur)
	{
		int cptG=0;
		int cptB=0;
		int cptV=0;
		int cptJ=0;
		int cptR=0;
		
		for ( Cube c : joueur.getListeCube() )
		{
			if ( c.getCouleur().equals("Gris") )
				cptG++;
			if ( c.getCouleur().equals("Vert") )
				cptV++;
			if ( c.getCouleur().equals("Jaune") )
				cptJ++;
			if ( c.getCouleur().equals("Bleu") )
				cptB++;
			if ( c.getCouleur().equals("Rouge") )
				cptR++;
		}
		
		if ( cptG >= 3 || cptB >= 4 || cptV >= 5 || cptJ >= 6 || cptR >= 7)
			return true;
			
		return false;
	}
	
	/**
	* Determine la couleur du trophée que l'on peu prendre
	* @param j
	*/
	public String couleurDispo( Joueur j )
	{
		String s ="Achat de Trophees.\nAchat Possibles :  ";
		
		int cptG=0;
		int cptB=0;
		int cptV=0;
		int cptJ=0;
		int cptR=0;
		
		for ( Cube b : j.getListeCube() )
		{
			if ( b.getCouleur().equals("Gris") )
				cptG++;
			if ( b.getCouleur().equals("Vert") )
				cptV++;
			if ( b.getCouleur().equals("Jaune") )
				cptJ++;
			if ( b.getCouleur().equals("Bleu") )
				cptB++;
			if ( b.getCouleur().equals("Rouge") )
				cptR++;
		}
		
		if ( cptG >= 3 )
			s += "Gris ";
		if ( cptB >= 4 )
			s += "Bleu ";
		if ( cptV >= 5 )
			s += "Vert ";
		if ( cptJ >= 6 )
			s += "Jaune ";
		if ( cptR >= 7 )
			s += "Rouge ";
			
		s += "\nRapel : P=Passer, G=Gris, B=Bleu, V=Vert, R=Rouge, J=Jaune.\nChoississez : ";
			
		return s;
	}
	
	/**
	* Determine si on peut prendre un Trophee
	* @param choixCouleur
	* @param joueur
	* @return true si on ne peut pas prendre une trophée
	*/
	public boolean prendreCubeImpossible(Joueur joueur, char choixCouleur)
	{
		int cpt = 0;
		for (Cube b : joueur.getListeCube())
			if (choixCouleur ==  b.getCouleur().charAt(0))
				cpt++;
	
		int attribut = 0;
		for (Trophee t: listeTrophee)
			if (choixCouleur == t.getCouleur().charAt(0))
				attribut = t.getNumero();
				
		if (cpt >= attribut && attribut > 0)
			return false;
			
		return true;
	}
	
	/**
	* Supprime un Trophee de la liste des trophées
	* @param t
	*/
	public void supprimerTrophee( Trophee t )
	{
		int cpt =0;
		
		for ( Trophee t2 : getListeTrophee() )
			if ( t2.getCouleur().equals(t.getCouleur()) )
				cpt=listeTrophee.indexOf(t2);
			
		listeTrophee.remove(cpt);
	}
	
	/**
	* Permet de savoir quel joueur a le plus de trophee
	* @param dernierJoueur
	* @return le numero du dernier joueur
	*/
	public int quiAPlusDeTrophee(int dernierJoueur)
	{
		if (joueur1.getTrophee() > joueur2.getTrophee())
			return 0;
		else if (joueur1.getTrophee() < joueur2.getTrophee())
			return 0;
		else
			return dernierJoueur;
	}
	
	/**
	* Affiche les noms des joueurs et le plateau
	*/
    public String toString()
    {
		String s;
	
		//affichage plateau entier
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
		Scanner entree;
		//Initialisation des Joueurs
		do
		{
			entree = new Scanner(System.in);
			System.out.print("Veuillez entrer le nom du joueur 1 : ");
			nom1 = entree.nextLine();
		} while (nom1.charAt(0) == ' ');
		
		do
		{	
			entree = new Scanner(System.in);
			System.out.print("Veuillez entrer le nom du joueur 2 : ");
			nom2 = entree.nextLine();
		} while (nom2.charAt(0) == ' ');
		
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
			entree = new Scanner(System.in);
			String str = entree.nextLine();
			choixCoteJ1 = str.charAt(0);
			
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
		int ancienChoixTuile = 0;
		Ballon ballonPose = null;
		char choixCote = ' ';
		char choixCouleur = ' ';
		boolean fini = false;
		boolean joueur1Bloquee = false, joueur2Bloquee = false;
		
		//Boucle de Jeu
		while (jeu.getJoueur1().getTrophee() != 3 || jeu.getJoueur2().getTrophee() != 3)
		{
			while (!jeu.AUneTuilePleine())
			{
				for (int j = 0; j < tabJoueur.length; j++)
				{
					System.out.println("\n" + jeu.getPlateau().toString() + "\n");
					System.out.println("Joueur : " + tabJoueur[j].getNomJoueur() + "\n");
					System.out.println(tabJoueur[j]);
					if (ballonPose != null && ancienChoixTuile < jeu.getPlateau().getTaille())
						System.out.println(tabJoueur[dernierJoueur].getNomJoueur() + " a joueur la carte " + ballonPose  + " sur la tuile " +  jeu.getPlateau().getTuile(ancienChoixTuile).getNom() + "\n");
					
					boolean pass = true;
					boolean impossibleDeJouer = false;
					do
					{
						pass = true;
						// si le joueur ne peut poser aucune carte alors il passe
						if (jeu.impossibleDeJoueur(tabJoueur[j]))
						{
							System.out.println("Vous ne pouvez pas jouer :( ");
							impossibleDeJouer = true;
							if (tabJoueur[j].equals(jeu.getJoueur1()))
								joueur1Bloquee = true;
							else
								joueur2Bloquee = true;
								
							break;
						}
						// on choisit le ballon, la tuile et le cote
						choixBallon = jeu.choisirBallon();
						choixTuile = jeu.choisirTuile();
						choixCote = jeu.choisirCote();
						
						// on verifie le cote n'est pas plein
						if (jeu.cotePlein(choixTuile, choixCote))
							pass = false;
						// on verifie que la carte qu'on met du cote sur la tuile est pas deja sur la tuile
						if (jeu.cubeUtilise(choixTuile, choixCote, tabJoueur[j].getBallon(choixBallon).getCouleur()))
							pass = false;
						// on verifie que la carte que l'on veut mettre est de la bonne couleur
						if (!jeu.verifcouleur(choixTuile, choixCote, tabJoueur[j].getBallon(choixBallon).getCouleur()))
							pass = false;		
					} while (!pass);
					
					// si c'est impossible de jouer pour les 2 joueurs on arrete la partie
					if (joueur1Bloquee && joueur2Bloquee)
					{
						fini = true;
						dernierJoueur = jeu.quiAPlusDeTrophee(dernierJoueur);
						break;
					}
					
					// si le joueur ne peut pas joueur il passe son tour
					if (impossibleDeJouer)
					{
						dernierJoueur = j;
						continue;
					}
						
					ballonPose = tabJoueur[j].getBallon(choixBallon);
					// permet de ne plus "utilise" le cube de la couleur de la carte
					jeu.ajouterCubeUtilise(choixTuile, choixCote, tabJoueur[j].getBallon(choixBallon).getCouleur());
					// on place la carte choisi sur le jeu
					jeu.carteVersTuile(tabJoueur[j], choixBallon, choixCote, choixTuile);
					// si la pioche est vide alors on remet des cartes
					if (jeu.getPioche().estVide())
					jeu.DefausseVersPioche();
					// lorsque le joueur joue il pioche une carte
					jeu.piocher(tabJoueur[j]);
					
					// on regarde si on peut acheter un trophee
					if (jeu.peutAcheterTrophee(tabJoueur[j]))
					{
						entree = new Scanner(System.in);
						System.out.println(" Voulez-vous acheter un Trophee ? ");
						boolean achete = entree.hasNextBoolean();
						
						if (achete)
						{
							do
							{
								entree = new Scanner(System.in);
								System.out.println(jeu.couleurDispo(tabJoueur[j]));
								String str = entree.nextLine();
								choixCouleur = str.charAt(0);
							} while (choixCouleur != 'P' && jeu.prendreCubeImpossible(tabJoueur[j], choixCouleur));
							
							if ( choixCouleur != 'P' )
							{	
								Trophee trophee = null;
								
								int i = 0;
								for (Trophee t: jeu.getListeTrophee())
								{
									if (choixCouleur == jeu.getListeTrophee().get(i).getCouleur().charAt(0))
										trophee = jeu.getListeTrophee().get(i);
										
									i++;
								}
									
								jeu.CubeEnTrophee( tabJoueur[j], trophee.getCouleur());

								System.out.println("Transaction Effectue !");
								jeu.supprimerTrophee(trophee);
								
								// on regarde si le plateau a toutes ces tuiles
								if (jeu.getPlateau().getTaille() < 4)
								{
									int nbTuileEncoreLa = 4 - jeu.getPlateau().getTaille();
									for (int l = 0; l < nbTuileEncoreLa; l++)
									{
										if (jeu.getSac().getNbCube() >= jeu.getPlateau().getPremiereTuileManquante().getAttribut())
										{
											// on recupere la tuile manquante
											Tuile tuile = jeu.getPlateau().getPremiereTuileManquante();
											// on l'ajoute a la bonne place du plateau
											jeu.getPlateau().ajouterTuileManquanteSurPlateau(tuile, tuile.getAttribut());
											// on la supprime de la liste des tuiles manquante
											jeu.getPlateau().supprimerPremiereTuileManquante();
											jeu.ajouterCube(tuile.getAttribut());
										}
									}
								}

							}
							else
								System.out.println("Aucun Achat Effectue !");
						}
					}
					dernierJoueur = j;
					
					ancienChoixTuile = choixTuile;
					// on arrete si le joueur a 3 trophee
					if (tabJoueur[dernierJoueur].getTrophee() == 3)
					{
						fini = true;
						break;
					}
				}
				if (fini)
					break;
			}
			if (fini)
				break;
            // on regarde quel tuile est pleine
			int tuilePleine = jeu.quelTuilePleine();
			// on regarde quel joueur a gagne
			Joueur joueur = jeu.quiAGagne(tabJoueur[dernierJoueur], coteJoueur, tuilePleine);
			// on distribue les cubes au gagnant et on supprime les cubes de la tuile
			jeu.distribuerCube(joueur, tuilePleine);

			// on met les cartes de la tuile fini dans la defausse
			jeu.tuileVersDefausse(tuilePleine);
			// on supprime les cube deja utilise
			jeu.supprimerCubeDejaUtilise(tuilePleine);
            // on inverse la tuile
			jeu.inverserLaTuile(tuilePleine);
			// on regarde si le sac contient assez de cube pour en remettre sur la tuile sinon on la supprime
			if (jeu.getSac().getNbCube() >= jeu.getPlateau().getTuile(tuilePleine).getAttribut())
				jeu.ajouterCube(tuilePleine);
			else
				jeu.getPlateau().supprimerTuile(tuilePleine);
		}
		System.out.println(tabJoueur[dernierJoueur].getNomJoueur() + " a gagné");
	}
}       