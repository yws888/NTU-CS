package lab4_q2;

public class Rectangle implements Shape {
	private int length;
	private int breadth;
	
	//constructor
	public Rectangle(int length, int breadth) {
		this.length = length;
		this.breadth = breadth;
	}
	
	public double calculateArea() {
		return (length*breadth);
	}
	
	public int getBreadth() {
		return breadth;
	}
	
	public int getLength() {
		return length;
	}
	
}
