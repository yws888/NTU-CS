package lab4_q2;

public class Cuboid extends Rectangle implements Shape3D {

	private int height;

	public Cuboid(int length, int breadth, int height) {
		super(length, breadth);
		this.height = height;
	}
	
	@Override
	public double calculateArea() {
		return (super.calculateArea()*2) + (super.getBreadth()*height*2) + (super.getLength()*height*2);
	}
	
	public double calculateVolume() {
		return (super.calculateArea()*height);
	}

	

}
