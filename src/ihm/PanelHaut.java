package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

import controleur.Controleur;


public class PanelHaut extends JPanel
{             
    private Controleur ctrl;
 
    private JButton btnLigne;
    private JButton btnCercle;
    private JButton btnCarre;
    private JButton btnTexte;
    private JButton btnRemplir;
    private JButton btnCouleur;


    public PanelHaut(Controleur ctrl)
    {
        this.ctrl = ctrl;

        btnLigne = new JButton();
        btnCercle = new JButton();
        btnCarre = new JButton();
        btnTexte = new JButton();
        btnRemplir = new JButton();
        btnCouleur = new JButton();

        btnLigne.setPreferredSize(new Dimension(50, 50));
        btnCercle.setPreferredSize(new Dimension(50, 50));
        btnCarre.setPreferredSize(new Dimension(50, 50));
        btnTexte.setPreferredSize(new Dimension(50, 50));
        btnRemplir.setPreferredSize(new Dimension(50, 50));
        btnCouleur.setPreferredSize(new Dimension(50, 50));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        /* Horizontal */
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(btnCercle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(btnCarre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(btnCouleur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(btnTexte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(btnRemplir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(btnLigne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCercle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCarre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCouleur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTexte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemplir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLigne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }

    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");
        Color btnBackColor     = this.ctrl.getTheme().get("btnBackground");

        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        btnLigne.setBackground(btnBackColor);
        btnLigne.setForeground(foreGeneralColor);

        btnCercle.setBackground(btnBackColor);
        btnCercle.setForeground(foreGeneralColor);

        btnCarre.setBackground(btnBackColor);
        btnCarre.setForeground(foreGeneralColor);

        btnTexte.setBackground(btnBackColor);
        btnTexte.setForeground(foreGeneralColor);

        btnRemplir.setBackground(btnBackColor);
        btnRemplir.setForeground(foreGeneralColor);

        btnCouleur.setBackground(btnBackColor);
        btnCouleur.setForeground(foreGeneralColor);
    }
}
