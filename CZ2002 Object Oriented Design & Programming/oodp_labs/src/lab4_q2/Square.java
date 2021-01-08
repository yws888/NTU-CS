package lab4_q2;

public class Square implements Shape {

	private int length;

	public Square(int nextInt) {
		length = nextInt;
	}

	@Override
	public double calculateArea() {
		return (length*length);
	}

}
