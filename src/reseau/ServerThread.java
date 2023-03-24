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
        this.serverToClientSockets = new ArrayList<ServerToClientSocket>();
        try
        {
            this.serverSocket = new ServerSocket(31337);
        }
        catch (Exception e)
        {
            System.err.println("Impossible de créer le serveur");
            e.printStackTrace();
        }
    }

    public void Stop()
    {
        try
        {
            this.serverSocket.close();
        }
        catch (Exception e)
        {
            System.err.println("Impossible de fermer le serveur");
            e.printStackTrace();
        }
    }

    public Controleur getCtrl()
    {
        return this.ctrl;
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

}
