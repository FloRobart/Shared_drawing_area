package controleur;

import ihm.FramePrinciple;
import metier.Metier;

public class Controleur
{
    private Metier metier;
    private FramePrinciple ihm;


    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new FramePrinciple(this);
    }


    public static void main(String[] args)
    {
        new Controleur();
    }
}