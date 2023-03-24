package reseau;

import controleur.Controleur;
import metier.Forme;

public class Client
{
    
    private ClientToServerSocket clientToServerSocket;

    public Client(Controleur ctrl)
    {
        this.clientToServerSocket = new ClientToServerSocket(ctrl);
    }

    public void sendForme(Forme form)
    {
        this.clientToServerSocket.sendForme(form);
    }

    public void majForme(Forme form)
    {
        this.clientToServerSocket.majForme(form);
    }

    public void removeFrome(Forme form)
    {
        this.clientToServerSocket.removeForme(form);
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
