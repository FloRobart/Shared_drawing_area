package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.awt.Toolkit;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import controleur.Controleur;
import path.Path;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class Metier
{
	public static final int[] TAILLE_PLATEAU = new int[]{Toolkit.getDefaultToolkit().getScreenSize().width - 100, Toolkit.getDefaultToolkit().getScreenSize().height - 200};

	/* Controleur */
    private Controleur ctrl;


	/* Gestion des formes */
	private List<Forme> lstFormes;
	private List<Forme> lstFormesSupprimer;
	private Color 	    selectedColor;
	private int         selectedForme;
	private boolean     rempli;
	private boolean     peindre;


    /* Thèmes */
	private int                     nbThemePerso;
	private List<String>            lstNomThemesPerso;
    private HashMap<String, Color>  hmColorTheme;
	


    public Metier(Controleur ctrl)
    {
		/* Controleur */
        this.ctrl = ctrl;

		/* Gestion des formes */
		this.lstFormes = new ArrayList<>();
		this.lstFormesSupprimer = new ArrayList<>();


        /* Thèmes */
		this.nbThemePerso      = this.initNbThemePerso();
		this.lstNomThemesPerso = this.initLstNameThemesPerso();
        this.hmColorTheme      = new HashMap<>();
        this.chargerThemes(this.getThemeUsed());

		/* Couleur sélectionné */
		this.selectedColor = Color.BLACK;
		this.selectedForme = Forme.TYPE_LIGNE;
		this.rempli        = false;
		this.peindre       = false;
    }

	/*====================*/
	/* Gestion des formes */
	/*====================*/
	/**
	 * Peremt de récupérer la liste des formes.
	 * @return la liste des formes.
	 */
	public List<Forme> getLstFormes() { return this.lstFormes; }

	/**
	 * Permet de récupérer la liste des formes supprimé.
	 * @return la liste des formes supprimé.
	 */
	public List<Forme> getLstFormesSupprimer() { return this.lstFormesSupprimer; }

	/**
	 * Permet de récupérer une forme.
	 * @param index : index de la forme à récupérer.
	 * @return la forme qui se trouve à l'index passé en paramètre.
	 */
	public Forme getFormeAt(int index)
	{
		if (index < 0 || index >= this.lstFormes.size()) throw new IllegalArgumentException("ERREUR dans la méthode 'getFormeAt(int)', l'index est invalide.");
		return this.lstFormes.get(index);
	}

	/**
	 * Permet de récupérer une forme supprimé.
	 * @param index : index de la forme à récupérer.
	 * @return la forme supprimé qui se trouve à l'index passé en paramètre.
	 */
	public Forme getFormeSupprimerAt(int index)
	{
		if (index < 0 || index >= this.lstFormesSupprimer.size()) throw new IllegalArgumentException("ERREUR dans la méthode 'getFormeSupprimerAt(int)', l'index est invalide.");
		return this.lstFormesSupprimer.get(index);
	}

	/**
	 * Permet d'ajouter une forme.
	 * Cette méthode ajoute la forme passé en paramètre à la liste des formes.
	 * @param forme : forme à ajouter.
	 */
	public void addForme(Forme forme) { this.lstFormes.add(forme); }

	/**
	 * Permet de supprimer une forme.
	 * Cette méthode ajoute la forme passé en paramètre à la liste des formes supprimé et la supprime de la liste des formes.
	 * @param forme : forme à supprimer.
	 */
	public void removeForme(Forme forme)
	{
		if (this.lstFormes.size() > 0 && this.lstFormes.contains(forme))
			this.lstFormesSupprimer.add(this.lstFormes.remove(this.lstFormes.indexOf(forme)));
	}

	/**
	 * Permet de rétablir une forme supprimé.
	 * Cette méthode ajoute la forme passé en paramètre à la liste des formes et la supprime de la liste des formes supprimé.
	 * @param forme : forme à rétablir.
	 */
	public void unRemoveForme()
	{
		if (this.lstFormesSupprimer.size() > 0)
			this.lstFormes.add(this.lstFormesSupprimer.remove(this.lstFormesSupprimer.size()-1));
	}

	public void unRemoveForme(Forme forme)
	{
		if (this.lstFormesSupprimer.size() > 0 && this.lstFormesSupprimer.contains(forme))
			this.lstFormes.add(this.lstFormesSupprimer.remove(this.lstFormesSupprimer.indexOf(forme)));
	}

	/**
	 * Permet de modifier la couleur sélectionné.
	 * @param color : nouvelle couleur sélectionné.
	 */
	public void setSelectedColor(Color color) { this.selectedColor = color; }

	/**
	 * Permet de récupérer la couleur sélectionné.
	 * @return la couleur sélectionné.
	 */
	public Color getSelectedColor() { return this.selectedColor; }

	/**
	 * Permet de modifier la forme sélectionné.
	 * @param forme : nouvelle forme sélectionné.
	 */
	public void setSelectedTypeForme(int forme) { this.selectedForme = forme; }

	/**
	 * Permet de récupérer la forme sélectionné.
	 * @return la forme sélectionné.
	 */
	public int getSelectedTypeForme() { return this.selectedForme; }

	/**
	 * Permet de modifier le remplissage.
	 * @param rempli : nouveau remplissage.
	 */
	public void setRempli(boolean rempli) { this.rempli = rempli; }

	/**
	 * Permet de récupérer le remplissage.
	 * @return le remplissage.
	 */
	public boolean getRempli() { return this.rempli; }

	/**
	 * Permet de définir le mode peindre.
	 * @return le remplissage.
	 */
	public void setPeindre(boolean peindre) { this.peindre = peindre; }

	/**
	 * Permet de savoir si on doit peindre ou non.
	 * @return le remplissage.
	 */
	public boolean getPeindre() { return this.peindre; }





    /*========*/
	/* Thèmes */
	/*========*/
	/**
	 * Permet d'initialiser le nombre de thèmes perso créer par l'utilisateur.
	 * @return int : nombre de thèmes perso.
	 */
	private int initNbThemePerso()
	{
		int nb = 0;

		File dossier = new File(Path.PATH_THEMES);

		for (File fichier : dossier.listFiles())
			if (fichier.getName().startsWith("theme_perso_"))
				nb++;

		return nb;
	}

	/**
	 * Permet de récupérer le nombre de thèmes perso créer par l'utilisateur.
	 * @return int : nombre de thèmes perso.
	 */
	public int getNbThemesPerso() { return this.nbThemePerso; }

	/**
	 * Permet de modifier le nombre de thèmes perso créer par l'utilisateur.
	 */
	public void majNbThemesPerso() { this.nbThemePerso = initNbThemePerso(); }

	/**
	 * Permet de renommer le fichier avec le nom du thème et de changer le nom enregistrer dans le fichier de sauvegarde.
	 * @param nomFichier : nouveau nom du fichier.
	 */
	public void setNomFichier(String nomFichier)
	{
		File fileTheme = new File(Path.PATH_THEME_X + this.getThemeUsed() + ".xml");

		fileTheme.renameTo(new File(Path.PATH_THEME_X + nomFichier + ".xml"));

		try
		{
			PrintWriter pw = new PrintWriter(Path.PATH_THEME_SAVE);
			pw.println("<theme>" + nomFichier + "</theme>");
			pw.close();
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de l'écriture du fichier XML du themes utilisé"); }
	}

	/**
	 * Permet de modifier un élément du thème dans le fichier xml.
	 * @param nameElement : nom de l'élément à modifier.
	 * @param color : nouvelle couleur de l'élément.
	 * @return boolean : true si l'élément a été modifié, false sinon.
	 */
	public boolean setElementTheme(String nameElement, Color color)
	{
		if (nameElement == null || color == null)        { return false; }
		if (!this.hmColorTheme.containsKey(nameElement)) { return false; }

		String sRet = "";

		try
		{
			Scanner sc = new Scanner(new File(Path.PATH_THEME_X + this.getThemeUsed() + ".xml"), "UTF8");

			String line = "";
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				if (line.contains(nameElement))
					line = "\t<" + nameElement + " red=\"" + color.getRed() + "\" green=\"" + color.getGreen() + "\" blue=\"" + color.getBlue() + "\" alpha=\"" + color.getAlpha()  + "\">" + "</" + nameElement + ">";

				sRet += line + "\n";
			}

			sc.close();

		} catch (FileNotFoundException e) { e.printStackTrace(); System.out.println("Erreur dans la méthode setElementTheme(), fichier '" + Path.PATH_THEME_X + this.getThemeUsed() + ".xml" + "' introuvable"); return false; }

		
		try
		{
			PrintWriter pw = new PrintWriter(new File(Path.PATH_THEME_X + this.getThemeUsed() + ".xml"), "UTF8");
			pw.print(sRet);
			pw.close();
		}
		catch (FileNotFoundException        e) { e.printStackTrace(); System.out.println("Erreur dans la méthode setElementTheme()"); return false; }
		catch (UnsupportedEncodingException e) { e.printStackTrace(); System.out.println("Erreur dans la méthode setElementTheme()"); return false; }

		return true;
	}

	/**
	 * Permet de changer le nom du thème en cours d'utilisation.
	 * @param nomTheme : nouveau nom du thème.
	 */
	public void setNomTheme(String nomTheme)
	{
		if (nomTheme != null)
		{
			String sRet = "";
			try
			{
				Scanner sc = new Scanner(new File(Path.PATH_THEME_X + this.getThemeUsed() + ".xml"), "UTF8");

				String line = "";
				while(sc.hasNextLine())
				{
					line = sc.nextLine();
					if (line.contains("name"))
						line = "<theme name=\"" + nomTheme + "\">";

					sRet += line + "\n";
				}

				sc.close();

			} catch (FileNotFoundException e) { e.printStackTrace(); System.out.println("Erreur dans la méthode setElementTheme(), fichier '" + Path.PATH_THEME_X + this.getThemeUsed() + ".xml" + "' introuvable"); }

			
			try
			{
				PrintWriter pw = new PrintWriter(new File(Path.PATH_THEME_X + this.getThemeUsed() + ".xml"), "UTF8");
				pw.print(sRet);
				pw.close();
			}
			catch (FileNotFoundException        e) { e.printStackTrace(); System.out.println("Erreur dans la méthode setNomTheme(String nomTheme)"); }
			catch (UnsupportedEncodingException e) { e.printStackTrace(); System.out.println("Erreur dans la méthode setNomTheme(String nomTheme)"); }
		}
	}

	/**
	 * Permet d'initialiser la liste des noms des thèmes perso créer par l'utilisateur.
	 * @return List : liste des noms des thèmes perso.
	 */
	private List<String> initLstNameThemesPerso()
	{
		List<String> lstNomThemesPerso = new ArrayList<String>();

		File dossier = new File(Path.PATH_THEMES);

		String name = "";
		for (File fichier : dossier.listFiles())
		{
			if (fichier.getName().startsWith("theme_perso_"))
			{
				SAXBuilder sxb = new SAXBuilder();
				try { name = sxb.build(fichier).getRootElement().getAttributeValue("name"); }
				catch (JDOMException e) { e.printStackTrace(); System.out.println("Erreur dans la lecture des noms des thèmes persos");  }
				catch (IOException   e) { e.printStackTrace(); System.out.println("Erreur dans la lecture des noms des thèmes persos"); }

				lstNomThemesPerso.add(name.replace("_", " "));
			}
		}

		return lstNomThemesPerso;
	}

	/**
	 * Permet de vérifier si le nom du thème n'est pas déjà utilisé et que le nom n'est pas null.
	 * @param nomTheme : nom du thème à vérifier.
	 * @return boolean : true si le nom du thème n'est pas déjà utilisé et qu'il n'est pas null, sinon false.
	 */
	public boolean verifNomTheme(String nomTheme)
	{
		if (nomTheme.equals("perso ")) return false;

		boolean nameOnlySpace = true;
		for (int i = 5; i < nomTheme.length(); i++)
			if (nomTheme.charAt(i) != ' ') { nameOnlySpace = false; break; }

		if (nameOnlySpace) return false;


		for (String s : this.lstNomThemesPerso)
			if (s.equals(nomTheme)) return false;

		return true;
	}

	/**
	 * Met à jour la liste des noms des thèmes perso. permet d'ajouter le nouveau thème qui viens d'être créer pour qui le liste sois à jour l'ors du prochaine appelle de la méthode verifNomTheme()
	 */
	public void majLstNomTheme()
	{
		this.lstNomThemesPerso = this.initLstNameThemesPerso();
	}


	/**
	 * Permet de récupérer la liste des noms des thèmes perso créer par l'utilisateur.
	 * @return List : liste des noms des thèmes perso.
	 */
	public List<String> getLstNameThemesPerso() { return this.lstNomThemesPerso; }


    /**
     * Permert de récupérer toute les couleurs de thème charger en mémoire.
     * @return HashMap - liste des couleurs du thème.
     */
    public HashMap<String, Color> getTheme() { return this.hmColorTheme;}


    /**
	 * Récupère le thème utilisé dans le fichier xml de sauvegarde
	 * @return String : thème à utilisé
	 */
	public String getThemeUsed()
	{
		String themeUsed = "";
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			themeUsed = sxb.build(Path.PATH_THEME_SAVE).getRootElement().getText();
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de la lecture du fichier XML du themes utilisé"); }

		return themeUsed;
	}


    /**
	 * Sauvegarde le thème selectionné par l'utilisateur dans le fichier xml de sauvegarde.
	 * Charge le thème selectionné dans la HashMap.
	 * Applique le thème selectionné (met à jour l'IHM).
	 * @param theme : thème à sauvegarder
	 */
	public void setThemeUsed(String theme)
	{
		if (!theme.equals(this.getThemeUsed()))
		{
			try
			{
				PrintWriter pw = new PrintWriter(Path.PATH_THEME_SAVE);
				pw.println("<theme>" + theme + "</theme>");
				pw.close();
			}
			catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de l'écriture du fichier XML du themes utilisé"); }

			this.chargerThemes(theme);

			this.ctrl.appliquerTheme();
		}
	}


    /**
	 * Charge les couleurs du thème choisi par l'utilisateur dans la HashMap
	 * @param theme : thème à charger
	 * @return HashMap contenant les couleurs du thème
	 */
	public void chargerThemes(String theme)
	{
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			Element racine = sxb.build(Path.PATH_THEME_X + theme + ".xml").getRootElement();

			/*----------------------------------------------*/
			/* Récupération des couleurs de chaque éléments */
			/*----------------------------------------------*/
			for (Element e : racine.getChildren())
			{
				Color color = new Color( Integer.parseInt(e.getAttributeValue("red")), Integer.parseInt(e.getAttributeValue("green")), Integer.parseInt(e.getAttributeValue("blue")), Integer.parseInt(e.getAttributeValue("alpha")));

				this.hmColorTheme.put(e.getName(), color);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Erreur lors de la lecture du fichier XML des informations du theme");
		}
	}

	/**
	 * Permet de supprimer les fichiers xml des thèmes perso dont les noms sont dans la liste passé en paramètre.
	 * Met à jour le nombre de thèmes perso.
	 * Met à jour la liste des noms des thèmes perso.
	 * @param lstNomsThemes
	 */
	public void supprimerThemePerso(List<String> lstNomsThemes)
	{
		for (String theme : lstNomsThemes)
		{
			File f = new File(Path.PATH_THEME_X + theme + ".xml");
			f.delete();
		}

		this.majNbThemesPerso();
		this.majLstNomTheme();
	}

	/**
	 * Permet de récupérer la liste des clées de la HashMap des thèmes.
	 * @return List : liste des clées.
	 */
	public String[] getEnsClesThemes()
	{
		List<String> lstCles = new ArrayList<String>();

		try
		{
			SAXBuilder sxb = new SAXBuilder();
			for (Element e : sxb.build(Path.PATH_THEME_X + "clair.xml").getRootElement().getChildren())
				lstCles.add(e.getName());
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de la lecture du fichier XML pour récupérer les clée de la HashMap des thème."); }

		return lstCles.toArray(new String[lstCles.size()]);
	}
}
