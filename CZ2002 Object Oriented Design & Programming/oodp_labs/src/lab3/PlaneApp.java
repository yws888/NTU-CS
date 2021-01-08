package lab6;

import java.util.*;

public class PlaneApp {

	public static void main(String[] args) {
		Plane plane = new Plane();
		int choice;
		Scanner sc = new Scanner(System.in); 
		do {
			System.out.println("(1) Show number of empty seats");
			System.out.println("(2) Show the list of empty seats");
			System.out.println("(3) Show the list of seat assignments by seat ID");
			System.out.println("(4) Show the list of seat assignments by customer ID");
			System.out.println("(5) Assign a customer to a seat");
			System.out.println("(6) Remove a seat assignment");
			System.out.println("(7) Exit");
			System.out.println();
			System.out.print("	Enter the number of your choice: "); 
			choice = sc.nextInt();
			
			switch (choice) {
				case 1: 
					plane.showNumEmptySeats();
					System.out.println();
					break;
				case 2: {
					plane.showEmptySeats();
					System.out.println();
					break;
					}
				case 3: {
					plane.showAssignedSeats(true);
					System.out.println();
					break;
					}
				case 4: {
					plane.showAssignedSeats(false);
					System.out.println();
					break;
				}
				case 5: {
					System.out.println("     Assigning Seat .. ");
					System.out.print("	Please enter SeatID: ");
					int seatID = sc.nextInt();
					
					System.out.print("	Please enter Customer ID: ");
					int custID = sc.nextInt();

					plane.assignSeat(seatID, custID);
					System.out.println();
					break;
				}
				case 6:
				{
					System.out.println("Enter SeatID to unassign customer from: ");
					plane.unAssignSeat(sc.nextInt());
					System.out.println();
					break;
				}
				case 7: //quit
					break;
				default:
					System.out.println("Please select an option from 1-7");
					System.out.println();
					break;
			}
		} while (choice != 7);
		
		sc.close();
	}

}
