import java.net.*;
import java.io.*;
public class ASCIIClient {
	public static void main(String[] args) throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, MyServer.PORT);
		try {
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new
				BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
			for (int i=97; i < 123; i++) {
				System.out.print("Sent: " + i);
				out.println("" + i);
				String str = in.readLine();
				System.out.println(" Received: " + str);
			}
			out.println(-1);
		}finally{
			System.out.println("closing...");
			socket.close();
		}
	}
}