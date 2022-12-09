import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class LowBattery implements Behavior {	
	
	@Override
	public boolean takeControl() {
		return (Battery.getVoltage() < 5.0);
	}

	@Override
	public void action() {
		LCD.drawString("Low Battery, shutting down", 0, 0);
		Delay.msDelay(1000);
		Main.end();
	}

	@Override
	public void suppress() {}

}
