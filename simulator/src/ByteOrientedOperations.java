/***
 * 
 * @author Moritz
 *
 */
public class ByteOrientedOperations { // operations that start with 00
	static int[] bitmask = { 0x7F, 0x40 };
	static int f, d, rotated;

	// add w and f
	public static void ADDWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int w = getW();
		// check digit carry
		int dF = (0x08 & fContent) >>> 3;
		int dW = (0x08 & w) >>> 3;
		// check digit carry
		if ((dF & dW) != 0) {
			setDC(1);
		} else {
			setDC(0);
		}
		// adding fContent and w
		fContent += w;
		// check carry bit
		if ((fContent & 0xF00) != 0) {
			setC(1);
			fContent = fContent & 0xFF; // so fContent is not > 0xFF
		}
		// set zero flag when f == 0
		checkZ(fContent);
		// store fContent in register w (when d==0) or in fContent (when d==1)
		setRegister(d, fContent, f);
	}

	// and w with f
	public static void ANDWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int w = getW();
		// and'ing w and f
		fContent = fContent & w;
		// set zero flag when f == 0
		checkZ(fContent);
		// store fContent in register w (when d==0) or in fContent (when d==1)
		setRegister(d, fContent, f);
	}

	// clear f
	public static void CLRF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		setZ(1);
		setRegisterContent(0, f);
	}

	// clear w
	public static void CLRW() {
		setZ(1);
		setW(0);
	}

	// complement f
	public static void COMF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		// complement f
		fContent = ~fContent;
		// set zero flag when f == 0
		checkZ(fContent);
		// store f in register w (when d==0) or in f (when d==1)
		setRegister(d, fContent, f);
	}

	// decrement f
	public static void DECF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		// decrement f
		fContent -= fContent;
		// set zero flag when f == 0
		checkZ(fContent);
		setRegister(d, fContent, f);
	}

	// decrement f, skip if 0
	public static void DECFSZ(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		// decrement f
		fContent--;
		setRegister(d, fContent, f);
		if (f == 0) {
			NOP();
		} else {
			// TODO execute the next instruction
		}
	}

	// increment f
	public static void INCF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		// increment f
		fContent++;
		// set zero flag when f == 0
		checkZ(f);
		setRegister(d, fContent, f);
	}

	// increment f, skip if 0
	public static void INCFSZ(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		// increment f
		fContent += 1;
		setRegister(d, fContent, f);
		if (f == 0) {
			NOP();
		} else {
			// TODO execute the next instruction
		}
	}

	// inclusive or w with f
	public static void IORWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int w = getW();
		// or'ing w with f
		w = w | fContent;
		// set zero flag when f == 0
		checkZ(fContent);
		setRegister(d, fContent, f);
	}

	// move f
	public static void MOVF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		// set zero flag when f == 0
		checkZ(fContent);
		setRegisterContent(fContent, f);
		// d = 1 is useful to test a file register since status flag z is affected
	}

	// move w to f
	public static void MOVWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		int w = getW();
		setRegisterContent(w, f);
	}

	// no operation
	public static void NOP() {
		// nothing
	}

	// rotate left f through carry
	public static void RLF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int temp = fContent & 0b100000000;
		rotated = fContent << 1;
		if (getC() == 1) {
			rotated++;
			setC(0);
		}
		if (temp != 0) {
			setC(1);
			rotated = rotated & 0b11111111;
		}
		fContent = rotated;
		setRegister(d, fContent, f);
	}

	// rotate right f through carry
	public static void RRF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int temp = fContent & 0b1;
		rotated = f >>> 1;
		if (getC() == 1) {
			rotated = rotated | 0b10000000;
			setC(0);
		}
		if (temp != 0) {
			setC(1);
		}
		fContent = rotated;
		setRegister(d, fContent, f);
	}

	// subtract w from literal
	public static void SUBWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int w = getW();
		// check digit carry
		int dF = (0x08 & fContent) >>> 3;
		int dW = (0x08 & w) >>> 3;
		if ((dF & dW) != 0) {
			setDC(1);
		} else {
			setDC(0);
		}
		// subtracting fContent with w
		fContent -= w;
		// check if k is negative
		if ((fContent & 0x40) != 0) {
			setC(0); // when fContent is negative
		} else {
			setC(1); // when fContent is positive
		}
		// set zero flag when fContent == 0
		checkZ(fContent);
		setRegister(d, w, f);
	}

	// swap nibbles in f
	public static void SWAPF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int bit1 = (f & 0x0F) << 3; // bit 3 ... 0 bits in fContent
		int bit2 = (f & 0x70) >>> 4; // bit 6 ... 4 in fContent
		fContent = bit1 | bit2;
		setRegister(d, fContent, f);
	}

	// exclusive or w with f
	public static void XORWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		int fContent = getRegisterContent(f);
		int wContent = getW();
		// xor'ing fContent with w
		fContent = fContent ^ wContent;
		// set zero flag when fContent == 0
		checkZ(fContent);
		setRegister(d, fContent, f);
	}

	// TODO clear watchdog timer
	public static void CLRWDT() {
		// WDT (watchdog timer) = 00h
		// WDT prescaler = 0
		// !TO = 1
		// !PD = 1
	}

	// TODO return from interrupt
	public static void RETFIE() {
		// return from interrupt
		// PC (programm counter) = TOS (top of the stack)
		// GIE (global interrupt enable bit) = 1

	}

	// return from subroutine
	public static void RETURN() {
		// PC (programm counter) = TOS (top of the stack)
	}

	public static void SLEEP() {
		// WDT (watchdog timer) = 00h
		// WDT prescaler = 0
		// !TO = 1
		// !PD = 0
	}

	public static void setRegister(int d, int content, int f) {
		if (d == 0) {
			setW(content);
		} else {
			// store content in register f
			setRegisterContent(content, f);
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

	public static int getRegisterContent(int f) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void setRegisterContent(int fContent, int f) {
		// TODO Auto-generated method stub

	}
}
