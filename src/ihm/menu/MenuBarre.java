package ihm.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.InputEvent;

import controleur.Controleur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;

	/* Frame Créée thème */
	private FrameCreerTheme frameCreerTheme;
	private FrameSuppTheme  frameSuppTheme;

	/* Menus */
	private JMenu menuFichiers;
	private JMenu menuMultiJoueur;
	private JMenu menuPreferences;
	private JMenu menuAide;

	/* Fichier */
	private JMenuItem menuiFichiersNouveau;
	private JMenuItem menuiFichiersOuvrir;
	private JMenuItem menuiFichiersSauvegarder;
	private JMenuItem menuiFichiersAnnuler;
	private JMenuItem menuiFichiersRestorer;

	/* Fichier */
	private JMenuItem menuiMultiJoueurCreerServer;
	private JMenuItem menuiMultiJoueurRejoindreServer;

	/* Préférences */
	private JMenu     menuiPreferencesThemes;
	private JMenuItem menuiPreferencesThemesClair;
	private JMenuItem menuiPreferencesThemesSombre;
	private JMenuItem menuiPreferencesThemesDark;
	private JMenu     menuiPreferencesThemesPerso;

	private List<JMenuItem> lstMenuiPreferencesThemesPerso;


	public MenuBarre(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		this.frameCreerTheme = null;
		this.frameSuppTheme  = null;

		/*=========================*/
		/* Création des composants */
		/*=========================*/
		/*----------*/
		/* Fichiers */
		/*----------*/
		// TODO : Ajouter les composants pour les fichiers (potentiellemnt les mêmes qui pour le clique droit pour faire fonctionner les raccourcis clavier)
		this.menuFichiers = new JMenu("Fichiers");
		this.menuFichiers.setMnemonic('F');

		/* Nouveau */
		this.menuiFichiersNouveau = new JMenuItem("Nouveau");
		this.menuiFichiersNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

		/* Ouvrir */
		this.menuiFichiersOuvrir = new JMenuItem("Ouvrir");
		this.menuiFichiersOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		/* Sauvegarder */
		this.menuiFichiersSauvegarder = new JMenuItem("Sauvegarder");
		this.menuiFichiersSauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		/* Annuler */
		this.menuiFichiersAnnuler = new JMenuItem("Annuler");
		this.menuiFichiersAnnuler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

		/* Restorer */
		this.menuiFichiersRestorer = new JMenuItem("Restorer");
		this.menuiFichiersRestorer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));

		/*--------------*/
		/* Multi-Joueur */
		/*--------------*/
		this.menuPreferences = new JMenu("Multi-Joueur");
		this.menuPreferences.setMnemonic('P');

		/* Créer un serveur */
		this.menuiMultiJoueurCreerServer = new JMenuItem("Créer un serveur");

		/* Rejoindre un serveur */
		this.menuiMultiJoueurRejoindreServer = new JMenuItem("Rejoindre un serveur");

		/*-------------*/
		/* Préférences */
		/*-------------*/
		this.menuMultiJoueur = new JMenu("Préférences");
		this.menuMultiJoueur.setMnemonic('M');



		/* Thèmes */
		this.menuiPreferencesThemes       = new JMenu    ("Thèmes ");

		/* Clair, Sombre, Dark */
		this.menuiPreferencesThemesClair  = new JMenuItem("Clair" );
		this.menuiPreferencesThemesSombre = new JMenuItem("Sombre");
		this.menuiPreferencesThemesDark   = new JMenuItem("Dark"  );

		/* Personnalisé */
		this.menuiPreferencesThemesPerso  = new JMenu    ("Personnalisé ");

		this.lstMenuiPreferencesThemesPerso = new ArrayList<JMenuItem>();
		this.lstMenuiPreferencesThemesPerso.add(new JMenuItem("Nouveau"));
		this.lstMenuiPreferencesThemesPerso.add(new JMenuItem("supprimer"));
		for (int i = 0; i < this.ctrl.getLstNameThemesPerso().size(); i++)
			this.lstMenuiPreferencesThemesPerso.add(new JMenuItem(this.ctrl.getLstNameThemesPerso().get(i)));


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide = new JMenu("Aide");
		this.menuAide.setMnemonic('A');


		/*=======================*/
		/* Ajouts des composants */
		/*=======================*/
		/*----------*/
		/* Fichiers */
		/*----------*/
		/* Nouveau */
		this.menuFichiers.add(this.menuiFichiersNouveau);

		/* Ouvrir */
		this.menuFichiers.add(this.menuiFichiersOuvrir);

		/* Sauvegarder */
		this.menuFichiers.add(this.menuiFichiersSauvegarder);

		/* Annuler */
		this.menuFichiers.add(this.menuiFichiersAnnuler);

		/* Restorer */
		this.menuFichiers.add(this.menuiFichiersRestorer);

		/* Ajout de tout à la JMenuBar */
		this.add(menuFichiers);

		/*--------------*/
		/* Multi-Joueur */
		/*--------------*/

		/* Créer un serveur */
		this.menuMultiJoueur.add(this.menuiMultiJoueurCreerServer);

		/* Rejoindre un serveur */
		this.menuMultiJoueur.add(this.menuiMultiJoueurRejoindreServer);

		/* Ajout de tout à la JMenuBar */
		this.add(menuMultiJoueur);


		/*------------*/
		/* Préférence */
		/*------------*/
		/* Thèmes prédéfinie */
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesClair);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesSombre);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesDark);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesPerso);

		/* Thèmes personnalisés */
		for (int i = 0; i < this.lstMenuiPreferencesThemesPerso.size(); i++)
		{
			this.menuiPreferencesThemesPerso.add(this.lstMenuiPreferencesThemesPerso.get(i));
			if (i == 1)
				this.menuiPreferencesThemesPerso.addSeparator();
		}

		this.menuPreferences.add(this.menuiPreferencesThemes);

		/* Ajout de tout à la JMenuBar */
		this.add(menuPreferences);


		/*------*/
		/* Aide */
		/*------*/
		this.add(menuAide);


		/*============================*/
		/* Activations des composants */
		/*============================*/
		/*----------*/
		/* Fichiers */
		/*----------*/
		/* Nouveau */
		this.menuiFichiersNouveau.addActionListener(this);

		/* Ouvrir */
		this.menuiFichiersOuvrir.addActionListener(this);

		/* Sauvegarder */
		this.menuiFichiersSauvegarder.addActionListener(this);

		/* Annuler */
		this.menuiFichiersAnnuler.addActionListener(this);

		/* Restorer */
		this.menuiFichiersRestorer.addActionListener(this);

		/*--------------*/
		/* Multi-Joueur */
		/*--------------*/

		/* Créer un serveur */
		this.menuiMultiJoueurCreerServer.addActionListener(this);

		/* Rejoindre un serveur */
		this.menuiMultiJoueurRejoindreServer.addActionListener(this);

		/*-------------*/
		/* Préférences */
		/*-------------*/
		/* Thèmes */
		this.menuiPreferencesThemesClair .addActionListener(this);
		this.menuiPreferencesThemesSombre.addActionListener(this);
		this.menuiPreferencesThemesDark  .addActionListener(this);

		for (int i = 0; i < this.lstMenuiPreferencesThemesPerso.size(); i++)
			this.lstMenuiPreferencesThemesPerso.get(i).addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			/*----------*/
			/* Fichiers */
			/*----------*/
			/* Nouveau */
			if (e.getSource() == this.menuiFichiersNouveau)
			{
				//this.ctrl.new();
				this.ctrl.majIhm();
			}

			/* Ouvrir */
			if (e.getSource() == this.menuiFichiersOuvrir)
			{
				//this.ctrl.open();
				this.ctrl.majIhm();
			}

			/* Sauvegarder */
			if (e.getSource() == this.menuiFichiersSauvegarder)
			{
				//this.ctrl.save();
			}

			/* Annuler */
			if (e.getSource() == this.menuiFichiersAnnuler)
			{
				if (this.ctrl.getLstFormes().size() > 0)
				{
					this.ctrl.removeForme(this.ctrl.getFormeAt(this.ctrl.getLstFormes().size() - 1));
					this.ctrl.majIhm();
				}
			}

			/* Restorer */
			if (e.getSource() == this.menuiFichiersRestorer)
			{
				this.ctrl.unRemoveForme();
				this.ctrl.majIhm();
			}
		
			/*--------------*/
			/* Multi-Joueur */
			/*--------------*/

			/* Créer un serveur */
			if (e.getSource() == this.menuiMultiJoueurCreerServer)
			{
				
			}

			/* Rejoindre un serveur */
			if (e.getSource() == this.menuiMultiJoueurRejoindreServer)
			{
				
			}

			/*-------------*/
			/* Préférences */
			/*-------------*/
			if (e.getSource() == this.menuiPreferencesThemesClair)
				this.ctrl.changerTheme("clair");

			if (e.getSource() == this.menuiPreferencesThemesSombre)
				this.ctrl.changerTheme("sombre");

			if (e.getSource() == this.menuiPreferencesThemesDark)
				this.ctrl.changerTheme("dark");

			/* Nouveau */
			if (e.getSource() == this.lstMenuiPreferencesThemesPerso.get(0))
			{
				if (this.frameCreerTheme == null)
					this.frameCreerTheme = new FrameCreerTheme(this.ctrl);
				else
					this.frameCreerTheme.setVisible(true);
			}

			/* Supprimer */
			if (e.getSource() == this.lstMenuiPreferencesThemesPerso.get(1))
			{
				if (this.ctrl.getNbThemesPerso() != 0)
				{
					if (this.frameSuppTheme == null)
						this.frameSuppTheme = new FrameSuppTheme(this.ctrl);
					else
						this.frameSuppTheme.setVisible(true);
				}
			}

			/* tout les thèmes personnalisé */
			for (int i = 2; i < this.lstMenuiPreferencesThemesPerso.size(); i++)
				if (e.getSource() == this.lstMenuiPreferencesThemesPerso.get(i))
					this.ctrl.changerTheme(this.lstMenuiPreferencesThemesPerso.get(i).getText().replace(" ", "_"));
		}
	}


	/**
     * Permet de fermer la fenêtre de création de thème
     */
    public void disposeFrameCreerTheme() { this.frameCreerTheme.dispose(); this.frameCreerTheme = null; }

	/**
     * Permet de fermer la fenêtre de suppression de thème
     */
    public void disposeFrameSuppTheme() { this.frameSuppTheme.dispose(); this.frameSuppTheme = null; }


	/**
	 * Permet d'ajouter un nouveau thème personnalisé à la menuBarre
	 * @param nomTheme : Nom du thème dans la menuBarre
	 */
	public void ajouterThemePersoOnMenuBarre(String nomTheme)
	{
		/* Création du thème + Ajout à la liste des thèmes personnalisé */
		this.lstMenuiPreferencesThemesPerso.add(new JMenuItem(nomTheme));

		/* Ajout du thème à la menuBarre */
		this.menuiPreferencesThemesPerso.add(this.lstMenuiPreferencesThemesPerso.get(this.lstMenuiPreferencesThemesPerso.size() - 1));

		/* Activation du thème */
		this.lstMenuiPreferencesThemesPerso.get(this.lstMenuiPreferencesThemesPerso.size() - 1).addActionListener(this);

		/* Mise à jour de la menuBarre */
		this.appliquerTheme();
	}


	/**
     * Permet de supprimer un thème personnalisé de la menuBarre
     * @param lstNomsThemes : Liste des noms des thèmes à supprimer
     */
    public void supprimerThemePersoOnMenuBarre(List<String> lstNomsThemes)
    {
		/* Suppression du thème de la menuBarre et de l'arrayList */
		for (int i = 0; i < this.lstMenuiPreferencesThemesPerso.size(); i++)
		{
			for (String themeASupp : lstNomsThemes)
			{
				if (this.lstMenuiPreferencesThemesPerso.get(i).getText().replace(" ", "_").equals(themeASupp))
				{
					this.menuiPreferencesThemesPerso.remove(this.lstMenuiPreferencesThemesPerso.get(i));
					this.lstMenuiPreferencesThemesPerso.remove(i).getText();
				}
			}
		}
	}


	
	/**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
	{
		Color backGeneralColor = this.ctrl.getTheme().get("background");
		Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

		/*-------------------------*/
		/* La Menu Barre elle même */
		/*-------------------------*/
		this.setBackground(backGeneralColor);
		this.setForeground(foreGeneralColor);

		/*-------*/
		/* Frame */
		/*-------*/
		if (this.frameCreerTheme != null) { this.frameCreerTheme.appliquerTheme(); }
		if (this.frameSuppTheme != null) { this.frameSuppTheme.appliquerTheme(); }



		/*---------*/
		/* Fichier */
		/*---------*/
		this.menuFichiers.setBackground(backGeneralColor);
		this.menuFichiers.setForeground(foreGeneralColor);
		


		/*------------*/
		/* Préférence */
		/*------------*/
		/* Préférence */
		this.menuPreferences.setBackground(backGeneralColor);
		this.menuPreferences.setForeground(foreGeneralColor);

		/* Thèmes */
		this.menuiPreferencesThemes      .setOpaque(true);
		this.menuiPreferencesThemes      .setBackground(backGeneralColor);
		this.menuiPreferencesThemes      .setForeground(foreGeneralColor);

		/* Clair */
		this.menuiPreferencesThemesClair .setBackground(backGeneralColor);
		this.menuiPreferencesThemesClair .setForeground(foreGeneralColor);

		/* Sombre */
		this.menuiPreferencesThemesSombre.setBackground(backGeneralColor);
		this.menuiPreferencesThemesSombre.setForeground(foreGeneralColor);

		/* Dark */
		this.menuiPreferencesThemesDark  .setBackground(backGeneralColor);
		this.menuiPreferencesThemesDark  .setForeground(foreGeneralColor);

		/* Personnalisé */
		this.menuiPreferencesThemesPerso .setOpaque(true);
		this.menuiPreferencesThemesPerso .setBackground(backGeneralColor);
		this.menuiPreferencesThemesPerso .setForeground(foreGeneralColor);


		for (int i = 0; i < this.lstMenuiPreferencesThemesPerso.size(); i++)
		{
			this.lstMenuiPreferencesThemesPerso.get(i).setBackground(backGeneralColor);
			this.lstMenuiPreferencesThemesPerso.get(i).setForeground(foreGeneralColor);
		}



		/*------*/
		/* Aide */
		/*------*/
		this.menuAide.setBackground(backGeneralColor);
		this.menuAide.setForeground(foreGeneralColor);
	}
}


