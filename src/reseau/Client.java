package reseau;

<<<<<<< HEAD
=======
import controleur.Controleur;
import metier.Forme;

>>>>>>> f77543b920a0c29b460a2f271a648a710a4fa3e5
public class Client
{
    
    private ClientToServerSocket clientToServerSocket;

    public Client()
    {
        this.clientToServerSocket = new ClientToServerSocket();
    }

    public void sendForme(Forme form)
    {
        this.clientToServerSocket.sendForme(form);
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
