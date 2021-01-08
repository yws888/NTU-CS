package lab4_q2;

public class Cone extends Circle implements Shape3D {
	
	private int height;
	private double slantHeight;

	public Cone(int height, int radius) {
		super(radius);
		this.height = height;
		slantHeight = Math.sqrt((radius)*(radius)+height*height);
	}
	
	@Override
	public double calculateArea() {
		return (super.calculateArea() + (Math.PI*super.getRadius()*slantHeight));
	}

	public double calculateVolume() {
		return (super.calculateArea()*height/3);
	}

}
