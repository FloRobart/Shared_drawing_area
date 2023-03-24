package reseau;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import controleur.Controleur;
import metier.Forme;

public class ServerThread extends Thread
{

    private ServerSocket serverSocket;
    private ArrayList<ServerToClientSocket> serverToClientSockets;
    private Controleur ctrl;

    public ServerThread(Controleur ctrl)
    {
        this.ctrl = ctrl;
        try
        {
            this.serverSocket = new ServerSocket(31337);
            this.serverToClientSockets = new ArrayList<ServerToClientSocket>();
        }
        catch (Exception e)
        {
            System.err.println("Impossible de créer le serveur");
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (true)
        {
            // Accept a new client
            try
            {
                Socket socket = this.serverSocket.accept();
                ServerToClientSocket serverToClientSocket = new ServerToClientSocket(this, socket);
                this.serverToClientSockets.add(serverToClientSocket);
                serverToClientSocket.start();
            }
            catch (Exception e)
            {
                System.err.println("Impossible d'accepter un nouveau client");
                e.printStackTrace();
            }
        }
    }

    public void broadcastForme(Forme form)
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.sendForme(form);
        }
    }

    public void broadcastMajForme(Forme forme)
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.majForme(forme);
        }
    }

    public Controleur getCtrl()
    {
        return this.ctrl;
    }

}
