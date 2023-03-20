package controleur;

import ihm.FramePrinciple;
import metier.Forme;
import metier.Metier;

import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import java.awt.Color;
import java.io.File;


public class Controleur
{
    private Metier metier;
    private FramePrinciple ihm;


    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new FramePrinciple(this);
    }




    /*====================*/
	/* Gestion des formes */
	/*====================*/
	public List<Forme> getLstFormes() { return this.metier.getLstFormes(); }
	public List<Forme> getLstFormesSupprimer() { return this.metier.getLstFormes(); }

	public Forme getFormeAt(int index) { return this.metier.getFormeAt(index); }
	public Forme getFormeSupprimerAt(int index) { return this.metier.getFormeSupprimerAt(index); }

	public void addForme(Forme forme) { this.metier.addForme(forme); }
	public void removeForme(Forme forme) { this.metier.removeForme(forme); }
	public void unRemoveForme(Forme forme) { this.metier.unRemoveForme(forme); }



    /*========*/
    /* Thèmes */
    /*========*/
    /**
     * Permet d'appliquer le thème à l'ihm
     */
    public void appliquerTheme() { this.ihm.appliquerTheme(); }

    /**
     * Permet de à l'ihm de récupérer la hashmap contenant les couleurs du thème
     * @return HashMap contenant les couleurs du thème
     */
    public HashMap<String, Color> getTheme() { return this.metier.getTheme(); }

	/**
	 * Permet de récupérer le nom partièle du thème utilisé (nom complet : theme_X.xml)
	 * @return Nom du thème utilisé (nom renvoyé par cette méthode : X)
	 */
	public String getThemeUsed() { return this.metier.getThemeUsed(); }

    /**
     * Change le thème à utilisé dans le fichier de sauvegarde.
     * Charge en mémoire le nouveau thème.
     * Met à jour l'ihm.
     * @param theme : Nom du thème à utiliser
     */
    public void changerTheme(String theme) { this.metier.setThemeUsed(theme); }

    /**
     * Permet de renommer le fichier avec le nom du thème et de changer le nom enregistrer dans le fichier de sauvegarde.
     * @param nomFichier : nouveau nom du fichier
     */
    public void setNomFichier(String nomFichier) { this.metier.setNomFichier(nomFichier); }

    /**
     * Permet de vérifier si le nom du thème est valide
     * @param nomTheme : Nom du thème à vérifier
     * @return boolean : true si le nom est valide, sinon false
     */
    public boolean verifNomTheme(String nomTheme) { return this.metier.verifNomTheme(nomTheme); }

    /**
     * Permet de mettre à jour la liste des noms des thèmes dans le métier. 
     */
    public void majLstNomTheme() { this.metier.majLstNomTheme(); }

    /**
     * Permet de fermer la fenêtre de création de thème
     */
    public void disposeFrameCreerTheme() { this.ihm.disposeFrameCreerTheme(); }

    /**
     * Permet de fermer la fenêtre de suppression de thème
     */
    public void disposeFrameSuppTheme() { this.ihm.disposeFrameSuppTheme(); }

    /**
	 * Permet d'ajouter un nouveau thème personnalisé à la menuBarre
     * @param nomTheme : Nom du thème dans la menuBarre
	 */
	public void ajouterThemePersoOnMenuBarre(String nomTheme) { this.ihm.ajouterThemePersoOnMenuBarre(nomTheme); }

    /**
	 * Permet de récupérer la liste des noms des thèmes perso créer par l'utilisateur.
	 * @return List : liste des noms des thèmes perso.
	 */
	public List<String> getLstNameThemesPerso() { return this.metier.getLstNameThemesPerso(); }

    /**
     * Permet de récupérer le nombre de thèmes perso créer par l'utilisateur.
     * @return int : nombre de thèmes perso.
     */
    public int getNbThemesPerso() { return this.metier.getNbThemesPerso(); }

    /**
     * Permet de modifier le nombre de thèmes perso créer par l'utilisateur.
     */
    public void majNbThemesPerso() { this.metier.majNbThemesPerso(); }

    /**
     * Permet de modifier le nom du thème utilisé
     * @param theme : Nom du thème à utiliser
     */
    public void setNomTheme(String theme) { this.metier.setNomTheme(theme); }

    /**
	 * Permet de modifier un élément du thème dans le fichier xml.
	 * @param nameElement : nom de l'élément à modifier.
	 * @param color : nouvelle couleur de l'élément.
	 * @return boolean : true si l'élément a été modifié, false sinon.
	 */
    public boolean setElementTheme(String nomElement, Color couleur) { return this.metier.setElementTheme(nomElement, couleur); }

    /**
     * Permet de supprimer un thème perso.
     * Supprime le fichier du thème perso.
     * Met à jour la liste des noms des thèmes perso.
     * Met à jour le nombre de thèmes perso.
     * Met à jour la menuBarre.
     * @param lstNomsThemes : Liste des noms des thèmes perso à supprimer
     */
    public void supprimerThemePerso(List<String> lstNomsThemes)
    {
        this.metier.supprimerThemePerso(lstNomsThemes);
        this.ihm.supprimerThemePersoOnMenuBarre(lstNomsThemes);
    }

    /**
     * Permet de récupérer la liste des clés de la HashMap contenant les couleurs du thème.
     * @return List : liste des clés
     */
    public String[] getEnsClesThemes() { return this.metier.getEnsClesThemes(); }



    public static void main(String[] args)
    {
        new Controleur();
    }
}