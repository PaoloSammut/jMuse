package mathPhysicsEngine;

public class ScreenPoint {
	private double x;
	private double y;

	public ScreenPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public ScreenPoint(ScreenPoint startSC) {
		this.x = startSC.getX();
		this.y = startSC.getY();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
