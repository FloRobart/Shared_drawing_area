package metier;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static metier.Metier.TAILLE_PLATEAU;
import java.awt.Color;


public class Forme implements java.io.Serializable
{

    public static int id_compteur = 0;

    public static final int TYPE_CERCLE = 0;
    public static final int TYPE_LIGNE  = 1;
    public static final int TYPE_RECT   = 2;
    public static final int TYPE_TEXT   = 3;

    private int xDeb;
    private int yDeb;
    private int xFin;
    private int yFin;
    private int type;
    private boolean rempli;
    private Color couleur;
    private String text;

    private int xOrig;
    private int yOrig;


    public Forme(int xDeb, int yDeb, int xFin, int yFin, int type, boolean rempli, Color couleur)
    {
        
        
        this.xDeb = Math.min(xDeb, xFin);
        this.yDeb = Math.min(yDeb, yFin);
        this.xFin = Math.max(xDeb, xFin);
        this.yFin = Math.max(yDeb, yFin);
        this.type = type;
        this.rempli = rempli;
        this.couleur = couleur;
        this.text = "";

        this.xOrig = xDeb;
        this.yOrig = yDeb;
    }

    public Forme(int xDeb, int yDeb, int type, boolean rempli, Color couleur)
    {
        this(xDeb, yDeb, xDeb, yDeb, type, rempli, couleur);
    }

    public int     getXDeb   () { return xDeb; }
    public int     getYDeb   () { return yDeb; }
    public int     getXOrig  () { return xOrig;}
    public int     getYOrig  () { return yOrig;}
    public int     getXFin   () { return xFin; }
    public int     getYFin   () { return yFin; }
    public int     getType   () { return type; }
    public boolean isRempli  () { return rempli; }
    public Color   getCouleur() { return couleur; }
    public String  getText   () { return text; }

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

    public void setXDeb   (int   xDeb    ) { this.xDeb = xDeb; }
    public void setYDeb   (int   yDeb    ) { this.yDeb = yDeb; }
    public void setXFin   (int   xFin    ) { this.xFin = min(max(xFin, 0), TAILLE_PLATEAU[0]); }
    public void setYFin   (int   yFin    ) { this.yFin = min(max(yFin, 0), TAILLE_PLATEAU[1]); }
    public void setType   (int   type    ) { this.type = type; }
    public void setRempli (boolean rempli) { this.rempli = rempli; }
    public void setCouleur(Color couleur ) { this.couleur = couleur; }
    public void setText   (String text   )
    {
        if (type != TYPE_TEXT) throw new RuntimeException("La forme n'est pas un texte");
        this.text = text;
    }

    public void setX(int x1, int x2) {
        this.xDeb = this.type == TYPE_LIGNE ? x1 : min(x1, x2);
        this.xFin = this.type == TYPE_LIGNE ? x2 : max(x1, x2);
    }

    public void setY(int y1, int y2) {
        this.yDeb = this.type == TYPE_LIGNE ? y1 : min(y1, y2);
        this.yFin = this.type == TYPE_LIGNE ? y2 : max(y1, y2);
    }

    public void resetOrig() {
        xOrig = xDeb;
        yOrig = yDeb;
    }

    public void serialisable()
    {
        // TODO
    }

    public void deserialisable()
    {
        // TODO
    }
}
