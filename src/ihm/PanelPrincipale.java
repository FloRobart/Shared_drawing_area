package ihm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur;
import metier.Metier;


public class PanelPrincipale extends JPanel
{
    private Controleur ctrl;
    private PanelOutils panelHaut;
    private JPanel panelConteneur;
    private PanelPaint panelPaint;
    private JScrollPane scrollPane;

    public PanelPrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());

        /* Création des composants */
        this.panelHaut      = new PanelOutils(ctrl);
        this.panelConteneur = new JPanel();
        this.panelPaint     = new PanelPaint(ctrl, Metier.TAILLE_PLATEAU);
        this.panelConteneur.add(this.panelPaint);
        this.scrollPane     = new JScrollPane(this.panelConteneur);


        /* Ajout des composants */
        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.scrollPane, BorderLayout.CENTER);
    }

    /**
     * Permet de mettre à jour l'IHM
     */
    public void majIhm() { this.panelPaint.repaint(); }


    /**
     * Permet d'appliquer le thème
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        this.panelHaut.appliquerTheme();
        this.panelConteneur.setBackground(backGeneralColor);
        this.scrollPane.getHorizontalScrollBar().setBackground(backGeneralColor);
        this.scrollPane.getVerticalScrollBar  ().setBackground(backGeneralColor);
    }
}
