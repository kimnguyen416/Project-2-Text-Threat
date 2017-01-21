//Name: Kim Nguyen
//80813737
//Section#:9504
//TA: Val
//Project Number: 2
//Brief Description of File Contents:Text Threat Assessment for UFPD
import java.util.Scanner; //imports Scanner
import java.io.File; //imports File for later usage in reading files
public class TextThreatAssessment {
	public static void main(String[] args) {
		
	//Declaration of Variables
	String keyword;//What user wants to find
	String levelOfThreat;
	//Used to continue/exit the program
	String userInput = "";
	//These variables are a part of the decrypting process of file text
	String ufid;
	String reverseFileText; //reverse file text
	
	//huge while loop to give users a choice to continue or exit
	while (!"n".equals(userInput)){
		
	//Creates Scanner object
	Scanner input = new Scanner(System.in);
	
	//placed at the beginning to prevent errors from counter
	String reverseByTwo = ""; //file code is reversed and looked at every other word
	int indexStart = 0; //just to read from 0 and find the keyword from there
	int indexKeyword = 0;
	int count = 0; //keep track of how many times keyword was found
	indexKeyword = 0;
		
	//Printed out statement for UFID
	System.out.print("Enter the UFID of the person who wrote the message: ");
	ufid = input.nextLine();
	
	//conditions to make sure there is only 8 digits for UFID
	if (ufid.length() < 8 || ufid.length() > 8){
		System.out.println("Error: UFID must be 8 digits. Now Exiting.");
		System.exit(1);
	}
	else if (ufid.indexOf("0") == 0){
		System.out.println("Error: UFID must not begin with a zero. Now Exiting.");
		System.exit(1);
	}
	
	//Obtaining files, to read the file ufid.txt
			String encryptedString = "";
				try
				{
					File file = new File(ufid + ".txt");
					Scanner sc = new Scanner(file);
					while (sc.hasNextLine())
					{
							encryptedString += sc.nextLine();
					}
					 sc.close();
				} catch(Exception ex)
				{
					ex.printStackTrace();
				}
		
	//opens ufid.txt file
	System.out.println("Opening file: " + ufid + ".txt");
	
	
	/*Conditions for reading code,reversing code,consider every other position of
	 decrypted message*/
	//This reverses the ufid.txt, encrypedString is the content of the ufid.txt
	reverseFileText = new StringBuffer(encryptedString).reverse().toString();
	
	//Gets every other word of the ufid.txt
	//represents the length of the string, reads every other character of ufid.txt
	int i;
	for (i = 1; i < reverseFileText.length() ; i += 2){
		reverseByTwo = reverseByTwo + reverseFileText.charAt(i);
	}
	
	//user enters keyword
	System.out.print("Enter the word (or phrase) of interest: ");
	keyword = input.nextLine();
	
	//Code to find the keyword in file
	//To test how many times the keyword is found in ufid.txt, within the file's.length()
	while (indexKeyword != -1 ) {
		indexKeyword = reverseByTwo.indexOf(keyword, indexStart);
		if (indexKeyword == -1){
			break;
		}
		else{
			count++;
			indexStart = indexKeyword + 1;
		}
	}
	
	//levelOFThreat
	//levelOfThreat based on number of times keyword is found	
	if (count >= 4){
		levelOfThreat = "Highly Threatening";
	}
	else if (count >= 3){
		levelOfThreat = "Threatening";
	}
	else if (count >= 2){
		levelOfThreat = "Somewhat Threatening";
	}
	else {
		levelOfThreat = "Safe";
	}
	
	//Final Output based on decrypted code
	System.out.println("The word " + keyword + " was found " + count + 
	" time(s).");
	System.out.println("Messages from student " + ufid + " were found to be: " + 
	levelOfThreat);
	
		
	//Continue (y)  or (n) to exit//Conditions to (y) continue or (n) to exit.
	System.out.print("Press (y) to continue or (n) to exit. ");
	userInput = input.nextLine();
	
			
	}

	}
}
