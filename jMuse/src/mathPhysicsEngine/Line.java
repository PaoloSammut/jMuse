package mathPhysicsEngine;

public class Line {

	private WorldPoint startVector;
	private WorldPoint endVector;
	private ScreenPoint startScreenCoordinates;
	private ScreenPoint endScreenCoordinates;

	public Line(WorldPoint startVec, WorldPoint endVec) {
		this.startVector = new WorldPoint(startVec);
		this.endVector = new WorldPoint(endVec);
	}

	public Line (ScreenPoint startSC, ScreenPoint endSC) {
		this.startScreenCoordinates = new ScreenPoint(startSC);
		this.endScreenCoordinates = new ScreenPoint(endSC);	
	}
	
	public WorldPoint getStartVector() {
		return startVector;
	}

	public WorldPoint getEndVector() {
		return endVector;
	}

	public void setStartVector(WorldPoint startVector) {
		this.startVector = startVector;
	}

	public void setEndVector(WorldPoint endVector) {
		this.endVector = endVector;
	}

	public double getEndVectorX() {
		return this.endVector.getX();
	}

	public double getStartVectorX() {
		return this.startVector.getX();
	}

	public double getEndVectorY() {
		return this.endVector.getY();
	}

	public double getStartVectorY() {
		return this.startVector.getY();
	}

	public ScreenPoint getStartScreenCoordinates() {
		return startScreenCoordinates;
	}

	public ScreenPoint getEndScreenCoordinates() {
		return endScreenCoordinates;
	}

	public void setStartScreenCoordinates(ScreenPoint startScreenCoordinates) {
		this.startScreenCoordinates = startScreenCoordinates;
	}

	public void setEndScreenCoordinates(ScreenPoint endScreenCoordinates) {
		this.endScreenCoordinates = endScreenCoordinates;
	}

}
