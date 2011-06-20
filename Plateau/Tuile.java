package Projet.Plateau;

import java.util.ArrayList;
import Projet.Carte.*;
import Projet.Cube.*;

public class Tuile
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
	private Paysage paysage;
	private ArrayList<Ballon> JeuGauche = new ArrayList<Ballon>();
	private ArrayList<Ballon> JeuDroite = new ArrayList<Ballon>();
	private int attribut;
	private static int nbTuile = 0;
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
	public Tuile(Paysage paysage)
	{
		this.paysage = paysage;
		attribut = ++nbTuile;
	}
    
	public void ajouterBallon(Ballon ballon, char cote)
	{
		if (cote == 'G')	JeuGauche.add(ballon);
		else				JeuDroite.add(ballon);
	}
    
	public void ajouterCubeSurPaysage(Cube cube)	{	paysage.ajouterCube(cube);	}
	
	public int getElementJeu (char cote)
	{
		if (cote == 'G')	return JeuGauche.size();
		else				return JeuDroite.size();
	}
    
	public Paysage getPaysage()     {   return paysage;     }
	public int getAttribut()        {   return attribut;    }
	
	public boolean aUneCarte()
	{
		if (JeuGauche.size() == 0 || JeuDroite.size() == 0)
			return false;
		
		return true;
	}
	
	public boolean estPleine (char cote)
	{
		if (cote == 'G')
			if (JeuGauche.size() == attribut)
				return true;
		
		if (cote == 'D')
			if (JeuDroite.size() == attribut)
				return true;
		
		return false;
	}
	
	public int getResultat(char cote)
	{
		int resultat = 0;
		if (cote == 'D')
			for (Ballon b: JeuDroite)
				resultat += b.getNumero();
		else if (cote == 'G')
			for (Ballon b: JeuGauche)
				resultat += b.getNumero();
				
		return resultat;
	}
	
	public String toString()
	{

		String s = "" ;


			s = "                ";
			
			for (int i =0; i < attribut; i++ )
				s += afficherCote(JeuGauche, i);

			s +=  paysage.getRecto() + " ";
			
			for (int i =0; i < attribut ; i++ )
				s += afficherCote(JeuDroite, i);
		
		
	return s ;
	}
	
	public String afficherCote( ArrayList<Ballon> arr, int ind )
	{
		String s="";
		
		if ( arr.isEmpty() || arr.size() <= ind  )
		
			s += "[ ] ";

		else
		{
			s += " ";
			s += arr.get(ind).toString();
			s += " ";
		}
		
	return s;
	}
	
	public Ballon getBallon (int num,char cote)
	{
		if (cote == 'D')
			return JeuDroite.get(num);
		else
			return JeuGauche.get(num);
	}
	
	public void supprimerCarte(int num, char cote)
	{
		if (cote == 'D')
			JeuDroite.remove(num);
		else
			JeuGauche.remove(num);
	}
	
	public void supprimerToutesLesCartes()
	{
		JeuDroite.clear();
		JeuGauche.clear();
	}
}