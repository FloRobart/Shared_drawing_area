package ihm.popUp;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controleur.Controleur;


public class popUpForme extends JPopupMenu implements ActionListener
{
    private Controleur ctrl;


    private JMenuItem changeDrive;
    private JMenuItem open;
    private JMenuItem openWith;
    private JMenuItem edit;


    public popUpForme(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Création des composants */
        this.changeDrive = new JMenuItem();
        this.open        = new JMenuItem();
        this.openWith    = new JMenuItem();
        this.edit        = new JMenuItem();


        /* Ajouts des composants */
        this.add(this.open     );
        this.add(this.openWith );
        this.add(this.edit     );

        /* Activations des composants */
        this.changeDrive.addActionListener(this);
        this.open       .addActionListener(this);
        this.openWith   .addActionListener(this);
        this.edit       .addActionListener(this);
    }

    @Override
    public void show(Component c, int x, int y)
    {
        super.show(c, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        /* Changer de lecteur */
        if (source == this.changeDrive)
        {
            
        }
        /* Ouvrir */
        else if (source == this.open)
        {
            
        }
        /* Ouvrir avec */
        else if (source == this.openWith)
        {
            
        }
        /* Editer */
        else if (source == this.edit)
        {
            
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

        this.changeDrive.setBackground(backGeneralColor);
        this.changeDrive.setForeground(foreGeneralColor);

        this.open.setBackground(backGeneralColor);
        this.open.setForeground(foreGeneralColor);

        this.openWith.setBackground(backGeneralColor);
        this.openWith.setForeground(foreGeneralColor);

        this.edit.setBackground(backGeneralColor);
        this.edit.setForeground(foreGeneralColor);
    }
}