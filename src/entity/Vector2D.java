package entity;

public class Vector2D {
	
	private double x, y;
	
	public Vector2D(double x, double y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public Vector2D(double angle) {
		
		this.x = Math.cos(Math.toRadians(angle));
		this.y = Math.sin(Math.toRadians(angle));
		
	}
	
	public Vector2D(Vector2D v) {
		
		this.x = v.x;
		this.y = v.y;
		
	}
	
	public double getX() {
		
		return this.x;
		
	}
	
	public double getY() {
		
		return this.y;
		
	}
	
	public void setX(double x) {
		
		this.x = x;
		
	}
	
	public void setY(double y) {
		
		this.y = y;
		
	}
	
	public void setCoords(double x, double y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void setCoords(Vector2D v) {
		
		this.x = v.x;
		this.y = v.y;
		
	}
	
	public void add(Vector2D v) {
		
		this.x += v.x;
		this.y += v.y;
		
	}
	
	public void mult(double scalar) {
		
		this.x *= scalar;
		this.y *= scalar;
		
	}
	
	public double dist(Vector2D v) {
		
		return Math.sqrt(Math.pow((v.x - this.x), 2) + Math.pow((v.y - this.y), 2));
		
	}
	
	public void limit(double max) {
		
		if(Math.abs(this.x) > max)
			this.x = max;
		
		if(Math.abs(this.y) > max)
			this.y = max;
		
	}
	
}
