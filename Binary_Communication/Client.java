import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected to server.");
            while(true) {
                String[] companyInput = input.readLine().split(":");
                String[] symbolInput = input.readLine().split(":");
                String[] quantityInput = input.readLine().split(":");
                String symbol = symbolInput[1];
                if (symbol.length() < 4) {
                    StringBuilder padString = new StringBuilder(symbol);
                    while (padString.length() < 4) {
                        padString.append(' ');
                    }
                    out.writeBytes(padString.toString());
                }
                else if (symbol.length() > 4) {
                    out.writeBytes(symbol.substring(0,4));
                }
                else {
                    out.writeBytes(symbol);
                }
                out.writeBytes(companyInput[1]);
                out.writeByte(0);
                out.writeInt(Integer.parseInt(quantityInput[1]));
                out.flush();

                int responseLength = in.readInt();
                byte[] response = new byte[responseLength];
                in.readFully(response);
                System.out.println(new String(response));
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    } 

}
