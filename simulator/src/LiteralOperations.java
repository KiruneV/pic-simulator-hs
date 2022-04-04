/***
 * 
 * @author Moritz
 *
 */
public class LiteralOperations { // operations that start with 11
	int bitmask = 0xFF; // 00 0000 1111 1111
	int k, w;

	// add literal and w
	public void ADDLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		w = getW();
		// check digit carry
		int dK = (0x08 & k) >>> 3;
		int dW = (0x08 & w) >>> 3;
		// check digit carry
		if ((dK & dW) != 0) {
			setDC(1);
		} else {
			setDC(0);
		}
		// adding literal and w
		k += w;
		// check carry bit
		if ((k & 0xF00) != 0) {
			setC(1);
			k = k & 0xFF; // so k is not > 0xFF
		}
		// set zero flag when k == 0
		checkZ(k);
		setW(k);
	}

	// and literal with w
	public void ANDLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		w = getW();
		// and'ing k with w
		k = k & w;
		// set zero flag when k == 0
		checkZ(k);
		setW(k);
	}

	// inclusive or literal and w
	public void IORLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		w = getW();
		// or'ing k with w
		k = k | w;
		// set zero flag when k == 0
		checkZ(k);
		setW(k);
	}

	// move literal to w
	public void MOVLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// load k into w register
		setW(k);
	}

	// return with literal in w
	public void RETLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// load k into w register
		setW(k);
		RETURN(); // jump out of subprogram
	}

	// subtract w from literal
	public void SUBLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		w = getW();
		// check digit carry
		int dK = (0x08 & k) >>> 3;
		int dW = (0x08 & w) >>> 3;
		if ((dK & dW) != 0) {
			setDC(1);
		} else {
			setDC(0);
		}
		// subtracting k with w
		k -= w;
		// check if k is negative
		if ((k & 0x80) != 0) {
			setC(0); // when k is negative
		} else {
			setC(1); // when k is positive
		}
		// set zero flag when k == 0
		checkZ(k);
		setW(k);
	}

	// exclusive or literal with w
	public void XORLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		w = getW();
		// xor'ing k with w
		k = k ^ w;
		// set zero flag when k == 0
		checkZ(k);
		setW(k);
	}

	// deleting
	public void RETURN() {
		// TODO Auto-generated method stub

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
}