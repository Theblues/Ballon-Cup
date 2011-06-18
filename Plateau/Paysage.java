package Projet.Plateau;

import Projet.Cube.*;
import java.util.ArrayList;

public class Paysage
{
    private String recto;
    private String verso;
    private ArrayList<Cube> listeCube = new ArrayList<Cube> ();
	private ArrayList<Cube> listeCubeUtiliseAGauche = new ArrayList<Cube>();
	private ArrayList<Cube> listeCubeUtiliseADroite = new ArrayList<Cube>();
	
    
    public Paysage(String recto, String verso)
    {
        this.recto = recto;
        this.verso = verso;
    }
	
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
			for (int i = 0;	i < listeCubeUtiliseAGauche.size(); i++)
				if (cube.equals(listeCubeUtiliseAGauche.get(i)))
					return true;
		
		if (cote == 'D')
			for (int i = 0;	i < listeCubeUtiliseADroite.size(); i++)
				if (cube.equals(listeCubeUtiliseADroite.get(i)))
					return true;
					
		return false;
	}
		
    
    public String getRecto()    {   return recto;   }
    public String getVerso()    {   return verso;   }
    public void ajouterCube(Cube cube)   {   listeCube.add(cube);   }
    public Cube getDernierElement()        {    return listeCube.get(listeCube.size()-1);   }
    public void supprimerDernierElement()  {        listeCube.remove(listeCube.size()-1);   }
	public void supprimerElement(int i)		{		listeCube.remove(i);	}
	public Cube getElement(int nb)	{	return listeCube.get(nb);	}
	
	public void inverserPaysage()
	{
		String s = recto;
		recto = verso;
		verso = s;
	}
    
}