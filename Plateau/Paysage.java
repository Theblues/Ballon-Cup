package Projet.Plateau;

import Projet.Cube.*;
import java.util.ArrayList;

public class Paysage
{
    private String recto;
    private String verso;
    private ArrayList<Cube> listeCube = new ArrayList<Cube> ();
    
    
    public Paysage(String recto, String verso)
    {
        this.recto = recto;
        this.verso = verso;
    }
    
    public String getRecto()    {   return recto;   }
    public String getVerso()    {   return verso;   }
    public void ajouterCube(Cube cube)   {   listeCube.add(cube);   }
    public Cube getElement()        {    return listeCube.get(listeCube.size()-1);   }
    public void supprimerElement()  {        listeCube.remove(listeCube.size()-1);   }
}