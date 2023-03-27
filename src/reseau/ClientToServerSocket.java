package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import controleur.Controleur;
import metier.Forme;
@SuppressWarnings("unchecked") 
public class ClientToServerSocket extends Thread
{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    private Boolean running;
    private Controleur ctrl;
    
    public ClientToServerSocket(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    public Boolean Connect(String ip, int port)
    {
        try
        {
            
            this.socket = new Socket(ip, port);

            if (this.socket.isConnected())
            {
                System.out.println("Connexion établie avec le serveur");
            }
            else
            {
                System.err.println("Impossible de se connecter au serveur");
                return false;
            }


            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
            this.running = true;
        }
        catch (Exception e)
        {
            System.err.println("Impossible de créer les flux d'entrée/sortie");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void Disconnect()
    {
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
        }

    }

    
    
    public void requestDrawing()
    {
        if (!this.running)
        {
            return;
        }

        try {
            this.oos.writeObject("requestDrawing");
        } catch (IOException e) {
            System.err.println("Impossible d'envoyer la demande de dessin");
            e.printStackTrace();
        }

    }

    public void sendUsername(String username)
    {
        try {
            oos.reset();
            oos.writeObject("setUsername");
            oos.writeObject(username);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendForme(Forme forme)
    {
        try {
            oos.reset();
            oos.writeObject("newDrawing");
            oos.writeObject(forme);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void majForme(Forme forme)
    {
        try {
            oos.reset();
            oos.writeObject("majDrawing");
            oos.writeObject(forme);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void removeForme(Forme forme)
    {
        try {
            oos.reset();
            oos.writeObject("removeDrawing");
            oos.writeObject(forme.getId());
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void unRemvoeForme(Forme forme)
    {
        try {
            oos.reset();
            oos.writeObject("unRemoveDrawing");
            oos.writeObject(forme.getId());
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void sendClear()
    {
        try {
            oos.reset();
            oos.writeObject("clear");
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (this.running)
        {

            // Read the object sent by the server
            try
            {
                String command = (String)ois.readObject();
                if (command.equals("disconnect"))
                {
                    this.Disconnect();
                }

                if (command.equals("usernameAccepted"))
                {
                    JOptionPane.showMessageDialog(null, "Connexion établie");
                }

                if (command.equals("usernameRefused"))
                {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur déjà utilisé");
                    this.Disconnect();
                }
                
                if (command.equals("drawings"))
                {
                    this.ctrl.getLstFormes().clear();
                    List<Forme> lstFormes = (List<Forme>)ois.readObject();
                    
                    for (Forme forme : lstFormes)
                    {
                        this.ctrl.getLstFormes().add(forme);
                    }
                    this.ctrl.majIhm();
                }

                if (command.equals("newDrawing"))
                {
                    Forme forme = (Forme)ois.readObject();
                    Boolean found = false;
                    for (Forme f : this.ctrl.getLstFormes())
                    {
                        if (f.getId().equals(forme.getId()))
                        {
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                    {
                        this.ctrl.getLstFormes().add(forme);
                    }
                    else
                    {
                        System.err.println("Forme déjà présente");
                    }
                    this.ctrl.majIhm();
                }

                if (command.equals("majDrawing"))
                {
                    Forme forme = (Forme)ois.readObject();
                    this.ctrl.majForme(forme);
                }

                if (command.equals("removeDrawing"))
                {
                    this.ctrl.removeFormeNetwork((String)ois.readObject());
                }

                if (command.equals("unRemoveDrawing"))
                {
                    this.ctrl.unRemoveFormeNetwork((String)ois.readObject());
                }

                if (command.equals("clear"))
                {
                    this.ctrl.newDrawingAreaNetwork();
                }

            }
            catch (Exception e)
            {
                System.err.println("Impossible de lire l'objet envoyé par le client");
                e.printStackTrace();
                break;
            }



        }
    }
    
}