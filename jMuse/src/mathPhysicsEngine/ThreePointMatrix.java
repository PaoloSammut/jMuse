package mathPhysicsEngine;

public class ThreePointMatrix {
	//	a1  a2  a3 
	//	b1  b2  b3
	//	c1  c2  c3
	
	private double a1;
	private double a2;
	private double a3;
	private double b1;
	private double b2;
	private double b3;
	private double c1;
	private double c2;
	private double c3;
	
	public ThreePointMatrix(double a1, double a2, double a3, double b1,
			double b2, double b3, double c1, double c2, double c3) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
	}

	public void setThreePointMatrix(double a1, double a2, double a3, double b1,double b2, double b3, double c1, double c2, double c3) {
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
	}
	
	public double getA1() {
		return a1;
	}

	public double getA2() {
		return a2;
	}

	public double getA3() {
		return a3;
	}

	public double getB1() {
		return b1;
	}

	public double getB2() {
		return b2;
	}

	public double getB3() {
		return b3;
	}

	public double getC1() {
		return c1;
	}

	public double getC2() {
		return c2;
	}

	public double getC3() {
		return c3;
	}

	public void setA1(double a1) {
		this.a1 = a1;
	}

	public void setA2(double a2) {
		this.a2 = a2;
	}

	public void setA3(double a3) {
		this.a3 = a3;
	}

	public void setB1(double b1) {
		this.b1 = b1;
	}

	public void setB2(double b2) {
		this.b2 = b2;
	}

	public void setB3(double b3) {
		this.b3 = b3;
	}

	public void setC1(double c1) {
		this.c1 = c1;
	}

	public void setC2(double c2) {
		this.c2 = c2;
	}

	public void setC3(double c3) {
		this.c3 = c3;
	}

	
	
	
}
