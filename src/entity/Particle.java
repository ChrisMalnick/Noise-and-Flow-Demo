package entity;

import java.util.Random;

import main.Panel;

public class Particle {
	
	private Vector2D currPos, prevPos, vel, acc;
	
	public Particle() {
		
		Random RNG = new Random();
		
		this.currPos = new Vector2D(RNG.nextInt(Panel.WIDTH), RNG.nextInt(Panel.HEIGHT));
		this.prevPos = new Vector2D(currPos);
		this.vel = new Vector2D(0, 0);
		this.acc = new Vector2D(0, 0);
		
	}
	
	public Particle(Particle[] particles) {
		
		Random RNG = new Random();
		
		this.currPos = new Vector2D(RNG.nextInt(Panel.WIDTH), RNG.nextInt(Panel.HEIGHT));
		/*
		while(this.intersects(particles))
			this.currPos.setCoords(RNG.nextInt(Panel.WIDTH), RNG.nextInt(Panel.HEIGHT));
		*/
		this.prevPos = new Vector2D(currPos);
		this.vel = new Vector2D(0, 0);
		this.acc = new Vector2D(0, 0);
		
	}
	
	public int getCurrX() {
		
		return (int)Math.rint(this.currPos.getX());
		
	}
	
	public int getCurrY() {
		
		return (int)Math.rint(this.currPos.getY());
		
	}
	
	public int getPrevX() {
		
		return (int)Math.rint(this.prevPos.getX());
		
	}
	
	public int getPrevY() {
		
		return (int)Math.rint(this.prevPos.getY());
		
	}
	
	public void update(double angle) {
		
		angle = angle - 2 * angle;
		
		this.acc.add(new Vector2D(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle))));
		this.acc.mult(Panel.accScalar);
		
		this.vel.add(this.acc);
		this.vel.mult(Panel.velScalar);
		this.vel.limit(Panel.maxSpeed);
		
		this.acc.mult(0);
		
		this.prevPos.setCoords(this.currPos);
		
		this.currPos.add(this.vel);
		
		this.handleOutOfBounds();
		
	}
	
	public void update(Particle[] particles, double angle) {
		
		angle = angle - 2 * angle;
		
		this.acc.add(new Vector2D(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle))));
		this.acc.mult(Panel.accScalar);
		
		this.vel.add(this.acc);
		this.vel.mult(Panel.velScalar);
		this.vel.limit(Panel.maxSpeed);
		
		this.acc.mult(0);
		
		this.prevPos.setCoords(this.currPos);
		
		this.currPos.add(this.vel);
		/*
		if(!this.atEdge())
			if(this.intersects(particles))
				this.currPos.setCoords(this.prevPos);
		*/
		this.handleOutOfBounds();
		
	}
	
	private void handleOutOfBounds() {
		
		if(this.currPos.getX() > Panel.WIDTH - 1) {
			
			this.currPos.setX(0);
			this.prevPos.setX(0);
			
		}
		
		if(this.currPos.getX() < 0) {
			
			this.currPos.setX(Panel.WIDTH - 1);
			this.prevPos.setX(Panel.WIDTH - 1);
			
		}
		
		if(this.currPos.getY() > Panel.HEIGHT - 1) {
			
			this.currPos.setY(0);
			this.prevPos.setY(0);
			
		}
		
		if(this.currPos.getY() < 0) {
			
			this.currPos.setY(Panel.HEIGHT - 1);
			this.prevPos.setY(Panel.HEIGHT - 1);
			
		}
		
	}
	/*
	private boolean intersects(Particle[] particles) {
		
		for(Particle particle : particles)
			if(particle != null)
				if(particle != this)
					if(this.currPos.dist(particle.currPos) < 8)
						return true;
		
		return false;
		
	}
	
	private boolean atEdge() {
		
		return (this.currPos.getX() == Panel.WIDTH - 1 ||
				this.currPos.getX() == 0 ||
				this.currPos.getY() == Panel.HEIGHT - 1 ||
				this.currPos.getY() == 0);
		
	}
	*/
}
