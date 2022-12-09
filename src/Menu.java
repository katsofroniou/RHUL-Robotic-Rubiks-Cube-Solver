import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Menu implements Behavior {
	private boolean runMenu = true;
	
	private SampleProvider sp;
	private float[] samples = new float[1];
	
	public Menu(SampleProvider sp) {
		this.sp = sp;
	}

	@Override
	public boolean takeControl() {
		// touch sensor
		sp.fetchSample(samples, 0);
		
		return (samples[0] == 1.0);
	}

	@Override
	public void action() {
		while (runMenu) {
			LCD.clear();
			LCD.drawString("Benjamin Shearlock", 0, -50);
			LCD.drawString("Charlie Kerr", 0, -100);
			LCD.drawString("Harry O'Keefe", 0, -150);
			LCD.drawString("Katerina Sofroniou", 0, -150);
			
			Delay.msDelay(1000);
			LCD.clear();
			LCD.drawString("Version 1.5.2", 0, -150);
			Delay.msDelay(1000);
			LCD.clear();
	
			LCD.drawString("Press enter to run", 0, 0);
			Button.ENTER.waitForPressAndRelease();
			Main.runRun = true;
			LCD.clear();
			runMenu = false;
		}
	}

	@Override
	public void suppress() {
		runMenu = false;
	}

}
