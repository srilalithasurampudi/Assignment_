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
                System.out.print("Enter Company: ");
                String company = input.readLine();
                System.out.print("Enter Symbol: ");
                String symbol = input.readLine();
                System.out.print("Enter Quantity: ");
                int quantity = Integer.parseInt(input.readLine());

                String req = "Company:" + company + "\n" + "Symbol:"+ symbol + "\n" + "Number Ordered:" + quantity;
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
