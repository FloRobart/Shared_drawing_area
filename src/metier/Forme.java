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


    private Forme()
    {
        this(0, 0, 0, 0, 0, 0, false, Color.BLACK);
    }

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
            if (!rempli)
            {
                double dif = (stroke/2);

                /* Extérieur */
                double xDebExt = xDeb-dif;
                double yDebExt = yDeb-dif;
                double xFinExt = xFin+dif;
                double yFinExt = yFin+dif;

                /* Intérieur */
                double xDebInt = xDeb+dif;
                double yDebInt = yDeb+dif;
                double xFinInt = xFin-dif;
                double yFinInt = yFin-dif;


                return (Math.pow(((posx-((xDebExt+xFinExt)/2))/(Math.abs(xFinExt-xDebExt)/2)), 2) + Math.pow(((posy-((yDebExt+yFinExt)/2))/(Math.abs(yFinExt-yDebExt)/2)), 2)) <= 1 &&
                       (Math.pow(((posx-((xDebInt+xFinInt)/2))/(Math.abs(xFinInt-xDebInt)/2)), 2) + Math.pow(((posy-((yDebInt+yFinInt)/2))/(Math.abs(yFinInt-yDebInt)/2)), 2)) >= 1;
            }

            return (Math.pow(((posx-(((double)xDeb+(double)xFin)/2))/(Math.abs((double)xFin-(double)xDeb)/2)), 2) + Math.pow(((posy-(((double)yDeb+(double)yFin)/2))/(Math.abs((double)yFin-(double)yDeb)/2)), 2)) <= 1;
        }
        else if (type == TYPE_LIGNE) /* Ligne */
        {
            // fonctionne
            double m = (double)(yFin-yDeb)/(double)(xFin-xDeb); // OK
            double b = yDeb + ((double)(m*xDeb) * (-1)); // OK

            double y = m*posx+b;

            return posy >= y-(stroke/2)  && posy <= y+(stroke/2) && (posx >= xDeb-1 && posx <= xFin+1 || posx >= xFin+1 && posx <= xDeb+1);
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
    private void setId    (String id     ) { this.id = id; }
    private void setXOrig (int   xOrig   ) { this.xOrig = xOrig; }
    private void setYOrig (int   yOrig   ) { this.yOrig = yOrig; }

    public void setText   (String text   )
    {
        if (type != TYPE_TEXT) throw new IllegalArgumentException("La forme n'est pas un texte");
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


    //private int xDeb;
    //private int yDeb;
    //private int xFin;
    //private int yFin;
    //private int stroke;
    //private int type;
    //private boolean rempli;
    //private Color couleur;
    //private String text;
//
    //private int xOrig;
    //private int yOrig;
//
    //private String id;


    public String serialisable()
    {
        String sRet = "";

        sRet += xDeb + ";" + yDeb + ";" + xFin + ";" + yFin + ";" + stroke + ";" + type + ";" + rempli + ";" + couleur.getRGB() + ";" + xOrig + ";" + yOrig + ";" + id + ";" + text + ";";


        return sRet;
    }

    public static Forme deserialisable(int xDeb, int yDeb, int xFin, int yFin, int stroke, int type, boolean rempli, Color couleur, String text, int xOrig, int yOrig, String id)
    {
        Forme forme = new Forme();
        forme.setXDeb(xDeb);
        forme.setYDeb(yDeb);
        forme.setXFin(xFin);
        forme.setYFin(yFin);
        forme.setStroke(stroke);
        forme.setType(type);
        forme.setRempli(rempli);
        forme.setCouleur(couleur);
        forme.setXOrig(xOrig);
        forme.setYOrig(yOrig);
        forme.setId(id);
        
        if (type == TYPE_TEXT)
            forme.setText(text);

        return forme;
    }
}
