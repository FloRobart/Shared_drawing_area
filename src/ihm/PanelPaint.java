package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controleur.Controleur;
import metier.Forme;


public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private Controleur ctrl;

    private int buttonDragged;
    private Forme currentShape;
    private Point ancienPointSourie;
    private boolean drag;


    public PanelPaint(Controleur ctrl, int[] taillePlateau)
    {
        this.ctrl  = ctrl;
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.buttonDragged = 0;
        this.currentShape = null;
        this.ancienPointSourie = null;
        this.drag = false;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.setSize(taillePlateau[0], taillePlateau[1]);
        this.setPreferredSize(new Dimension(taillePlateau[0], taillePlateau[1]));
        this.setBackground(Color.WHITE);


        /* Ajout des listeners */
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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
            g2.setStroke(new BasicStroke(forme.getStroke()));

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
                g2.setFont(new Font("Arial", Font.PLAIN, forme.getYFin()-forme.getYDeb()));
                g2.drawString(forme.getText(), forme.getXDeb(), forme.getYDeb());
                continue;
            }
        }
    }

    /**
     * Permet de récuperer la forme sur laquelle on a cliqué
     * @param x : coordonnée x du clique
     * @param y : coordonnée y du clique
     */
    private void setCurrentShape(int x, int y)
    {
        for (Forme f : this.ctrl.getLstFormes())
            if (f.isIn(x, y)) { this.currentShape = f; return; }

        this.currentShape = null;
    }


    @Override
    public void mousePressed(MouseEvent me)
    {
        if (this.drag) return;
        
        if (me.getButton() == MouseEvent.BUTTON1)
        {
            if (this.ctrl.getPeindre()) return;

            this.buttonDragged = MouseEvent.BUTTON1;
            Forme forme = null;

            /* Texte */
            if (this.ctrl.getSelectedTypeForme() == Forme.TYPE_TEXT)
                forme = new Forme(me.getX(), me.getY(), "Exemple", this.ctrl.getSelectedColor());
            else /* Toutes les autres formes */
                forme = new Forme(me.getX(), me.getY(), this.ctrl.getStroke(), this.ctrl.getSelectedTypeForme(), this.ctrl.getRempli(), this.ctrl.getSelectedColor());


            this.ctrl.addForme(forme);
            this.currentShape = forme;
            this.ctrl.finaliseForme(this.currentShape);
        }
        else if (me.getButton() == MouseEvent.BUTTON3)
        {
            this.buttonDragged = MouseEvent.BUTTON3;
            System.out.println("Clic droit : " + me.getX() + " : " + me.getY());
            this.setCurrentShape(me.getX(), me.getY());
            this.ancienPointSourie = me.getPoint();
        }

        System.out.println("lsite des formes : " + this.ctrl.getLstFormes().size());
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        if ((this.ctrl.getPeindre() && this.buttonDragged != MouseEvent.BUTTON3) || this.currentShape == null) return;

        this.drag = true;
        if (this.buttonDragged == MouseEvent.BUTTON1)
        {
            this.currentShape.setXOrig(this.currentShape.getXOrig(), me.getX());
            this.currentShape.setYOrig(this.currentShape.getYOrig(), me.getY());
        }
        else if (this.buttonDragged == MouseEvent.BUTTON3)
        {
            int deltaX = ancienPointSourie.x - me.getX();
            int deltaY = ancienPointSourie.y - me.getY();

            this.currentShape.setXDeb(this.currentShape.getXDeb() - deltaX);
            this.currentShape.setYDeb(this.currentShape.getYDeb() - deltaY);
            this.currentShape.setXFin(this.currentShape.getXFin() - deltaX);
            this.currentShape.setYFin(this.currentShape.getYFin() - deltaY);

            this.ancienPointSourie = me.getPoint();
        }


        this.ctrl.ihmMajForme(this.currentShape);
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        this.drag = false;
        if (me.getButton() != MouseEvent.BUTTON1 || this.ctrl.getPeindre()) { this.currentShape = null; return; }

        if (this.currentShape.getYFin() - this.currentShape.getYDeb() <= 3 && this.currentShape.getXFin() - this.currentShape.getXDeb() <= 3)
        {
            this.ctrl.getLstFormes().remove(this.currentShape);
            this.repaint();
            this.currentShape = null;
            return;
        }

        if (this.ctrl.getSelectedTypeForme() == Forme.TYPE_TEXT)
        {
            String text = null;
            if (this.currentShape.getYFin() - this.currentShape.getYDeb() >= 3)
                text = JOptionPane.showInputDialog(this.getParent().getParent().getParent(), "Texte : ", "Exemple");

            if (text == null)
            {
                this.ctrl.getLstFormes().remove(this.currentShape);
                this.repaint();
                this.currentShape = null;
                return;
            }
            else
                this.currentShape.setText(text);
        }

        this.currentShape.resetOrig();
        this.ctrl.ihmMajForme(this.currentShape);
        this.repaint();
        this.currentShape = null;
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
        if (this.drag) return;

        if (me.getButton() == MouseEvent.BUTTON1)
        {
            /* Gestion du pot de peinture */
            if (this.ctrl.getPeindre())
            {
                this.setCurrentShape(me.getX(), me.getY());
                if (this.currentShape == null) return;

                this.currentShape.setRempli(!this.currentShape.isRempli());
                this.currentShape.setCouleur(this.ctrl.getSelectedColor());
                this.ctrl.ihmMajForme(this.currentShape);
                this.repaint();
            }
        }
        else if (me.getButton() == MouseEvent.BUTTON3)
        {
            // TODO : afficher une popup pour supprimer la forme et la modifier
        }

        this.currentShape = null;
    }


    @Override
    public void mouseMoved(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
}
