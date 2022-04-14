/***
 * 
 * @author Moritz
 *
 */
public class ControlOperations { // operations that start with 10
	static int bitmask = 0x7FF; // 00 0111 1111 1111
	static int k;

	// call subroutine
	public static void CALL(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO lege address k in stack
		// setAddress(k); 
	}

	// unconditional branch
	public static void GOTO(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO jump to address k
		// setAddress(k);
	}

	public static void setRegister(int d, int w) {
		if (d == 0) {
			setW(w);
		} else {
			// store f back in register f
			setF(w);
		}

	}

	// set zero flag
	public static void setZ(int i) {
		// TODO Auto-generated method stub

	}

	// check zero flag
	public static void checkZ(int i) {
		if (i == 0) {
			setZ(1);
		}
		setZ(0);
	}

	// set carry bit
	public static void setC(int i) {
		// TODO Auto-generated method stub

	}

	// get carry bit
	public static int getC() {
		// TODO Auto-generated method stub
		return 0;
	}

	// set digit carry
	public static void setDC(int i) {
		// TODO Auto-generated method stub

	}

	// set w register
	public static void setW(int w) {
		// TODO Auto-generated method stub

	}

	public static int getW() {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void setF(int f) {
		// TODO Auto-generated method stub

	}
}
