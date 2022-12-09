import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	static EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S4);
	static SampleProvider sample = ts.getTouchMode();

	static Behavior emergencyStop = new EmergencyStop(CubeSolver2.mTwist, CubeSolver2.armMove, CubeSolver2.mFlip);
	static Behavior menu = new Menu(sample);
	static Behavior lowBattery = new LowBattery();
	static Behavior run = new Run();
	
	public static boolean runRun = false;
	
	static Arbitrator arb = new Arbitrator(new Behavior[] {run, menu, lowBattery, emergencyStop}, true);
	
	public static Arbitrator getArb() {
		return arb;
	}
	
	public static void end() {
		CubeSolver2.mTwist.stop();
		CubeSolver2.armMove.stop();
		CubeSolver2.mFlip.stop();
		getArb().stop();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		
		getArb().go();
		getArb().stop();
	}


}
