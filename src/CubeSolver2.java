import java.util.Random;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class CubeSolver2 {
	// Variables\\
	public static String OverallString = "";
	private static String shuffle;
	private static int cubeSide = 0;

	// Arrays\\
	private static String[] sideTrans = new String[12];
	private static String[] orientTrans = new String[9];
	public static String[][] cube = { { "W", "W", "W", "W", "W", "W", "W", "W", "W" },
			{ "R", "R", "R", "R", "R", "R", "R", "R", "R" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "O", "O", "O", "O", "O", "O", "O", "O", "O" }, { "G", "G", "G", "G", "G", "G", "G", "G", "G" },
			{ "Y", "Y", "Y", "Y", "Y", "Y", "Y", "Y", "Y" } };

	// Movement Import\\
	public final static BaseRegulatedMotor mTwist = new EV3LargeRegulatedMotor(MotorPort.A);
	public final static BaseRegulatedMotor armMove = new EV3LargeRegulatedMotor(MotorPort.C);
	public final static BaseRegulatedMotor mFlip = new EV3LargeRegulatedMotor(MotorPort.D);
	private final static EV3ColorSensor sample = new EV3ColorSensor(SensorPort.S1);
	private final static int twistTurn = 270;
	private final static int armLock = 110;
	private final static int armFlip = 140;
	private final static int armFlipReset = -250;
	private final static int twistCorrect = 66;
	private final static int MOVE_OVER_EDGE = 240;
	private final static int MOVE_CUBE_ROUND = 135;

	public final static String colourSensor() {
		float[] min = { 1, 1, 1 };
		float[] max = { 0, 0, 0 };
		for (int i = 0; i < 5; i++) {
			float[] level = new float[3];
			SampleProvider colour = sample.getRGBMode();
			colour.fetchSample(level, 0);
			for (int x = 0; x < 3; x++) {
				if (level[x] > max[x]) {
					max[x] = level[x];
				}
				if (level[x] < min[x]) {
					min[x] = level[x];
				}
			}
		}
		float[] avg = new float[3];
		for (int i = 0; i < 3; i++) {
			avg[i] = Float.parseFloat(Float.toString(((min[i] + max[i]) / 2) * 100).substring(0, 4));
		}
		if (((avg[0] > 21) && (avg[0] < 26.5)) && ((avg[1] > 20.5) && (avg[1] < 27.5))
				&& ((avg[2] > 22.5) && (avg[2] < 28.5))) {
			return "W";
		} else if (((avg[0] > 13.5) && (avg[0] < 17)) && ((avg[1] > 6.9) && (avg[1] < 8.5))
				&& ((avg[2] > 5.5) && (avg[2] < 7))) {
			return "R";
		} else if (((avg[0] > 1.5) && (avg[0] < 2.5)) && ((avg[1] > 5.5) && (avg[1] < 7.5))
				&& ((avg[2] > 12.5) && (avg[2] < 14.5))) {
			return "B";
		} else if (((avg[0] > 2.5) && (avg[0] < 4.0)) && ((avg[1] > 10.5) && (avg[1] < 14))
				&& ((avg[2] > 17) && (avg[2] < 19))) {
			return "G";
		} else if (((avg[0] > 18.0) && (avg[0] < 19.5)) && ((avg[1] > 18.5) && (avg[1] < 21.0))
				&& ((avg[2] > 28.5) && (avg[2] < 30.5))) {
			return "Y";
		} else {
			return "O";
		}
	}

	public static void readFace(int[] adjustment) {
		armMove.rotate(-MOVE_OVER_EDGE);
		int[] arrayIndex = { 5, 2, 1, 0, 3, 6, 7, 8 };
		int count = 0;
		for (int index : arrayIndex) {
			cube[cubeSide][index] = colourSensor();
			LCD.drawString(cube[cubeSide][index], 0, 0);
			armMove.rotate(adjustment[count]);
			mTwist.rotate(MOVE_CUBE_ROUND);
			Delay.msDelay(200);
			count++;
		}
		cubeSide++;
		armMove.rotate(MOVE_OVER_EDGE);
	}

	public static void readCube() {
		armMove.setSpeed(300);
		mTwist.setSpeed(400);
		mFlip.setSpeed(250);
		int[] adjustment = { 25, -50, 25, -50, 50, 0, 25, -25 };
		readFace(adjustment);
		mTwist.rotate(-twistTurn);
		mFlip.rotate(-armFlipReset);
		mFlip.rotate(armFlipReset);
		mTwist.rotate(twistTurn);
		readFace(adjustment);
		mFlip.rotate(-armFlipReset);
		mFlip.rotate(armFlipReset);
		readFace(adjustment);
		mFlip.rotate(-armFlipReset);
		mFlip.rotate(armFlipReset);
		readFace(adjustment);
		mFlip.rotate(-armFlipReset);
		mFlip.rotate(armFlipReset);
		readFace(adjustment);
		mTwist.rotate(-twistTurn);
		mFlip.rotate(-armFlipReset);
		mFlip.rotate(armFlipReset);
		mTwist.rotate(twistTurn);
		readFace(adjustment);
		mTwist.rotate(twistTurn);
		mFlip.rotate(-armFlipReset);
		mFlip.rotate(-armFlip);
		mFlip.rotate(armFlip);
		mFlip.rotate(armFlipReset);
	}

	public static void up(int clockwise, int turnTimes) {
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(-armFlip);
		mFlip.rotate(armFlip);
		mFlip.rotate(-armFlip);
		for (int turn = 0; turn < turnTimes; turn++) {
			mTwist.rotate(clockwise * ((-twistTurn) + (-twistCorrect)));
			mTwist.rotate(clockwise * twistCorrect);
		}
		mFlip.rotate(armFlip);
		mFlip.rotate(-armFlip);
		mFlip.rotate(armFlip);
		mFlip.rotate(-armFlip);
		mFlip.rotate(armFlipReset);
		Delay.msDelay(250);
	}

	public static void down(int clockwise, int turnTimes) {
		mFlip.rotate(armLock);
		for (int turn = 0; turn < turnTimes; turn++) {
			mTwist.rotate(clockwise * ((-twistTurn) + (-twistCorrect)));
			mTwist.rotate(clockwise * twistCorrect);
		}
		mFlip.rotate(-armLock);
		Delay.msDelay(250);
	}

	public static void left(int clockwise, int turnTimes) {
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(-armFlip);
		for (int turn = 0; turn < turnTimes; turn++) {
			mTwist.rotate(clockwise * ((-twistTurn) + (-twistCorrect)));
			mTwist.rotate(clockwise * twistCorrect);
		}
		mFlip.rotate(-armLock);
		mTwist.rotate(2 * twistTurn);
		mFlip.rotate(armLock + armFlip);
		mTwist.rotate(2 * twistTurn);
		mFlip.rotate(armFlipReset);
		Delay.msDelay(250);
	}

	public static void right(int clockwise, int turnTimes) {
		mTwist.rotate(2 * twistTurn);
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(-armFlip);
		for (int turn = 0; turn < turnTimes; turn++) {
			mTwist.rotate(clockwise * ((-twistTurn) + (-twistCorrect)));
			mTwist.rotate(clockwise * twistCorrect);
		}
		mFlip.rotate(-armLock);
		mTwist.rotate(2 * twistTurn);
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(armFlipReset);
		Delay.msDelay(250);
	}

	public static void front(int clockwise, int turnTimes) {
		mTwist.rotate(twistTurn);
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(-armFlip);
		for (int turn = 0; turn < turnTimes; turn++) {
			mTwist.rotate(clockwise * ((-twistTurn) + (-twistCorrect)));
			mTwist.rotate(clockwise * twistCorrect);
		}
		mFlip.rotate(-armLock);
		mTwist.rotate(2 * twistTurn);
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(armFlipReset);
		mTwist.rotate(twistTurn);
		Delay.msDelay(250);
	}

	public static void back(int clockwise, int turnTimes) {
		mTwist.rotate(-twistTurn);
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(-armFlip);
		for (int turn = 0; turn < turnTimes; turn++) {
			mTwist.rotate(clockwise * ((-twistTurn) + (-twistCorrect)));
			mTwist.rotate(clockwise * twistCorrect);
		}
		mFlip.rotate(-armLock);
		mTwist.rotate(2 * twistTurn);
		mFlip.rotate(armLock + armFlip);
		mFlip.rotate(armFlipReset);
		mTwist.rotate(-twistTurn);
		Delay.msDelay(250);
	}

	// Clockwise\\
	public static void orientSideClock(int side) {
		for (int x = 0; x < 9; x++) {
			orientTrans[x] = cube[side][x];
		}
		cube[side][0] = orientTrans[6];
		cube[side][1] = orientTrans[3];
		cube[side][2] = orientTrans[0];
		cube[side][3] = orientTrans[7];
		cube[side][5] = orientTrans[1];
		cube[side][6] = orientTrans[8];
		cube[side][7] = orientTrans[5];
		cube[side][8] = orientTrans[2];
	}

	// AntiClockwise\\
	public static void orientSideAnticlock(int side) {
		for (int x = 0; x < 9; x++) {
			orientTrans[x] = cube[side][x];
		}
		cube[side][0] = orientTrans[2];
		cube[side][1] = orientTrans[5];
		cube[side][2] = orientTrans[8];
		cube[side][3] = orientTrans[1];
		cube[side][5] = orientTrans[7];
		cube[side][6] = orientTrans[0];
		cube[side][7] = orientTrans[3];
		cube[side][8] = orientTrans[6];
	}

	// Front\\
	public static void front() {
		sideTrans[0] = cube[0][6];
		sideTrans[1] = cube[0][7];
		sideTrans[2] = cube[0][8];
		sideTrans[3] = cube[2][0];
		sideTrans[4] = cube[2][3];
		sideTrans[5] = cube[2][6];
		sideTrans[6] = cube[5][8];
		sideTrans[7] = cube[5][5];
		sideTrans[8] = cube[5][2];
		sideTrans[9] = cube[4][8];
		sideTrans[10] = cube[4][5];
		sideTrans[11] = cube[4][2];
	}

	public static void frontClock() {
		front();
		cube[0][6] = sideTrans[9];
		cube[0][7] = sideTrans[10];
		cube[0][8] = sideTrans[11];
		cube[2][0] = sideTrans[0];
		cube[2][3] = sideTrans[1];
		cube[2][6] = sideTrans[2];
		cube[5][8] = sideTrans[3];
		cube[5][5] = sideTrans[4];
		cube[5][2] = sideTrans[5];
		cube[4][8] = sideTrans[6];
		cube[4][5] = sideTrans[7];
		cube[4][2] = sideTrans[8];
		orientSideClock(1);
	}

	public static void frontAnticlock() {
		front();
		cube[0][6] = sideTrans[3];
		cube[0][7] = sideTrans[4];
		cube[0][8] = sideTrans[5];
		cube[2][0] = sideTrans[6];
		cube[2][3] = sideTrans[7];
		cube[2][6] = sideTrans[8];
		cube[5][8] = sideTrans[9];
		cube[5][5] = sideTrans[10];
		cube[5][2] = sideTrans[11];
		cube[4][8] = sideTrans[0];
		cube[4][5] = sideTrans[1];
		cube[4][2] = sideTrans[2];
		orientSideAnticlock(1);
	}

	// Back\\
	public static void back() {
		sideTrans[0] = cube[0][2];
		sideTrans[1] = cube[0][1];
		sideTrans[2] = cube[0][0];
		sideTrans[3] = cube[4][0];
		sideTrans[4] = cube[4][3];
		sideTrans[5] = cube[4][6];
		sideTrans[6] = cube[5][0];
		sideTrans[7] = cube[5][3];
		sideTrans[8] = cube[5][6];
		sideTrans[9] = cube[2][8];
		sideTrans[10] = cube[2][5];
		sideTrans[11] = cube[2][2];
	}

	public static void backClock() {
		back();
		cube[0][2] = sideTrans[9];
		cube[0][1] = sideTrans[10];
		cube[0][0] = sideTrans[11];
		cube[4][0] = sideTrans[0];
		cube[4][3] = sideTrans[1];
		cube[4][6] = sideTrans[2];
		cube[5][0] = sideTrans[3];
		cube[5][3] = sideTrans[4];
		cube[5][6] = sideTrans[5];
		cube[2][8] = sideTrans[6];
		cube[2][5] = sideTrans[7];
		cube[2][2] = sideTrans[8];
		orientSideClock(3);
	}

	public static void backAnticlock() {
		back();
		cube[0][2] = sideTrans[3];
		cube[0][1] = sideTrans[4];
		cube[0][0] = sideTrans[5];
		cube[4][0] = sideTrans[6];
		cube[4][3] = sideTrans[7];
		cube[4][6] = sideTrans[8];
		cube[5][0] = sideTrans[9];
		cube[5][3] = sideTrans[10];
		cube[5][6] = sideTrans[11];
		cube[2][8] = sideTrans[0];
		cube[2][5] = sideTrans[1];
		cube[2][2] = sideTrans[2];
		orientSideAnticlock(3);
	}

	// Left\\
	public static void left() {
		sideTrans[0] = cube[0][0];
		sideTrans[1] = cube[0][3];
		sideTrans[2] = cube[0][6];
		sideTrans[3] = cube[1][0];
		sideTrans[4] = cube[1][3];
		sideTrans[5] = cube[1][6];
		sideTrans[6] = cube[5][2];
		sideTrans[7] = cube[5][1];
		sideTrans[8] = cube[5][0];
		sideTrans[9] = cube[3][8];
		sideTrans[10] = cube[3][5];
		sideTrans[11] = cube[3][2];
	}

	public static void leftClock() {
		left();
		cube[0][0] = sideTrans[9];
		cube[0][3] = sideTrans[10];
		cube[0][6] = sideTrans[11];
		cube[1][0] = sideTrans[0];
		cube[1][3] = sideTrans[1];
		cube[1][6] = sideTrans[2];
		cube[5][2] = sideTrans[3];
		cube[5][1] = sideTrans[4];
		cube[5][0] = sideTrans[5];
		cube[3][8] = sideTrans[6];
		cube[3][5] = sideTrans[7];
		cube[3][2] = sideTrans[8];
		orientSideClock(4);
	}

	public static void leftAnticlock() {
		left();
		cube[0][0] = sideTrans[3];
		cube[0][3] = sideTrans[4];
		cube[0][6] = sideTrans[5];
		cube[1][0] = sideTrans[6];
		cube[1][3] = sideTrans[7];
		cube[1][6] = sideTrans[8];
		cube[5][2] = sideTrans[9];
		cube[5][1] = sideTrans[10];
		cube[5][0] = sideTrans[11];
		cube[3][8] = sideTrans[0];
		cube[3][5] = sideTrans[1];
		cube[3][2] = sideTrans[2];
		orientSideAnticlock(4);
	}

	// Right\\
	public static void right() {
		sideTrans[0] = cube[0][8];
		sideTrans[1] = cube[0][5];
		sideTrans[2] = cube[0][2];
		sideTrans[3] = cube[3][0];
		sideTrans[4] = cube[3][3];
		sideTrans[5] = cube[3][6];
		sideTrans[6] = cube[5][6];
		sideTrans[7] = cube[5][7];
		sideTrans[8] = cube[5][8];
		sideTrans[9] = cube[1][8];
		sideTrans[10] = cube[1][5];
		sideTrans[11] = cube[1][2];
	}

	public static void rightClock() {
		right();
		cube[0][8] = sideTrans[9];
		cube[0][5] = sideTrans[10];
		cube[0][2] = sideTrans[11];
		cube[3][0] = sideTrans[0];
		cube[3][3] = sideTrans[1];
		cube[3][6] = sideTrans[2];
		cube[5][6] = sideTrans[3];
		cube[5][7] = sideTrans[4];
		cube[5][8] = sideTrans[5];
		cube[1][8] = sideTrans[6];
		cube[1][5] = sideTrans[7];
		cube[1][2] = sideTrans[8];
		orientSideClock(2);
	}

	public static void rightAnticlock() {
		right();
		cube[0][8] = sideTrans[3];
		cube[0][5] = sideTrans[4];
		cube[0][2] = sideTrans[5];
		cube[3][0] = sideTrans[6];
		cube[3][3] = sideTrans[7];
		cube[3][6] = sideTrans[8];
		cube[5][6] = sideTrans[9];
		cube[5][7] = sideTrans[10];
		cube[5][8] = sideTrans[11];
		cube[1][8] = sideTrans[0];
		cube[1][5] = sideTrans[1];
		cube[1][2] = sideTrans[2];
		orientSideAnticlock(2);
	}

	// Up\\
	public static void up() {
		sideTrans[0] = cube[3][2];
		sideTrans[1] = cube[3][1];
		sideTrans[2] = cube[3][0];
		sideTrans[3] = cube[2][2];
		sideTrans[4] = cube[2][1];
		sideTrans[5] = cube[2][0];
		sideTrans[6] = cube[1][2];
		sideTrans[7] = cube[1][1];
		sideTrans[8] = cube[1][0];
		sideTrans[9] = cube[4][2];
		sideTrans[10] = cube[4][1];
		sideTrans[11] = cube[4][0];
	}

	public static void upClock() {
		up();
		cube[3][2] = sideTrans[9];
		cube[3][1] = sideTrans[10];
		cube[3][0] = sideTrans[11];
		cube[2][2] = sideTrans[0];
		cube[2][1] = sideTrans[1];
		cube[2][0] = sideTrans[2];
		cube[1][2] = sideTrans[3];
		cube[1][1] = sideTrans[4];
		cube[1][0] = sideTrans[5];
		cube[4][2] = sideTrans[6];
		cube[4][1] = sideTrans[7];
		cube[4][0] = sideTrans[8];
		orientSideClock(0);
	}

	public static void upAnticlock() {
		up();
		cube[3][2] = sideTrans[3];
		cube[3][1] = sideTrans[4];
		cube[3][0] = sideTrans[5];
		cube[2][2] = sideTrans[6];
		cube[2][1] = sideTrans[7];
		cube[2][0] = sideTrans[8];
		cube[1][2] = sideTrans[9];
		cube[1][1] = sideTrans[10];
		cube[1][0] = sideTrans[11];
		cube[4][2] = sideTrans[0];
		cube[4][1] = sideTrans[1];
		cube[4][0] = sideTrans[2];
		orientSideAnticlock(0);
	}

	// Down\\
	public static void down() {
		sideTrans[0] = cube[1][6];
		sideTrans[1] = cube[1][7];
		sideTrans[2] = cube[1][8];
		sideTrans[3] = cube[2][6];
		sideTrans[4] = cube[2][7];
		sideTrans[5] = cube[2][8];
		sideTrans[6] = cube[3][6];
		sideTrans[7] = cube[3][7];
		sideTrans[8] = cube[3][8];
		sideTrans[9] = cube[4][6];
		sideTrans[10] = cube[4][7];
		sideTrans[11] = cube[4][8];
	}

	public static void downClock() {
		down();
		cube[1][6] = sideTrans[9];
		cube[1][7] = sideTrans[10];
		cube[1][8] = sideTrans[11];
		cube[2][6] = sideTrans[0];
		cube[2][7] = sideTrans[1];
		cube[2][8] = sideTrans[2];
		cube[3][6] = sideTrans[3];
		cube[3][7] = sideTrans[4];
		cube[3][8] = sideTrans[5];
		cube[4][6] = sideTrans[6];
		cube[4][7] = sideTrans[7];
		cube[4][8] = sideTrans[8];
		orientSideClock(5);
	}

	public static void downAnticlock() {
		down();
		cube[1][6] = sideTrans[3];
		cube[1][7] = sideTrans[4];
		cube[1][8] = sideTrans[5];
		cube[2][6] = sideTrans[6];
		cube[2][7] = sideTrans[7];
		cube[2][8] = sideTrans[8];
		cube[3][6] = sideTrans[9];
		cube[3][7] = sideTrans[10];
		cube[3][8] = sideTrans[11];
		cube[4][6] = sideTrans[0];
		cube[4][7] = sideTrans[1];
		cube[4][8] = sideTrans[2];
		orientSideAnticlock(5);
	}

	// Shuffle\\
	public static void shuffle() {
		shuffle = "";
		Random random = new Random();
		for (int x = 0; x < 10 + random.nextInt(5); x++) {
			int randomShuffle = random.nextInt(12);
			switch (randomShuffle) {
				case 0:
					frontClock();
					shuffle += "F ";
					break;
				case 1:
					frontAnticlock();
					shuffle += "F' ";
					break;
				case 2:
					backClock();
					shuffle += "B ";
					break;
				case 3:
					backAnticlock();
					shuffle += "B' ";
					break;
				case 4:
					leftClock();
					shuffle += "L ";
					break;
				case 5:
					leftAnticlock();
					shuffle += "L' ";
					break;
				case 6:
					rightClock();
					shuffle += "R ";
					break;
				case 7:
					rightAnticlock();
					shuffle += "R' ";
					break;
				case 8:
					upClock();
					shuffle += "U ";
					break;
				case 9:
					upAnticlock();
					shuffle += "U' ";
					break;
				case 10:
					downClock();
					shuffle += "D ";
					break;
				case 11:
					downAnticlock();
					shuffle += "D' ";
					break;
			}
		}
	}

	// Repetition Check\\
	public static String notationRepetitionCheck(String data) {
		String[] nRCList = data.split(" ");
		boolean loopCheck = false;
		for (int arrayIndex = 0; arrayIndex < nRCList.length - 1; arrayIndex++) {
			if (nRCList[arrayIndex].substring(0, 1).equals(nRCList[arrayIndex + 1].substring(0, 1))) {
				data = nRCList[arrayIndex].substring(0, 1);
				loopCheck = true;
				break;
			}
		}
		if (loopCheck != true) {
			data = null;
		}
		return data;
	}

	public static String repetitionCheck(String combinedNotationData) {
		int startingPostition;
		String letter = notationRepetitionCheck(combinedNotationData);
		if (letter != null) {
			if (combinedNotationData.contains(letter + " " + letter + "2")) { // This could also be expressed with *if
																				// combinedNotationData.indexOf(letter +
																				// " " + letter +"2") != -1:*
				startingPostition = combinedNotationData.indexOf(letter + " " + letter + "2");
				combinedNotationData = combinedNotationData.substring(0, startingPostition) + letter + "'"
						+ combinedNotationData.substring(startingPostition + 4, +combinedNotationData.length());
			} else if (combinedNotationData.contains(letter + "2 " + letter + "2")) {
				startingPostition = combinedNotationData.indexOf(letter + "2 " + letter + "2");
				combinedNotationData = combinedNotationData.substring(0, startingPostition)
						+ combinedNotationData.substring(startingPostition + 6, combinedNotationData.length());
			} else if ((combinedNotationData.contains(letter + "' " + letter + "2"))
					|| (combinedNotationData.contains(letter + "2 " + letter + "'"))) {
				int condition1 = combinedNotationData.indexOf(letter + "' " + letter + "2");
				int condition2 = combinedNotationData.indexOf(letter + "2 " + letter + "'");
				if (((condition1 < condition2) && (condition1 != -1)) || (condition2 == -1)) {
					startingPostition = combinedNotationData.indexOf(letter + "' " + letter + "2");
				} else {
					startingPostition = combinedNotationData.indexOf(letter + "2 " + letter + "'");
				}
				combinedNotationData = combinedNotationData.substring(0, startingPostition) + letter
						+ combinedNotationData.substring(startingPostition + 5, combinedNotationData.length());
			} else if (combinedNotationData.contains(letter + "' " + letter + "'")) {
				startingPostition = combinedNotationData.indexOf(letter + "' " + letter + "'");
				combinedNotationData = combinedNotationData.substring(0, startingPostition) + letter + "2"
						+ combinedNotationData.substring(startingPostition + 5, combinedNotationData.length());
			} else if (combinedNotationData.contains(letter + "' " + letter)) {
				startingPostition = combinedNotationData.indexOf(letter + "' " + letter);
				combinedNotationData = combinedNotationData.substring(0, startingPostition)
						+ combinedNotationData.substring(startingPostition + 5, combinedNotationData.length());
			} else if (combinedNotationData.contains(letter + " " + letter + "'")) {
				startingPostition = combinedNotationData.indexOf(letter + " " + letter + "'");
				combinedNotationData = combinedNotationData.substring(0, startingPostition)
						+ combinedNotationData.substring(startingPostition + 5, combinedNotationData.length());
			} else if (combinedNotationData.contains(letter + " " + letter)) {
				startingPostition = combinedNotationData.indexOf(letter + " " + letter);
				combinedNotationData = combinedNotationData.substring(0, startingPostition) + letter + ("2")
						+ combinedNotationData.substring(startingPostition + 3, combinedNotationData.length());
			} else if (combinedNotationData.contains(letter + "2 " + letter)) {
				startingPostition = combinedNotationData.indexOf(letter + "2 " + letter);
				combinedNotationData = combinedNotationData.substring(0, startingPostition) + letter + ("'")
						+ combinedNotationData.substring(startingPostition + 4, combinedNotationData.length());
			}
			combinedNotationData = repetitionCheck(combinedNotationData);
		}
		return combinedNotationData;
	}

	// Notation to Movement\\
	static void notationToMovement(String movement) {
		String[] nTMList = movement.split(" ");
		for (int arrayIndex = 0; arrayIndex < nTMList.length; arrayIndex++) {
			switch (nTMList[arrayIndex]) {
				case "F":
					frontClock();
					OverallString += "F ";
					break;
				case "F'":
					frontAnticlock();
					OverallString += "F' ";
					break;
				case "B":
					backClock();
					OverallString += "B ";
					break;
				case "B'":
					backAnticlock();
					OverallString += "B' ";
					break;
				case "L":
					leftClock();
					OverallString += "L ";
					break;
				case "L'":
					leftAnticlock();
					OverallString += "L' ";
					break;
				case "R":
					rightClock();
					OverallString += "R ";
					break;
				case "R'":
					rightAnticlock();
					OverallString += "R' ";
					break;
				case "U":
					upClock();
					OverallString += "U ";
					break;
				case "U'":
					upAnticlock();
					OverallString += "U' ";
					break;
				case "D":
					downClock();
					OverallString += "D ";
					break;
				case "D'":
					downAnticlock();
					OverallString += "D' ";
					break;
			}
		}
	}

	// Corner Orientation Check\\
	public static void cornerOrientationCheck(String face) {
		cornerloop: while (true) {
			switch (face) {
				case "Red":
					if (cube[0][8] != "W") {
						notationToMovement("R' D' R D R' D' R D");
						break;
					} else {
						break cornerloop;
					}
				case "Blue":
					if (cube[0][2] != "W") {
						notationToMovement("B' D' B D B' D' B D");
						break;
					} else {
						break cornerloop;
					}
				case "Orange":
					if (cube[0][0] != "W") {
						notationToMovement("L' D' L D L' D' L D");
						break;
					} else {
						break cornerloop;
					}
				case "Green":
					if (cube[0][6] != "W") {
						notationToMovement("F' D' F D F' D' F D");
						break;
					} else {
						break cornerloop;
					}
			}
		}
	}

	// Robot Movement\\
	public static void robotMovement(String moves) {
		String[] move = repetitionCheck(moves).split(" ");
		for (int arrayIndex = 0; arrayIndex < move.length; arrayIndex++) {
			LCD.drawString(Integer.toString(arrayIndex + 1) + "/" + move.length, 0, 0);
			switch (move[arrayIndex]) {
				case "F":
					front(1, 1);
					break;
				case "F'":
					front(-1, 1);
					break;
				case "F2":
					front(1, 2);
					break;
				case "B":
					back(1, 1);
					break;
				case "B'":
					back(-1, 1);
					break;
				case "B2":
					back(1, 2);
					break;
				case "L":
					left(1, 1);
					break;
				case "L'":
					left(-1, 1);
					break;
				case "L2":
					left(1, 2);
					break;
				case "R":
					right(1, 1);
					break;
				case "R'":
					right(-1, 1);
					break;
				case "R2":
					right(1, 2);
					break;
				case "U":
					up(1, 1);
					break;
				case "U'":
					up(-1, 1);
					break;
				case "U2":
					up(1, 2);
					break;
				case "D":
					down(1, 1);
					break;
				case "D'":
					down(-1, 1);
					break;
				case "D2":
					down(1, 2);
					break;
			}
		}
	}
}