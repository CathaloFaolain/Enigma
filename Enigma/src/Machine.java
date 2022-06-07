
public class Machine {
	//An enigma machine has 3 rotors
	private Rotor rotor1;
	private Rotor rotor2;
	private Rotor rotor3;
	
	//And 1 reflector        /ABCDEFGHIJKLMNOPQRSTUVWXYZ/
	private String reflector="YRUHQSLDPXNGOKMIEBFZCWVJAT";
	
	//Constructor for the machine class
	public Machine(Rotor A, Rotor B, Rotor C) {
		//Initalize the rotors
		this.rotor1=A;
		this.rotor2=B;
		this.rotor3=C;
	}
	
	//This is the enigma machine encoding steps
	public String encoding(String message){
		//Set the message to only be capitals and letters
		message=message.toUpperCase();
		
		//Split the message string into a character array
		char [] messageletters=message.toCharArray();
		
		//Create a character array to hold the encoded letters
		char [] encoded= new char [messageletters.length];
		
		//Go through all the letters in the character array and encode them
		for(int i=0; i<messageletters.length; i++) {
			if((messageletters[i] >= 'A') && (messageletters[i] <= 'Z')) {
				//Encode the letter
				char encode;
				//Go through the rotors
				encode=this.rotor1.encodeIn(messageletters[i]);
				encode=this.rotor2.encodeIn(encode);
				encode=this.rotor3.encodeIn(encode);
				
				//Reflector
				encode=reflect(encode);
				
				//Reflect the letter through the rotors again
				encode=this.rotor3.encodeOut(encode);
				encode=this.rotor2.encodeOut(encode);
				encode=this.rotor1.encodeOut(encode);
				
				//Add it to the character array of encoded letters
				encoded[i]=encode;
				
				//Rotate the rotors. If a notch has been hit, rotate the next rotor in the sequence
				if(this.rotor1.rotate()) {
					if(this.rotor2.rotate()) {
						this.rotor3.rotate();
					}
				}
			}
		}
		
		//Create a String from the encoded character array
		String encodedmessage= String.valueOf(encoded);
		
		//Return the encoded message
		return encodedmessage;
	}
	
	//This is the function to encode the letter
	public char reflect(char given) {
		//Get the reflector string as a character array
		char [] letters= this.reflector.toCharArray();
				
		//The formula to get the letter's corresponding number
		char encoded=letters[given - 'A'];
		
		//Return the encoded number
		return encoded;
	}
}
