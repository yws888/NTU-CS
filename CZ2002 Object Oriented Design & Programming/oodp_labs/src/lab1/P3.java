package lab1;

import java.util.Scanner;

public class P3 {

	public static void main(String[] args) {
		Scanner sc_start = new Scanner(System.in);
		Scanner sc_end = new Scanner(System.in);
		Scanner sc_incr = new Scanner(System.in);

		
		System.out.println("Enter the starting value:");
		
		int start = sc_start.nextInt();
		
		System.out.println("Enter the ending value:");
		
		int end = sc_end.nextInt();
		
		System.out.println("Enter the increment value:");

		int incr = sc_incr.nextInt();
		
		if (start > end) {
			System.out.println("Error input!!");
			System.exit(0);
		}
		
		System.out.println("Table 1:");
		System.out.println("US$         S$");
		System.out.println("--------------");
		
		for(int i = start; i <= end; i+=incr) {
			System.out.println(i + "           " + i*1.82);
		}
		
		System.out.println();
		System.out.println("Table 2:");
		System.out.println("US$         S$");
		System.out.println("--------------");
		
		int j = start;
		
		while(j <= end) {
			System.out.println(j + "           " + j*1.82);
			j+=incr;
		}
		
		System.out.println();
		System.out.println("Table 3:");
		System.out.println("US$         S$");
		System.out.println("--------------");
		
		int k = start;
		
		do {
			System.out.println(k + "           " + k*1.82);
			k+=incr;
		} while(k <= end);
		
	}

}
