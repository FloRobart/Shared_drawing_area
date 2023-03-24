package controleur;

import ihm.FramePrinciple;
import metier.Forme;
import metier.Metier;
import reseau.Client;
import reseau.ServerThread;

import java.util.HashMap;
import java.util.List;
import java.awt.Color;


public class Controleur
{
    private Metier metier;
    private FramePrinciple ihm;
    private ServerThread serverThread;
    private Client client;


    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new FramePrinciple(this);
    }




    /*====================*/
	/* Gestion des formes */
	/*====================*/
    /**
	 * Peremt de récupérer la liste des formes.
	 * @return la liste des formes.
	 */
	public List<Forme> getLstFormes() { return this.metier.getLstFormes(); }

    /**
	 * Permet de récupérer la liste des formes supprimé.
	 * @return la liste des formes supprimé.
	 */
	public List<Forme> getLstFormesSupprimer() { return this.metier.getLstFormes(); }

    /**
	 * Permet de récupérer une forme.
	 * @param index : index de la forme à récupérer.
	 * @return la forme qui se trouve à l'index passé en paramètre.
	 */
	public Forme getFormeAt(int index) { return this.metier.getFormeAt(index); }

    /**
	 * Permet de récupérer une forme supprimé.
	 * @param index : index de la forme à récupérer.
	 * @return la forme supprimé qui se trouve à l'index passé en paramètre.
	 */
	public Forme getFormeSupprimerAt(int index) { return this.metier.getFormeSupprimerAt(index); }

    /**
	 * Permet d'ajouter une forme.
	 * Cette méthode ajoute la forme passé en paramètre à la liste des formes.
	 * @param forme : forme à ajouter.
	 */
	public void addForme(Forme forme)
    {
        this.metier.addForme(forme);
        if (this.client != null)
            this.client.sendForme(forme);
        
        if (this.serverThread != null)
            this.serverThread.broadcastForme(forme);

    }

    /**
	 * Permet de supprimer une forme.
	 * Cette méthode ajoute la forme passé en paramètre à la liste des formes supprimé et la supprime de la liste des formes.
	 * @param forme : forme à supprimer.
	 */
	public void removeForme(Forme forme) { this.metier.removeForme(forme); }

    /**
	 * Permet de rétablir une forme supprimé.
	 * Cette méthode ajoute la forme passé en paramètre à la liste des formes et la supprime de la liste des formes supprimé.
	 * @param forme : forme à rétablir.
	 */
	public void unRemoveForme() { this.metier.unRemoveForme(); }

    /**
	 * Permet de modifier la couleur sélectionné.
	 * @param color : nouvelle couleur sélectionné.
	 */
	public void setSelectedColor(Color color) { this.metier.setSelectedColor(color);; }

	/**
	 * Permet de récupérer la couleur sélectionné.
	 * @return la couleur sélectionné.
	 */
	public Color getSelectedColor() { return this.metier.getSelectedColor(); }

    /**
	 * Permet de modifier la forme sélectionné.
	 * @param forme : nouvelle forme sélectionné.
	 */
	public void setSelectedTypeForme(int forme) { this.metier.setSelectedTypeForme(forme); }

	/**
	 * Permet de récupérer la forme sélectionné.
	 * @return la forme sélectionné.
	 */
	public int getSelectedTypeForme() { return this.metier.getSelectedTypeForme(); }

	/**
	 * Permet de modifier le remplissage.
	 * @param rempli : nouveau remplissage.
	 */
	public void setRempli(boolean rempli) { this.metier.setRempli(rempli); }

	/**
	 * Permet de récupérer le remplissage.
	 * @return le remplissage.
	 */
	public boolean getRempli() { return this.metier.getRempli(); }

    /**
	 * Permet de définir le mode peindre.
	 * @return le remplissage.
	 */
	public void setPeindre(boolean peindre) { this.metier.setPeindre(peindre); }

	/**
	 * Permet de savoir si on doit peindre ou non.
	 * @return le remplissage.
	 */
	public boolean getPeindre() { return this.metier.getPeindre(); }

    /**
     * Permet de mettre à jour l'IHM
     */
    public void majIhm() { this.ihm.majIhm(); }



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


    public Boolean startServer()
    {
        if (this.serverThread == null)
        {
            this.serverThread = new ServerThread(this);
            this.serverThread.start();
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean joinServer()
    {
        this.client = new Client(this);
        this.client.Connect("127.0.0.1", 31337, "JoeTesto");


        return true;
    }


    public static void main(String[] args)
    {
        new Controleur();
    }
}