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
                byte[] symbolBytes = new byte[4];
                in.readFully(symbolBytes);
                String symbol = new String(symbolBytes);
                StringBuilder companyBuilder = new StringBuilder();
                byte b = in.readByte();
                while (b != 0) {
                    companyBuilder.append((char) b);
                    b = in.readByte();
                }
                String company = companyBuilder.toString();
                int numOrdered = in.readInt();

                BusinessLogic businessLogic = new BusinessLogic();
                int result = businessLogic.calculatePrice(symbol, company, numOrdered);
                String response;
                if (result == -1)
                    response = "Price: ERROR NOT KNOWN";
                else
                    response = "Price: " + Integer.toString(result);
                byte[] responseOutput = response.getBytes();
                out.writeInt(responseOutput.length);
                out.write(responseOutput);
                out.flush();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
