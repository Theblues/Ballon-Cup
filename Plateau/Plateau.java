package Projet.Plateau;

import java.util.ArrayList;

public class Plateau
{
    private ArrayList<Tuile> plateau = new ArrayList<Tuile>();
    private int choix;
    
    public void ajouterTuile(Tuile tuile)
    {
        plateau.add (tuile);
    }
    
	public void choixTuile(int choix)   {        this.choix = choix;            }
	public Tuile getTuile(int choix)             {        return plateau.get(choix);     }
	public ArrayList<Tuile> getPlateau() { return plateau; }
    
	public String toString()
	{
		String  s ="";
		
		for (Tuile t: plateau)
		{
			s += t.toString();
			s += "\n";
			for(int i = 0; i < t.getAttribut();	++i)
			{
				s += t.getPaysage().getElement(i);
				s += " ";
			}
			s += "\n";
		}
			
		return s;
	}
	
	public void afficherplateau()
	{
		for (Tuile t: plateau)	System.out.println(t);
	}
}