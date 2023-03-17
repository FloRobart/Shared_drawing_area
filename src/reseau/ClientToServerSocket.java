package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientToServerSocket extends Thread
{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    private Boolean running;
    
    public ClientToServerSocket()
    {

    }

    public Boolean Connect(String ip, int port)
    {
        try
        {
            
            this.socket = new Socket(ip, port);

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

    public void sendName(String name)
    {
        if (!this.running)
        {
            return;
        }

        try {
            this.oos.writeObject("name");
            this.oos.writeObject(name);
        } catch (IOException e) {
            System.err.println("Impossible d'envoyer le nom");
            e.printStackTrace();
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
                else
                {
                    System.out.println("Commande inconnue");
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
