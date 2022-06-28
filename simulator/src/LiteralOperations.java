/***
 * 
 * @author Moritz
 *
 */
public class LiteralOperations { // operations that start with 11
	static int bitmask = 0xFF; // 00 0000 1111 1111
	static int k, wContent, result;

	// add literal and w
	public static void ADDLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		wContent = RAM.getW();
		// check digit carry
		int dK = (0x0F & k);
		int dW = (0x0F & wContent);
		if ((dK + dW) > 15) {
			RAM.setDC(1);
		} else {
			RAM.setDC(0);
		}
		// adding literal and wContent
		result = k + wContent;
		// check carry bit
		if ((result & 0xF00) != 0) {
			RAM.setC(1);
			result = result & 0xFF; // so k is not > 0xFF
		} else {
			RAM.setC(0);
		}
		// set zero flag when k == 0
		RAM.checkZ(result);
		RAM.setW(result);
	}

	// and literal with w
	public static void ANDLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		wContent = RAM.getW();
		// and'ing k with w
		result = k & wContent;
		// set zero flag when k == 0
		RAM.checkZ(result);
		RAM.setW(result);
	}

	// inclusive or literal and w
	public static void IORLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		wContent = RAM.getW();
		// or'ing k with wContent
		result = k | wContent;
		// set zero flag when k == 0
		RAM.checkZ(result);
		RAM.setW(result);
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
		ByteOrientedOperations.RETURN(); // jump out of subprogram
	}

	// subtract w from literal
	public static void SUBLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		wContent = RAM.getW();
		// check digit carry
		int dF = (0x0F & result);
		int dW = (0x0F & ~wContent);
		if ((dF + (dW + 1)) > 15) {
			RAM.setDC(1);
		} else {
			RAM.setDC(0);
		}
		// subtracting k with w
		result = k - wContent;
		// check if k is negative
		if ((result & 0x80) != 0) {
			RAM.setC(0); // when k is negative
		} else {
			RAM.setC(1); // when k is positive
		}
		// set zero flag when k == 0
		RAM.checkZ(result);
		RAM.setW(result);
	}

	// exclusive or literal with w
	public static void XORLW(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		wContent = RAM.getW();
		// xor'ing k with wwContent
		result = k ^ wContent;
		// set zero flag when k == 0
		RAM.checkZ(result);
		RAM.setW(result);
	}

	// deleting
//	public static void RETURN() {
//		// ruft goto auf die oberste adresse vom stack auf (pop)
//	}

}