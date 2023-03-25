package metier;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static metier.Metier.TAILLE_PLATEAU;
import java.awt.Color;
import java.awt.Point;
import java.util.UUID;


public class Forme implements java.io.Serializable
{

    public static final int TYPE_CERCLE = 0;
    public static final int TYPE_LIGNE  = 1;
    public static final int TYPE_RECT   = 2;
    public static final int TYPE_TEXT   = 3;

    private int xDeb;
    private int yDeb;
    private int xFin;
    private int yFin;
    private int stroke;
    private int type;
    private boolean rempli;
    private Color couleur;
    private String text;

    private int xOrig;
    private int yOrig;

    private String id;


    public Forme(int xDeb, int yDeb, int xFin, int yFin, int stroke, int type, boolean rempli, Color couleur)
    {
        
        // make if a guid
        this.id = UUID.randomUUID().toString();
        this.xDeb = min(xDeb, xFin);
        this.yDeb = min(yDeb, yFin);
        this.xFin = max(xDeb, xFin);
        this.yFin = max(yDeb, yFin);
        this.stroke = stroke;
        this.type = type;
        this.rempli = rempli;
        this.couleur = couleur;
        this.text = "";

        this.xOrig = xDeb;
        this.yOrig = yDeb;
    }

    public Forme(int xDeb, int yDeb, int stroke, int type, boolean rempli, Color couleur)
    {
        this(xDeb, yDeb, xDeb, yDeb, stroke, type, rempli, couleur);
    }

    /**
     * Constructeur pour les textes
     * @param xDeb : x du coin haut gauche
     * @param yDeb : y du coin haut gauche
     * @param text : texte
     * @param couleur : couleur du texte
     */
    public Forme(int xDeb, int yDeb, String text, Color couleur)
    {
        this(xDeb, yDeb, xDeb, yDeb, 0, Forme.TYPE_TEXT, false, couleur);
        this.text = text;
    }

    public int     getXDeb   () { return xDeb; }
    public int     getYDeb   () { return yDeb; }
    public int     getXFin   () { return xFin; }
    public int     getYFin   () { return yFin; }
    public int     getStroke () { return stroke; }
    public int     getType   () { return type; }
    public boolean isRempli  () { return rempli; }
    public Color   getCouleur() { return couleur; }
    public String  getText   () { return text; }

    public int     getXOrig  () { return xOrig;}
    public int     getYOrig  () { return yOrig;}

    public String  getId     () { return id; }
    
    public boolean isIn(int posx, int posy)
    {
        if (type == TYPE_RECT) /* Rectangle */
        { // fonctionne
            if (rempli)
                return posx >= xDeb && posx <= xFin && posy >= yDeb && posy <= yFin;
            else
                return posx >= xDeb-(stroke/2) && posx <= xFin+(stroke/2) && posy >= yDeb-(stroke/2) && posy <= yFin+(stroke/2) && !(posx >= xDeb+(stroke/2) && posx <= xFin-(stroke/2) && posy >= yDeb+(stroke/2) && posy <= yFin-(stroke/2));
        }
        else if (type == TYPE_CERCLE) /* Cercle */
        { // fonctionne pas
            int width = xFin - xDeb;
            int height = yFin - yDeb;
            Point s1 = new Point(xDeb + width/2, yDeb);
            Point s2 = new Point(xDeb + width, yDeb + height/2);
            Point s3 = new Point(xDeb + width/2, yDeb + height);
            Point s4 = new Point(xDeb, yDeb + height/2);

            double grandAxe = max(width, height);
            double petitAxe = min(width, height);

            double h = s1.getX();
            double k = s1.getY();

            double a = grandAxe/2;
            double b = petitAxe/2;




            double f1 = -1;
            double f2 = -1;
        }
        else if (type == TYPE_LIGNE) /* Ligne */
        {
            // fonctionne
            double m = (double)(yFin-yDeb)/(double)(xFin-xDeb); // OK
            double b = yDeb + ((double)(m*xDeb) * (-1)); // OK

            double y = m*posx+b;

            return posy >= y-stroke  && posy <= y+stroke && (posx >= xDeb-1 && posx <= xFin+1 || posx >= xFin+1 && posx <= xDeb+1);
        }
        else if (type == TYPE_TEXT) /* Texte */
        {
            // fonctionne
            int xDebRect = this.xDeb;
            int xFinRect = xDebRect + ((this.yFin - this.yDeb) * (int)(this.text.length()/1.5));
            int yDebRect = this.yDeb - (int)((this.yFin-this.yDeb) * 0.8);
            int yFinRect = yDebRect + (this.yFin - this.yDeb);

            return posx >= xDebRect && posx <= xFinRect && posy >= yDebRect && posy <= yFinRect;
        }

        return false;
    }

    public void setXDeb   (int   xDeb    ) { this.xDeb = xDeb; }
    public void setYDeb   (int   yDeb    ) { this.yDeb = yDeb; }
    public void setXFin   (int   xFin    ) { this.xFin = min(max(xFin, 0), TAILLE_PLATEAU[0]); }
    public void setYFin   (int   yFin    ) { this.yFin = min(max(yFin, 0), TAILLE_PLATEAU[1]); }
    public void setStroke (int   stroke  ) { this.stroke = stroke; }
    public void setType   (int   type    ) { this.type = type; }
    public void setRempli (boolean rempli) { this.rempli = rempli; }
    public void setCouleur(Color couleur ) { this.couleur = couleur; }
    public void setText   (String text   )
    {
        if (type != TYPE_TEXT) throw new RuntimeException("La forme n'est pas un texte");
        this.text = text;
    }

    public void setXOrig(int x1, int x2) {
        this.xDeb = this.type == TYPE_LIGNE ? x1 : min(x1, x2);
        this.xFin = this.type == TYPE_LIGNE ? x2 : max(x1, x2);
    }

    public void setYOrig(int y1, int y2) {
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
