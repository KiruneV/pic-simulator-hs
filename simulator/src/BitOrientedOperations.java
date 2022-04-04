/***
 * 
 * @author Moritz
 *
 */
public class BitOrientedOperations { // operations that start with 01
	int[] bitmask = { 0x7F, 0x380 };
	int f, b;

	// bit clear f
	public void BCF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		b = (hexInt & bitmask[1]) >>> 7; // select b out of the hexInt and rotate 7 times to the right
		int mask = 0b01 << b;
		// clear bit b in f
		f = f & ~mask;
		setF(f);
	}

	// bit set f
	public void BSF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		b = (hexInt & bitmask[1]) >>> 7; // select b out of the hexInt and rotate 7 times to the right
		int mask = 0b01 << b;
		// set bit b in f
		f = f | mask;
		setF(f);
	}

	// bit test, skip if clear
	public void BTFSC(int hexInt) {
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
	public void BTFSS(int hexInt) {
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

	public void setRegister(int d, int w) {
		if (d == 0) {
			setW(w);
		} else {
			// store f back in register f
			setF(w);
		}

	}

	// set zero flag
	public void setZ(int i) {
		// TODO Auto-generated method stub

	}

	// check zero flag
	public void checkZ(int i) {
		if (i == 0) {
			setZ(1);
		}
		setZ(0);
	}

	// set carry bit
	public void setC(int i) {
		// TODO Auto-generated method stub

	}

	// get carry bit
	public int getC() {
		// TODO Auto-generated method stub
		return 0;
	}

	// set digit carry
	public void setDC(int i) {
		// TODO Auto-generated method stub

	}

	// set w register
	public void setW(int w) {
		// TODO Auto-generated method stub

	}

	public int getW() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setF(int f) {
		// TODO Auto-generated method stub

	}

	public void NOP() {
		// TODO Auto-generated method stub

	}
}
