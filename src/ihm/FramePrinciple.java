package ihm;

import java.util.List;

import javax.swing.JFrame;

import controleur.Controleur;
import ihm.menu.MenuBarre;

public class FramePrinciple extends JFrame
{
    private Controleur ctrl;

    private MenuBarre menuBarre;
    private PanelPrincipale panelPrincipale;


    public FramePrinciple(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Frame principale");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /* Création des composants */
        this.menuBarre = new MenuBarre(this.ctrl);
        this.panelPrincipale = new PanelPrincipale(this.ctrl);


        /* Ajout des composants */
        this.setJMenuBar(this.menuBarre);
        this.add(this.panelPrincipale);

        /* Activation des composants */


        /* Affichage de la fenêtre */
        this.appliquerTheme();
        this.setVisible(true);
    }

    /**
     * Permet de fermer la fenêtre de création de thème
     */
    public void disposeFrameCreerTheme() { this.menuBarre.disposeFrameCreerTheme(); }

    /**
     * Permet de fermer la fenêtre de suppression de thème
     */
    public void disposeFrameSuppTheme() { this.menuBarre.disposeFrameSuppTheme(); }

    /**
	 * Permet d'ajouter un nouveau thème personnalisé à la menuBarre
     * @param nomTheme : Nom du thème dans la menuBarre
	 */
	public void ajouterThemePersoOnMenuBarre(String nomTheme)
	{
		this.menuBarre.ajouterThemePersoOnMenuBarre(nomTheme);
	}

    /**
     * Permet de supprimer un thème personnalisé de la menuBarre
     * @param lstNomsThemes : Liste des noms des thèmes à supprimer
     */
    public void supprimerThemePersoOnMenuBarre(List<String> lstNomsThemes)
    {
        this.menuBarre.supprimerThemePersoOnMenuBarre(lstNomsThemes);
    }

    /**
     * Permet d'appliquer le thème à l'ihm
     */
    public void appliquerTheme()
    {
        this.menuBarre.appliquerTheme();
        this.panelPrincipale.appliquerTheme();
    }
}
