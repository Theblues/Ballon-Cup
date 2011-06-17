package Projet.Plateau;

import java.util.ArrayList;
import Projet.Carte.*;
import Projet.Cube.*;

public class Tuile
{
	private Paysage paysage;
	private ArrayList<Ballon> JeuGauche = new ArrayList<Ballon>();
	private ArrayList<Ballon> JeuDroite = new ArrayList<Ballon>();
	private int attribut;
	private static int nbTuile = 0;
    
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
    
	public void ajouterCubeSurPaysage(Cube cube)
	{
		paysage.ajouterCube(cube);
	}
	
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
	
	public int getTaille(char cote)
	{
		if (cote == 'D')
			return JeuDroite.size();
		else
			return JeuGauche.size();
	}
	
	public String toString()
	{
		return paysage.getRecto() + " ("+ attribut +")";
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
}