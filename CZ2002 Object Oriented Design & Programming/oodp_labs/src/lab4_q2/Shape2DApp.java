package lab4_q2;

import java.util.Scanner;

public class Shape2DApp {

	public static void main(String[] args) {
		// Calculate the total surface area of a figure
		int noOfShapes;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the total number of 2D shapes:");
		noOfShapes = sc.nextInt();
		
		Shape[] ShapeArray = new Shape[noOfShapes]; 
		
		for (int i = 0; i < noOfShapes; i++) {
			System.out.println("Choose 2D shape " + (i+1) + " by entering its corresponding number:");
			System.out.println("1. Circle");
			System.out.println("2. Triangle");
			System.out.println("3. Rectangle");
			System.out.println("4. Square");


			switch(sc.nextInt()) {
				case 1:
					System.out.println("You have selected a Circle");
					System.out.println("Enter Circle Radius:");
					ShapeArray[i] = new Circle(sc.nextInt());
					break;
				case 2:
					System.out.println("You have selected a Triangle");
					System.out.println("Enter Triangle Height:");
					int height = sc.nextInt();
					System.out.println("Enter Triangle Base:");
					ShapeArray[i] = new Triangle(height, sc.nextInt());
					break;
				case 3:
					System.out.println("You have selected a Rectangle");
					System.out.println("Enter Rectangle Length:");
					int length = sc.nextInt();
					System.out.println("Enter Rectangle Breadth:");
					ShapeArray[i] = new Rectangle(length, sc.nextInt());
					break;
				case 4:
					System.out.println("You have selected a Square");
					System.out.println("Enter Square Length:");
					ShapeArray[i] = new Square(sc.nextInt());
					break;
									
				default:
					System.out.println("Enter an integer between 1 - 4:");
					break;
			}
		}
		System.out.println("Choose what to calculate by entering its corresponding number:");
		System.out.println("1. Area");
		switch(sc.nextInt()) {
		case 1:
			double area = 0;
			for (int i = 0; i < noOfShapes; i++) {
				area = area + ShapeArray[i].calculateArea();
			}
			System.out.println("Total Area to 2 d.p. = " + String.format("%.2f", area));
			break;
		default:
			break;
		}
		sc.close();
	}

}
