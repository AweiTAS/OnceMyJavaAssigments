import java.rmi.*;

public class Server{
	public static void main(String args[]){
		try{
			QueryImpl query = new QueryImpl();
			Naming.rebind("theServer", query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}