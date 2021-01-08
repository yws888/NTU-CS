package lab2;

import java.util.*;

public class Lab2p1 {
	public static void main(String[] args) {
		int choice;
		Scanner sc = new Scanner(System.in); 
		do {
			System.out.println("Perform the following methods:"); 
			System.out.println("1: multiplication test"); 
			System.out.println("2: quotient using division by subtraction"); 
			System.out.println("3: remainder using division by subtraction"); 
			System.out.println("4: count the number of digits"); 
			System.out.println("5: position of a digit"); 
			System.out.println("6: extract all odd digits"); 
			System.out.println("7: quit");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1: 
					Lab2p1.mulTest();
					break;
				case 2: {
					System.out.println("Enter a positive integer (i.e. 1,2,3,4,...) m:");
					int m = sc.nextInt();

					System.out.println("Enter a positive integer (i.e. 1,2,3,4,...) n:");
					int n = sc.nextInt();
	
					System.out.println(Lab2p1.divide(m, n));
					break;
					}
				case 3: {
					System.out.println("Enter a positive integer (i.e. 1,2,3,4,...) m:");
					int m = sc.nextInt();

					System.out.println("Enter a positive integer (i.e. 1,2,3,4,...) n:");
					int n = sc.nextInt();

					System.out.println(modulus(m, n));
					break;
					}
				case 4: {/* add countDigits() call */
					System.out.println("Enter a positive integer (i.e. 1,2,3,4,...):");	
					int n = sc.nextInt();
					
					System.out.print("n : " + n);
					if ((countDigits(n)) == -1) {
						System.out.println(" - Error input!!");
					}
					
					else {
						System.out.println(" - count = " + countDigits(n));
					}
					
					break;
				}
				case 5: {/* add position() call */
					System.out.println("Enter a positive integer n:");	
					int n = sc.nextInt();
					System.out.println("Enter a digit:");	
					int digit = sc.nextInt();
					
					System.out.println("position = " + position(n, digit));							
					break;
				}
				case 6: /* add extractOddDigits() call */
				{
					System.out.println("Enter a positive number:");
					extractOddDigits(sc.nextLong());
					break;
				}
				case 7: 
					System.out.println("Program terminating ....");
			}
		} while (choice < 7); 
	}
					

	public static void mulTest() {
		Scanner sc_ans = new Scanner(System.in);
		int noOfCorrect = 0;
		
		for (int i = 1; i<=5; i++) {
			int x = (int)(1 + Math.random()*9);
			int y = (int)(1 + Math.random()*9);
			System.out.print("How much is " + x + " times " + y + "?");
			int ans = sc_ans.nextInt();
			if (ans == (x*y)) {
				noOfCorrect++;
			}
		}
		
		System.out.println(noOfCorrect + " answers out of 5 are correct.");		
	}
	
	public static int divide(int m, int n) {
		int ans = 0;
		
		System.out.print(m + "/" + n + " = ");
		
		while (m >= n) {
			m = m-n;
			ans++;
		}
		
		return ans;
	}
	
	
	
	public static int modulus(int m, int n) {
		
		System.out.print(m + "%" + n + " = ");
		
		while (m >= n) {
			m = m-n;
		}
						
		return m;
	}
	
	//if a double is entered error occurs?
	public static int countDigits(int n) {
		
		if (n<=0) {
			return -1;
		}
		
		else {
		
			int noOfDigits = 1;
		
			while (n >= 10) {
				n = n/10;
				noOfDigits++;
			}
					
			return noOfDigits;
		}
	}
	
	public static int position(int n, int digit) 
	{
		int nLength = countDigits(n);
		
		for (int i = 1; i<=nLength; i++) {
			if (digit==(n%10))
			{
				return i;
			}
			n = n/10;
		}
		
		return -1;
	}
	
	
	public static long extractOddDigits(long n)
	{
		long oddDigits = 0;
		long rev_n = 0;
		
		if (n<=0) 
		{
			System.out.println("oddDigits = Error input!!");
			return -1;
		}
		
		else 
		{
			//reverse n and store in rev_n
			while (n > 0) {
				rev_n = (rev_n*10) + (n%10);
				n = n/10;
			}
			
			while (rev_n > 0) {
				if ( ((rev_n%10)%2) == 1) //if digit is odd
				{
					oddDigits = (oddDigits*10) + (rev_n%10);
				}
				rev_n = rev_n/10;
			}
			
			if (oddDigits == 0) {
				System.out.println("oddDigits = -1");
				return -1;
			}
			
			System.out.println("oddDigits = " + oddDigits);
			return oddDigits;
		}
	}
	
	
}
