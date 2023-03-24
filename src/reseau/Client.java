package reseau;

import controleur.Controleur;

public class Client
{
    
    private ClientToServerSocket clientToServerSocket;
    private Controleur ctrl;

    public Client(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.clientToServerSocket = new ClientToServerSocket(this.ctrl);
    }

    public Boolean Connect(String ip, int port, String name)
    {

        if (this.clientToServerSocket.isAlive())
        {
            this.clientToServerSocket.Disconnect();
        }

        Boolean success = this.clientToServerSocket.Connect(ip, port);

        this.clientToServerSocket.sendName(name);
        this.clientToServerSocket.requestDrawing();

        if (success)
        {
            this.clientToServerSocket.start();
        }

        return success;

    }




}
