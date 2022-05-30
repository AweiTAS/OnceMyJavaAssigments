import java.io.*;
import java.net.*;
public class ASCIIServer {
    public static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        int i;
        char c;
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: " + s);
        try {
            Socket socket = s.accept();
            try {
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new
                    BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
                while (true) {
                    String str = in.readLine();
                    System.out.print("Received: " + str);
                    if (str.equals("-1"))
                        break;
                    i = Integer.parseInt(str);
                    c = (char)i;
                    System.out.println(" Sent: " + c);
                    out.println(c);
                }
            }finally{
                System.out.println("");
                System.out.println("closing...");
                socket.close();
            }
        }finally{
             s.close();
        }
    }
}