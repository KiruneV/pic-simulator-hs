/***
 * 
 * @author Moritz
 *
 */
public class ByteOrientedOperations { // operations that start with 00
	static int[] bitmask = { 0x7F, 0x80 };
	static int f, d, result, wContent;

	// add w and f
	// status affected: c, dc, z
	public static void ADDWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		wContent = RAM.getW();
		// check digit carry
		int dF = (0x0F & result);
		int dW = (0x0F & wContent);
		if ((dF + dW) > 15) {
			RAM.setDC(1);
		} else {
			RAM.setDC(0);
		}
		// int before = result;
		// adding fContent and w
		result += wContent;
		// check carry bit
		if (result > 0xFF) {
			RAM.setC(1);
			result = result & 0xFF; // so k is not > 0xFF
		} else {
			RAM.setC(0);
		}

		// check digit carry
//		if ((result & 0xF0) > (before & 0xF0)) {
//			RAM.setDC(1);
//		} else {
//			RAM.setDC(0);
//		}

		// set zero flag when f == 0
		RAM.checkZ(result);
		// store fContent in register w (when d==0) or in fContent (when d==1)
		RAM.setRegister(d, result, f);
	}

	// and w with f
	// status affected: z
	public static void ANDWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		wContent = RAM.getW();
		// and'ing w and f
		result = result & wContent;
		// set zero flag when f == 0
		RAM.checkZ(result);
		// store fContent in register w (when d==0) or in fContent (when d==1)
		RAM.setRegister(d, result, f);
	}

	// clear f
	// status affected: z
	public static void CLRF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		RAM.setZ(1);
		RAM.setRegisterContent(0, f);
	}

	// clear w
	// status affected: z
	public static void CLRW() {
		RAM.setZ(1);
		RAM.setW(0);
	}

	// complement f
	// status affected: z
	public static void COMF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		// complement f
		result = ~result & 0xFF;
		// set zero flag when f == 0
		RAM.checkZ(result);
		// store f in register w (when d==0) or in f (when d==1)
		RAM.setRegister(d, result, f);
	}

	// decrement f
	// status affected: z
	public static void DECF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		if (result == 0) {
			result = 255;
		} else {
			// decrement f
			result--;
			// set zero flag when f == 0
		}
		RAM.checkZ(result);
		RAM.setRegister(d, result, f);
	}

	// decrement f, skip if 0
	// status affected: none
	public static void DECFSZ(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		// decrement f
		if (result == 0) {
			result = 255;
		} else {
			// decrement f
			result--;
			// set zero flag when result == 0
		}
		RAM.setRegister(d, result, f);
		if (result == 0) {
			RAM.setPCL(RAM.getPCL() + 1);
		} // else {
//			 execute the next instruction
//		}
	}

	// increment f
	// status affected: z
	public static void INCF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		if (result == 255) {
			result = 0;
		} else {
			result++;
		}
		// increment f
//		result += 1;
//		if(result > 0xFF) {
//			result = 0;
//		}
		// set zero flag when result == 0
		RAM.checkZ(result);
		RAM.setRegister(d, result, f);
	}

	// increment f, skip if 0
	// status affected: none
	public static void INCFSZ(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		// increment f
		if (result == 255) {
			result = 0;
		} else {
			result++;
		}
//		result += 1;
//		if(result > 0xFF) {
//			result = 0;
//		}
		RAM.setRegister(d, result, f);
		if (result == 0) {
			RAM.setPCL(RAM.getPCL() + 1);
		} // else {
			// execute the next instruction
			// }
	}

	// inclusive or w with f
	// status affected: z
	public static void IORWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		wContent = RAM.getW();
		// or'ing w with f
		result = wContent | result;
		// set zero flag when f == 0
		RAM.checkZ(result);
		RAM.setRegister(d, result, f);
	}

	// move f
	// status affected: z
	public static void MOVF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		// set zero flag when f == 0
		RAM.checkZ(result);
		RAM.setRegister(d, result, f);
		// d = 1 is useful to test a file register since status flag z is affected
	}

	// move w to f
	// status affected: none
	public static void MOVWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		wContent = RAM.getW();
		RAM.setRegisterContent(wContent, f);
	}

	// no operation
	// status affected: none
	public static void NOP() {
		// skippen!
	}

	// rotate left f through carry
	// status affected: c
	public static void RLF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		result = result << 1;
		if (RAM.getC() == 1) {
			result = result | 0x01;
			RAM.setC(0);
		}
		if (result > 0xFF) {
			RAM.setC(1);
			result = result & 0xFF;
		}
		RAM.setRegister(d, result, f);
	}

	// TODO
	// rotate right f through carry
	// status affected: c
	public static void RRF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		// int temp = result & 0b1;
		int c;
		if ((result & 0x01) != 0) {
			c = 1;
		} else {
			c = 0; 
		}
		result = result >>> 1;
		if (RAM.getC() == 1) {
			result = result | 0x80;
		}
		RAM.setC(c);
		RAM.setRegister(d, result, f);
	}

	// subtract w from literal
	// status affected: c, dc, z
	public static void SUBWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		wContent = RAM.getW();
		// check digit carry
		int dF = (0x0F & result);
		int dW = (0x0F & ~wContent);
		if ((dF + (dW + 1)) > 15) {
			RAM.setDC(1);
		} else {
			RAM.setDC(0);
		}
//		if (0xF < (fContent & 0xF) + ((~w + 1) & 0x0F)) {
//			RAM.setDC(1);
//		} else {
//			RAM.setDC(0);
//		}
		// subtracting fContent with w
		result -= wContent;
		// check if fContent is negative
		if (result < 0) {
			RAM.setC(0); // when fContent is negative
			result = result & 0xFF;
		} else {
			RAM.setC(1); // when fContent is positive
		}
//		if ((result & 0x80) != 0) {
//			RAM.setC(0); // when result is negative
//		} else {
//			RAM.setC(1); // when result is positive
//		}
		// check digit carry
//		if ((fContent & 0x10) != 0) {
//			RAM.setDC(1);
//		} else {
//			RAM.setDC(0);
//		}
		// set zero flag when fContent == 0
		RAM.checkZ(result);
		RAM.setRegister(d, result, f);
	}

	// swap nibbles in f
	// status affected: none
	public static void SWAPF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		int bit1 = (result & 0x0F) << 4; // bit 3 ... 0 bits in fContent
		int bit2 = (result & 0xF0) >> 4; // bit 6 ... 4 in fContent
		result = bit1 | bit2;
		RAM.setRegister(d, result, f);
	}

	// exclusive or w with f
	// status affected: z
	public static void XORWF(int hexInt) {
		f = hexInt & bitmask[0]; // select f out of the hexInt
		d = hexInt & bitmask[1]; // select d out of the hexInt
		result = RAM.getRegisterContent(f);
		wContent = RAM.getW();
		// xor'ing fContent with w
		result = wContent ^ result;
		// set zero flag when fContent == 0
		RAM.checkZ(result);
		RAM.setRegister(d, result, f);
	}

	// TODO clear watchdog timer
	// status affected: !to, !pd
	public static void CLRWDT() {
		// WDT (watchdog timer) = 00h
		// WDT prescaler = 0
		// !TO = 1
		RAM.setTO(1);
		// !PD = 1
		RAM.setPD(1);
	}

	// TODO return from interrupt
	// status affected: none
	public static void RETFIE() {
		// return from interrupt
		// PC (programm counter) = TOS (top of the stack)
		if (!globalthings.stack8.isEmpty()) {
			RAM.setPCL(globalthings.stack8.pop());
			globalthings.jumpPerformed = true;
		} else {
			if (globalthings.debugMode == true) {
				System.out.println("STACK is empty!");
			}
		}
		// GIE (global interrupt enable bit) = 1
		RAM.setGIE(1);

	}

	// return from subroutine
	// status affected: none
	public static void RETURN() {
		// PC (programm counter) = TOS (top of the stack)
		if (!globalthings.stack8.isEmpty()) {
			RAM.setPCL(globalthings.stack8.pop());
			globalthings.jumpPerformed = true;
		} else {
			if (globalthings.debugMode == true) {
				System.out.println("STACK is empty!");
			}
		}
	}

	// TODO sleep
	// status affected: !to, !pd
	public static void SLEEP() {
		CLRWDT();
		// WDT (watchdog timer) = 00h
		// WDT prescaler = 0
		// already done in CLRWDT !TO = 1
		// !PD = 0
		RAM.setPD(0);
		// refresh gui manually so that one could see the changes
		if (globalthings.GUIon) {
			ApplicationGui.refresh();
		}
	}
}
