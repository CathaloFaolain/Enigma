public class Rotor {
	//The Scrambler Rotor Value
	private String scramblerString;
	//The value on the notch, which would set the next rotor to rotate
	private char notch1;
	//The second notch was only used in later rotors
	private char notch2;
	//This is how offset the values are from the scramblerString 
	private int shift;
	
	//This is the letter that is seen by the operator in the screen-the letter that the rotor is on
	// and maps to the letter A
	public char letter;
	
	//The Rotor class will take in a number. The number will designate its type and therefore its notches 
	// Scramble value
	public Rotor(int which, char orientation) {
		//Select the rotor scrambler settings from between 1 and 8
		switch(which) {
			default:                //ABCDEFGHIJKLMNOPQRSTUVWXYZ
				this.scramblerString="EKMFLGDQVZNTOWYHXUSPAIBRCJ";
				this.notch1='Q';
				this.notch2='$';
				break;
			case 2:					//ABCDEFGHIJKLMNOPQRSTUVWXYZ
				this.scramblerString="AJDKSIRUXBLHWTMCQGZNPYFVOE";
				this.notch1='E';
				this.notch2='$';
				break;
			case 3:					//ABCDEFGHIJKLMNOPQRSTUVWXYZ
				this.scramblerString="BDFHJLCPRTXVZNYEIWGAKMUSQO";
				this.notch1='V';
				this.notch2='$';
				break;
			case 4:
				this.scramblerString="ESOVPZJAYQUIRHXLNFTGKDCMWB";
				this.notch1='J';
				this.notch2='$';
				break;
			case 5:					//ABCDEFGHIJKLMNOPQRSTUVWXYZ
				this.scramblerString="VZBRGITYUPSDNHLXAWMJQOFECK";
				this.notch1='Z';
				this.notch2='$';
				break;
			case 6:
				this.scramblerString="JPGVOUMFYQBENHZRDKASXLICTW";
				this.notch1='Z';
				this.notch2='M';
				break;
			case 7:
				this.scramblerString="NZJHGRCXMYSWBOUFAIVLPEKQDT";
				this.notch1='Z';
				this.notch2='M';
				break;
			case 8:
				this.scramblerString="FKQHTLXOCBJSPDZRAMEWNIUYGV";
				this.notch1='Z';
				this.notch2='M';
				break;
			};
				
			//Set the orientation and offset values
			this.letter=orientation;
			this.shift=this.scramblerString.indexOf(orientation);	
	}
	
	//A toString method that tell the important information about the rotor
	public String toString() {
		return "The Rotor has a scrambler of: \""+this.scramblerString + 
				"\" and is in the position where the letter \'" + this.letter + 
				"\' is on the top, and thus an offset of " +this.shift + ".";
	}
	
	//The function to rotate the wheels
	public boolean rotate() {
		//Get the value of the letter corresponding with A prior to rotation
		char previous=this.letter;
		
		//Change the shift to be plus 1 of the current, modulo 26 as there is only 26 letters possible
		this.shift=(this.shift+1)%26;
		
		//Change the letter to be the next letter in the rotation sequence
		char [] letters=this.scramblerString.toCharArray();
		this.letter=letters[this.shift];
		
		//If the rotation has hit a notch, return True so as to rotate the next rotor
		if((previous == this.notch1) || (previous== this.notch2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//This is the function to encode the letter as its going in (before reflector)
	public char encodeIn(char given) {
		//Get the scrambler string as a character array
		char [] letters= this.scramblerString.toCharArray();
				
		//The formula to get the letter's corresponding number
		char encoded=letters[(this.shift + (given - 'A'))%26];
		
		//Return the encoded number
		return encoded;
	}
	
	//This is the function to encode the letter as its going out (after reflector)
	public char encodeOut(char given) {
		//We will need the alphabet for this next bit
		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char [] alphabets=alphabet.toCharArray();
		
		//Get the letter that corresponds to the alphabet given in
		char encoded=alphabets[(this.scramblerString.indexOf(given)-this.shift + 26)%26];
		
		return encoded;
	}
}
