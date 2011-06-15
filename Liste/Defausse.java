package Projet.Liste;

import java.util.ArrayList;
import java.util.Collections;

import Projet.Carte.*;

public class Defausse
{
    private ArrayList<Ballon> defausse = new ArrayList<Ballon>();
    
    public ArrayList<Ballon> getDefausse()      {   return defausse;	    }
    public void ajouterElement(Ballon ballon)   {   defausse.add(ballon);   }
    public void supprimerTout()          {  defausse.clear();    }
}