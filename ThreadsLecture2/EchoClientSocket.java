package ThreadsLecture2;

//https://www.roseindia.net/java/network/echoclientsocket.shtml
import java.io.*;
import java.net.*;

public class EchoClientSocket {
	public static void main(String[] args) throws IOException {
		try {
			Socket echoSocket = new Socket("www.bbc.co.uk", 80);
			System.out.println(echoSocket);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: roseindi.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("I understand about " + "the host: roseindi.");
			System.exit(1);
		}
	}
}