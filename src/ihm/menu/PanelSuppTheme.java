package ihm.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Controleur;


public class PanelSuppTheme extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JLabel lblTitre;
    private JPanel panelCentre;
    private JPanel panelSud;

    private List<JCheckBox> lstCheckBox;
    private List<String>    lstNomThemePerso;
    private List<JLabel>    lstLabel;
    private List<JButton>   lstButton;

    private JButton btnSupprimer;
    private JButton btnAnnuler;


    public PanelSuppTheme(Controleur ctrl)
    {
        this.ctrl = ctrl;
        
        this.setLayout(new BorderLayout(0, 20));

        this.panelCentre = new JPanel(new GridLayout(this.ctrl.getNbThemesPerso()+1, 3, 20, 5));
        this.panelSud    = new JPanel();

        this.lblTitre    = new JLabel("", SwingConstants.CENTER);
        this.lblTitre.setFont(new Font("Arial", Font.BOLD, 36));

        /*-------------------------*/
        /* Création des composants */
        /*-------------------------*/
        this.lstNomThemePerso = this.ctrl.getLstNameThemesPerso();
        int nbThemePerso = this.lstNomThemePerso.size();

        /* Checkbox */
        this.lstCheckBox = new ArrayList<JCheckBox>();
        for (int i = 0; i < nbThemePerso; i++)
        {
            this.lstCheckBox.add(new JCheckBox());
            this.lstCheckBox.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
        }

        /* Labels nom theme */
        this.lstLabel = new ArrayList<JLabel>();
        for (int i = 0; i < nbThemePerso; i++)
        {
            this.lstLabel.add(new JLabel(this.lstNomThemePerso.get(i)));
            this.lstLabel.get(i).setHorizontalAlignment(SwingConstants.LEFT);
        }

        /* Entête de colonne */
        this.lstLabel.add(this.lstLabel.size(), new JLabel());
        this.lstLabel.get(this.lstLabel.size()-1).setHorizontalAlignment(SwingConstants.RIGHT);

        this.lstLabel.add(this.lstLabel.size(), new JLabel());
        this.lstLabel.get(this.lstLabel.size()-1).setHorizontalAlignment(SwingConstants.CENTER);

        this.lstLabel.add(this.lstLabel.size(), new JLabel());
        this.lstLabel.get(this.lstLabel.size()-1).setHorizontalAlignment(SwingConstants.CENTER);

        /* Boutons appliquer thème */
        this.lstButton = new ArrayList<JButton>();
        for (int i = 0; i < nbThemePerso; i++)
            this.lstButton.add(new JButton());

        /* Boutons supprimer et annuler */
        this.btnSupprimer = new JButton("Supprimer");
        this.btnAnnuler   = new JButton("Annuler");


        /*----------------------*/
        /* Ajout des composants */
        /*----------------------*/
        this.panelCentre.add(this.lstLabel.get(this.lstLabel.size()-3));
        this.panelCentre.add(this.lstLabel.get(this.lstLabel.size()-2));
        this.panelCentre.add(this.lstLabel.get(this.lstLabel.size()-1));
        this.panelCentre.add(new JLabel());
        for (int i = 0; i < nbThemePerso; i++)
        {
            this.panelCentre.add(this.lstCheckBox.get(i));
            this.panelCentre.add(this.lstLabel   .get(i));
            this.panelCentre.add(this.lstButton  .get(i));
            this.panelCentre.add(new JLabel());
        }

        this.panelSud.add(this.btnAnnuler  );
        this.panelSud.add(this.btnSupprimer);

        this.add(this.lblTitre   , BorderLayout.NORTH );
        this.add(this.panelCentre, BorderLayout.CENTER);
        this.add(this.panelSud   , BorderLayout.SOUTH );


        /*---------------------------*/
        /* Activation des composants */
        /*---------------------------*/
        for (int i = 0; i < nbThemePerso; i++)
            this.lstButton.get(i).addActionListener(this);

        this.btnSupprimer.addActionListener(this);
        this.btnAnnuler.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnSupprimer)
        {
            if (JOptionPane.showConfirmDialog(this, "Êtes vous sur de vous supprimer les thème sélectionner", "Confirmation", JOptionPane.YES_NO_OPTION) == 0)
            {
                List<String> lstThemeASupp = new ArrayList<String>();
                for (int i = 0; i < this.lstCheckBox.size(); i++)
                {
                    if (this.lstCheckBox.get(i).isSelected())
                    {
                        if (this.ctrl.getThemeUsed().equals(this.lstLabel.get(i).getText().replace(" ", "_")))
                            this.ctrl.changerTheme("clair");

                        lstThemeASupp.add(this.lstLabel.get(i).getText().replace(" ", "_"));
                    }
                }

                this.ctrl.supprimerThemePerso(lstThemeASupp);
                this.ctrl.disposeFrameSuppTheme();
            }
        }

        if (e.getSource() == this.btnAnnuler)
        {
            this.ctrl.disposeFrameSuppTheme();
        }

        for (int i = 0; i < this.lstButton.size(); i++)
            if (e.getSource() == this.lstButton.get(i))
                this.ctrl.changerTheme(this.lstLabel.get(i).getText().replace(" ", "_"));
    }


    /**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");
        Color btnBackColor     = this.ctrl.getTheme().get("buttonsBackground");


        /* Le Panel elle même */
        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        this.panelCentre.setBackground(backGeneralColor);

        this.panelSud.setBackground(backGeneralColor);


        /* Label titre */
        this.lblTitre.setOpaque(false);
        this.lblTitre.setForeground(foreGeneralColor);

        
        for (int i = 0; i < this.lstCheckBox.size(); i++)
        {
            /* CheckBox */
            this.lstCheckBox.get(i).setOpaque(false);
            this.lstCheckBox.get(i).setForeground(foreGeneralColor);

            /* Label */
            this.lstLabel.get(i).setOpaque(false);
            this.lstLabel.get(i).setForeground(foreGeneralColor);

            /* Bouton */
            this.lstButton.get(i).setOpaque(true);
            this.lstButton.get(i).setBackground(btnBackColor);
            this.lstButton.get(i).setForeground(foreGeneralColor);
        }

        /* Entêtes des colonnes */
        for (int i = this.lstCheckBox.size() - 1; i < this.lstLabel.size(); i++)
        {
            this.lstLabel.get(i).setOpaque(false);
            this.lstLabel.get(i).setForeground(foreGeneralColor);
        }


        /* Boutons */
        this.btnSupprimer.setBackground(btnBackColor);
        this.btnSupprimer.setForeground(foreGeneralColor);

        this.btnAnnuler.setBackground(btnBackColor);
        this.btnAnnuler.setForeground(foreGeneralColor);
    }
}
