package lab4_q2;

public class Circle implements Shape {
	private int radius;
	
	public Circle(int nextInt) {
		radius = nextInt;
	}

	public double calculateArea() {
		return ((Math.PI)*getRadius()*getRadius());
	}

	public int getRadius() {
		return radius;
	}
	
	

}
