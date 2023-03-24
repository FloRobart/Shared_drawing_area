package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.Forme;


public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
    private Controleur ctrl;

    private int click;
    private Forme currentShape;


    public PanelPaint(Controleur ctrl, int[] taillePlateau)
    {
        this.ctrl  = ctrl;
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.click = -1;
        this.currentShape = null;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.setSize(taillePlateau[0], taillePlateau[1]);
        this.setPreferredSize(new Dimension(taillePlateau[0], taillePlateau[1]));
        this.setBackground(Color.WHITE);


        /* Ajout des listeners */
        this.addMouseListener(this);
        this.addMouseMotionListener(this);


        this.addKeyListener(this);
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        List<Forme> formes = this.ctrl.getLstFormes();
        for (Forme forme : formes)
        {
            g2.setColor(forme.getCouleur());
            g2.setStroke(new BasicStroke(5));

            /* Cercle */
            if (forme.getType() == Forme.TYPE_CERCLE)
            {
                if (forme.isRempli())
                    g2.fillOval(forme.getXDeb(), forme.getYDeb(), forme.getXFin() - forme.getXDeb(), forme.getYFin() - forme.getYDeb());
                else
                    g2.drawOval(forme.getXDeb(), forme.getYDeb(), forme.getXFin() - forme.getXDeb(), forme.getYFin() - forme.getYDeb());

                continue;
            }

            /* Carre */
            if (forme.getType() == Forme.TYPE_RECT)
            {
                if (forme.isRempli())
                    g2.fillRect(forme.getXDeb(), forme.getYDeb(), forme.getXFin() - forme.getXDeb(), forme.getYFin() - forme.getYDeb());
                else
                    g2.drawRect(forme.getXDeb(), forme.getYDeb(), forme.getXFin() - forme.getXDeb(), forme.getYFin() - forme.getYDeb());

                continue;
            }

            /* Ligne */
            if (forme.getType() == Forme.TYPE_LIGNE)
            {
                g2.drawLine(forme.getXDeb(), forme.getYDeb(), forme.getXFin(), forme.getYFin());
                continue;
            }

            /* Texte */
            if (forme.getType() == Forme.TYPE_TEXT)
            {
                //g2.drawString(forme.getText(), forme.getXDeb(), forme.getYDeb());
                continue;
            }
        }
    }



    @Override
    public void mousePressed(MouseEvent me)
    {
        if (me.getButton() == MouseEvent.BUTTON1)
        {
            /* Texte */
            if (this.ctrl.getSelectedTypeForme() == Forme.TYPE_TEXT)
            {
                this.ctrl.setSelectedTypeForme(Forme.TYPE_TEXT);

                return;
            }

            /* Peindre */
            if (this.ctrl.getPeindre())
            {
                this.click = -1;
                for (Forme f : this.ctrl.getLstFormes())
                    if (f.isIn(me.getX(), me.getY()))
                    {
                        f.setRempli(!f.isRempli());
                        f.setCouleur(this.ctrl.getSelectedColor());
                        this.ctrl.ihmMajForme(f);
                        return;
                    }
            }
            else /* Toutes les autres formes */
            {
                this.click = MouseEvent.BUTTON1;
                Forme forme = new Forme(me.getX(), me.getY(), this.ctrl.getSelectedTypeForme(), this.ctrl.getRempli(), this.ctrl.getSelectedColor());
                this.ctrl.addForme(forme);
                this.currentShape = forme;
            }
        }
        else if (me.getButton() == MouseEvent.BUTTON3)
        {
            this.click = MouseEvent.BUTTON3;
            for (Forme f : this.ctrl.getLstFormes())
            {
                if (f.isIn(me.getX(), me.getY()))
                {
                    this.currentShape = f;
                    break;
                }
            }
        }

        System.out.println("lsite des formes : " + this.ctrl.getLstFormes().size());
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        if (this.click == -1 || this.ctrl.getLstFormes().size() == 0 || this.currentShape == null) return;

        if (this.click == MouseEvent.BUTTON1)
        {
            this.currentShape.setX(this.currentShape.getXOrig(), me.getX());
            this.currentShape.setY(this.currentShape.getYOrig(), me.getY());
        }
        else if (this.click == MouseEvent.BUTTON3)
        {
            //TODO: ca parce que ca marche pas
            this.currentShape.setXDeb(me.getX());
            this.currentShape.setYDeb(me.getY());

            this.currentShape.setXFin(this.currentShape.getXFin() + me.getX());
            this.currentShape.setYFin(this.currentShape.getYFin() + me.getY());
        }

        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        if (this.currentShape != null)
        {
            this.ctrl.finaliseForme(this.currentShape);
        }
        if (me.getButton() == MouseEvent.BUTTON1)
        {
            if (this.currentShape != null)
            {
                this.currentShape.resetOrig();
            }
        }
        else if (me.getButton() == MouseEvent.BUTTON3)
        {
            if (this.currentShape != null)
            {
                // afficher une popup pour supprimer la forme et la modifier
                this.currentShape = null;
            }
        }

        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        System.out.println("key pressed : " + ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        System.out.println("key released : " + ke.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        System.out.println("key typed : " + ke.getKeyCode());
    }


    @Override
    public void mouseMoved(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void mouseClicked(MouseEvent me) {}
}
