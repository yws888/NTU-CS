package lab1;

import java.util.Scanner;


public class P2 {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);  // Create a Scanner object
	    
	    System.out.println("Enter salary:");
	    
	    int salary = sc.nextInt();
	    
	    System.out.println("Enter merit points:");
	    
	    int merit = sc.nextInt();
	    
	    if (800 <= salary && salary <= 899) {
		    System.out.println("Grade A");
	    }
	    
	    else if (700 <= salary && salary <= 799) {
	    	if (merit < 20) {
	    		System.out.println("Grade B");
	    	}
	    	else
	    		System.out.println("Grade A");
	    }
	    	
	    
	    else if (650 <= salary && salary <= 699) {
		    System.out.println("Grade B");
	    }
	    
	    else if (600 <= salary && salary <= 649) {
	    	if (merit < 10) {
	    		System.out.println("Grade C");
	    	}
	    	else
	    		System.out.println("Grade B");
	    }
	    
	    else if (500 <= salary && salary <= 599) {
		    System.out.println("Grade C");
	    } 
	    
	    else {
	    	System.out.println("Salary out of range");
	    }
	    
	    sc.close();
	    
	}

}
