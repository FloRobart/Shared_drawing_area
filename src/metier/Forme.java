package metier;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static metier.Metier.TAILLE_PLATEAU;
import java.awt.Color;


public class Forme
{
    private static final int TYPE_CERCLE = 0;
    private static final int TYPE_LIGNE  = 1;
    private static final int TYPE_RECT   = 2;

    private int xDeb;
    private int yDeb;
    private int xFin;
    private int yFin;
    private int type;
    private Color couleur;


    public Forme(int xDeb, int yDeb, int xFin, int yFin, int type, Color couleur)
    {
        this.xDeb = xDeb;
        this.yDeb = yDeb;
        this.xFin = xFin;
        this.yFin = yFin;
        this.type = type;
        this.couleur = couleur;
    }

    public Forme(int xDeb, int yDeb, int type, Color couleur)
    {
        this(xDeb, yDeb, xDeb, yDeb, type, couleur);
    }

    public int   getxDeb   () { return xDeb; }
    public int   getyDeb   () { return yDeb; }
    public int   getxFin   () { return xFin; }
    public int   getyFin   () { return yFin; }
    public Color getCouleur() { return couleur; }

    public void setxDeb   (int   xDeb   ) { this.xDeb = xDeb; }
    public void setyDeb   (int   yDeb   ) { this.yDeb = yDeb; }
    public void setxFin   (int   xFin   ) { this.xFin = min(max(xFin, 0), TAILLE_PLATEAU[0]); }
    public void setyFin   (int   yFin   ) { this.yFin = min(max(yFin, 0), TAILLE_PLATEAU[1]); }
    public void setCouleur(Color couleur) { this.couleur = couleur; }
}
