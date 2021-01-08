package lab4_q2;

public class Sphere extends Circle implements Shape3D {
	
	public Sphere(int radius) {
		super(radius);
	}
	
	@Override
	public double calculateArea() {
		return (4*(Math.PI)*super.getRadius()*super.getRadius());
	}
	
	@Override
	public double calculateVolume() {
		return ((double)4/3)*super.calculateArea()*super.getRadius();
	}

}
