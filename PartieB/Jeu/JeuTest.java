package Projet.Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.fusesource.jansi.*;

import Projet.Jeu.*;
import Projet.Liste.*;
import Projet.Carte.*;
import Projet.Joueur.*;
import Projet.Couleur.*;
import Projet.Plateau.*;
import Projet.Cube.*;
import iut.algo.Console;


import java.io.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
* @author Erwan Lebrun
* @author Jeremy Lebair
* @author Sarah Vernichon
* @version 1.0
*/
public class JeuTest
{
    
	public static void main (String[] args)
	{
		// initialisation des couleurs
		AnsiConsole.systemInstall();
	  
		Jeu jeu = new Jeu();
		int numJoueur=0;
		boolean peutPasJouer = true;
		
		ObjectInputStream in;
		ObjectOutputStream out;
		
		File fichier  = new File ( "jeu.dat" );
		

		char choixCoteJ1 = ' ', choixCoteJ2 = ' ';
		
		if ( !fichier.exists() )
		{	
		
			
			//Nouveau Jeu
			jeu.initialiserJeu();
			
			
			//Initialisation des Joueurs
			Scanner entree = new Scanner(System.in);
			System.out.println("Veuillez entrer le nom du joueur 1 : ");
			String nom1 = entree.nextLine();
			
			jeu.setJoueur1(nom1);
			
			
			
			
			
			numJoueur=1;
			
			try {	
					out = new ObjectOutputStream(new FileOutputStream(fichier));
					out.writeObject(jeu);
					out.close();
					
			} catch (Exception e) {e.printStackTrace();}
		}
		else
		{

			try {
				
					in = new ObjectInputStream(new FileInputStream(fichier));
					jeu = (Jeu)in.readObject();
					in.close();
				
				
			} catch (Exception e) {e.printStackTrace();}
		
		
			Scanner entree = new Scanner(System.in);
			System.out.println("Veuillez entrer le nom du joueur 2 : ");
			String nom2 = entree.nextLine();
			
			jeu.setJoueur2(nom2);
			
			numJoueur=2;
			
			//Partie commence !
			jeu.setNumJoueurEnCour(1);
	
			try {	
					out = new ObjectOutputStream(new FileOutputStream(fichier));
					out.writeObject(jeu);
					out.close();
					
			} catch (Exception e) {e.printStackTrace();}
				
		}
		
		
		if (numJoueur == 1)
		{
			do
			{
				System.out.println("Choisissez votre cote (G/D): ");
				Scanner entreee = new Scanner(System.in);
				String str = entreee.nextLine();
				choixCoteJ1 = str.charAt(0);
				
			} while (choixCoteJ1 != 'G' && choixCoteJ1 != 'D');
			
			if ( choixCoteJ1 == 'G')		choixCoteJ2 = 'D';
			else							choixCoteJ2 = 'G';
			
			char[][] coteJoueur = { {     '1'    ,     '2'     },
							{ choixCoteJ1, choixCoteJ2 },};
			
			jeu.setCoteJoueur( coteJoueur );
			
			
			try {	
					out = new ObjectOutputStream(new FileOutputStream(fichier));
					out.writeObject(jeu);
					out.close();
					
			} catch (Exception e) {e.printStackTrace();}
		}
			
						
		Joueur[] tabJoueur = { jeu.getJoueur1(), jeu.getJoueur2() };
			
		int choixTuile = 0;
		int choixBallon = 0;
		int dernierJoueur = 0;
		char choixCote = ' ';
		char choixCouleur = ' ';
		boolean fini = false;
		boolean joueur1Bloquee = false, joueur2Bloquee = false;
		
		
		
		
		try {
				
			in = new ObjectInputStream(new FileInputStream(fichier));
			jeu = (Jeu)in.readObject();
			in.close();
				
		} catch (Exception e) {e.printStackTrace();}
		

		
		
	
		//Boucle de Jeu
		while (jeu.getJoueur1().getTrophee() != 3 || jeu.getJoueur2().getTrophee() != 3)
		{
			while (!jeu.AUneTuilePleine())
			{

				try {
				
					in = new ObjectInputStream(new FileInputStream(fichier));
					jeu = (Jeu)in.readObject();
					in.close();
				
				} catch (Exception e) {e.printStackTrace();}
			
				while ( peutPasJouer )
					{
						try 
						{

							in = new ObjectInputStream(new FileInputStream(fichier));
							jeu = (Jeu)in.readObject();
							
							if ( jeu.getNumJoueurEnCour() != numJoueur )
							{
								System.out.println("Veuillez patientez un Instant S.V.P...");
								SECONDS.sleep(5);		
							}
							else
							{
								peutPasJouer = false;
							}
							in.close();
						}
						catch ( Exception e) { e.printStackTrace(); }
					}
				
					System.out.println("\n" + jeu.getPlateau().toString() + "\n");
					System.out.println("Joueur : " + tabJoueur[jeu.getNumJoueurEnCour()-1].getNomJoueur() + "\n");
					System.out.println(tabJoueur[jeu.getNumJoueurEnCour()-1].toString() + "\n");
					
					boolean pass = true;
					boolean impossibleDeJouer = false;
					do
					{
						pass = true;
						// si le joueur ne peut poser aucune carte alors il passe
						if (jeu.impossibleDeJoueur(tabJoueur[jeu.getNumJoueurEnCour()-1]))
						{
							System.out.println("Vous ne pouvez pas jouer :( ");
							impossibleDeJouer = true;
							if (tabJoueur[jeu.getNumJoueurEnCour()-1].equals(jeu.getJoueur1()))
								joueur1Bloquee = true;
							else
								joueur2Bloquee = true;
								
							break;
						}
						choixBallon = jeu.choisirBallon();
						choixTuile = jeu.choisirTuile();
						choixCote = jeu.choisirCote();
					
						if (jeu.cotePlein(choixTuile, choixCote))
							pass = false;
						if (jeu.cubeUtilise(choixTuile, choixCote, tabJoueur[jeu.getNumJoueurEnCour()-1].getBallon(choixBallon).getCouleur()))
							pass = false;
						if (!jeu.verifcouleur(choixTuile, choixCote, tabJoueur[jeu.getNumJoueurEnCour()-1].getBallon(choixBallon).getCouleur()))
							pass = false;		
					} while (!pass);
					
					if (joueur1Bloquee && joueur2Bloquee)
					{
						fini = true;
						dernierJoueur = jeu.quiAPlusDeTrophee(dernierJoueur);
						break;
					}
					
					if (impossibleDeJouer)
						continue;
						
					// permet de ne plus "utilise" le cube de la couleur de la carte
					jeu.ajouterCubeUtilise(choixTuile, choixCote, tabJoueur[jeu.getNumJoueurEnCour()-1].getBallon(choixBallon).getCouleur());
					// on place la carte choisi sur le jeu
					jeu.carteVersTuile(tabJoueur[jeu.getNumJoueurEnCour()-1], choixBallon, choixCote, choixTuile);
					// si la pioche est vide alors on remet des cartes
					if (jeu.getPioche().estVide())
						jeu.DefausseVersPioche();
					// lorsque le joueur joue il pioche une carte
					jeu.piocher(tabJoueur[jeu.getNumJoueurEnCour()-1]);
					
					try {	
						out = new ObjectOutputStream(new FileOutputStream(fichier));
						out.writeObject(jeu);
						out.close();
						
					} catch (Exception e) {e.printStackTrace();}	
					
					if (jeu.peutAcheterTrophee(tabJoueur[jeu.getNumJoueurEnCour()-1]))
					{
						Scanner entree = new Scanner(System.in);
						System.out.println(" Voulez-vous acheter un Trophee ? ");
						boolean achete = entree.hasNextBoolean();
						
						if (achete)
						{
							do
							{
								entree = new Scanner(System.in);
								System.out.println(jeu.couleurDispo(tabJoueur[jeu.getNumJoueurEnCour()-1]));
								String str = entree.nextLine();
								choixCouleur = str.charAt(0);
								
							} while (choixCouleur != 'P' || jeu.prendreCubeImpossible(tabJoueur[jeu.getNumJoueurEnCour()-1], choixCouleur));
							
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
									
								jeu.acheterTrophee( tabJoueur[jeu.getNumJoueurEnCour()-1], trophee);

								System.out.println("Transaction Effectue !");
								jeu.supprimerTrophee(trophee);

							}
							else
								System.out.println("Aucun Achat Effectue !");
						}
					}
					dernierJoueur = jeu.getNumJoueurEnCour()-1;
					
					try {
				
						in = new ObjectInputStream(new FileInputStream(fichier));
						jeu = (Jeu)in.readObject();
						in.close();
						
					} catch (Exception e) {e.printStackTrace();}
					
					if ( jeu.getNumJoueurEnCour() == 1 )
						
							jeu.setNumJoueurEnCour(2);
						else
							jeu.setNumJoueurEnCour(1);
					
					if (tabJoueur[dernierJoueur].getTrophee() == 3)
					{
						fini = true;
						break;
					}
					
					peutPasJouer = true;
					
					
					try {	
						out = new ObjectOutputStream(new FileOutputStream(fichier));
						out.writeObject(jeu);
						out.close();
						
					} catch (Exception e) {e.printStackTrace();}	
					
				
				if (fini)
					break;
			}
			if (fini)
				break;
				
			try {
				
				in = new ObjectInputStream(new FileInputStream(fichier));
				jeu = (Jeu)in.readObject();
				in.close();
				
			} catch (Exception e) {e.printStackTrace();}
				
			int tuilePleine = jeu.quelTuilePleine();
			// on regarde quel joueur a gagne
			
					
			Joueur joueur = jeu.quiAGagne(tabJoueur[dernierJoueur], jeu.getCoteJoueur(), tuilePleine);
			// on distribue les cubes au gagnant et on supprime les cubes de la tuile
			jeu.distribuerCube(joueur, tuilePleine);
			
			// on met les cartes de la tuile fini dans la defausse
			jeu.tuileVersDefausse(tuilePleine);
			// on supprime les cube deja utilise
			jeu.supprimerCubeDejaUtilise(tuilePleine);
			jeu.inverserLaTuile(tuilePleine);
			jeu.ajouterCube(tuilePleine);
			
			try {	
				out = new ObjectOutputStream(new FileOutputStream(fichier));
				out.writeObject(jeu);
				out.close();
						
			} catch (Exception e) {e.printStackTrace();}
		}
		System.out.println(tabJoueur[dernierJoueur].getNomJoueur() + " a gagné"); 
	}
}