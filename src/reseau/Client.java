package reseau;

public class Client
{
    
    private ClientToServerSocket clientToServerSocket;

    public Client()
    {
        this.clientToServerSocket = new ClientToServerSocket();
    }

    public Boolean Connect(String ip, int port, String name)
    {

        if (this.clientToServerSocket.isAlive())
        {
            this.clientToServerSocket.Disconnect();
        }

        Boolean success = this.clientToServerSocket.Connect(ip, port);

        this.clientToServerSocket.sendName(name);

        if (success)
        {
            this.clientToServerSocket.start();
        }

        return success;

    }




}
