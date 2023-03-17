package reseau;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerToClientSocket extends Thread
{
    private ServerThread serverThread;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Boolean running;
    
    public ServerToClientSocket(ServerThread serverThread, Socket socket)
    {
        this.serverThread = serverThread;
        try
        {
            this.ois = new ObjectInputStream(socket.getInputStream());
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.running = true;
        }
        catch (Exception e)
        {
            System.err.println("Impossible de créer les flux d'entrée/sortie");
            e.printStackTrace();
        }
    }

    public void Disconnect()
    {
        this.running = false;
    }



    @Override
    public void run()
    {
        while (this.running)
        {

            // Read the object sent by the client
            try
            {
                String command = (String)ois.readObject();
                if (command.equals("disconnect"))
                {
                    this.Disconnect();
                    break;
                }

                if (command.equals("name"))
                {
                    String name = (String)ois.readObject();
                    System.out.println("Client name: " + name);
                }

                if (command.equals("requestDrawing"))
                {
                    System.out.println("Client requested drawing");

                    // Send the drawing to the client
                    

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
