/***
 * 
 * @author Moritz
 *
 */
public class ByteOrientedOperations { // operations that start with 00
	int[] bitmask = { 0x7F, 0x40 };
	int f, d, w, rotated;

	// add w and f
	public void ADDWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		w = getW();
		// check digit carry
		int dF = (0x08 & f) >>> 3;
		int dW = (0x08 & w) >>> 3;
		// check digit carry
		if ((dF & dW) != 0) {
			setDC(1);
		} else {
			setDC(0);
		}
		// adding f and w
		f += w;
		// check carry bit
		if ((f & 0xF00) != 0) {
			setC(1);
			f = f & 0xFF; // so f is not > 0xFF
		}
		// set zero flag when f == 0
		checkZ(f);
		// store f in register w (when d==0) or in f (when d==1)
		setRegister(d, f);
	}

	// and w with f
	public void ANDWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		w = getW();
		// and'ing w and f
		f = f & w;
		// set zero flag when f == 0
		checkZ(f);
		// store f in register w (when d==0) or in f (when d==1)
		setRegister(d, f);
	}

	// clear f
	public void CLRF(int hexInt) {
		setZ(1);
		setF(0);
	}

	// clear w
	public void CLRW() {
		setZ(1);
		setW(0);
	}

	// complement f
	public void COMF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		// complement f
		f = ~f;
		// set zero flag when f == 0
		checkZ(f);
		// store f in register w (when d==0) or in f (when d==1)
		setRegister(d, f);
	}

	// decrement f
	public void DECF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		// decrement f
		f -= f;
		// set zero flag when f == 0
		checkZ(f);
		setRegister(d, f);
	}

	// decrement f, skip if 0
	public void DECFSZ(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		// decrement f
		f -= 1;
		// set zero flag when f == 0
		checkZ(f);
		setRegister(d, f);
		if (f == 0) {
			NOP();
		} else {
			// TODO execute the next instruction
		}
	}

	// increment f
	public void INCF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		// increment f
		f += 1;
		// set zero flag when f == 0
		checkZ(f);
		setRegister(d, f);
	}

	// increment f, skip if 0
	public void INCFSZ(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		// increment f
		f += 1;
		setRegister(d, f);
		if (f == 0) {
			NOP();
		} else {
			// TODO execute the next instruction
		}
	}

	// inclusive or w with f
	public void IORWF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		w = getW();
		// or'ing w with f
		w = w | f;
		// set zero flag when f == 0
		checkZ(f);
		setRegister(d, f);
	}

	// move f
	public void MOVF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		// set zero flag when f == 0
		checkZ(f);
		setF(f);
		// d = 1 is useful to test a file register since status flag z is affected
	}

	// move w to f
	public void MOVWF(int hexInt) {
		w = getW();
		setF(w);
	}

	// no operation
	public void NOP() {
		// nothing
	}

	// TODO rotate left f through carry
	public void RLF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		rotated = f << 1;
		if (getC() == 1) {
			// TODO
		} else {
			// TODO
		}
		setRegister(d, f);
	}

	// TODO rotate right f through carry
	public void RRF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		rotated = f >>> 1;
		if (getC() == 1) {
			// TODO
		} else {
			// TODO
		}
		setRegister(d, f);
	}

	// subtract w from literal
	public void SUBWF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		w = getW();
		// check digit carry
		int dF = (0x08 & f) >>> 3;
		int dW = (0x08 & w) >>> 3;
		if ((dF & dW) != 0) {
			setDC(1);
		} else {
			setDC(0);
		}
		// subtracting f with w
		f -= w;
		// check if k is negative
		if ((f & 0x40) != 0) {
			setC(0); // when f is negative
		} else {
			setC(1); // when f is positive
		}
		// set zero flag when w == 0
		checkZ(w);
		setRegister(d, w);
	}

	// swap nibbles in f
	public void SWAPF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		int bit1 = (f & 0x0F) << 3; // bit 3 ... 0 bits in f
		int bit2 = (f & 0x70) >>> 4; // bit 6 ... 4 in f
		f = bit1 | bit2;
		setRegister(d, f);
	}

	// exclusive or w with f
	public void XORWF(int hexInt) {
		f = hexInt & bitmask[0];
		d = hexInt & bitmask[1];
		w = getW();
		// xor'ing f with w
		f = f ^ w;
		// set zero flag when f == 0
		checkZ(f);
		setRegister(d, f);
	}

	// clear watchdog timer
	public void CLRWDT() {
		// TODO ????????????????
	}

	public void RETFIE() {
		// TODO
	}

	public void RETURN() {
		// TODO
	}

	public void SLEEP() {
		// TODO
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

	private void setF(int f) {
		// TODO Auto-generated method stub

	}
}
