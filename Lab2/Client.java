import java.rmi.*;

public class Client{
	public static void main(String args[]){
		try{
			String URL;
			URL = "rmi://127.0.0.1/theServer";
			QueryInterface server = (QueryInterface) Naming.lookup(URL);
			System.out.println("" + server.getPantient(Integer.parseInt(args[0])));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}