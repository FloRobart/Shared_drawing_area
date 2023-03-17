package reseau;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread
{

    private ServerSocket serverSocket;
    private ArrayList<ServerToClientSocket> serverToClientSockets;

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

}
