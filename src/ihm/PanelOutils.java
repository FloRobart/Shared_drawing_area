package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;


public class PanelOutils extends JPanel implements ActionListener
{           
    private static final int TAILLE_BOUTONS = 50;

    private Controleur ctrl;

    private Color couleurSelectionned;
 
    private JButton btnLigne;
    private JButton btnCercle;
    private JButton btnCarre;
    private JButton btnTexte;
    private JButton btnRempli;
    private JButton btnCouleur;
    private JButton btnPeindre;


    public PanelOutils(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.couleurSelectionned = this.ctrl.getTheme().get("foreground");

        /*-------------------------*/
        /* Création des composants */
        /*-------------------------*/
        btnLigne   = new JButton("");
        btnCercle  = new JButton("");
        btnCarre   = new JButton("");
        btnTexte   = new JButton("");
        btnRempli  = new JButton("");
        btnCouleur = new JButton("");
        btnPeindre = new JButton("");

        /*-----------------------------*/
        /* Modification des composants */
        /*-----------------------------*/
        /* Taille */
        btnLigne  .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        btnCercle .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        btnCarre  .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        btnTexte  .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        btnRempli .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        btnCouleur.setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        btnPeindre.setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));

        /* Couleur et dessins */
        btnLigne.setIcon(this.iconLigne());
        btnCercle.setIcon(this.iconCercle());
        btnCarre.setIcon(this.iconCarre());
        btnTexte.setIcon(this.iconTexte());
        btnRempli.setIcon(this.iconRempli());
        btnCouleur.setIcon(this.iconCouleur());
        btnPeindre.setIcon(this.iconPeindre());



        /*----------------------*/
        /* Ajout des composants */
        /*----------------------*/
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        /* Horizontale */
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnLigne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnCercle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnCarre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnTexte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnRempli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnCouleur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnPeindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        /* Verticale */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLigne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCercle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCarre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTexte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPeindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRempli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCouleur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        /*---------------------*/
        /* Ajout des listeners */
        /*---------------------*/
        btnLigne  .addActionListener(this);
        btnCercle .addActionListener(this);
        btnCarre  .addActionListener(this);
        btnTexte  .addActionListener(this);
        btnRempli .addActionListener(this);
        btnCouleur.addActionListener(this);
        btnPeindre.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == btnLigne)
        {
            //this.ctrl.setMode("Ligne");
        }
        else if (ae.getSource() == btnCercle)
        {
            //this.ctrl.setMode("Cercle");
        }
        else if (ae.getSource() == btnCarre)
        {
            //this.ctrl.setMode("Carre");
        }
        else if (ae.getSource() == btnTexte)
        {
            //this.ctrl.setMode("Texte");
        }
        else if (ae.getSource() == btnRempli)
        {
            //this.ctrl.setMode("Rempli");
        }
        else if (ae.getSource() == btnCouleur)
        {
            //this.ctrl.setMode("Couleur");
            //Color colorRet = new Color(255, 255 - r, 255 - g, 255 - b);
        }
        else if (ae.getSource() == btnPeindre)
        {
            //this.ctrl.setMode("Peindre");
        }
    }


    /**
     * Permet de dessiner une ligne
     * @return Icon du dessin de la ligne
     */
    private Icon iconLigne()
    {
        Icon iconLigne = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                /* Fond du bouton */
                g2.setColor(ctrl.getTheme().get("buttonsBackground"));
                g2.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                /* Determination des position */
                int xFin = PanelOutils.TAILLE_BOUTONS/5;
                int xDeb = PanelOutils.TAILLE_BOUTONS - xFin;
                int yDeb  = PanelOutils.TAILLE_BOUTONS/5;
                int yFin  = PanelOutils.TAILLE_BOUTONS - yDeb;

                /* Dessins de la ligne */
                g2.setColor(couleurSelectionned);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(xDeb, yDeb, xFin, yFin);
            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconLigne;
    }

    /**
     * Permet de dessiner un cercle
     * @return Icon du dessin du cercle
     */
    private Icon iconCercle()
    {
        Icon iconCercle = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                /* Fond du bouton */
                g2.setColor(ctrl.getTheme().get("buttonsBackground"));
                g2.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                /* Determination des position */
                int posX = PanelOutils.TAILLE_BOUTONS/5;
                int posY = PanelOutils.TAILLE_BOUTONS/5;
                int taille  = PanelOutils.TAILLE_BOUTONS - (posX + posY);

                /* Dessins du cercle */
                g2.setColor(couleurSelectionned);
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(posX, posY, taille, taille);
            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconCercle;
    }

    /**
     * Permet de dessiner un carré
     * @return Icon du dessin du carré
     */
    private Icon iconCarre()
    {
        Icon iconCarre = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                /* Fond du bouton */
                g2.setColor(ctrl.getTheme().get("buttonsBackground"));
                g2.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                /* Determination des position */
                int posX = PanelOutils.TAILLE_BOUTONS/5;
                int posY = PanelOutils.TAILLE_BOUTONS/5;
                int taille  = PanelOutils.TAILLE_BOUTONS - (posX + posY);

                /* Dessins du carré */
                g2.setColor(couleurSelectionned);
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(posX, posY, taille, taille);
            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconCarre;
    }

    /**
     * Permet de dessiner une icon de texte
     * @return Icon du dessin de l'icon de texte
     */
    private Icon iconTexte()
    {
        Icon iconTexte = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                /* Fond du bouton */
                g2.setColor(ctrl.getTheme().get("buttonsBackground"));
                g2.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                /* Dessins du texte */
                g2.setColor(couleurSelectionned);
                g2.setFont(new Font("Bell MT", Font.BOLD, 45));
                g2.drawString("T", 8, 40);
            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconTexte;
    }

    /**
     * Permet de dessiner un boutons de remplissage
     * @return Icon du dessin du boutons de remplissage
     */
    private Icon iconRempli()
    {
        Icon iconRempli = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                /* Fond du bouton */
                g2.setColor(ctrl.getTheme().get("buttonsBackground"));
                g2.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                /* Determination des position */
                int posX = PanelOutils.TAILLE_BOUTONS/5;
                int posY = PanelOutils.TAILLE_BOUTONS/5;
                int taille  = PanelOutils.TAILLE_BOUTONS - (posX+posY);

                /* Dessins du carre */
                g2.setColor(couleurSelectionned);
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(posX, posY, taille, taille);

                /* Rayures dans la carre */
                g2.setStroke(new BasicStroke(1));
                for (int i = 0; i <= taille; i = i+5)
                {
                    g2.drawLine(posX+i, posY, posX, posY+i);
                    g2.drawLine(taille+posX, posX+i, posY+i, taille+posY);
                }
            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconRempli;
    }

    /**
     * Permet de dessiner un bouton de choix de couleur
     * @return Icon du dessin du bouton de choix de couleur
     */
    private Icon iconCouleur()
    {
        Icon iconCouleur = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                /* Fond du bouton */
                g.setColor(ctrl.getTheme().get("buttonsBackground"));
                g.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                /* Determination des position */
                int posX = PanelOutils.TAILLE_BOUTONS/5;
                int posY = PanelOutils.TAILLE_BOUTONS/5;
                int taille  = PanelOutils.TAILLE_BOUTONS - (posX+posY);

                /* Dessins du Carre de couleur */
                g.setColor(couleurSelectionned);
                g.fillRect(posX, posY, taille, taille);
            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconCouleur;
    }

    /**
     * Permet de dessiner un bouton pour remplir une forme existante
     * @return Icon du dessin du bouton de peinture
     */
    private Icon iconPeindre()
    {
        Icon iconPeindre = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                /* Fond du bouton */
                g2.setColor(ctrl.getTheme().get("buttonsBackground"));
                g2.fillRect(0, 0, PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS);

                
                /* Dessin du pot de peinture */
                g2.setColor(ctrl.getTheme().get("foreground"));
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(20, 10, 45, 20); // ligne horizontale du haut
                g2.drawLine(20, 10, 14, 24); // ligne verticale du haut
                g2.drawLine(45, 20, 39, 34); // ligne verticale du bas
                g2.drawLine(14, 24, 39, 34); // ligne horizontale du bas

                /* Dessin de la poigner du pot */
                g2.setStroke(new BasicStroke((float)1.5));
                g2.drawLine(30, 10, 19, 19);
                //g2.drawArc(20, 10, 25, 25, 0, 180);


                /* Dessins de la peinture à l'intérieur du pot */
                g2.setColor(couleurSelectionned);


                /* Dessins de la goutte de peinturee qui tombre du pot */
                g2.setColor(couleurSelectionned);
                g2.setStroke(new BasicStroke(3));
                g2.fillOval(14/2, 24+10, 10, 10);
                g2.drawLine(14, 24, (14/2)+3, (24+10)+(10/2)); // coté gauche de la goutte
                g2.drawLine(14, 24, (14/2)+8, (24+10)+(10/2)); // coté droit de la goutte

            }

            @Override
            public int getIconWidth() { return PanelOutils.TAILLE_BOUTONS; }
            @Override
            public int getIconHeight() { return PanelOutils.TAILLE_BOUTONS; }
        };

        return iconPeindre;
    }

    /**
     * Permet de mettre à jour les boutons de la barre d'outils (ceux qui ont besoins d'être mis à jour)
     */
    public void majIhm()
    {
        btnLigne  .setIcon(this.iconLigne  ());
        btnCercle .setIcon(this.iconCercle ());
        btnCarre  .setIcon(this.iconCarre  ());
        btnTexte  .setIcon(this.iconTexte  ());
        btnRempli .setIcon(this.iconRempli ());
        btnCouleur.setIcon(this.iconCouleur());
        btnPeindre.setIcon(this.iconPeindre());
    }


    /**
     * Applique le thème à la barre d'outils
     */
    public void appliquerTheme()
    {
        this.setBackground(this.ctrl.getTheme().get("background"));
        this.setForeground(this.ctrl.getTheme().get("foreground"));

        btnLigne  .setIcon(this.iconLigne  ());
        btnCercle .setIcon(this.iconCercle ());
        btnCarre  .setIcon(this.iconCarre  ());
        btnTexte  .setIcon(this.iconTexte  ());
        btnRempli .setIcon(this.iconRempli ());
        btnCouleur.setIcon(this.iconCouleur());
        btnPeindre.setIcon(this.iconPeindre());
    }
}
