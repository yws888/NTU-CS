package lab4_q2;

public class Cylinder extends Circle implements Shape3D {

	private int height;

	public Cylinder(int height, int radius) {
		super(radius);
		this.height=height;
	}
	
	@Override
	public double calculateArea() {
		return (super.calculateArea()*2 + (2*Math.PI*super.getRadius()*height));
	}
	
	public double calculateVolume() {
		return (super.calculateArea()*height);
	}

}
