import lejos.robotics.subsumption.Behavior;

public class Run implements Behavior {

	boolean run = Main.runRun;
	
	@Override
	public boolean takeControl() {
		return run;
	}

	@Override
	public void action() {
		while (run) {
			 CubeSolver2.readCube();

			String[][] cube = CubeSolver2.cube;
			
			// Section 1 - Cross at the top\\
			while (!((cube[1][1] == "R") && (cube[0][7] == "W"))) {
				if ((cube[0][5] == "R") && (cube[2][1] == "W") || (cube[0][5] == "W") && (cube[2][1] == "R")) {
					CubeSolver2.notationToMovement("U");
				} else if ((cube[0][1] == "R") && (cube[3][1] == "W") || (cube[0][1] == "W") && (cube[3][1] == "R")) {
					CubeSolver2.notationToMovement("U U");
				} else if ((cube[0][3] == "R") && (cube[4][1] == "W") || (cube[0][3] == "W") && (cube[4][1] == "R")) {
					CubeSolver2.notationToMovement("U'");
				} else if ((cube[1][5] == "R") && (cube[2][3] == "W") || (cube[1][5] == "W") && (cube[2][3] == "R")) {
					CubeSolver2.notationToMovement("F'");
				} else if ((cube[2][5] == "R") && (cube[3][3] == "W") || (cube[2][5] == "W") && (cube[3][3] == "R")) {
					CubeSolver2.notationToMovement("R' U");
				} else if ((cube[3][5] == "R") && (cube[4][3] == "W") || (cube[3][5] == "W") && (cube[4][3] == "R")) {
					CubeSolver2.notationToMovement("L U'");
				} else if ((cube[4][5] == "R") && (cube[1][3] == "W") || (cube[4][5] == "W") && (cube[1][3] == "R")) {
					CubeSolver2.notationToMovement("F");
				} else if ((cube[1][7] == "R") && (cube[5][5] == "W") || (cube[1][7] == "W") && (cube[5][5] == "R")) {
					CubeSolver2.notationToMovement("F F");
				} else if ((cube[2][7] == "R") && (cube[5][7] == "W") || (cube[2][7] == "W") && (cube[5][7] == "R")) {
					CubeSolver2.notationToMovement("D' F F");
				} else if ((cube[3][7] == "R") && (cube[5][3] == "W") || (cube[3][7] == "W") && (cube[5][3] == "R")) {
					CubeSolver2.notationToMovement("D D F F");
				} else if ((cube[4][7] == "R") && (cube[5][1] == "W") || (cube[4][7] == "W") && (cube[5][1] == "R")) {
					CubeSolver2.notationToMovement("D F F");
				} else {
					break;
				}
				if (cube[0][7] != "W") {
					CubeSolver2.notationToMovement("F F D' L' F L");
				}
			}

			while (!((cube[2][1] == "B") && (cube[0][5] == "W"))) {
				if ((cube[0][1] == "B") && (cube[3][1] == "W") || (cube[0][1] == "W") && (cube[3][1] == "B")) {
					CubeSolver2.notationToMovement("B' R'");
				} else if ((cube[0][3] == "B") && (cube[4][1] == "W") || (cube[0][3] == "W") && (cube[4][1] == "B")) {
					CubeSolver2.notationToMovement("L L D D R R");
				} else if ((cube[1][5] == "B") && (cube[2][3] == "W") || (cube[1][5] == "W") && (cube[2][3] == "B")) {
					CubeSolver2.notationToMovement("R");
				} else if ((cube[2][5] == "B") && (cube[3][3] == "W") || (cube[2][5] == "W") && (cube[3][3] == "B")) {
					CubeSolver2.notationToMovement("R'");
				} else if ((cube[3][5] == "B") && (cube[4][3] == "W") || (cube[3][5] == "W") && (cube[4][3] == "B")) {
					CubeSolver2.notationToMovement("U U L U U");
				} else if ((cube[4][5] == "B") && (cube[1][3] == "W") || (cube[4][5] == "W") && (cube[1][3] == "B")) {
					CubeSolver2.notationToMovement("U U L' U U");
				} else if ((cube[1][7] == "B") && (cube[5][5] == "W") || (cube[1][7] == "W") && (cube[5][5] == "B")) {
					CubeSolver2.notationToMovement("D R R");
				} else if ((cube[2][7] == "B") && (cube[5][7] == "W") || (cube[2][7] == "W") && (cube[5][7] == "B")) {
					CubeSolver2.notationToMovement("R R");
				} else if ((cube[3][7] == "B") && (cube[5][3] == "W") || (cube[3][7] == "W") && (cube[5][3] == "B")) {
					CubeSolver2.notationToMovement("D' R R");
				} else if ((cube[4][7] == "B") && (cube[5][1] == "W") || (cube[4][7] == "W") && (cube[5][1] == "B")) {
					CubeSolver2.notationToMovement("D D R R");
				} else {
					break;
				}
				if (cube[0][5] != "W") {
					CubeSolver2.notationToMovement("R R D' F' R F");
				}
			}

			while (!((cube[3][1] == "O") && (cube[0][1] == "W"))) {
				if ((cube[0][3] == "O") && (cube[4][1] == "W") || (cube[0][3] == "W") && (cube[4][1] == "O")) {
					CubeSolver2.notationToMovement("L' B'");
				} else if ((cube[1][5] == "O") && (cube[2][3] == "W") || (cube[1][5] == "W") && (cube[2][3] == "O")) {
					CubeSolver2.notationToMovement("U R U'");
				} else if ((cube[2][5] == "O") && (cube[3][3] == "W") || (cube[2][5] == "W") && (cube[3][3] == "O")) {
					CubeSolver2.notationToMovement("B");
				} else if ((cube[3][5] == "O") && (cube[4][3] == "W") || (cube[3][5] == "W") && (cube[4][3] == "O")) {
					CubeSolver2.notationToMovement("B'");
				} else if ((cube[4][5] == "O") && (cube[1][3] == "W") || (cube[4][5] == "W") && (cube[1][3] == "O")) {
					CubeSolver2.notationToMovement("U' L' U");
				} else if ((cube[1][7] == "O") && (cube[5][5] == "W") || (cube[1][7] == "W") && (cube[5][5] == "O")) {
					CubeSolver2.notationToMovement("D D B B");
				} else if ((cube[2][7] == "O") && (cube[5][7] == "W") || (cube[2][7] == "W") && (cube[5][7] == "O")) {
					CubeSolver2.notationToMovement("D B B");
				} else if ((cube[3][7] == "O") && (cube[5][3] == "W") || (cube[3][7] == "W") && (cube[5][3] == "O")) {
					CubeSolver2.notationToMovement("B B");
				} else if ((cube[4][7] == "O") && (cube[5][1] == "W") || (cube[4][7] == "W") && (cube[5][1] == "O")) {
					CubeSolver2.notationToMovement("D' B B");
				} else {
					break;
				}
				if (cube[0][1] != "W") {
					CubeSolver2.notationToMovement("B B D' R' B R");
				}
			}

			while (!((cube[4][1] == "G") && (cube[0][3] == "W"))) {
				if ((cube[1][5] == "G") && (cube[2][3] == "W") || (cube[1][5] == "W") && (cube[2][3] == "G")) {
					CubeSolver2.notationToMovement("U' F' U");
				} else if ((cube[2][5] == "G") && (cube[3][3] == "W") || (cube[2][5] == "W") && (cube[3][3] == "G")) {
					CubeSolver2.notationToMovement("U B U'");
				} else if ((cube[3][5] == "G") && (cube[4][3] == "W") || (cube[3][5] == "W") && (cube[4][3] == "G")) {
					CubeSolver2.notationToMovement("L");
				} else if ((cube[4][5] == "G") && (cube[1][3] == "W") || (cube[4][5] == "W") && (cube[1][3] == "G")) {
					CubeSolver2.notationToMovement("L'");
				} else if ((cube[1][7] == "G") && (cube[5][5] == "W") || (cube[1][7] == "W") && (cube[5][5] == "G")) {
					CubeSolver2.notationToMovement("D' L L");
				} else if ((cube[2][7] == "G") && (cube[5][7] == "W") || (cube[2][7] == "W") && (cube[5][7] == "G")) {
					CubeSolver2.notationToMovement("D D L L");
				} else if ((cube[3][7] == "G") && (cube[5][3] == "W") || (cube[3][7] == "W") && (cube[5][3] == "G")) {
					CubeSolver2.notationToMovement("D L L");
				} else if ((cube[4][7] == "G") && (cube[5][1] == "W") || (cube[4][7] == "W") && (cube[5][1] == "G")) {
					CubeSolver2.notationToMovement("L L");
				} else {
					break;
				}
				if (cube[0][3] != "W") {
					CubeSolver2.notationToMovement("L L D' B' L B");
				}
			}

			// Section 2 - Corners & Orientation for top\\
			Boolean WXX;
			while (true) {
				WXX = true;
				if ((((cube[1][8] == "W") && (cube[2][6] == "B") && (cube[5][8] == "R"))
						|| ((cube[1][8] == "R") && (cube[2][6] == "W") && (cube[5][8] == "B"))
						|| ((cube[1][8] == "B") && (cube[2][6] == "R") && (cube[5][8] == "W")))
						|| (((cube[2][8] == "W") && (cube[3][6] == "B") && (cube[5][6] == "R"))
								|| ((cube[2][8] == "R") && (cube[3][6] == "W") && (cube[5][6] == "B"))
								|| ((cube[2][8] == "B") && (cube[3][6] == "R") && (cube[5][6] == "W")))
						|| (((cube[3][8] == "W") && (cube[4][6] == "B") && (cube[5][0] == "R"))
								|| ((cube[3][8] == "R") && (cube[4][6] == "W") && (cube[5][0] == "B"))
								|| ((cube[3][8] == "B") && (cube[4][6] == "R") && (cube[5][0] == "W")))
						|| (((cube[4][8] == "W") && (cube[1][6] == "B") && (cube[5][2] == "R"))
								|| ((cube[4][8] == "R") && (cube[1][6] == "W") && (cube[5][2] == "B"))
								|| ((cube[4][8] == "B") && (cube[1][6] == "R") && (cube[5][2] == "W")))) {
					if (((cube[1][8] == "W") && (cube[2][6] == "B") && (cube[5][8] == "R"))
							|| ((cube[1][8] == "R") && (cube[2][6] == "W") && (cube[5][8] == "B"))
							|| ((cube[1][8] == "B") && (cube[2][6] == "R") && (cube[5][8] == "W"))) {
						CubeSolver2.notationToMovement("F D F'");
					} else if (((cube[2][8] == "W") && (cube[3][6] == "B") && (cube[5][6] == "R"))
							|| ((cube[2][8] == "R") && (cube[3][6] == "W") && (cube[5][6] == "B"))
							|| ((cube[2][8] == "B") && (cube[3][6] == "R") && (cube[5][6] == "W"))) {
						CubeSolver2.notationToMovement("F D' F'");
					} else if (((cube[3][8] == "W") && (cube[4][6] == "B") && (cube[5][0] == "R"))
							|| ((cube[3][8] == "R") && (cube[4][6] == "W") && (cube[5][0] == "B"))
							|| ((cube[3][8] == "B") && (cube[4][6] == "R") && (cube[5][0] == "W"))) {
						CubeSolver2.notationToMovement("F D D F'");
					} else if (((cube[4][8] == "W") && (cube[1][6] == "B") && (cube[5][2] == "R"))
							|| ((cube[4][8] == "R") && (cube[1][6] == "W") && (cube[5][2] == "B"))
							|| ((cube[4][8] == "B") && (cube[1][6] == "R") && (cube[5][2] == "W"))) {
						CubeSolver2.notationToMovement("R' D R");
					}
					CubeSolver2.cornerOrientationCheck("Red");
					WXX = false;

				}
				if ((((cube[1][8] == "W") && (cube[2][6] == "O") && (cube[5][8] == "B"))
						|| ((cube[1][8] == "B") && (cube[2][6] == "W") && (cube[5][8] == "O"))
						|| ((cube[1][8] == "O") && (cube[2][6] == "B") && (cube[5][8] == "W")))
						|| (((cube[2][8] == "W") && (cube[3][6] == "O") && (cube[5][6] == "B"))
								|| ((cube[2][8] == "B") && (cube[3][6] == "W") && (cube[5][6] == "O"))
								|| ((cube[2][8] == "O") && (cube[3][6] == "B") && (cube[5][6] == "W")))
						|| (((cube[3][8] == "W") && (cube[4][6] == "O") && (cube[5][0] == "B"))
								|| ((cube[3][8] == "B") && (cube[4][6] == "W") && (cube[5][0] == "O"))
								|| ((cube[3][8] == "O") && (cube[4][6] == "B") && (cube[5][0] == "W")))
						|| (((cube[4][8] == "W") && (cube[1][6] == "O") && (cube[5][2] == "B"))
								|| ((cube[4][8] == "B") && (cube[1][6] == "W") && (cube[5][2] == "O"))
								|| ((cube[4][8] == "O") && (cube[1][6] == "B") && (cube[5][2] == "W")))) {
					if (((cube[1][8] == "W") && (cube[2][6] == "O") && (cube[5][8] == "B"))
							|| ((cube[1][8] == "B") && (cube[2][6] == "W") && (cube[5][8] == "O"))
							|| ((cube[1][8] == "O") && (cube[2][6] == "B") && (cube[5][8] == "W"))) {
						CubeSolver2.notationToMovement("B' D B");
					} else if (((cube[2][8] == "W") && (cube[3][6] == "O") && (cube[5][6] == "B"))
							|| ((cube[2][8] == "B") && (cube[3][6] == "W") && (cube[5][6] == "O"))
							|| ((cube[2][8] == "O") && (cube[3][6] == "B") && (cube[5][6] == "W"))) {
						CubeSolver2.notationToMovement("R D R'");
					} else if (((cube[3][8] == "W") && (cube[4][6] == "O") && (cube[5][0] == "B"))
							|| ((cube[3][8] == "B") && (cube[4][6] == "W") && (cube[5][0] == "O"))
							|| ((cube[3][8] == "O") && (cube[4][6] == "B") && (cube[5][0] == "W"))) {
						CubeSolver2.notationToMovement("R D' R'");
					} else if (((cube[4][8] == "W") && (cube[1][6] == "O") && (cube[5][2] == "B"))
							|| ((cube[4][8] == "B") && (cube[1][6] == "W") && (cube[5][2] == "O"))
							|| ((cube[4][8] == "O") && (cube[1][6] == "B") && (cube[5][2] == "W"))) {
						CubeSolver2.notationToMovement("R D D R'");
					}
					CubeSolver2.cornerOrientationCheck("Blue");
					WXX = false;

				}
				if ((((cube[1][8] == "W") && (cube[2][6] == "G") && (cube[5][8] == "O"))
						|| ((cube[1][8] == "O") && (cube[2][6] == "W") && (cube[5][8] == "G"))
						|| ((cube[1][8] == "G") && (cube[2][6] == "O") && (cube[5][8] == "W")))
						|| (((cube[2][8] == "W") && (cube[3][6] == "G") && (cube[5][6] == "O"))
								|| ((cube[2][8] == "O") && (cube[3][6] == "W") && (cube[5][6] == "G"))
								|| ((cube[2][8] == "G") && (cube[3][6] == "O") && (cube[5][6] == "W")))
						|| (((cube[3][8] == "W") && (cube[4][6] == "G") && (cube[5][0] == "O"))
								|| ((cube[3][8] == "O") && (cube[4][6] == "W") && (cube[5][0] == "G"))
								|| ((cube[3][8] == "G") && (cube[4][6] == "O") && (cube[5][0] == "W")))
						|| (((cube[4][8] == "W") && (cube[1][6] == "G") && (cube[5][2] == "O"))
								|| ((cube[4][8] == "O") && (cube[1][6] == "W") && (cube[5][2] == "G"))
								|| ((cube[4][8] == "G") && (cube[1][6] == "O") && (cube[5][2] == "W")))) {
					if (((cube[1][8] == "W") && (cube[2][6] == "G") && (cube[5][8] == "O"))
							|| ((cube[1][8] == "O") && (cube[2][6] == "W") && (cube[5][8] == "G"))
							|| ((cube[1][8] == "G") && (cube[2][6] == "O") && (cube[5][8] == "W"))) {
						CubeSolver2.notationToMovement("B D D B'");
					} else if (((cube[2][8] == "W") && (cube[3][6] == "G") && (cube[5][6] == "O"))
							|| ((cube[2][8] == "O") && (cube[3][6] == "W") && (cube[5][6] == "G"))
							|| ((cube[2][8] == "G") && (cube[3][6] == "O") && (cube[5][6] == "W"))) {
						CubeSolver2.notationToMovement("L' D L");
					} else if (((cube[3][8] == "W") && (cube[4][6] == "G") && (cube[5][0] == "O"))
							|| ((cube[3][8] == "O") && (cube[4][6] == "W") && (cube[5][0] == "G"))
							|| ((cube[3][8] == "G") && (cube[4][6] == "O") && (cube[5][0] == "W"))) {
						CubeSolver2.notationToMovement("B D B'");
					} else if (((cube[4][8] == "W") && (cube[1][6] == "G") && (cube[5][2] == "O"))
							|| ((cube[4][8] == "O") && (cube[1][6] == "W") && (cube[5][2] == "G"))
							|| ((cube[4][8] == "G") && (cube[1][6] == "O") && (cube[5][2] == "W"))) {
						CubeSolver2.notationToMovement("B D' B'");
					}
					CubeSolver2.cornerOrientationCheck("Orange");
					WXX = false;

				}
				if ((((cube[1][8] == "W") && (cube[2][6] == "R") && (cube[5][8] == "G"))
						|| ((cube[1][8] == "G") && (cube[2][6] == "W") && (cube[5][8] == "R"))
						|| ((cube[1][8] == "R") && (cube[2][6] == "G") && (cube[5][8] == "W")))
						|| (((cube[2][8] == "W") && (cube[3][6] == "R") && (cube[5][6] == "G"))
								|| ((cube[2][8] == "G") && (cube[3][6] == "W") && (cube[5][6] == "R"))
								|| ((cube[2][8] == "R") && (cube[3][6] == "G") && (cube[5][6] == "W")))
						|| (((cube[3][8] == "W") && (cube[4][6] == "R") && (cube[5][0] == "G"))
								|| ((cube[3][8] == "G") && (cube[4][6] == "W") && (cube[5][0] == "R"))
								|| ((cube[3][8] == "R") && (cube[4][6] == "G") && (cube[5][0] == "W")))
						|| (((cube[4][8] == "W") && (cube[1][6] == "R") && (cube[5][2] == "G"))
								|| ((cube[4][8] == "G") && (cube[1][6] == "W") && (cube[5][2] == "R"))
								|| ((cube[4][8] == "R") && (cube[1][6] == "G") && (cube[5][2] == "W")))) {
					if (((cube[1][8] == "W") && (cube[2][6] == "R") && (cube[5][8] == "G"))
							|| ((cube[1][8] == "G") && (cube[2][6] == "W") && (cube[5][8] == "R"))
							|| ((cube[1][8] == "R") && (cube[2][6] == "G") && (cube[5][8] == "W"))) {
						CubeSolver2.notationToMovement("L D' L'");
					} else if (((cube[2][8] == "W") && (cube[3][6] == "R") && (cube[5][6] == "G"))
							|| ((cube[2][8] == "G") && (cube[3][6] == "W") && (cube[5][6] == "R"))
							|| ((cube[2][8] == "R") && (cube[3][6] == "G") && (cube[5][6] == "W"))) {
						CubeSolver2.notationToMovement("L D D L'");
					} else if (((cube[3][8] == "W") && (cube[4][6] == "R") && (cube[5][0] == "G"))
							|| ((cube[3][8] == "G") && (cube[4][6] == "W") && (cube[5][0] == "R"))
							|| ((cube[3][8] == "R") && (cube[4][6] == "G") && (cube[5][0] == "W"))) {
						CubeSolver2.notationToMovement("F' D F");
					} else if (((cube[4][8] == "W") && (cube[1][6] == "R") && (cube[5][2] == "G"))
							|| ((cube[4][8] == "G") && (cube[1][6] == "W") && (cube[5][2] == "R"))
							|| ((cube[4][8] == "R") && (cube[1][6] == "G") && (cube[5][2] == "W"))) {
						CubeSolver2.notationToMovement("L D L'");
					}
					CubeSolver2.cornerOrientationCheck("Green");
					WXX = false;
				}
				if (WXX == true) {
					if ((((cube[0][6] == "W") && (cube[1][0] == "R") && (cube[4][2] == "G"))
							|| ((cube[0][6] == "G") && (cube[1][0] == "W") && (cube[4][2] == "R"))
							|| ((cube[0][6] == "R") && (cube[1][0] == "G") && (cube[4][2] == "W")))
							&& (((cube[0][2] == "W") && (cube[3][0] == "O") && (cube[2][2] == "B"))
									|| ((cube[0][2] == "B") && (cube[3][0] == "W") && (cube[2][2] == "O"))
									|| ((cube[0][2] == "O") && (cube[3][0] == "B") && (cube[2][2] == "W")))) {
						if (((cube[0][8] == "W") && (cube[2][0] == "B") && (cube[1][2] == "R"))
								|| ((cube[0][8] == "R") && (cube[2][0] == "W") && (cube[1][2] == "B"))
								|| ((cube[0][8] == "B") && (cube[2][0] == "R") && (cube[1][2] == "W"))) {
							if (((cube[0][8] == "W") && (cube[2][0] == "B") && (cube[1][2] == "R"))
									&& ((cube[0][2] == "W") && (cube[3][0] == "O") && (cube[2][2] == "B"))
									&& ((cube[0][0] == "W") && (cube[4][0] == "G") && (cube[3][2] == "O"))
									&& ((cube[0][6] == "W") && (cube[1][0] == "R") && (cube[4][2] == "G"))) {
								break;
							} else {
								if (cube[0][8] != "W") {
									CubeSolver2.cornerOrientationCheck("Red");
								}
								if (cube[0][2] != "W") {
									CubeSolver2.cornerOrientationCheck("Blue");
								}
								if (cube[0][0] != "W") {
									CubeSolver2.cornerOrientationCheck("Orange");
								}
								if (cube[0][6] != "W") {
									CubeSolver2.cornerOrientationCheck("Green");
								}
							}
						} else {
							CubeSolver2.notationToMovement("R' D' R D");
						}
					} else {
						if (((cube[0][6] == "W") && (cube[1][0] == "R") && (cube[4][2] == "G"))
								|| ((cube[0][6] == "G") && (cube[1][0] == "W") && (cube[4][2] == "R"))
								|| ((cube[0][6] == "R") && (cube[1][0] == "G") && (cube[4][2] == "W"))) {
							CubeSolver2.notationToMovement("B' D' B D");
						} else {
							CubeSolver2.notationToMovement("F' D' F D");
						}
					}
				}
			}

			// Section 3 - Solve sides to create two complete layers\\
			while (true) {
				boolean XX = true;
				if ((((cube[1][7] == "R") && (cube[5][5] == "B")) || ((cube[1][7] == "B") && (cube[5][5] == "R")))
						|| (((cube[2][7] == "R") && (cube[5][7] == "B"))
								|| ((cube[2][7] == "B") && (cube[5][7] == "R")))
						|| (((cube[3][7] == "R") && (cube[5][3] == "B"))
								|| ((cube[3][7] == "B") && (cube[5][3] == "R")))
						|| (((cube[4][7] == "R") && (cube[5][1] == "B"))
								|| ((cube[4][7] == "B") && (cube[5][1] == "R")))) {
					if (((cube[1][7] == "R") && (cube[5][5] == "B")) || ((cube[2][7] == "R") && (cube[5][7] == "B"))
							|| ((cube[3][7] == "R") && (cube[5][3] == "B"))
							|| ((cube[4][7] == "R") && (cube[5][1] == "B"))) {
						if ((cube[1][7] == "R") && (cube[5][5] == "B")) {
							CubeSolver2.notationToMovement("D' R' D R D F D' F'");
						} else if ((cube[2][7] == "R") && (cube[5][7] == "B")) {
							CubeSolver2.notationToMovement("D D R' D R D F D' F'");
						} else if ((cube[3][7] == "R") && (cube[5][3] == "B")) {
							CubeSolver2.notationToMovement("D R' D R D F D' F'");
						} else if ((cube[4][7] == "R") && (cube[5][1] == "B")) {
							CubeSolver2.notationToMovement("R' D R D F D' F'");
						}
					} else {
						if ((cube[1][7] == "B") && (cube[5][5] == "R")) {
							CubeSolver2.notationToMovement("D D F D' F' D' R' D R");
						} else if ((cube[2][7] == "B") && (cube[5][7] == "R")) {
							CubeSolver2.notationToMovement("D F D' F' D' R' D R");
						} else if ((cube[3][7] == "B") && (cube[5][3] == "R")) {
							CubeSolver2.notationToMovement("F D' F' D' R' D R");
						} else if ((cube[4][7] == "B") && (cube[5][1] == "R")) {
							CubeSolver2.notationToMovement("D' F D' F' D' R' D R");
						}
					}
					XX = false;
				}
				if ((((cube[1][7] == "B") && (cube[5][5] == "O")) || ((cube[1][7] == "O") && (cube[5][5] == "B")))
						|| (((cube[2][7] == "B") && (cube[5][7] == "O"))
								|| ((cube[2][7] == "O") && (cube[5][7] == "B")))
						|| (((cube[3][7] == "B") && (cube[5][3] == "O"))
								|| ((cube[3][7] == "O") && (cube[5][3] == "B")))
						|| (((cube[4][7] == "B") && (cube[5][1] == "O"))
								|| ((cube[4][7] == "O") && (cube[5][1] == "B")))) {
					if (((cube[1][7] == "B") && (cube[5][5] == "O")) || ((cube[2][7] == "B") && (cube[5][7] == "O"))
							|| ((cube[3][7] == "B") && (cube[5][3] == "O"))
							|| ((cube[4][7] == "B") && (cube[5][1] == "O"))) {
						if ((cube[1][7] == "B") && (cube[5][5] == "O")) {
							CubeSolver2.notationToMovement("B' D B D R D' R'");
						} else if ((cube[2][7] == "B") && (cube[5][7] == "O")) {
							CubeSolver2.notationToMovement("D' B' D B D R D' R'");
						} else if ((cube[3][7] == "B") && (cube[5][3] == "O")) {
							CubeSolver2.notationToMovement("D D B' D B D R D' R'");
						} else if ((cube[4][7] == "B") && (cube[5][1] == "O")) {
							CubeSolver2.notationToMovement("D B' D B D R D' R'");
						}
					} else {
						if ((cube[1][7] == "O") && (cube[5][5] == "B")) {
							CubeSolver2.notationToMovement("D' R D' R' D' B' D B");
						} else if ((cube[2][7] == "O") && (cube[5][7] == "B")) {
							CubeSolver2.notationToMovement("D D R D' R' D' B' D B");
						} else if ((cube[3][7] == "O") && (cube[5][3] == "B")) {
							CubeSolver2.notationToMovement("D R D' R' D' B' D B");
						} else if ((cube[4][7] == "O") && (cube[5][1] == "B")) {
							CubeSolver2.notationToMovement("R D' R' D' B' D B");
						}
					}
					XX = false;
				}
				if ((((cube[1][7] == "O") && (cube[5][5] == "G")) || ((cube[1][7] == "G") && (cube[5][5] == "O")))
						|| (((cube[2][7] == "O") && (cube[5][7] == "G"))
								|| ((cube[2][7] == "G") && (cube[5][7] == "O")))
						|| (((cube[3][7] == "O") && (cube[5][3] == "G"))
								|| ((cube[3][7] == "G") && (cube[5][3] == "O")))
						|| (((cube[4][7] == "O") && (cube[5][1] == "G"))
								|| ((cube[4][7] == "G") && (cube[5][1] == "O")))) {
					if (((cube[1][7] == "O") && (cube[5][5] == "G")) || ((cube[2][7] == "O") && (cube[5][7] == "G"))
							|| ((cube[3][7] == "O") && (cube[5][3] == "G"))
							|| ((cube[4][7] == "O") && (cube[5][1] == "G"))) {
						if ((cube[1][7] == "O") && (cube[5][5] == "G")) {
							CubeSolver2.notationToMovement("D L' D L D B D' B'");
						} else if ((cube[2][7] == "O") && (cube[5][7] == "G")) {
							CubeSolver2.notationToMovement("L' D L D B D' B'");
						} else if ((cube[3][7] == "O") && (cube[5][3] == "G")) {
							CubeSolver2.notationToMovement("D' L' D L D B D' B'");
						} else if ((cube[4][7] == "O") && (cube[5][1] == "G")) {
							CubeSolver2.notationToMovement("D D L' D L D B D' B'");
						}
					} else {
						if ((cube[1][7] == "G") && (cube[5][5] == "O")) {
							CubeSolver2.notationToMovement("B D' B' D' L' D L");
						} else if ((cube[2][7] == "G") && (cube[5][7] == "O")) {
							CubeSolver2.notationToMovement("D' B D' B' D' L' D L");
						} else if ((cube[3][7] == "G") && (cube[5][3] == "O")) {
							CubeSolver2.notationToMovement("D D B D' B' D' L' D L");
						} else if ((cube[4][7] == "G") && (cube[5][1] == "O")) {
							CubeSolver2.notationToMovement("D B D' B' D' L' D L");
						}
					}
					XX = false;
				}
				if ((((cube[1][7] == "G") && (cube[5][5] == "R")) || ((cube[1][7] == "R") && (cube[5][5] == "G")))
						|| (((cube[2][7] == "G") && (cube[5][7] == "R"))
								|| ((cube[2][7] == "R") && (cube[5][7] == "G")))
						|| (((cube[3][7] == "G") && (cube[5][3] == "R"))
								|| ((cube[3][7] == "R") && (cube[5][3] == "G")))
						|| (((cube[4][7] == "G") && (cube[5][1] == "R"))
								|| ((cube[4][7] == "R") && (cube[5][1] == "G")))) {
					if (((cube[1][7] == "G") && (cube[5][5] == "R")) || ((cube[2][7] == "G") && (cube[5][7] == "R"))
							|| ((cube[3][7] == "G") && (cube[5][3] == "R"))
							|| ((cube[4][7] == "G") && (cube[5][1] == "R"))) {
						if ((cube[1][7] == "G") && (cube[5][5] == "R")) {
							CubeSolver2.notationToMovement("D D F' D F D L D' L'");
						} else if ((cube[2][7] == "G") && (cube[5][7] == "R")) {
							CubeSolver2.notationToMovement("D F' D F D L D' L'");
						} else if ((cube[3][7] == "G") && (cube[5][3] == "R")) {
							CubeSolver2.notationToMovement("F' D F D L D' L'");
						} else if ((cube[4][7] == "G") && (cube[5][1] == "R")) {
							CubeSolver2.notationToMovement("D' F' D F D L D' L'");
						}
					} else {
						if ((cube[1][7] == "R") && (cube[5][5] == "G")) {
							CubeSolver2.notationToMovement("D L D' L' D' F' D F");
						} else if ((cube[2][7] == "R") && (cube[5][7] == "G")) {
							CubeSolver2.notationToMovement("L D' L' D' F' D F");
						} else if ((cube[3][7] == "R") && (cube[5][3] == "G")) {
							CubeSolver2.notationToMovement("D' L D' L' D' F' D F");
						} else if ((cube[4][7] == "R") && (cube[5][1] == "G")) {
							CubeSolver2.notationToMovement("D D L D' L' D' F' D F");
						}
					}
					XX = false;
				}
				if (XX == true) {
					if (((cube[1][3] == "R") && (cube[4][5] == "G")) && ((cube[2][5] == "B") && (cube[3][3] == "O"))) {
						if ((cube[1][5] == "R") && (cube[2][3] == "B")) {
							if ((cube[3][5] == "O") && (cube[4][3] == "G")) {
								break;
							} else {
								CubeSolver2.notationToMovement("L' D L D B D' B'");
							}
						} else {
							CubeSolver2.notationToMovement("R' D R D F D' F'");
						}
					} else {
						if ((cube[1][3] == "R") && (cube[4][5] == "G")) {
							CubeSolver2.notationToMovement("B' D B D R D' R'");
						} else {
							CubeSolver2.notationToMovement("F' D F D L D' L'");
						}
					}
				}
			}

			// Section 4- Yellow cross on the bottom\\
			if ((cube[5][1] != "Y") || (cube[5][3] != "Y") || (cube[5][5] != "Y") || (cube[5][7] != "Y")) {
				if ((cube[5][1] != "Y") && (cube[5][3] != "Y") && (cube[5][5] != "Y") && (cube[5][7] != "Y")) {
					CubeSolver2.notationToMovement("F L D L' D' F'");
				}
				if ((cube[5][1] == "Y") && (cube[5][3] == "Y")) {
					CubeSolver2.notationToMovement("D' F L D L' D' F'");
				} else if ((cube[5][1] == "Y") && (cube[5][5] == "Y")) {
					CubeSolver2.notationToMovement("D D F L D L' D' F'");
				} else if ((cube[5][5] == "Y") && (cube[5][7] == "Y")) {
					CubeSolver2.notationToMovement("D F L D L' D' F'");
				} else if ((cube[5][3] == "Y") && (cube[5][7] == "Y")) {
					CubeSolver2.notationToMovement("F L D L' D' F'");
				}
				if ((cube[5][3] == "Y") && (cube[5][5] == "Y")) {
					CubeSolver2.notationToMovement("D F L D L' D' F'");
				} else if ((cube[5][1] == "Y") && (cube[5][7] == "Y")) {
					CubeSolver2.notationToMovement("F L D L' D' F'");
				}
			}

			// Section 5 - Correcting the side positions of the yellow cross\\
			if ((cube[1][7] != "R") || (cube[2][7] != "B") || (cube[3][7] != "O") || (cube[4][7] != "G")) {
				if (cube[1][7] != "R") {
					if (cube[2][7] == "R") {
						CubeSolver2.notationToMovement("D'");
					} else if (cube[3][7] == "R") {
						CubeSolver2.notationToMovement("D D");
					} else if (cube[4][7] == "R") {
						CubeSolver2.notationToMovement("D");
					}
				}
				if (cube[3][7] == "O") {
					CubeSolver2.notationToMovement("L D L' D L D D L'");
				}
				if (cube[2][7] == "B") {
					CubeSolver2.notationToMovement("D D L D L' D L D D L' D'");
				} else if (cube[2][7] == "G") {
					CubeSolver2.notationToMovement("L D L' D L D D L'");
				} else if (cube[4][7] == "B") {
					CubeSolver2.notationToMovement("D L D L' D L D D L' D");
				} else if (cube[4][7] == "G") {
					CubeSolver2.notationToMovement("D' L D L' D L D D L' D D");
				}
			}

			// Section 6 - Moving the bottom corners into the correct positions\\
			while (true) {
				if ((((cube[1][8] == "Y") && (cube[2][6] == "R") && (cube[5][8] == "B"))
						|| ((cube[1][8] == "B") && (cube[2][6] == "Y") && (cube[5][8] == "R"))
						|| ((cube[1][8] == "R") && (cube[2][6] == "B") && (cube[5][8] == "Y")))
						&& (((cube[2][8] == "Y") && (cube[3][6] == "B") && (cube[5][6] == "O"))
								|| ((cube[2][8] == "O") && (cube[3][6] == "Y") && (cube[5][6] == "B"))
								|| ((cube[2][8] == "B") && (cube[3][6] == "O") && (cube[5][6] == "Y")))
						&& (((cube[3][8] == "Y") && (cube[4][6] == "O") && (cube[5][0] == "G"))
								|| ((cube[3][8] == "G") && (cube[4][6] == "Y") && (cube[5][0] == "O"))
								|| ((cube[3][8] == "O") && (cube[4][6] == "G") && (cube[5][0] == "Y")))
						&& (((cube[4][8] == "Y") && (cube[1][6] == "G") && (cube[5][2] == "R"))
								|| ((cube[4][8] == "R") && (cube[1][6] == "Y") && (cube[5][2] == "G"))
								|| ((cube[4][8] == "G") && (cube[1][6] == "R") && (cube[5][2] == "Y")))) {
					break;
				}
				if (((cube[4][8] == "Y") && (cube[1][6] == "G") && (cube[5][2] == "R"))
						|| ((cube[4][8] == "R") && (cube[1][6] == "Y") && (cube[5][2] == "G"))
						|| ((cube[4][8] == "G") && (cube[1][6] == "R") && (cube[5][2] == "Y"))) {
					CubeSolver2.notationToMovement("D L D' R' D L' D' R");
				} else if (((cube[1][8] == "Y") && (cube[2][6] == "R") && (cube[5][8] == "B"))
						|| ((cube[1][8] == "B") && (cube[2][6] == "Y") && (cube[5][8] == "R"))
						|| ((cube[1][8] == "R") && (cube[2][6] == "B") && (cube[5][8] == "Y"))) {
					CubeSolver2.notationToMovement("D F D' B' D F' D' B");
				} else if (((cube[2][8] == "Y") && (cube[3][6] == "B") && (cube[5][6] == "O"))
						|| ((cube[2][8] == "O") && (cube[3][6] == "Y") && (cube[5][6] == "B"))
						|| ((cube[2][8] == "B") && (cube[3][6] == "O") && (cube[5][6] == "Y"))) {
					CubeSolver2.notationToMovement("D R D' L' D R' D' L");
				} else if (((cube[3][8] == "Y") && (cube[4][6] == "O") && (cube[5][0] == "G"))
						|| ((cube[3][8] == "G") && (cube[4][6] == "Y") && (cube[5][0] == "O"))
						|| ((cube[3][8] == "O") && (cube[4][6] == "G") && (cube[5][0] == "Y"))) {
					CubeSolver2.notationToMovement("D B D' F' D B' D' F");
				} else {
					CubeSolver2.notationToMovement("D L D' R' D L' D' R");
				}
			}

			// Section 7 - Orienting the bottom corners\\
			while (true) {
				if ((cube[4][8] == "G") && (cube[1][6] == "R") && (cube[5][2] == "Y")) {
					CubeSolver2.notationToMovement("D");
				} else if (((cube[4][8] == "R") && (cube[1][6] == "Y") && (cube[5][2] == "G"))
						|| ((cube[4][8] == "Y") && (cube[1][6] == "G") && (cube[5][2] == "R"))) {
					CubeSolver2.notationToMovement("L' U' L U L' U' L U");
				}
				if ((cube[4][8] == "O") && (cube[1][6] == "G") && (cube[5][2] == "Y")) {
					CubeSolver2.notationToMovement("D");
				} else if (((cube[4][8] == "G") && (cube[1][6] == "Y") && (cube[5][2] == "O"))
						|| ((cube[4][8] == "Y") && (cube[1][6] == "O") && (cube[5][2] == "G"))) {
					CubeSolver2.notationToMovement("L' U' L U L' U' L U");
				}
				if ((cube[4][8] == "B") && (cube[1][6] == "O") && (cube[5][2] == "Y")) {
					CubeSolver2.notationToMovement("D");
				} else if (((cube[4][8] == "O") && (cube[1][6] == "Y") && (cube[5][2] == "B"))
						|| ((cube[4][8] == "Y") && (cube[1][6] == "B") && (cube[5][2] == "O"))) {
					CubeSolver2.notationToMovement("L' U' L U L' U' L U");
				}
				if ((cube[4][8] == "R") && (cube[1][6] == "B") && (cube[5][2] == "Y")) {
					break;
				} else if (((cube[4][8] == "B") && (cube[1][6] == "Y") && (cube[5][2] == "R"))
						|| ((cube[4][8] == "Y") && (cube[1][6] == "R") && (cube[5][2] == "B"))) {
					CubeSolver2.notationToMovement("L' U' L U L' U' L U");
				}

				// Section final - Completing the solve\\
			}
			if (cube[1][7] != "R") {
				if (cube[2][7] == "R") {
					CubeSolver2.notationToMovement("D'");
				} else if (cube[3][7] == "R") {
					CubeSolver2.notationToMovement("D D");
				} else if (cube[4][7] == "R") {
					CubeSolver2.notationToMovement("D");
				}
			}
			CubeSolver2.mTwist.setSpeed(600);
			CubeSolver2.mFlip.setSpeed(250);
			CubeSolver2.robotMovement(CubeSolver2.OverallString);
			
			run = !run;
		}
	}

	@Override
	public void suppress() {

	}

}
