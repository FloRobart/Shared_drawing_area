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

    public void unRemoveForme(Forme form)
    {
        this.clientToServerSocket.unRemvoeForme(form);
    }

    public void sendClear()
    {
        this.clientToServerSocket.sendClear();
    }

    public void disconnect()
    {
        this.clientToServerSocket.disconnect();
    }

    public Boolean connect(String ip, int port, String pseudo)
    {

        if (this.clientToServerSocket.isAlive())
        {
            this.clientToServerSocket.disconnect();
        }

        Boolean success = this.clientToServerSocket.connect(ip, port);


        if (success)
        {
            this.clientToServerSocket.start();
            this.clientToServerSocket.sendUsername(pseudo);
            this.clientToServerSocket.requestDrawing();

        }

        return success;

    }




}
