package lab6;


public class Plane {
	
	private PlaneSeat[] seat;
	private int numEmptySeat;
	
	public Plane() {
		seat = new PlaneSeat[12];
		numEmptySeat = 12;
		for (int i = 0; i < seat.length; i++) {
			seat[i] = new PlaneSeat(i+1);
		}
	}
	
	private PlaneSeat[] sortSeats(){		
		
		PlaneSeat[] sortedSeatsCopy = new PlaneSeat[12];
		
		PlaneSeat temp = new PlaneSeat(0);
		
		for (int i = 0; i < seat.length; i++) 
		{
			sortedSeatsCopy[i] = new PlaneSeat(i+1);
			if (seat[i].isOccupied() == true) {
				sortedSeatsCopy[i].assign(seat[i].getCustomerID());
			}
		}
		
		for (int i = 0; i < sortedSeatsCopy.length; i++) 
        {
            for (int j = i+1; j < sortedSeatsCopy.length; j++) { 
                if ((sortedSeatsCopy[i].getCustomerID() > sortedSeatsCopy[j].getCustomerID())) 
                {
                	temp = sortedSeatsCopy[i];
                	sortedSeatsCopy[i] = sortedSeatsCopy[j];
                	sortedSeatsCopy[j] = temp;
                }
            }
        }
		
		return sortedSeatsCopy;
	}
	
	public void showNumEmptySeats() {
		System.out.println("     There are " + numEmptySeat + " empty seats");
	}
	
	public void showEmptySeats() {
		System.out.println("     The following seats are empty:");
		for (int i = 0; i < seat.length; i++) {
			if (seat[i].isOccupied() == false) {
				System.out.println("     SeatID " + seat[i].getSeatID());
			}
		}
	}
	
	public void showAssignedSeats(boolean bySeatId) {
		System.out.println("     The seat assignments are as follows:");
		if (bySeatId) {
			if (numEmptySeat == 12) {
				System.out.println("no seats assigned");
			}
			else {
				for (int i = 0; i < seat.length; i++) {
					if (seat[i].isOccupied() == true) {
						System.out.println("     SeatID " + seat[i].getSeatID() + " assigned to CustomerID " + seat[i].getCustomerID() + ".");
					}
				}
			}
		}
		
		else {
			PlaneSeat[] temp = sortSeats();
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].isOccupied() == true) {
					System.out.println("     SeatID " + temp[i].getSeatID() + " assigned to CustomerID " + temp[i].getCustomerID() + ".");
				}
			}
		}
	}
	

	public void assignSeat(int seatId, int cust_id) {
		if (seat[(seatId - 1)].isOccupied() == true) {
			System.out.println("Seat already assigned to a customer.");
		}
		else {
			seat[(seatId - 1)].assign(cust_id);
			numEmptySeat--;
			System.out.println("     Seat Assigned!");
		}
	}
	
	public void unAssignSeat(int seatId) {
		if (seat[(seatId - 1)].isOccupied() == true) {
			seat[(seatId - 1)].unAssign();
			numEmptySeat++;
			System.out.println("Seat Unassigned!");}
		else {
			System.out.println("Seat not assigned!");
		}
	}
	
	
}
