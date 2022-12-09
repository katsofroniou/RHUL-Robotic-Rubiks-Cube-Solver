import lejos.hardware.Button;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior  {
	private BaseRegulatedMotor mTwist, armMove, mFlip;
	
	public EmergencyStop(BaseRegulatedMotor mTwist, BaseRegulatedMotor armMove, BaseRegulatedMotor mFlip) {
		this.mTwist = mTwist;
		this.armMove = armMove;
		this.mFlip = mFlip;
		
	}

	@Override
	public boolean takeControl() {
		return Button.LEFT.isDown();
	}

	@Override
	public void action() {
		mTwist.stop();
		armMove.stop();
		mFlip.stop();
		System.exit(0);
	}

	@Override
	public void suppress() {
		
	}
}
