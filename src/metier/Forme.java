package metier;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static metier.Metier.TAILLE_PLATEAU;
import java.awt.Color;


public class Forme
{
    public static final int TYPE_CERCLE = 0;
    public static final int TYPE_LIGNE  = 1;
    public static final int TYPE_RECT   = 2;

    private int xDeb;
    private int yDeb;
    private int xFin;
    private int yFin;
    private int type;
    private boolean rempli;
    private Color couleur;


    public Forme(int xDeb, int yDeb, int xFin, int yFin, int type, boolean rempli, Color couleur)
    {
        this.xDeb = xDeb;
        this.yDeb = yDeb;
        this.xFin = xFin;
        this.yFin = yFin;
        this.type = type;
        this.rempli = rempli;
        this.couleur = couleur;
    }

    public Forme(int xDeb, int yDeb, int type, boolean rempli, Color couleur)
    {
        this(xDeb, yDeb, xDeb, yDeb, type, rempli, couleur);
    }

    public int   getXDeb   () { return xDeb; }
    public int   getYDeb   () { return yDeb; }
    public int   getXFin   () { return xFin; }
    public int   getYFin   () { return yFin; }
    public int   getType   () { return type; }
    public boolean isRempli() { return rempli; }
    public Color getCouleur() { return couleur; }

    public boolean isIn(int x, int y)
    {
        // TODO : fait avec copilot
        if (type == TYPE_CERCLE)
        {
            int xM = (xDeb + xFin) / 2;
            int yM = (yDeb + yFin) / 2;
            int r  = (xFin - xDeb) / 2;
            return (x - xM) * (x - xM) + (y - yM) * (y - yM) <= r * r;
        }
        else if (type == TYPE_LIGNE)
        {
            int xM = (xDeb + xFin) / 2;
            int yM = (yDeb + yFin) / 2;
            int r  = 5;
            return (x - xM) * (x - xM) + (y - yM) * (y - yM) <= r * r;
        }
        else if (type == TYPE_RECT)
        {
            return x >= xDeb && x <= xFin && y >= yDeb && y <= yFin;
        }
        return false;
    }

    public void setXDeb   (int   xDeb   ) { this.xDeb = xDeb; }
    public void setYDeb   (int   yDeb   ) { this.yDeb = yDeb; }
    public void setXFin   (int   xFin   ) { this.xFin = min(max(xFin, 0), TAILLE_PLATEAU[0]); }
    public void setYFin   (int   yFin   ) { this.yFin = min(max(yFin, 0), TAILLE_PLATEAU[1]); }
    public void setType   (int   type   ) { this.type = type; }
    public void setRemplis(boolean rempli) { this.rempli = rempli; }
    public void setCouleur(Color couleur) { this.couleur = couleur; }

    public void serialisable()
    {
        // TODO
    }

    public void deserialisable()
    {
        // TODO
    }
}
