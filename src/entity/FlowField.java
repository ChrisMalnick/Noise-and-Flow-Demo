package entity;

import main.Panel;

public class FlowField {
	
	public static final int SCALE = 10;
	
	public static final int COLS = Panel.WIDTH / SCALE;
	public static final int ROWS = Panel.HEIGHT / SCALE;
	
	private static final double MAX_ANGLE = 360.0d;
	
	private double[][] angleGrid;
	
	public FlowField() {
		
		this.angleGrid = new double[COLS][ROWS];
		
	}
	
	public void initialize() {
		
		
		
	}
	
	public double getAngle(int x, int y) {
		
		return this.angleGrid[x][y];
		
	}
	
	public void update(double seed, double zOff) {
		
		double /*avg, */xOff, yOff = seed;
		
		for(int i = 0; i < ROWS; i ++) {
			
			xOff = seed;
			
			for(int j = 0; j < COLS; j ++) {
				
				this.angleGrid[j][i] = ((PerlinNoise.noise(xOff, yOff, zOff) + 1.0d) / 2.0d) * MAX_ANGLE * (double)Panel.angleMultiplier;
				/*
				while(this.angleGrid[j][i] > 360.0d)
					this.angleGrid[j][i] -= 360.0d;
				
				if(j == COLS - 1) {
					
					avg = average(angleGrid[0][i], angleGrid[COLS - 1][i]);
					
					angleGrid[0][i] = average(angleGrid[0][i], avg);
					angleGrid[COLS - 1][i] = average(angleGrid[COLS - 1][i], avg);
					
				}
				
				if(i == ROWS - 1) {
					
					avg = average(angleGrid[j][0], angleGrid[j][ROWS - 1]);
					
					angleGrid[j][0] = average(angleGrid[j][0], avg);
					angleGrid[j][ROWS - 1] = average(angleGrid[j][ROWS - 1], avg);
					
				}
				*/
				xOff += 0.01d;
				
			}
			
			yOff += 0.01d;
			
		}
		
	}
	/*
	private double average(double d1, double d2) {
		
		while(d1 > MAX_ANGLE)
			d1 -= MAX_ANGLE;
		
		while(d2 > MAX_ANGLE)
			d2 -= MAX_ANGLE;
		
		double x = Math.abs(d1 - d2);
		
		if(x < 180)
			return ((d1 + d2) / 2);
		
		if(x > 180)
			return (((d1 + d2) / 2) + 180);
		
		return d1 + 90;
		
	}
	*/
}
