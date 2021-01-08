package lab4_q2;

public class Triangle implements Shape {
	private int height;
	private int base;
	
	public Triangle(int nextInt, int nextInt2) {
		height = nextInt;
		base = nextInt2;
	}

	@Override
	public double calculateArea() {
		return (0.5*base*height);
	}
	
	public int getBase() {
		return base;
	}
	
	public int getHeight() {
		return height;
	}

}
