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
    
    // Les cartes Troph�es
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
        
        // initialisation de la liste des troph�es
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
	
	public int choisirTuile()
	{
		int choix = 0;
		do
		{
			System.out.println("Choissisez une tuile : ");
			choix = Clavier.lire_int();
			choix--;
		} while ( choix > 4 || choix < 0 || tuilePrise(choix));
		
		return choix;
	}
	
	public boolean tuilePrise(int numTuile)
	{
		if (plateau.estTuileUtilise(plateau.getTuile(numTuile)))
			return true;
			
		return false;
	}
	
	
	public boolean choixFini( Joueur joueur, int choixTuile, int choixBallon )
	{
		int cptG, cptD, cptC;
		
		ArrayList<Ballon> gauche = plateau.getTuile(choixTuile).getJeuGauche();
		ArrayList<Ballon> droite = plateau.getTuile(choixTuile).getJeuDroite();
		
		String couleurBallon = joueur.getBallon(choixBallon).getCouleur();
		cptG=0;
		for ( int i = 0; i < gauche.size(); i++ )
		{
			if (couleurBallon.equals(gauche.get(i).getCouleur()))
				cptG++;
		}
		
		cptD=0;
		for ( int i=0; i < droite.size(); i++ )
		{
			if (couleurBallon.equals(droite.get(i).getCouleur()))
				cptD++;
		}
		
		cptC=0;
		for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
		{
			if (couleurBallon.equals(plateau.getTuile(choixTuile).getPaysage().getElement(i).getCouleur()))
				cptC++;
		}
		
		
		System.out.println(cptC);
		
		if ( cptC == cptD && cptC == cptG && cptC != 0)
		
			return true;
		else
			return false;
			
	
	}
	
	public int choisirBallon( Joueur joueur, int choixTuile )
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
			
			if (choixFini(joueur, choixTuile, choixBallon))
			{
				System.out.println("test");
				return -1;
			}
			
			// on parcours tous les cubes de la tuile
			for (int i = 0; i < plateau.getTuile(choixTuile).getAttribut(); i++)
			{
				// on recupere le cube
				cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
				// on parcours les cartes du joueurs
				for (int j = 0; j < NB_CARTE_PAR_JOUEUR; j++)
				{
					// on recupere la carte ballon du joueur
					ballon = joueur.getBallon(j);
					
					// on regarde si le joueur possede une carte de la meme couleur qu'un cube
					if (cube.getCouleur().equals(ballon.getCouleur()))
					{
						// si le cube a la meme couleur que la carte choisi on retourne l'indice de cette carte
						if (cube.getCouleur().equals(joueur.getBallon(choixBallon).getCouleur()))
								return choixBallon;
						stop = true;
					}
				}
			}
			// si le joueur ne possede aucune carte de la meme couleur qu'un cube
			if (!stop)
				return -1;
				
		} while (true);
	}
	
	public char choixCote(int choixTuile, String couleur, Joueur joueur)
	{	
		char choixCote = ' ';
		Cube cube = null;
		do
		{
			System.out.println("Choissisez un cote pour mettre votre carte : ");
			choixCote = Clavier.lire_char();
			
		} while ((choixCote != 'D' && choixCote != 'G') || plateau.getTuile(choixTuile).estPleine(choixCote));
		
		int nbCubeSurTuile = plateau.getTuile(choixTuile).getAttribut();
		
		for (int i = 0; i < nbCubeSurTuile; i++)
		{
			// on recupere le cube
			cube = plateau.getTuile(choixTuile).getPaysage().getElement(i);
			// si la couleur est egale a la couleur du cube
			if (couleur.equals(cube.getCouleur()))
				// si le cube est "deja" utilise
				if (plateau.getTuile(choixTuile).getPaysage().estUtilise(cube, choixCote))
						choixCote = 'Z';
		}
		
		return choixCote;
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
	
	public void ajouterTuileEnDejaUtilise(int choixTuile)
	{
		plateau.ajouterTuileUtilise(plateau.getTuile(choixTuile));
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
			for (int k = 0; k < NB_TUILE; k++)
			{
				System.out.println();
				System.out.println(jeu.getPlateau().toString());
				System.out.println();
				System.out.print("Joueur : ");
				System.out.println(tabJoueur[dernierJoueur].getNomJoueur());
				System.out.println();
				System.out.println(tabJoueur[dernierJoueur]);
				System.out.println();
				choixTuile = jeu.choisirTuile();
				
				for (int i = 0; i < jeu.getPlateau().getTuile(choixTuile).getAttribut(); i++)
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
						
						boolean pass = false;
						do 
						{	
							choixBallon = jeu.choisirBallon(tabJoueur[j], choixTuile);
							
							if ( jeu.getPlateau().getTuile(choixTuile).estEntierementPleine()  )
								break;
							
							// si choixBallon == -1 alors le joueur ne peut pas poser de carte donc il passe
							if (choixBallon == -1)
							{
								System.out.println("Tu n'as aucune carte de la couleur d'un cube de la tuile");
								// ceci est pour ne pas compter dans la boucle des attributs de la tuile
								System.out.println("test sur le i + \t" + i );
								i--;
								break;
							}
							// retourne 'G', 'D' ou 'Z'
							// 'Z' si la carte d'une couleur qu'on veux mettre d'un c�t� est d�ja mise
							choixCote = jeu.choixCote(choixTuile, tabJoueur[j].getBallon(choixBallon).getCouleur(), tabJoueur[j]);
							
							if ( choixCote != 'Z' )
								pass = true;
								
							
						} while (pass == false);
						
						if (choixBallon == -1)
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
				// on regarde quel joueur a gagne
				Joueur joueur = jeu.quiAGagne(tabJoueur[dernierJoueur], coteJoueur, choixTuile);
				// on distribu les cubes au gagnant et on supprime les cubes de la tuile
				jeu.distribuerCube(joueur, choixTuile);
				
				// on ajoute la tuile sur une arraylist de tuile pour pas que les joueurs puissent l'utilise
				jeu.ajouterTuileEnDejaUtilise(choixTuile);
				// on met les cartes de la tuile fini dans la defausse
				jeu.tuileVersDefausse(choixTuile);
				// on supprime les cube deja utilise
				jeu.supprimerCubeDejaUtilise(choixTuile);

			}
			
			//Acheter Trophee
			System.out.println("Souhaitez-vous acheter un trophee ?  (Y/N) ");
			char choixTrophee = Clavier.lire_char();
				
			if ( choixTrophee == 'Y' )
			{
				System.out.println("Choisissez une couleur ? ( G/B/V/J/R ) ");
				char choixCoul = Clavier.lire_char();
					
				Trophee t = null;

					
				if ( choixCoul == 'G' ) { t = jeu.getListeTrophee().get(0); }
				if ( choixCoul == 'B' ) { t = jeu.getListeTrophee().get(1); }
				if ( choixCoul == 'V' ) { t = jeu.getListeTrophee().get(2); }
				if ( choixCoul == 'J' ) { t = jeu.getListeTrophee().get(3); }
				if ( choixCoul == 'R' ) { t = jeu.getListeTrophee().get(4); }
					
				if ( acheterTrophee( tabJoueur[dernierJoueur], t  ) )
				
					System.out.println("Transaction �ffectu� !");
				else
					System.out.println("Transfert non effectu� !");
			}
			
			
			// on inverse les tuiles (plaine => montagne et montagne => plaine)
			jeu.inverserTuile();
			jeu.initialiserCubeSurTuile();
			
		}		
	}
}       