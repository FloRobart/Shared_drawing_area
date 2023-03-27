package reseau;

import java.io.IOException;
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
    private Boolean running;

    public ServerThread(Controleur ctrl)
    {
        this.ctrl = ctrl;
        try
        {
            this.serverSocket = new ServerSocket(31337);
            this.serverToClientSockets = new ArrayList<ServerToClientSocket>();
            this.running = true;
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
        while (this.running)
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

    public void broadcastRemoveForme(Forme forme)
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.removeForme(forme);
        }
    }

    public void broadcastUnRemoveForme(Forme forme)
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.unRemoveForme(forme);
        }
    }

    public void broadcastClear()
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.clearDrawings();
        }
    }

    public void closeServer()
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.disconnect();
        }
        this.running = false;

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Controleur getCtrl()
    {
        return this.ctrl;
    }

}
