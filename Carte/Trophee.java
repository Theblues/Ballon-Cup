
public class Trophee extends Carte
{
    public Trophee (int num, String couleur)
    {
        super(num, couleur);
    }
    
    public static void main (String[] args)
    {
        Trophee tropheeGris = new Trophee(3, "Gris");
        System.out.println(tropheeGris.getNumero() + tropheeGris.getCouleur());
       }
}

