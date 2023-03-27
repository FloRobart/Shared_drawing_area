package ihm.popUp;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controleur.Controleur;
import metier.Forme;


public class PopUpForme extends JPopupMenu implements ActionListener
{
    private Controleur ctrl;

    private JMenuItem remove;
    private Forme formeToRemove;


    public PopUpForme(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.formeToRemove = null;

        /* Création des composants */
        this.remove = new JMenuItem("Supprimer");


        /* Ajouts des composants */
        this.add(this.remove);

        /* Activations des composants */
        this.remove.addActionListener(this);
    }

    public void show(Component c, int x, int y, Forme f)
    {
        super.show(c, x, y);
        this.formeToRemove = f;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        /* Changer de lecteur */
        if (source == this.remove)
        {
            this.ctrl.removeForme(this.formeToRemove);
            this.ctrl.majIhm();
        }
    }


    /**
     * Permet d'appliquer le theme
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");


        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        this.remove.setBackground(backGeneralColor);
        this.remove.setForeground(foreGeneralColor);
    }
}