package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;

import java.util.Random;

import javax.swing.JPanel;

import entity.FlowField;
import entity.Particle;
import entity.PerlinNoise;

public class Panel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 0L;
	
	/*Dimensions*/
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	/*Run Parameters*/
	private Thread thread;
	private boolean running;
	private static final int FPS = 60;
	private static final long targetTime = 1000 / FPS;
	
	/*Entities*/
	private Particle[] particles;
	private FlowField flowField;
	
	private BufferedImage canvas;
	
	/*Attributes*/
	private boolean displayRandomNoise,
					displayScrollingPerlinNoise,
					displayTemporalPerlinNoise,
					displayFlowField,
					displayParticles,
					displayBlend;
	
	private double seed, zOff, scroll, time;
	
	public static int particleCount, angleMultiplier, maxSpeed;
	public static double accScalar, velScalar;
	
	public Panel() {
		
		super();
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setOpaque(true);
		this.setFocusable(true);
		this.requestFocus();
		
	}
	
	public void addNotify() {
		
		super.addNotify();
		
		if(this.thread != null)
			return;
		
		this.thread = new Thread(this);
		this.thread.start();
		
	}
	
	public void run() {
		
		this.initialize();
		
		long start, elapsed, wait;
		
		/*Event Loop*/
		while(running) {
			
			start = System.nanoTime();
			
			this.update();
			this.repaint();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait < 0)
				wait = 5;
			
			try {
				
				Thread.sleep(wait);
				
			}
			
			catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	private void initialize() {
		
		Random RNG = new Random();
		this.zOff = this.seed = RNG.nextDouble();
		
		particleCount = 1000;
		angleMultiplier = 4;
		maxSpeed = 2;
		
		accScalar = 0.5d;
		velScalar = 0.75d;
		
		this.initializeParticles();
		this.flowField = new FlowField();
		
		this.running = true;
		
	}
	
	private void update() {
		
		
		
	}
	
	public synchronized void paintChildren(Graphics g) {
		
		Graphics2D G2D = (Graphics2D) g;
		
		G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(this.displayRandomNoise)
			this.drawRandomNoise(G2D);
		
		if(this.displayScrollingPerlinNoise)
			this.drawScrollingPerlinNoise(G2D);
		
		if(this.displayTemporalPerlinNoise)
			this.drawTemporalPerlinNoise(G2D);
		
		if(this.displayFlowField)
			this.drawFlowField(G2D);
		
		if(this.displayParticles)
			this.drawParticles(G2D);
		
		if(this.displayBlend)
			this.drawBlend(G2D);
		
	}
	
	private void drawRandomNoise(Graphics2D G2D) {
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Random RNG = new Random();
		
		Color color;
		
		int grayScaleValue;
		
		for(int i = 0; i < HEIGHT; i ++)
			for(int j = 0; j < WIDTH; j ++) {
				
				grayScaleValue = RNG.nextInt(256);
				color = new Color(grayScaleValue, grayScaleValue, grayScaleValue);
				image.setRGB(j, i, color.getRGB());
				
			}
		
		G2D.drawImage(image, 0, 0, this);
		
	}
	
	private void drawScrollingPerlinNoise(Graphics2D G2D) {
		
		this.seed += this.scroll;
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Color color;
		
		double xOff, yOff = seed;
		int grayScaleValue;
		
		for(int i = 0; i < HEIGHT; i ++) {
			
			xOff = seed;
			
			for(int j = 0; j < WIDTH; j ++) {
				
				grayScaleValue = (int)(((PerlinNoise.noise(xOff, yOff) + 1) / 2) * 255);
				color = new Color(grayScaleValue, grayScaleValue, grayScaleValue);
				image.setRGB(j, i, color.getRGB());
				
				xOff += 0.01d;
				
			}
			
			yOff += 0.01d;
			
		}
		
		G2D.drawImage(image, 0, 0, this);
		
	}
	
	private void drawTemporalPerlinNoise(Graphics2D G2D) {
		
		this.zOff += this.time;
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Color color;
		
		double xOff, yOff = seed;
		int grayScaleValue;
		
		for(int i = 0; i < HEIGHT; i ++) {
			
			xOff = seed;
			
			for(int j = 0; j < WIDTH; j ++) {
				
				grayScaleValue = (int)(((PerlinNoise.noise(xOff, yOff, zOff) + 1) / 2) * 255);
				color = new Color(grayScaleValue, grayScaleValue, grayScaleValue);
				image.setRGB(j, i, color.getRGB());
				
				xOff += 0.01d;
				
			}
			
			yOff += 0.01d;
			
		}
		
		G2D.drawImage(image, 0, 0, this);
		
	}
	
	private void drawFlowField(Graphics2D G2D) {
		
		this.zOff += this.time;
		this.flowField.update(this.seed, this.zOff);
		
		G2D.setStroke(new BasicStroke(2));
		G2D.setColor(Color.GRAY);
		
		double angle;
		int x1, y1, x2, y2, x3, y3;
		
		for(int i = 0; i < FlowField.ROWS; i ++)
			for(int j = 0; j < FlowField.COLS; j ++) {
				
				angle = this.flowField.getAngle(j, i);
				angle = angle - 2 * angle;
				
				x1 = j * FlowField.SCALE + FlowField.SCALE / 2 + (int)(Math.cos(Math.toRadians(angle + 180)) * FlowField.SCALE / 2);
				y1 = i * FlowField.SCALE + FlowField.SCALE / 2 + (int)(Math.sin(Math.toRadians(angle + 180)) * FlowField.SCALE / 2);
				
				x2 = j * FlowField.SCALE + FlowField.SCALE / 2 + (int)(Math.cos(Math.toRadians(angle)) * FlowField.SCALE / 2);
				y2 = i * FlowField.SCALE + FlowField.SCALE / 2 + (int)(Math.sin(Math.toRadians(angle)) * FlowField.SCALE / 2);
				
				G2D.drawLine(x1, y1, x2, y2);
				
				x3 = x2 + (int)(Math.cos(Math.toRadians(angle + 135)) * FlowField.SCALE / 2);
				y3 = y2 + (int)(Math.sin(Math.toRadians(angle + 135)) * FlowField.SCALE / 2);
				
				G2D.drawLine(x2, y2, x3, y3);
				
				x3 = x2 + (int)(Math.cos(Math.toRadians(angle + 225)) * FlowField.SCALE / 2);
				y3 = y2 + (int)(Math.sin(Math.toRadians(angle + 225)) * FlowField.SCALE / 2);
				
				G2D.drawLine(x2, y2, x3, y3);
				
			}
		
	}
	
	private void drawParticles(Graphics2D G2D) {
		
		if(!displayFlowField) {
			
			this.zOff += this.time;
			this.flowField.update(this.seed, this.zOff);
			
		}
		
		G2D.setColor(Color.BLACK);
		
		for(Particle particle : this.particles) {
			
			particle.update(this.flowField.getAngle(particle.getCurrX() / FlowField.SCALE, particle.getCurrY() / FlowField.SCALE));
			G2D.fillOval(particle.getCurrX(), particle.getCurrY(), 8, 8);
			
		}
		
	}
	
	private void drawBlend(Graphics2D G2D) {
		
		this.zOff += this.time;
		this.flowField.update(this.seed, this.zOff);
		
		Graphics2D canvasG2D = canvas.createGraphics();
		
		canvasG2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		canvasG2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.005f));
		canvasG2D.setStroke(new BasicStroke(2));
		canvasG2D.setColor(Color.BLACK);
		
		for(Particle particle : this.particles) {
			
			particle.update(this.flowField.getAngle(particle.getCurrX() / FlowField.SCALE, particle.getCurrY() / FlowField.SCALE));
			canvasG2D.drawLine(particle.getPrevX(), particle.getPrevY(), particle.getCurrX(), particle.getCurrY());
			
		}
		
		G2D.drawImage(canvas, 0, 0, this);
		
	}
	
	public void initializeParticles() {
		
		this.particles = new Particle[particleCount];
		
		for(int i = 0; i < particleCount; i ++)
			this.particles[i] = new Particle();
		
		this.canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
	}
	
	public void switchDisplayRandomNoise() {
		
		this.displayRandomNoise ^= true;
		
	}
	
	public void switchDisplayScrollingPerlinNoise() {
		
		this.displayScrollingPerlinNoise ^= true;
		
	}
	
	public void switchDisplayTemporalPerlinNoise() {
		
		this.displayTemporalPerlinNoise ^= true;
		
	}
	
	public void switchDisplayFlowField() {
		
		this.displayFlowField ^= true;
		
	}
	
	public void switchDisplayParticles() {
		
		this.displayParticles ^= true;
		
	}
	
	public void switchDisplayBlend() {
		
		this.displayBlend ^= true;
		
	}
	
	public void setScrollSpeed(int speed) {
		
		this.scroll = ((double)speed / 100.0d) * 0.1d;
		
	}
	
	public void setTimeSpeed(int speed) {
		
		this.time = ((double)speed / 100.0d) * 0.01d;
		
	}
	
	public void setParticleCount(int i) {
		
		particleCount = i;
		
	}
	
	public void setAngleMultiplier(int i) {
		
		angleMultiplier = i;
		
	}
	
	public void setMaxSpeed(int i) {
		
		maxSpeed = i;
		
	}
	
	public void setAccScalar(int i) {
		
		accScalar = ((double)i / 100.0d) * 5.0d;
		
	}
	
	public void setVelScalar(int i) {
		
		velScalar = ((double)i / 100.0d) * 1.0d;
		
	}
	
}
