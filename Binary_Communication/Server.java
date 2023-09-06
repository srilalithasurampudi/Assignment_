import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server listening on port 1234");
            Socket clientSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            while(true) {
                String request = in.readUTF();
                BusinessLogic businessLogic = new BusinessLogic();
                int result = businessLogic.calculatePrice(request);
                String response;
                if (result == -1)
                    response = "Price: ERROR NOT KNOWN";
                else
                    response = "Price: " + Integer.toString(result);
                out.writeUTF(response);
                out.flush();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
