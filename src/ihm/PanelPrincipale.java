package ihm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur;


public class PanelPrincipale extends JPanel
{
    private Controleur ctrl;
    private PanelHaut panelHaut;
    private PanelPaint panelPaint;
    private JScrollPane scrollPane;

    public PanelPrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());

        /* Création des composants */
        this.panelHaut = new PanelHaut(ctrl);
        this.panelPaint = new PanelPaint(ctrl);
        this.scrollPane = new JScrollPane(this.panelPaint);


        /* Ajout des composants */
        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.scrollPane, BorderLayout.CENTER);


        /* Activation des composants */
    }


    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        this.panelHaut.appliquerTheme();
        this.scrollPane.getHorizontalScrollBar().setBackground(backGeneralColor);
        this.scrollPane.getVerticalScrollBar  ().setBackground(backGeneralColor);
    }
}
