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
                String companyInput = input.readLine();
                String symbolInput = input.readLine();
                String quantityInput = input.readLine();

                String req = companyInput + "\n" + symbolInput + "\n" + quantityInput;
                byte[] request = req.getBytes();

                out.writeInt(request.length);
                out.write(request);
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
