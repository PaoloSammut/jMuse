package mathPhysicsEngine;

public class WorldPoint {
	private double x;
	private double y;
	private double z;
	private double t;

	public WorldPoint(double x, double y, double z, double t) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = t;
	}

	public WorldPoint(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = 1D;
	}

	public WorldPoint() {
		super();
		this.x = 0D;
		this.y = 0D;
		this.z = 0D;
		this.t = 1D;
	}

	public WorldPoint(WorldPoint startVec) {
		this.x = startVec.getX();
		this.y = startVec.getY();
		this.z = startVec.getZ();
		this.t = startVec.getT();
	}

	public void scaleVector(double scaleFactor) {
		this.x = this.x * scaleFactor;
		this.y = this.y * scaleFactor;
		this.z = this.z * scaleFactor;
		this.t = this.t * scaleFactor;
	}

	public void translateVector(WorldPoint translation) {
		this.x = this.x + translation.getX();
		this.y = this.y + translation.getY();
		this.z = this.z + translation.getZ();
		this.t = this.t + translation.getT();
	}

	@SuppressWarnings("static-access")
	public ScreenPoint perspective(WorldPoint vector) {
		Camera camera = new Camera();
		WorldPoint wp = new WorldPoint();

		//wp = multiplyWorldPointwithFourPointMatrix(camera.getPerspectiveMatrix());
		double screenY = 360 * (wp.getY() / wp.getZ());
		double screenX = 360 * (wp.getX() / wp.getZ());

		ScreenPoint screenCoordinate = new ScreenPoint(screenX, screenY);
		screenCoordinate.setX(screenX);
		screenCoordinate.setY(screenY);
		return screenCoordinate;

	}

	public WorldPoint multiplyWorldPointwithFourPointMatrix(FourPointMatrix fpm) {
		WorldPoint result = new WorldPoint();

		result.setX((this.x * fpm.getA1()) + (this.y * fpm.getB1())	+ (this.z * fpm.getC1()) + (this.t * fpm.getD1()));
		result.setY((this.x * fpm.getA2()) + (this.y * fpm.getB2())	+ (this.z * fpm.getC2()) + (this.t * fpm.getD2()));
		result.setZ((this.x * fpm.getA3()) + (this.y * fpm.getB3())	+ (this.z * fpm.getC3()) + (this.t * fpm.getD3()));
		result.setT((this.x * fpm.getA4()) + (this.y * fpm.getB4())	+ (this.z * fpm.getC4()) + (this.t * fpm.getD4()));

		return result;
	}

	public void transform(FourPointMatrix transformationMatrix) {
		WorldPoint result = new WorldPoint();

		result = multiplyWorldPointwithFourPointMatrix(transformationMatrix);

		this.x = result.getX();
		this.y = result.getY();
		this.z = result.getZ();
		this.t = result.getT();

	}

	public String getStringVector() {
		return ("x:" + Double.toString(x) + " y:" + Double.toString(y) + " z:"
				+ Double.toString(z) + " t:" + Double.toString(t));
	}

	public WorldPoint getWorldPoint() {
		return new WorldPoint(x, y, z, t);
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
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

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
