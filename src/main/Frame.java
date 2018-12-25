package main;

import javax.swing.JFrame;

public class Frame {
	
	private static JFrame frame;
	private static MenuBar menuBar;
	private static Panel panel;
	
	public static void main(String[] args) {
		
		frame = new JFrame("Noise and Flow Demo");
		menuBar = new MenuBar();
		panel = new Panel();
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.setContentPane(panel);
		frame.pack();
		frame.getContentPane().setSize(Panel.WIDTH, Panel.HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public static void initializeParticles() {
		
		panel.initializeParticles();
		
	}
	
	public static void switchDisplayRandomNoise() {
		
		panel.switchDisplayRandomNoise();
		
	}
	
	public static void switchDisplayScrollingPerlinNoise() {
		
		panel.switchDisplayScrollingPerlinNoise();
		
	}
	
	public static void switchDisplayTemporalPerlinNoise() {
		
		panel.switchDisplayTemporalPerlinNoise();
		
	}
	
	public static void switchDisplayFlowField() {
		
		panel.switchDisplayFlowField();
		
	}
	
	public static void switchDisplayParticles() {
		
		panel.switchDisplayParticles();
		
	}
	
	public static void switchDisplayBlend() {
		
		panel.switchDisplayBlend();
		
	}
	
	public static void setScrollSpeed(int speed) {
		
		panel.setScrollSpeed(speed);
		
	}
	
	public static void setTimeSpeed(int speed) {
		
		panel.setTimeSpeed(speed);
		
	}
	
	public static void setParticleCount(int i) {
		
		panel.setParticleCount(i);
		
	}
	
	public static void setAngleMultiplier(int i) {
		
		panel.setAngleMultiplier(i);
		
	}
	
	public static void setMaxSpeed(int i) {
		
		panel.setMaxSpeed(i);
		
	}
	
	public static void setAccScalar(int i) {
		
		panel.setAccScalar(i);
		
	}
	
	public static void setVelScalar(int i) {
		
		panel.setVelScalar(i);
		
	}
	
}
