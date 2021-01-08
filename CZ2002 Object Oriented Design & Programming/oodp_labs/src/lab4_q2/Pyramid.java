package lab4_q2;

public class Pyramid extends Triangle implements Shape3D {
	
	private double slantHeight;

	
	public Pyramid(int nextInt, int nextInt2) {
		super(nextInt, nextInt2);
		slantHeight = Math.sqrt((0.5*nextInt2)*(0.5*nextInt2)+nextInt*nextInt);
	}
	
	
	
	public double calculateArea() {
		return super.getBase()*super.getBase() + (2*slantHeight*super.getBase());
	}
	
	@Override
	public double calculateVolume() {
		return (super.getBase()*super.getBase()*super.getHeight()/3);
	}

}
