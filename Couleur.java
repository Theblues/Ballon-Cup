public enum Couleur
{
	ROUGE ( "Rouge"),
    JAUNE ( "Jaune"),
	VERT  ( "Vert"),
	BLEU  ( "Bleu"),
	GRIS( "Gris");

	private String libelle;
    
    Couleur (String libelle)
    {
        this.libelle = libelle;
    }
    
    public String getLibelle()  {   return libelle; }
}