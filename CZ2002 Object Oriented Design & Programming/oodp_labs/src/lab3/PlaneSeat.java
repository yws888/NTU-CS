package lab6;

public class PlaneSeat {
	
	//inst vars
	private int seatId = 0; 
	private boolean assigned = false;
	private int customerId = 0;
	
	//inst methods
	public PlaneSeat(int seat_id) {
		seatId = seat_id;
	}

	public int getSeatID() {
		return seatId;
	}
	
	public int getCustomerID() {
		return customerId;
	}
	
	public boolean isOccupied() {
		return assigned;
	}
	
	public void assign(int cust_id) {
		customerId = cust_id;
		assigned = true;
	}
	
	public void unAssign() {
		customerId = 0;
		assigned = false;
	}
}
