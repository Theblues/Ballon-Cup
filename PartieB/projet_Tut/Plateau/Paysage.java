package Projet.Plateau;
import java.io.*;
import Projet.Cube.*;
import java.util.ArrayList;

public class Paysage implements Serializable
{
	/*********************/
    /***** ATTRIBUTS *****/
    /*********************/
	
    private String recto;
    private String verso;
    private ArrayList<Cube> listeCube = new ArrayList<Cube> ();
	private ArrayList<Cube> listeCubeUtiliseAGauche = new ArrayList<Cube>();
	private ArrayList<Cube> listeCubeUtiliseADroite = new ArrayList<Cube>();
    
	/********************/
	/*** CONSTRUCTEUR ***/
	/********************/
	
    public Paysage(String recto, String verso)
    {
        this.recto = recto;
        this.verso = verso;
    }
	
	/*****************/
	/*** ACCESSEUR ***/
	/*****************/
	
	public String getRecto()    			{   return recto;   }
    public String getVerso()    			{   return verso;   }
	public Cube getDernierElement()        	{   return listeCube.get(listeCube.size()-1);   }
	public Cube getElement(int nb)			{	return listeCube.get(nb);	}
	
	public void ajouterCubeUtilise(Cube cube, char choixCote)	
	{
		if (choixCote == 'G')
			listeCubeUtiliseAGauche.add(cube);
		
		if (choixCote == 'D')
			listeCubeUtiliseADroite.add(cube);
	}
	
	public boolean estUtilise(Cube cube, char cote)
	{
		if (cote == 'G')
			// on parcours la liste des cubes utilises
			for (int i = 0; i < listeCubeUtiliseAGauche.size(); i++)
				// si le cube est egaux a celui dans la liste des cubes utilises
				if (cube.equals(listeCubeUtiliseAGauche.get(i)))
					// si le nombre de cube par couleur est atteint
					if (nbCubeDansListeCube(cube.getCouleur()) == nbCubeDansListeCubeUtilise(cube.getCouleur(), cote))
						return true;

		if (cote == 'D')
			for (int i = 0; i < listeCubeUtiliseADroite.size(); i++)
				if (cube.equals(listeCubeUtiliseADroite.get(i)))
					if (nbCubeDansListeCube(cube.getCouleur()) == nbCubeDansListeCubeUtilise(cube.getCouleur(), cote))
						return true;
					
		return false;
	}

	public int nbCubeDansListeCube(String couleur)
	{
		int cpt = 0;
		for (int i = 0; i < listeCube.size(); i++)
			if (couleur.equals(getElement(i).getCouleur()))
				cpt++;
				
		return cpt;
	}
	
	public int nbCubeDansListeCubeUtilise(String couleur, char cote)
	{
		int cpt = 0;
		if (cote == 'G')
			for (int i = 0;	i < listeCubeUtiliseAGauche.size(); i++)
				if (couleur.equals(listeCubeUtiliseAGauche.get(i).getCouleur()))
					cpt++;
		
		if (cote == 'D')
			for (int i = 0;	i < listeCubeUtiliseADroite.size(); i++)
				if (couleur.equals(listeCubeUtiliseADroite.get(i).getCouleur()))
					cpt++;
					
		return cpt;
	}
	
    
    public void ajouterCube(Cube cube)   	{   listeCube.add(cube);   }
    public void supprimerDernierElement()  	{	listeCube.remove(listeCube.size()-1);   }
	public void supprimerElement(int i)		{	listeCube.remove(i);	}
	public void supprimerTousLesElements()	{	listeCube.clear();	}
	public void supprimerCubeDejaUtilise()  {	listeCubeUtiliseAGauche.clear();	listeCubeUtiliseADroite.clear();	}
	
	public void inverserPaysage()
	{
		String s = recto;
		recto = verso;
		verso = s;
	}
    
}