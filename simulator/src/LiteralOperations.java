/***
 * 
 * @author Moritz
 *
 */
public class LiteralOperations { // operations that start with 11
	static int bitmask = 0xFF; // 00 0000 1111 1111
	static int k;

	// add literal and w 
	public static void ADDLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		int wContent = RAM.getW();
		// check digit carry
		int dK = (0x08 & k) >>> 3;
		int dW = (0x08 & wContent) >>> 3;
		if ((dK & dW) != 0) {
			RAM.setDC(1);
		} else {
			RAM.setDC(0);
		}
		// adding literal and wContent
		k += wContent;
		// check carry bit
		if ((k & 0xF00) != 0) {
			RAM.setC(1);
			k = k & 0xFF; // so k is not > 0xFF
		}
		// set zero flag when k == 0
		RAM.checkZ(k);
		RAM.setW(k);
	}

	// and literal with w
	public static void ANDLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		int wContent = RAM.getW();
		// and'ing k with w
		k = k & wContent;
		// set zero flag when k == 0
		RAM.checkZ(k);
		RAM.setW(k);
	}

	// inclusive or literal and w
	public static void IORLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		int wContent = RAM.getW();
		// or'ing k with wContent
		k = k | wContent;
		// set zero flag when k == 0
		RAM.checkZ(k);
		RAM.setW(k);
	}

	// move literal to w
	public static void MOVLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// load k into w register
		RAM.setW(k);
	}

	// return with literal in w
	public static void RETLW(int hexInt) {
		MOVLW(hexInt); // RETLW = MOVLW + RETURN
		RETURN(); // jump out of subprogram
	}

	// subtract w from literal
	public static void SUBLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		int wContent = RAM.getW();
		// check digit carry
		int dK = (0x08 & k) >>> 3;
		int dW = (0x08 & wContent) >>> 3;
		if ((dK & dW) != 0) {
			RAM.setDC(1);
		} else {
			RAM.setDC(0);
		}
		// subtracting k with w
		k -= wContent;
		// check if k is negative
		if ((k & 0x80) != 0) {
			RAM.setC(0); // when k is negative
		} else {
			RAM.setC(1); // when k is positive
		}
		// set zero flag when k == 0
		RAM.checkZ(k);
		RAM.setW(k);
	}

	// exclusive or literal with w
	public static void XORLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		int wContent = RAM.getW();
		// xor'ing k with wwContent
		k = k ^ wContent;
		// set zero flag when k == 0
		RAM.checkZ(k);
		RAM.setW(k);
	}

	// deleting
	public static void RETURN() {
	}

}