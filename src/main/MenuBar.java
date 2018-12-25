package main;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import javax.swing.event.ChangeListener;

public class MenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 0L;
	
	private static JMenu settingsMenu;
	
	private static JCheckBoxMenuItem displayRandomNoiseCheckBoxMenuItem,
									 displayScrollingPerlinNoiseCheckBoxMenuItem,
									 displayTemporalPerlinNoiseCheckBoxMenuItem,
									 displayFlowFieldCheckBoxMenuItem,
									 displayParticlesCheckBoxMenuItem,
									 displayBlendCheckBoxMenuItem;
	
	private static JSlider scrollSpeedSlider,
						   timeSpeedSlider,
						   angleMultiplierSlider,
						   maxSpeedSlider,
						   accScalarSlider,
						   velScalarSlider;
	
	private static JSpinner particleCountSpinner;
	
	private static JButton initializeParticlesButton;
	
	public MenuBar() {
		
		super();
		
		settingsMenu = new JMenu("Settings");
		this.add(settingsMenu);
		
		displayRandomNoiseCheckBoxMenuItem = new JCheckBoxMenuItem("Display Random Noise");
		displayRandomNoiseCheckBoxMenuItem.addActionListener(new MenuActionListener());
		settingsMenu.add(displayRandomNoiseCheckBoxMenuItem);
		
		settingsMenu.addSeparator();
		
		displayScrollingPerlinNoiseCheckBoxMenuItem = new JCheckBoxMenuItem("Display Scrolling Perlin Noise");
		displayScrollingPerlinNoiseCheckBoxMenuItem.addActionListener(new MenuActionListener());
		settingsMenu.add(displayScrollingPerlinNoiseCheckBoxMenuItem);
		
		Hashtable<Integer, JLabel> speedLabelTable = new Hashtable<Integer, JLabel>();
		speedLabelTable.put(new Integer(0), new JLabel("Stop"));
		speedLabelTable.put(new Integer(100), new JLabel("Fast"));
		
		settingsMenu.add(new JLabel("Scroll Speed"));
		
		scrollSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		scrollSpeedSlider.setMajorTickSpacing(25);
		scrollSpeedSlider.setMinorTickSpacing(5);
		scrollSpeedSlider.setLabelTable(speedLabelTable);
		scrollSpeedSlider.setPaintTicks(true);
		scrollSpeedSlider.setPaintLabels(true);
		
		ChangeListener changeListener1 = e -> {
			
			JSlider slider = (JSlider) e.getSource();
			Frame.setScrollSpeed(slider.getValue());
			
		};
		
		scrollSpeedSlider.addChangeListener(changeListener1);
		scrollSpeedSlider.setEnabled(false);
		settingsMenu.add(scrollSpeedSlider);
		
		settingsMenu.addSeparator();
		
		displayTemporalPerlinNoiseCheckBoxMenuItem = new JCheckBoxMenuItem("Display Temporal Perlin Noise");
		displayTemporalPerlinNoiseCheckBoxMenuItem.addActionListener(new MenuActionListener());
		settingsMenu.add(displayTemporalPerlinNoiseCheckBoxMenuItem);
		
		displayFlowFieldCheckBoxMenuItem = new JCheckBoxMenuItem("Display Flow Field");
		displayFlowFieldCheckBoxMenuItem.addActionListener(new MenuActionListener());
		settingsMenu.add(displayFlowFieldCheckBoxMenuItem);
		
		displayParticlesCheckBoxMenuItem = new JCheckBoxMenuItem("Display Particles");
		displayParticlesCheckBoxMenuItem.addActionListener(new MenuActionListener());
		settingsMenu.add(displayParticlesCheckBoxMenuItem);
		
		displayBlendCheckBoxMenuItem = new JCheckBoxMenuItem("Display Blend");
		displayBlendCheckBoxMenuItem.addActionListener(new MenuActionListener());
		settingsMenu.add(displayBlendCheckBoxMenuItem);
		
		settingsMenu.add(new JLabel("Time Lapse Speed"));
		
		timeSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		timeSpeedSlider.setMajorTickSpacing(25);
		timeSpeedSlider.setMinorTickSpacing(5);
		timeSpeedSlider.setLabelTable(speedLabelTable);
		timeSpeedSlider.setPaintTicks(true);
		timeSpeedSlider.setPaintLabels(true);
		
		ChangeListener changeListener2 = e -> {
			
			JSlider slider = (JSlider) e.getSource();
			Frame.setTimeSpeed(slider.getValue());
			
		};
		
		timeSpeedSlider.addChangeListener(changeListener2);
		timeSpeedSlider.setEnabled(false);
		settingsMenu.add(timeSpeedSlider);
		
		settingsMenu.add(new JLabel("Angle Multiplier"));
		
		angleMultiplierSlider = new JSlider(JSlider.HORIZONTAL, 0, 16, 4);
		angleMultiplierSlider.setMajorTickSpacing(4);
		angleMultiplierSlider.setMinorTickSpacing(1);
		angleMultiplierSlider.setPaintTicks(true);
		angleMultiplierSlider.setPaintLabels(true);
		
		ChangeListener changeListener3 = e -> {
			
			JSlider slider = (JSlider) e.getSource();
			Frame.setAngleMultiplier(slider.getValue());
			
		};
		
		angleMultiplierSlider.addChangeListener(changeListener3);
		angleMultiplierSlider.setEnabled(false);
		settingsMenu.add(angleMultiplierSlider);
		
		settingsMenu.add(new JLabel("Max Speed"));
		
		maxSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 8, 2);
		maxSpeedSlider.setMajorTickSpacing(2);
		maxSpeedSlider.setMinorTickSpacing(1);
		maxSpeedSlider.setPaintTicks(true);
		maxSpeedSlider.setPaintLabels(true);
		
		ChangeListener changeListener4 = e -> {
			
			JSlider slider = (JSlider) e.getSource();
			Frame.setMaxSpeed(slider.getValue());
			
		};
		
		maxSpeedSlider.addChangeListener(changeListener4);
		maxSpeedSlider.setEnabled(false);
		settingsMenu.add(maxSpeedSlider);
		
		Hashtable<Integer, JLabel> scalarLabelTable = new Hashtable<Integer, JLabel>();
		scalarLabelTable.put(new Integer(0), new JLabel("0"));
		scalarLabelTable.put(new Integer(100), new JLabel("High"));
		
		settingsMenu.add(new JLabel("Acceleration Scalar"));
		
		accScalarSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		accScalarSlider.setMajorTickSpacing(25);
		accScalarSlider.setMinorTickSpacing(5);
		accScalarSlider.setLabelTable(scalarLabelTable);
		accScalarSlider.setPaintTicks(true);
		accScalarSlider.setPaintLabels(true);
		
		ChangeListener changeListener5 = e -> {
			
			JSlider slider = (JSlider) e.getSource();
			Frame.setAccScalar(slider.getValue());
			
		};
		
		accScalarSlider.addChangeListener(changeListener5);
		accScalarSlider.setEnabled(false);
		settingsMenu.add(accScalarSlider);
		
		settingsMenu.add(new JLabel("Velocity Scalar"));
		
		velScalarSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 75);
		velScalarSlider.setMajorTickSpacing(25);
		velScalarSlider.setMinorTickSpacing(5);
		velScalarSlider.setLabelTable(scalarLabelTable);
		velScalarSlider.setPaintTicks(true);
		velScalarSlider.setPaintLabels(true);
		
		ChangeListener changeListener6 = e -> {
			
			JSlider slider = (JSlider) e.getSource();
			Frame.setVelScalar(slider.getValue());
			
		};
		
		velScalarSlider.addChangeListener(changeListener6);
		velScalarSlider.setEnabled(false);
		settingsMenu.add(velScalarSlider);
		
		settingsMenu.add(new JLabel("Particle Count"));
		
		particleCountSpinner = new JSpinner();
		particleCountSpinner.setModel(new SpinnerNumberModel(1000, 100, 5000, 1));
		
		ChangeListener changeListener7 = e -> {
			
			JSpinner spinner = (JSpinner) e.getSource();
			Frame.setParticleCount((int)spinner.getValue());
			
		};
		
		particleCountSpinner.addChangeListener(changeListener7);
		particleCountSpinner.setEnabled(false);
		settingsMenu.add(particleCountSpinner);
		
		initializeParticlesButton = new JButton("Initialize Particles");
		initializeParticlesButton.addActionListener(new MenuActionListener());
		initializeParticlesButton.setEnabled(false);
		settingsMenu.add(initializeParticlesButton);
		
	}
	
	private class MenuActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			switch(e.getActionCommand().toString()) {
			
			case "Display Random Noise":			Frame.switchDisplayRandomNoise();
													switchEnabled(displayScrollingPerlinNoiseCheckBoxMenuItem);
													switchEnabled(displayTemporalPerlinNoiseCheckBoxMenuItem);
													switchEnabled(displayFlowFieldCheckBoxMenuItem);
													switchEnabled(displayParticlesCheckBoxMenuItem);
													switchEnabled(displayBlendCheckBoxMenuItem);
													break;
			
			case "Display Scrolling Perlin Noise":	Frame.switchDisplayScrollingPerlinNoise();
													switchEnabled(displayRandomNoiseCheckBoxMenuItem);
													switchEnabled(scrollSpeedSlider);
													switchEnabled(displayTemporalPerlinNoiseCheckBoxMenuItem);
													switchEnabled(displayFlowFieldCheckBoxMenuItem);
													switchEnabled(displayParticlesCheckBoxMenuItem);
													break;
			
			case "Display Temporal Perlin Noise":	Frame.switchDisplayTemporalPerlinNoise();
													switchEnabled(displayRandomNoiseCheckBoxMenuItem);
													switchEnabled(displayScrollingPerlinNoiseCheckBoxMenuItem);
													switchEnabled(displayFlowFieldCheckBoxMenuItem);
													switchEnabled(displayParticlesCheckBoxMenuItem);
													switchEnabled(displayBlendCheckBoxMenuItem);
													switchEnabled(timeSpeedSlider);
													break;
			
			case "Display Flow Field":				Frame.switchDisplayFlowField();
													
													if(!displayParticlesCheckBoxMenuItem.isSelected()) {
														
														switchEnabled(displayRandomNoiseCheckBoxMenuItem);
														switchEnabled(displayScrollingPerlinNoiseCheckBoxMenuItem);
														switchEnabled(displayTemporalPerlinNoiseCheckBoxMenuItem);
														switchEnabled(displayBlendCheckBoxMenuItem);
														switchEnabled(timeSpeedSlider);
														switchEnabled(angleMultiplierSlider);
														
													}
													
													break;
			
			case "Display Particles":				Frame.switchDisplayParticles();
													
													if(!displayFlowFieldCheckBoxMenuItem.isSelected()) {
														
														switchEnabled(displayRandomNoiseCheckBoxMenuItem);
														switchEnabled(displayScrollingPerlinNoiseCheckBoxMenuItem);
														switchEnabled(displayTemporalPerlinNoiseCheckBoxMenuItem);
														switchEnabled(displayBlendCheckBoxMenuItem);
														switchEnabled(timeSpeedSlider);
														switchEnabled(angleMultiplierSlider);
														
													}
													
													switchEnabled(maxSpeedSlider);
													switchEnabled(accScalarSlider);
													switchEnabled(velScalarSlider);
													switchEnabled(particleCountSpinner);
													switchEnabled(initializeParticlesButton);
													break;
			
			case "Display Blend":					Frame.switchDisplayBlend();
													switchEnabled(displayRandomNoiseCheckBoxMenuItem);
													switchEnabled(displayScrollingPerlinNoiseCheckBoxMenuItem);
													switchEnabled(displayTemporalPerlinNoiseCheckBoxMenuItem);
													switchEnabled(displayFlowFieldCheckBoxMenuItem);
													switchEnabled(displayParticlesCheckBoxMenuItem);
													switchEnabled(timeSpeedSlider);
													switchEnabled(angleMultiplierSlider);
													switchEnabled(maxSpeedSlider);
													switchEnabled(accScalarSlider);
													switchEnabled(velScalarSlider);
													switchEnabled(particleCountSpinner);
													switchEnabled(initializeParticlesButton);
													break;
			
			case "Initialize Particles":			Frame.initializeParticles();
													break;
			
			}
			
		}
		
	}
	
	private void switchEnabled(Container container) {
		
		container.setEnabled(container.isEnabled() ^ true);
		
	}
	
}
