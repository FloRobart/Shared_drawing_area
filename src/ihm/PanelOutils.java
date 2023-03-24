package ihm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import controleur.Controleur;
import metier.Forme;


public class PanelOutils extends JPanel implements ActionListener
{           
    private static final int TAILLE_BOUTONS = 50;

    private Controleur ctrl;
 
    private JButton btnLigne;
    private JButton btnCercle;
    private JButton btnCarre;
    private JButton btnTexte;
    private JButton btnRempli;
    private JButton btnCouleur;
    private JButton btnPeindre;

    private Border defaultBorder;
    private Border selectedBorder;


    public PanelOutils(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /*-------------------------*/
        /* Création des composants */
        /*-------------------------*/
        this.btnLigne   = new JButton("");
        this.btnCercle  = new JButton("");
        this.btnCarre   = new JButton("");
        this.btnTexte   = new JButton("");
        this.btnRempli  = new JButton("");
        this.btnCouleur = new JButton("");
        this.btnPeindre = new JButton("");

        this.defaultBorder  = this.btnLigne.getBorder();
        this.selectedBorder = BorderFactory.createBevelBorder(1, this.ctrl.getTheme().get("enableColor"), this.ctrl.getTheme().get("enableColor"));

        /*-----------------------------*/
        /* Modification des composants */
        /*-----------------------------*/
        /* Taille */
        this.btnLigne  .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        this.btnCercle .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        this.btnCarre  .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        this.btnTexte  .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        this.btnRempli .setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        this.btnCouleur.setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));
        this.btnPeindre.setPreferredSize(new Dimension(PanelOutils.TAILLE_BOUTONS, PanelOutils.TAILLE_BOUTONS));

        /* Couleur et dessins */
        this.btnLigne  .setIcon(this.iconLigne  ());
        this.btnCercle .setIcon(this.iconCercle ());
        this.btnCarre  .setIcon(this.iconCarre  ());
        this.btnTexte  .setIcon(this.iconTexte  ());
        this.btnRempli .setIcon(this.iconRempli ());
        this.btnCouleur.setIcon(this.iconCouleur());
        this.btnPeindre.setIcon(this.iconPeindre());



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
        this.btnLigne  .addActionListener(this);
        this.btnCercle .addActionListener(this);
        this.btnCarre  .addActionListener(this);
        this.btnTexte  .addActionListener(this);
        this.btnRempli .addActionListener(this);
        this.btnCouleur.addActionListener(this);
        this.btnPeindre.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == this.btnCouleur)
        {
            Color couleur = JColorChooser.showDialog(this, "Choisissez une couleur", this.ctrl.getTheme().get("foreground"));
            if (couleur != null)
                this.ctrl.setSelectedColor(couleur);

            this.majIhm();
            return;
        }

        if (ae.getSource() == this.btnRempli)
        {
            this.btnRempli.setBorder(this.btnRempli.getBorder() == this.selectedBorder ? this.defaultBorder : this.selectedBorder);

            this.ctrl.setRempli(!this.ctrl.getRempli());

            this.majIhm();
            return;
        }

        this.btnLigne  .setBorder(this.defaultBorder);
        this.btnCercle .setBorder(this.defaultBorder);
        this.btnCarre  .setBorder(this.defaultBorder);
        this.btnTexte  .setBorder(this.defaultBorder);
        this.btnPeindre.setBorder(this.defaultBorder);
        this.ctrl.setPeindre(false);

        if (ae.getSource() == this.btnLigne)
        {
            this.btnLigne.setBorder(this.selectedBorder);
            this.ctrl.setSelectedTypeForme(Forme.TYPE_LIGNE);

            return;
        }
        
        if (ae.getSource() == this.btnCercle)
        {
            this.btnCercle.setBorder(this.selectedBorder);
            this.ctrl.setSelectedTypeForme(Forme.TYPE_CERCLE);

            return;
        }
        
        if (ae.getSource() == this.btnCarre)
        {
            this.btnCarre.setBorder(this.selectedBorder);
            this.ctrl.setSelectedTypeForme(Forme.TYPE_RECT);

            return;
        }
        
        if (ae.getSource() == this.btnTexte)
        {
            this.btnTexte.setBorder(this.selectedBorder);
            this.ctrl.setSelectedTypeForme(Forme.TYPE_TEXT);

            return;
        }
        
        if (ae.getSource() == this.btnPeindre)
        {
            this.btnPeindre.setBorder(this.btnPeindre.getBorder() == this.selectedBorder ? this.defaultBorder : this.selectedBorder);
            this.ctrl.setPeindre(!this.ctrl.getPeindre());

            return;
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
                g2.setColor(ctrl.getTheme().get("foreground"));
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
                g2.setColor(ctrl.getTheme().get("foreground"));
                g2.setStroke(new BasicStroke(2));
                if (ctrl.getRempli())
                    g2.fillOval(posX, posY, taille, taille);
                else
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
                g2.setColor(ctrl.getTheme().get("foreground"));
                g2.setStroke(new BasicStroke(2));
                if (ctrl.getRempli())
                    g2.fillRect(posX, posY, taille, taille);
                else
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
                g2.setColor(ctrl.getTheme().get("foreground"));
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
                g2.setColor(ctrl.getTheme().get("foreground"));
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
                g.setColor(ctrl.getSelectedColor());
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


                /* Dessins de la peinture à l'intérieur du pot */
                g2.setColor(ctrl.getSelectedColor());
                g2.setStroke(new BasicStroke(3));
                g2.drawLine(15, 23, 41, 23);
                g2.drawLine(21, 26, 40, 26);
                g2.drawLine(29, 29, 39, 29);
                g2.drawLine(36, 31, 38, 31);


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


                /* Dessins de la goutte de peinturee qui tombre du pot */
                g2.setColor(ctrl.getSelectedColor());
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
        this.btnCercle .setIcon(this.iconCercle ());
        this.btnCarre  .setIcon(this.iconCarre  ());
        this.btnCouleur.setIcon(this.iconCouleur());
        this.btnPeindre.setIcon(this.iconPeindre());
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
