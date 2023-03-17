package ihm;

import java.awt.Color;

import javax.swing.JPanel;

import controleur.Controleur;

public class PanelPaint extends JPanel
{
    private Controleur ctrl;
    public PanelPaint(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }
}
