package lab1;

import java.util.Scanner;

public class P1 {

	public static void main(String[] args) {
	    Scanner userInput = new Scanner(System.in);  // Create a Scanner object
	    
	    System.out.println("Enter a character:");
	    
	    char choice = userInput.next().charAt(0);
	    
	    switch(choice) {
	    
	    	case 'a': 
	    	case 'A':
	    		System.out.println("Action movie fan\n");
	    		break;
	    	case 'c': 
	    	case 'C':
	    		System.out.println("Comedy movie fan\n");
	    		break;
	    	case 'D': 
	    	case 'd':
	    		System.out.println("Drama movie fan\n");
	    		break;
	    	default:
	    		System.out.println("Invalid choice\n");
	            break;

	    }
	    userInput.close();
	    
	}

}
