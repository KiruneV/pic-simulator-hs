/***
 * 
 * @author Moritz
 *
 */
public class BitOrientedOperations { // operations that start with 01
	static int[] bitmask = { 0x7F, 0x380 };
	static int f;
	static int b;

	// bit clear f
	public static void BCF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		b = (hexInt & bitmask[1]) >>> 7; // select b out of the hexInt and rotate 7 times to the right
		int mask = 0b01 << b;
		// clear bit b in f
		f = f & ~mask;
		setF(f);
	}

	// bit set f
	public static void BSF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		b = (hexInt & bitmask[1]) >>> 7; // select b out of the hexInt and rotate 7 times to the right
		int mask = 0b01 << b;
		// set bit b in f
		f = f | mask;
		setF(f);
	}

	// bit test, skip if clear
	public static void BTFSC(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		b = (hexInt & bitmask[1]) >>> 7; // select b out of the hexInt and rotate 7 times to the right
		int mask = 0b01 << b;
		// check if bit b in f is null
		if ((f & mask) == 0) {
			NOP();
		} else {
			// TODO then the next instruction is executed
		}
	}

	// bit test f, skip if set
	public static void BTFSS(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		b = (hexInt & bitmask[1]) >>> 7; // select b out of the hexInt and rotate 7 times to the right
		int mask = 0b01 << b;
		// check if bit b in f is not null
		if ((f & mask) != 0) {
			NOP();
		} else {
			// TODO then the next instruction is executed
		}
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

	public static void setF(int f) {
		// TODO Auto-generated method stub

	}

	public static void NOP() {
		// TODO Auto-generated method stub

	}
}
