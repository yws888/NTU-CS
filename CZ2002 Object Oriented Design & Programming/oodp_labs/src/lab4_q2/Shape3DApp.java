package lab4_q2;

import java.util.Scanner;

public class Shape3DApp {

	public static void main(String[] args) {
		int noOfShapes;
		Scanner sc = new Scanner(System.in);
		boolean repeat = true;
		
		while(repeat) {
		System.out.println("Enter the total number of 3D shapes:");
		noOfShapes = sc.nextInt();
		
		
		Shape3D[] Shape3DArray = new Shape3D[noOfShapes]; 
		
		for (int i = 0; i < noOfShapes; i++) {
			System.out.println("Choose 3D shape " + (i+1) + " by entering its corresponding number:");
			System.out.println("1. Sphere");
			System.out.println("2. Pyramid");
			System.out.println("3. Cuboid");
			System.out.println("4. Cone");
			System.out.println("5. Cylinder");

			switch(sc.nextInt()) {
				case 1:
					System.out.println("You have selected a Sphere");
					System.out.println("Enter Sphere Radius:");
					Shape3DArray[i] = new Sphere(sc.nextInt());
					break;
				case 2:
					System.out.println("You have selected a Pyramid");
					System.out.println("Enter Pyramid Height:");
					int height = sc.nextInt();
					System.out.println("Enter Pyramid Base:");
					Shape3DArray[i] = new Pyramid(height, sc.nextInt());
					break;
				case 3:
					System.out.println("You have selected a Cuboid");
					System.out.println("Enter Cuboid Length:");
					int length = sc.nextInt();
					System.out.println("Enter Cuboid Breadth:");
					int breadth = sc.nextInt();
					System.out.println("Enter Cuboid Height:");
					Shape3DArray[i] = new Cuboid(length, breadth, sc.nextInt());
					break;
				case 4:
					System.out.println("You have selected a Cone");
					System.out.println("Enter Cone Height:");
					height = sc.nextInt();
					System.out.println("Enter Cone Radius:");
					Shape3DArray[i] = new Cone(height, sc.nextInt());
					break;
				case 5:
					System.out.println("You have selected a Cylinder");
					System.out.println("Enter Cylinder Height:");
					height = sc.nextInt();
					System.out.println("Enter Cylinder Radius:");
					Shape3DArray[i] = new Cylinder(height, sc.nextInt());
					break;
				default:
					System.out.println("Enter an integer between 1 - 5:");
					break;
			}
		}
		System.out.println("Choose what to calculate by entering its corresponding number:");
		System.out.println("1. Area");
		System.out.println("2. Volume");
		switch(sc.nextInt()) {
		case 1:
			double area = 0;
			for (int i = 0; i < noOfShapes; i++) {
				area = area + Shape3DArray[i].calculateArea();
			}
			System.out.println("Total Area to 2 d.p. = " + String.format("%.2f", area));
			break;
		case 2:
			double volume = 0;
			for (int i = 0; i < noOfShapes; i++) {
				volume = volume + Shape3DArray[i].calculateVolume();
			}
			System.out.println("Total volume to 2 d.p. = " + String.format("%.2f", volume));
			break;
		default:
			System.out.println("Enter 1 or 2:");
			break;
		}
		System.out.println();
		System.out.println("Choose what to do next by entering its corresponding number:");
		System.out.println("1. Calculate again");
		System.out.println("2. Exit:");
		System.out.println();
		if (sc.nextInt() != 1)
			repeat = false;
		}
		sc.close();
	}

}
