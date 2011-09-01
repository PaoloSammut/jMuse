package mathPhysicsEngine;

import com.jme3.math.Vector3f;

public class Camera {
	private Vector3f cameraPosition;
	private Vector3f cameraLookAt;
	private float cameraVelocity = 1;

	public Camera() {
		super();
		this.cameraPosition = new Vector3f(0f, 0f, 10f);
		this.cameraLookAt = new Vector3f(0f, 0f, -1f);
	}
	
	public Camera(Vector3f cameraPosition, Vector3f cameraLookAt) {
		super();
		this.cameraPosition = cameraPosition;
		this.cameraLookAt = cameraLookAt;
	}

	public Vector3f getCameraPosition() {
		return cameraPosition;
	}

	public void setCameraPosition(Vector3f cameraPosition) {
		this.cameraPosition = cameraPosition;
	}

	public Vector3f getCameraLookAt() {
		return cameraLookAt;
	}

	public void setCameraLookAt(Vector3f cameraLookAt) {
		this.cameraLookAt = cameraLookAt;
	}
	
	
}
