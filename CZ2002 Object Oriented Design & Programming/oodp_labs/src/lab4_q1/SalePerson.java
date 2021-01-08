package lab4_q1;

public class SalePerson implements Comparable {
	private String firstName;
	private String lastName;
	private int totalSales;
	
	public SalePerson(String string, String string2, int i) {
		firstName = string;
		lastName = string2;
		totalSales = i;
	}

	public String toString() {
		return (lastName + " , " + firstName + " : " + totalSales);
	}
	
	public boolean equals(Object o) {
		if (o instanceof SalePerson) //check if downcasting is legitimate
			return ((firstName == ((SalePerson) o).getFirstName()) && (lastName == ((SalePerson) o).getLastName()));
		else
			return false;
	}
	
	public int compareTo(Object o) {
		if (o instanceof SalePerson) //check if downcasting is legitimate
		{
			if ( totalSales != ((SalePerson)o).getTotalSales() )
				return (totalSales - ((SalePerson)o).getTotalSales());
			else				
				return  ( ((SalePerson)o).getLastName()).compareTo(lastName);
		}
		
		else
			return 0;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public int getTotalSales() {
		return totalSales;
	}


}
