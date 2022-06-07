

public class Main {

	//Main here takes input. In most ways it simulates the operator of the enigma machine
	public static void main(String args[]){
		//Create a machine with 3 rotors
		//Create the rotors to encode
		Rotor A = new Rotor(1, 'E');
		Rotor B=new Rotor(2, 'A');
		Rotor C=new Rotor(5, 'V');
		
		Machine m= new Machine(A, B, C);
		
		//Give a message
		String message= "lettersforsmelly";
		
		//Encode the message and print
		String encoded= m.encoding(message);
		System.out.println("The encoded message is :\n" + encoded);
		
		//Reset the rotors to be the same as the previous one
		Rotor D = new Rotor(1, 'E');
		Rotor E=new Rotor(2, 'A');
		Rotor F=new Rotor(5, 'V');
		
		//Create a new machine
		Machine s= new Machine(D, E, F);
		
		//Decode the message and print
		String decoded=s.encoding(encoded);
		System.out.println("The decoded message is :\n" + decoded);
		
	}
	
}
