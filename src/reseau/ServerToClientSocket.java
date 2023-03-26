package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import metier.Forme;

public class ServerToClientSocket extends Thread
{
    private ServerThread serverThread;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Boolean running;
    
    public ServerToClientSocket(ServerThread serverThread, Socket socket)
    {
        this.serverThread = serverThread;
        try
        {
            this.ois = new ObjectInputStream(socket.getInputStream());
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.running = true;
        }
        catch (Exception e)
        {
            System.err.println("Impossible de créer les flux d'entrée/sortie");
            e.printStackTrace();
        }
    }

    public void Disconnect()
    {
        this.running = false;
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

    public void unRemoveForme(Forme forme)
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

    public void clearDrawings()
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

            // Read the object sent by the client
            try
            {
                String command = (String)ois.readObject();
                if (command.equals("disconnect"))
                {
                    this.Disconnect();
                    break;
                }

                if (command.equals("name"))
                {
                    String name = (String)ois.readObject();
                    System.out.println("Client name: " + name);
                }

                if (command.equals("requestDrawing"))
                {
                    System.out.println("Client requested drawing");

                    // Send the drawing to the client

                    List<Forme> forms = this.serverThread.getCtrl().getLstFormes();
                    
                    oos.writeObject("drawings");
                    oos.writeObject(forms);
                    oos.flush();
                }

                if (command.equals("newDrawing"))
                {
                    Forme form = (Forme)ois.readObject();
                    this.serverThread.getCtrl().getLstFormes().add(form);

                    // Envoyer la forme à tous les clients

                    this.serverThread.broadcastForme(form);
                    this.serverThread.getCtrl().majIhm();
                }

                if (command.equals("majDrawing"))
                {
                    Forme form = (Forme)ois.readObject();
                    this.serverThread.getCtrl().majForme(form);

                    // Envoyer la forme à tous les clients
                    this.serverThread.broadcastMajForme(form);
                }

                if (command.equals("removeDrawing"))
                {
                    String id = (String)ois.readObject();
                    this.serverThread.getCtrl().removeFormeNetwork(id);
                    for (Forme form : this.serverThread.getCtrl().getLstFormes())
                    {
                        if (form.getId().equals(id))
                        {
                            this.serverThread.broadcastRemoveForme(form);
                            break;
                        }
                    }
                }

                if (command.equals("unRemoveDrawing"))
                {
                    String id = (String)ois.readObject();
                    this.serverThread.getCtrl().unRemoveFormeNetwork(id);
                    for (Forme form : this.serverThread.getCtrl().getLstFormes())
                    {
                        if (form.getId().equals(id))
                        {
                            this.serverThread.broadcastUnRemoveForme(form);
                            break;
                        }
                    }
                }

                if (command.equals("clear"))
                {
                    this.serverThread.getCtrl().newDrawingAreaNetwork();
                    this.serverThread.broadcastClear();
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
