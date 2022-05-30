import java.rmi.*;

public interface QueryInterface extends Remote{
	Patient getPantient(int id) throws RemoteException;
}