import java.io.Serializable;

public class Patient implements Serializable{
	int id;
	String name;
	int yearOfBirth;

	Patient(int i, String arg, int j){
		id = i;
		name = arg;
		yearOfBirth = j;
	}
	
	public String get(){ return this.toString(); }

	public String toString() { return "ID: " + id + "; Name: " + name + "; Year of birth: " + yearOfBirth;}
}