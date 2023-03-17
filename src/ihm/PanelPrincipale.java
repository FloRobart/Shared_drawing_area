package ihm;

import java.awt.Color;

import javax.swing.JPanel;

import controleur.Controleur;


public class PanelPrincipale extends JPanel
{
    private Controleur ctrl;


    public PanelPrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;

    }


    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);
    }
}
