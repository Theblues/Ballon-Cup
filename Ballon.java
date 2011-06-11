
public class Ballon extends Carte
{
    public Ballon (int num, String couleur)
    {
        super(num, couleur);
    }
    
    // modifiable comme vous le souhaitez
    public String toString()
    {
        return "Carte de type ballon" + super.toString();
    }
}