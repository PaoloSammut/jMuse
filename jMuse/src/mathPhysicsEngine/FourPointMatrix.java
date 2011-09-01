package mathPhysicsEngine;

public class FourPointMatrix {
	//	a1  a2  a3	a4 
	//	b1  b2  b3	b4
	//	c1  c2  c3	c4
	//  d1  d2  d3  d4
	
	private double a1;
	private double a2;
	private double a3;
	private double a4;
	private double b1;
	private double b2;
	private double b3;
	private double b4;
	private double c1;
	private double c2;
	private double c3;
	private double c4;
	private double d1;
	private double d2;
	private double d3;
	private double d4;
	
	public FourPointMatrix(double a1, double a2, double a3, double a4,
			double b1, double b2, double b3, double b4, double c1, double c2,
			double c3, double c4, double d1, double d2, double d3, double d4) {
		super();
		this.a1 = a1;		this.a2 = a2;		this.a3 = a3;		this.a4 = a4;
		this.b1 = b1;		this.b2 = b2;		this.b3 = b3;		this.b4 = b4;
		this.c1 = c1;		this.c2 = c2;		this.c3 = c3;		this.c4 = c4;
		this.d1 = d1;		this.d2 = d2;		this.d3 = d3;		this.d4 = d4;
	}

	public FourPointMatrix() {
		super();
		this.a1 = 0;		this.a2 = 0;		this.a3 = 0;		this.a4 = 0;
		this.b1 = 0;		this.b2 = 0;		this.b3 = 0;		this.b4 = 0;
		this.c1 = 0;		this.c2 = 0;		this.c3 = 0;		this.c4 = 0;
		this.d1 = 0;		this.d2 = 0;		this.d3 = 0;		this.d4 = 0;
	}
	
	
	public void setFourPointMatrix(double a1, double a2, double a3, double a4, double b1, double b2, double b3, double b4, double c1, double c2, double c3, double c4, double d1, double d2, double d3, double d4) {
		this.a1 = a1;		this.a2 = a2;		this.a3 = a3;		this.a4 = a4;
		this.b1 = b1;		this.b2 = b2;		this.b3 = b3;		this.b4 = b4;
		this.c1 = c1;		this.c2 = c2;		this.c3 = c3;		this.c4 = c4;
		this.d1 = d1;		this.d2 = d2;		this.d3 = d3;		this.d4 = d4;
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

	public double getA4() {
		return a4;
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

	public double getB4() {
		return b4;
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

	public double getC4() {
		return c4;
	}

	public double getD1() {
		return d1;
	}

	public double getD2() {
		return d2;
	}

	public double getD3() {
		return d3;
	}

	public double getD4() {
		return d4;
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

	public void setA4(double a4) {
		this.a4 = a4;
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

	public void setB4(double b4) {
		this.b4 = b4;
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

	public void setC4(double c4) {
		this.c4 = c4;
	}

	public void setD1(double d1) {
		this.d1 = d1;
	}

	public void setD2(double d2) {
		this.d2 = d2;
	}

	public void setD3(double d3) {
		this.d3 = d3;
	}

	public void setD4(double d4) {
		this.d4 = d4;
	}
	
	public String getStringMatrix() {
		
		return ("a1:" + Double.toString(a1) + " a2:" + Double.toString(a2) + " a3:" + Double.toString(a3)+ " a4:" + Double.toString(a4))
			  + "b1:" + Double.toString(b1) + " b2:" + Double.toString(b2) + " b3:" + Double.toString(b3)+ " b4:" + Double.toString(b4)
			  + "c1:" + Double.toString(c1) + " c2:" + Double.toString(c2) + " c3:" + Double.toString(c3)+ " c4:" + Double.toString(c4)
			  + "d1:" + Double.toString(d1) + " d2:" + Double.toString(d2) + " d3:" + Double.toString(d3)+ " d4:" + Double.toString(d4);

	
	
	}
	
	

}
