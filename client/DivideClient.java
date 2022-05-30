public class DivideClient{
	public static void main(String args[]){
		String divideServerURL;
		divideServerURL="rmi://"+args[0]+"/DivideServer";
		DivideServer divideServer = null;
		divideServer = (DivideServer) Naming.lookup (divideServerURL);
		//Can now treat the remote object as local!
		divideServer.divide(10, 100);
	}
}