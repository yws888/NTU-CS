package lab1;

import java.util.Scanner;

public class P4 {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);  // Create a Scanner object
	    
		System.out.print("Enter height:");
	    
	    int height = sc.nextInt();
	    
	    int j, k = 1;
	    
	    if (height >= 1) 
	    {
	    	for (int i = 1; i <= height; i++) 
	    	{	
	    		if (i % 2 == 1) //for odd rows print AA first
	    		{
	    		    for (j = 1; j <= i; j++) 
	    		    {
	    		    	if (j%2 == 1) 
	    		    		System.out.print("AA");
	    		    	else
	    		    		System.out.print("BB");
	    		    }
	    		    System.out.print("\n");
	    		}
	    		
	    		else if (i % 2 == 0) //for even rows print BB first
	    		{
	    		    for (k = 1; k <= i; k++) 
	    		    {
	    		    	if (k%2 == 1) 
	    		    		System.out.print("BB");
	    		    	else
	    		    		System.out.print("AA");
	    		    }
	    		    System.out.print("\n");
	    		}
	    	}
	    }
	    
	    else {
		    System.out.println("Error input!!");
	    }
	}

}
