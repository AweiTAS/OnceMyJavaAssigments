import java.rmi.*;
import java.rmi.server.*;

public class QueryImpl extends UnicastRemoteObject implements QueryInterface{
	Patient arr[];

	public QueryImpl()throws RemoteException{
		arr = new Patient[4];
		arr[0] = new Patient(1001, "Tom", 1967);
		arr[1] = new Patient(1002, "Sarah", 1986);
		arr[2] = new Patient(1003, "Ben", 1975);
		arr[3] = new Patient(1004, "Eileen", 1984);
	}
	
	public Patient getPantient(int id) throws RemoteException{
		for (int p = 0; p < arr.length; p++){
			if(arr[p].id == id) return arr[p];
		}
		return (Patient)null;
	}
}